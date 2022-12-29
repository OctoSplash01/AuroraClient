package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * NoDowntime. Will be fixed in new release.
 */
public class NoDowntime {

    static Minecraft mc = Minecraft.getMinecraft();
    static Queue<String> wiad = new LinkedList<String> ();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) throws InterruptedException {
        if(Config.NoDowntime && Conditions.inSkyblock()) {
            String message = event.message.getFormattedText().replaceAll("\u00a7.", "");
            wiad.offer(message);
            if (wiad.size() > 3) {
                wiad.poll();

            }
            String type = null;
            int fl = 0;
            if(message.matches(".*Defeated*.[Bonzo, Scarf, Professor, Thorn, Livid, Sadan, Necron]*.in.*")) {
                assert wiad.peek() != null;
                if (wiad.peek().contains("Master")) {
                    type = "master_catacombs";
                    if (message.contains("Bonzo")) {
                        fl = 1;
                    } else if (message.contains("Scarf")) {
                        fl = 2;

                    } else if (message.contains("Professor")) {
                        fl = 3;

                    } else if (message.contains("Thorn")) {
                        fl = 4;

                    } else if (message.contains("Livid")) {
                        fl = 5;

                    } else if (message.contains("Sadan")) {
                        fl = 6;

                    } else if (message.contains("Necro")) {
                        fl = 7;

                    }
                } else {
                    type = "catacombs";
                    if (message.contains("Bonzo")) {
                        fl = 1;
                    } else if (message.contains("Scarf")) {
                        fl = 2;

                    } else if (message.contains("Professor")) {
                        fl = 3;

                    } else if (message.contains("Thorn")) {
                        fl = 4;

                    } else if (message.contains("Livid")) {
                        fl = 5;

                    } else if (message.contains("Sadan")) {
                        fl = 6;

                    } else if (message.contains("Necro")) {
                        fl = 7;

                    }
                }
                ClientMessages.sendClientMessage("Waiting...");
                ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

                if (!(fl == 0)) {
                    String finalType = type;
                    int finalFl = fl;
                    String finalType1 = type;
                    int finalFl1 = fl;
                    exec.schedule(new Runnable() {
                        public void run() {
                            mc.thePlayer.sendChatMessage("/joindungeon " + finalType + " " + String.valueOf(finalFl));
                            if (finalType1 == "catacombs") {
                                ClientMessages.sendClientMessage("Joining Catacombs Floor " + finalFl1);
                            } else {
                                ClientMessages.sendClientMessage("Joining Master Mode Catacombs Floor " + finalFl1);
                            }
                        }
                    }, Config.downtime, TimeUnit.SECONDS);
                }
            }


        }
    }
}