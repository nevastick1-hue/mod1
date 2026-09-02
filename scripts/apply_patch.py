from pathlib import Path

cfg = Path("upstream/common/src/main/java/dev/xylonity/tooltipoverhaul/config/TooltipsConfig.java")
s = cfg.read_text()
needle = "    public static int TOOLTIP_POSITION_X = 0;"
extra = """

    @ConfigEntry(category = "layout", comment = "Enable custom tooltip area")
    public static boolean TOOLTIP_AREA_ENABLED = true;
    @ConfigEntry(category = "layout", comment = "Restrict tooltip horizontally")
    public static boolean TOOLTIP_AREA_RESTRICT_X = true;
    @ConfigEntry(category = "layout", comment = "Restrict tooltip vertically")
    public static boolean TOOLTIP_AREA_RESTRICT_Y = true;
    @ConfigEntry(category = "layout", comment = "Left boundary in GUI pixels")
    public static int TOOLTIP_AREA_LEFT = 950;
    @ConfigEntry(category = "layout", comment = "Top boundary in GUI pixels")
    public static int TOOLTIP_AREA_TOP = 80;
    @ConfigEntry(category = "layout", comment = "Right margin in GUI pixels")
    public static int TOOLTIP_AREA_RIGHT = 30;
    @ConfigEntry(category = "layout", comment = "Bottom margin in GUI pixels")
    public static int TOOLTIP_AREA_BOTTOM = 80;
"""
if "TOOLTIP_AREA_ENABLED" not in s:
    if needle not in s: raise RuntimeError("Config anchor not found")
    cfg.write_text(s.replace(needle, needle + extra))

pos = Path("upstream/common/src/main/java/dev/xylonity/tooltipoverhaul/client/layout/TooltipPositionCalculator.java")
s = pos.read_text()
needle = "        return new Vec2(posX - viewOffsetX, posY - viewOffsetY);"
extra = """        if (isMainTooltip && TooltipsConfig.TOOLTIP_AREA_ENABLED) {
            if (TooltipsConfig.TOOLTIP_AREA_RESTRICT_X) {
                float minX = TooltipsConfig.TOOLTIP_AREA_LEFT;
                float maxX = Math.max(minX, screenWidth - TooltipsConfig.TOOLTIP_AREA_RIGHT - tooltipWidth);
                posX = Math.max(minX, Math.min(maxX, posX));
            }
            if (TooltipsConfig.TOOLTIP_AREA_RESTRICT_Y) {
                float minY = TooltipsConfig.TOOLTIP_AREA_TOP;
                float maxY = Math.max(minY, screenHeight - TooltipsConfig.TOOLTIP_AREA_BOTTOM - tooltipHeight);
                posY = Math.max(minY, Math.min(maxY, posY));
            }
        }

"""
if "TOOLTIP_AREA_ENABLED" not in s:
    if needle not in s: raise RuntimeError("Position anchor not found")
    pos.write_text(s.replace(needle, extra + needle))
