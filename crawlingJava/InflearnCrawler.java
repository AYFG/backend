import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InflearnCrawler {
    public static void main(String[] args) {
        String roadmapUrl = "https://www.inflearn.com/roadmaps/2238";
        try {
            System.out.println("Fetching roadmap: " + roadmapUrl);
            Document doc = Jsoup.connect(roadmapUrl).get();
            Element nextDataScript = doc.select("script#__NEXT_DATA__").first();
            if (nextDataScript == null) {
                System.out.println("Could not find __NEXT_DATA__ in roadmap.");
                return;
            }

            JSONObject nextData = new JSONObject(nextDataScript.html());
            JSONArray queries = nextData.getJSONObject("props")
                                        .getJSONObject("pageProps")
                                        .getJSONObject("dehydratedState")
                                        .getJSONArray("queries");

            JSONArray courses = null;
            for (int i = 0; i < queries.length(); i++) {
                JSONObject query = queries.getJSONObject(i);
                if ("roadmapDetail".equals(query.getJSONArray("queryKey").getString(0))) {
                    courses = query.getJSONObject("state")
                                   .getJSONObject("data")
                                   .getJSONObject("roadmapCourses")
                                   .getJSONArray("courses");
                    break;
                }
            }

            if (courses == null) {
                System.out.println("Could not find course list in roadmap JSON.");
                return;
            }

            System.out.println("Found " + courses.length() + " courses.");

            for (int i = 0; i < courses.length(); i++) {
                JSONObject courseObj = courses.getJSONObject(i);
                String slug = courseObj.getString("slug");
                String title = courseObj.getString("title");
                
                // Keep the title clean for file systems
                String safeTitle = title.replaceAll("[\\\\/:*?\"<>|]", "_");
                String fileName = (i + 1) + "_" + safeTitle + ".md";
                
                System.out.println("Crawling course " + (i+1) + "/" + courses.length() + ": " + title);
                crawlCourseCurriculum(slug, title, fileName);
                
                // Sleep to avoid rate limiting
                Thread.sleep(1000);
            }
            System.out.println("Crawling complete.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void crawlCourseCurriculum(String slug, String courseTitle, String fileName) {
        try {
            String courseUrl = "https://www.inflearn.com/course/" + slug;
            Document doc = Jsoup.connect(courseUrl).get();
            Element nextDataScript = doc.select("script#__NEXT_DATA__").first();
            if (nextDataScript == null) {
                 System.out.println("  Could not find __NEXT_DATA__ for " + courseTitle);
                 return;
            }
            
            JSONObject nextData = new JSONObject(nextDataScript.html());
            JSONArray queries = nextData.getJSONObject("props")
                                        .getJSONObject("pageProps")
                                        .getJSONObject("dehydratedState")
                                        .getJSONArray("queries");
                                        
            JSONArray curriculum = null;
            for (int i = 0; i < queries.length(); i++) {
                JSONObject query = queries.getJSONObject(i);
                String queryKey = query.getJSONArray("queryKey").getString(0);
                if (queryKey != null && queryKey.contains("curriculum")) {
                    JSONObject stateData = query.getJSONObject("state").getJSONObject("data");
                    if (stateData.has("data")) {
                        JSONObject internalData = stateData.getJSONObject("data");
                        if (internalData.has("curriculum")) {
                            curriculum = internalData.getJSONArray("curriculum");
                            break;
                        } else if (internalData.has("sections")) { // Fallback, APIs might vary slightly
                            curriculum = internalData.getJSONArray("sections");
                            break;
                        }
                    } else if (stateData.has("curriculum")) {
                         curriculum = stateData.getJSONArray("curriculum");
                         break;
                    }
                }
            }

            if (curriculum == null) {
                System.out.println("  Could not find curriculum data for " + courseTitle);
                return;
            }

            try (java.io.OutputStreamWriter fw = new java.io.OutputStreamWriter(new java.io.FileOutputStream(fileName), java.nio.charset.StandardCharsets.UTF_8)) {
                fw.write("# " + courseTitle + " - 커리큘럼\n\n");
                
                for (int i = 0; i < curriculum.length(); i++) {
                    JSONObject section = curriculum.getJSONObject(i);
                    String sectionTitle = section.getString("title");
                    fw.write("## " + sectionTitle + "\n\n");
                    
                    if (section.has("units")) {
                        JSONArray units = section.getJSONArray("units");
                        for (int j = 0; j < units.length(); j++) {
                            JSONObject unit = units.getJSONObject(j);
                            String unitTitle = unit.getString("title");
                            int runtimeIndex = unit.has("runtime") ? unit.getInt("runtime") : 0;
                            String runtimeStr = runtimeIndex > 0 ? (" (" + (runtimeIndex / 60) + "분 " + (runtimeIndex % 60) + "초)") : "";
                            fw.write("- [" + unit.getString("type") + "] " + unitTitle + runtimeStr + "\n");
                        }
                    } else if (section.has("items")) {
                        JSONArray units = section.getJSONArray("items");
                        for (int j = 0; j < units.length(); j++) {
                            JSONObject unit = units.getJSONObject(j);
                            String unitTitle = unit.getString("title");
                            fw.write("- " + unitTitle + "\n");
                        }
                    }
                    fw.write("\n");
                }
                System.out.println("  Saved " + fileName);
            }
            
        } catch (Exception e) {
            System.out.println("  Error crawling course " + courseTitle + ": " + e.getMessage());
        }
    }
}
