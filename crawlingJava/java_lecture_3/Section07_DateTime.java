package java_lecture_3;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * ## 섹션 7. 날짜와 시간
 */
public class Section07_DateTime {
    public static void main(String[] args) {
        // 1. [강의: LocalDateTime]
        /*
         * 날짜와 시간을 함께 표현하며, 타임존은 포함하지 않습니다.
         */
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2024, 1, 1, 0, 0);
        System.out.println("현재 시간: " + now);

        // 2. [강의: ZonedDateTime]
        /*
         * 특정 타임존(ZoneId)이 포함된 시간입니다.
         */
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("서울 시간: " + zdt);

        // 3. [강의: 기간과 시간 차이 (Period, Duration)]
        /*
         * Period: 날짜의 차이 (년, 월, 일)
         * Duration: 시간의 차이 (시, 분, 초, 나노초)
         */
        Period period = Period.between(LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1));
        System.out.println("기간 차이: " + period.getYears() + "년");

        // 4. [강의: 포맷팅]
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = now.format(formatter);
        System.out.println("포맷팅된 시간: " + formatted);
    }
}
