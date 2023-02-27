package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

public class AutoSprint  implements Module {
    public String name() {
        return "AutoSprint";
    }

    public boolean toggled() {
        return Config.autoSprint;
    }
    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (Config.autoSprintSettings == 0) {
            if (event.entityLiving instanceof EntityPlayer) {
                if (Config.autoSprint && Minecraft.getMinecraft().thePlayer.onGround && !(mc.currentScreen instanceof GuiChat) && !mc.thePlayer.isSneaking() && !mc.thePlayer.isUsingItem() && (GameSettings.isKeyDown(mc.gameSettings.keyBindForward))) {
                    mc.thePlayer.setSprinting(true);
                }
            }
        }
        if (Config.autoSprintSettings == 1) {
            if (event.entityLiving instanceof EntityPlayer) {
                if (Config.autoSprint && !(mc.currentScreen instanceof GuiChat) && !mc.thePlayer.isSneaking() && !mc.thePlayer.isUsingItem() && (GameSettings.isKeyDown(mc.gameSettings.keyBindRight) || GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) || GameSettings.isKeyDown(mc.gameSettings.keyBindForward) || GameSettings.isKeyDown(mc.gameSettings.keyBindBack))) {
                    mc.thePlayer.setSprinting(true);
                }
            }
        }
    }
}
