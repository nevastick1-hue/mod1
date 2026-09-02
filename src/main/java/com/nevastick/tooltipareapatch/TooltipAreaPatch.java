package com.nevastick.tooltipareapatch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.ModLoadingContext;
@Mod(TooltipAreaPatch.MODID)
public class TooltipAreaPatch {
 public static final String MODID="tooltipareapatch";
 public TooltipAreaPatch(){ ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC); }
}