package com.nevastick.tooltipareapatch;
import net.minecraftforge.common.ForgeConfigSpec;
public final class ClientConfig {
 public static final ForgeConfigSpec SPEC;
 public static final ForgeConfigSpec.BooleanValue ENABLED, CLAMP_X, CLAMP_Y;
 public static final ForgeConfigSpec.ConfigValue<String> MODE;
 public static final ForgeConfigSpec.IntValue FIXED_X,FIXED_Y,AREA_LEFT,AREA_TOP,AREA_RIGHT,AREA_BOTTOM;
 static { ForgeConfigSpec.Builder b=new ForgeConfigSpec.Builder(); b.push("tooltip_position");
 ENABLED=b.comment("Enable custom tooltip positioning").define("enabled",true);
 MODE=b.comment("FIXED = anchored. CLAMP = vanilla movement inside area").defineInList("mode","FIXED",java.util.List.of("FIXED","CLAMP"));
 FIXED_X=b.comment("Fixed X in GUI pixels").defineInRange("fixed_x",950,-10000,10000);
 FIXED_Y=b.comment("Fixed Y in GUI pixels").defineInRange("fixed_y",80,-10000,10000);
 AREA_LEFT=b.defineInRange("area_left",950,-10000,10000); AREA_TOP=b.defineInRange("area_top",80,-10000,10000);
 AREA_RIGHT=b.defineInRange("area_right",30,-10000,10000); AREA_BOTTOM=b.defineInRange("area_bottom",80,-10000,10000);
 CLAMP_X=b.define("clamp_x",true); CLAMP_Y=b.define("clamp_y",true); b.pop(); SPEC=b.build(); }
 private ClientConfig(){}
}