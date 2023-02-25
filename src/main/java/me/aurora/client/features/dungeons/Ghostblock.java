package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;


public class Ghostblock  implements Module {

    public String name() {
        return "Ghostblock";
    }

    public boolean toggled() {
        return Config.ghostblocks;
    }


    Minecraft mc = Minecraft.getMinecraft();



  /*  @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (mc.gameSettings.isKeyDown(key))


    } */

  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
      if(Keyboard.isKeyDown(Keyboard.KEY_G) && Config.ghostblocks) {
          MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
          mc.theWorld.setBlockToAir(POSITIONOFBLOCK.getBlockPos());
      }
  }

}

