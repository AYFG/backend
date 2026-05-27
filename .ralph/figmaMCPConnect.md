# Task

Check whether the third-party `figma-free-mcp` server can realistically be used in this environment with pi.

## Goals
- Determine whether `figma-free-mcp` is official or third-party and how it works
- Verify whether this machine/environment currently meets the runtime requirements

## Checklist
- [x] Identify the linked server/repository and inspect its setup model
- [x] Check core local prerequisites already available in this environment
- [x] Verify whether Figma Desktop is installed and usable here
- [x] Verify whether pi can be configured to connect to this MCP in practice
- [x] Produce a final recommendation with exact next steps

## Notes
- Investigated the linked MCP Market entry and traced it to `haju-xp/figma-free-mcp`.
- Confirmed it is a third-party/community MCP, not the official Figma MCP.
- Read repository files including `README.md`, `package.json`, `scripts/cli.js`, `src/server.ts`, `src/socket.ts`, and `plugin/manifest.json`.
- Architecture: MCP stdio server + local WebSocket relay on port 3055 + Figma desktop plugin.
- The project is primarily set up for Claude Desktop / Claude Code, not pi-specific auto-install.
- Environment check:
  - Node.js available: `v22.14.0`
  - npm available: `11.5.2`
  - Current pi MCP status before setup: `0/0 servers`
- Verified Figma Desktop is installed by resolving existing shortcuts:
  - `C:\Users\sek31\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Figma.lnk` → `C:\Users\sek31\AppData\Local\Figma\app-126.3.12\Figma.exe`
  - `C:\Users\sek31\AppData\Roaming\Microsoft\Internet Explorer\Quick Launch\User Pinned\TaskBar\Figma.lnk` → `C:\Users\sek31\AppData\Local\Figma\Figma.exe`
  - `C:\Users\sek31\AppData\Local\Figma\` exists and contains app versions plus `Figma.exe`.
- Read pi MCP adapter docs from `.../node_modules/pi-mcp-adapter/README.md`.
- Confirmed pi supports manual MCP registration through shared config files such as project `.mcp.json` or user-global `~/.config/mcp/mcp.json`.
- Therefore `figma-free-mcp` is compatible with pi in principle because it exposes a standard stdio MCP server (`npx ... figma-free-mcp-server`).
- Practical usage requirements in this environment:
  1. Start the relay server: `npx --package figma-free-mcp figma-free-mcp-socket`
  2. Import/install the Figma plugin from `~/.figma-free-mcp/plugin/manifest.json` (or repo plugin files)
  3. Run the plugin inside Figma Desktop so it connects to `ws://localhost:3055`
  4. Add a Pi MCP config entry, e.g. project `.mcp.json` with:
     ```json
     {
       "mcpServers": {
         "figma-free": {
           "command": "npx",
           "args": ["-y", "--package", "figma-free-mcp", "figma-free-mcp-server"]
         }
       }
     }
     ```
- Final recommendation:
  - Yes, this machine can likely use `figma-free-mcp` with pi.
  - The blocker is not licensing; it is setup work.
  - It is not turnkey for pi, but manual configuration appears straightforward.
  - Best next step would be to create `.mcp.json`, run the socket relay, import the plugin, then connect/test via the pi MCP gateway.
