package com.nevastick.tooltipareapatch;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;

@Mod(TooltipAreaPatch.MODID)
public class TooltipAreaPatch {
    public static final String MODID = "tooltipareapatch";
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.BooleanValue RESTRICT_X;
    public static final ForgeConfigSpec.BooleanValue RESTRICT_Y;
    public static final ForgeConfigSpec.IntValue LEFT;
    public static final ForgeConfigSpec.IntValue TOP;
    public static final ForgeConfigSpec.IntValue RIGHT;
    public static final ForgeConfigSpec.IntValue BOTTOM;
    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        b.push("tooltip_area");
        ENABLED=b.define("ENABLED", true);
        RESTRICT_X=b.define("RESTRICT_X", true);
        RESTRICT_Y=b.define("RESTRICT_Y", true);
        LEFT=b.defineInRange("LEFT", 950, 0, 10000);
        TOP=b.defineInRange("TOP", 80, 0, 10000);
        RIGHT=b.defineInRange("RIGHT", 30, 0, 10000);
        BOTTOM=b.defineInRange("BOTTOM", 80, 0, 10000);
        b.pop(); SPEC=b.build();
    }
    public TooltipAreaPatch() { ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SPEC, "tooltip-area-patch.toml"); }
    public static int clampX(int x,int tooltipWidth,int screenWidth){ if(!ENABLED.get()||!RESTRICT_X.get())return x; int min=LEFT.get(); int max=Math.max(min,screenWidth-RIGHT.get()-tooltipWidth); return Math.max(min,Math.min(max,x)); }
    public static int clampY(int y,int tooltipHeight,int screenHeight){ if(!ENABLED.get()||!RESTRICT_Y.get())return y; int min=TOP.get(); int max=Math.max(min,screenHeight-BOTTOM.get()-tooltipHeight); return Math.max(min,Math.min(max,y)); }
}
