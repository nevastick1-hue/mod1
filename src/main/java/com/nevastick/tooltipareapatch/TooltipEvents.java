package com.nevastick.tooltipareapatch;
import net.minecraft.client.gui.Font;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid=TooltipAreaPatch.MODID,value=Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.FORGE)
public final class TooltipEvents {
 @SubscribeEvent public static void onTooltipPre(RenderTooltipEvent.Pre e){
  if(!ClientConfig.ENABLED.get()) return;
  if("FIXED".equals(ClientConfig.MODE.get())){e.setX(ClientConfig.FIXED_X.get());e.setY(ClientConfig.FIXED_Y.get());return;}
  Font f=e.getFont(); int w=0,h=0;
  for(var c:e.getComponents()){w=Math.max(w,c.getWidth(f));h+=c.getHeight();}
  int x=e.getX(),y=e.getY();
  if(ClientConfig.CLAMP_X.get()){int min=ClientConfig.AREA_LEFT.get(),max=Math.max(min,e.getScreenWidth()-ClientConfig.AREA_RIGHT.get()-w);x=Math.max(min,Math.min(max,x));}
  if(ClientConfig.CLAMP_Y.get()){int min=ClientConfig.AREA_TOP.get(),max=Math.max(min,e.getScreenHeight()-ClientConfig.AREA_BOTTOM.get()-h);y=Math.max(min,Math.min(max,y));}
  e.setX(x);e.setY(y);
 }
}