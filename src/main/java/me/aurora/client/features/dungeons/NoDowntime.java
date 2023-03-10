package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
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
 * NoDowntime.
 * Improving this is a downtime. Works? Works.
 */
public class NoDowntime implements Module {

    public String name() {
        return "NoDowntime";
    }

    public boolean toggled() {
        return Config.noDowntime;
    }


    static Minecraft mc = Minecraft.getMinecraft();
    static Queue<String> messagesQueue = new LinkedList<String> ();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) throws InterruptedException {
        if(Config.noDowntime && Conditions.inSkyblock()) {
            String message = event.message.getFormattedText().replaceAll("\u00a7.", "");
            messagesQueue.offer(message);
            if (messagesQueue.size() > 3) {
                messagesQueue.poll();

            }
            String type = null;
            int floor = 0;
            if(message.matches(".*Defeated*.[Bonzo, Scarf, Professor, Thorn, Livid, Sadan, Necron]*.in.*")) {
                assert messagesQueue.peek() != null;
                if (messagesQueue.peek().contains("Master")) {
                    type = "master_catacombs";
                    if (message.contains("Bonzo")) {
                        floor = 1;
                    } else if (message.contains("Scarf")) {
                        floor = 2;

                    } else if (message.contains("Professor")) {
                        floor = 3;

                    } else if (message.contains("Thorn")) {
                        floor = 4;

                    } else if (message.contains("Livid")) {
                        floor = 5;

                    } else if (message.contains("Sadan")) {
                        floor = 6;

                    } else if (message.contains("Necro")) {
                        floor = 7;

                    }
                } else {
                    type = "catacombs";
                    if (message.contains("Bonzo")) {
                        floor = 1;
                    } else if (message.contains("Scarf")) {
                        floor = 2;

                    } else if (message.contains("Professor")) {
                        floor = 3;

                    } else if (message.contains("Thorn")) {
                        floor = 4;

                    } else if (message.contains("Livid")) {
                        floor = 5;

                    } else if (message.contains("Sadan")) {
                        floor = 6;

                    } else if (message.contains("Necro")) {
                        floor = 7;

                    }
                }
                ClientMessages.sendClientMessage("Waiting...");
                ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

                if (!(floor == 0)) {
                    String finalType = type;
                    int finalFl = floor;
                    String finalType1 = type;
                    int finalFl1 = floor;
                    exec.schedule(new Runnable() {
                        public void run() {
                            mc.thePlayer.sendChatMessage("/joindungeon " + finalType + " " + String.valueOf(finalFl));
                            if (finalType1 == "catacombs") {
                                ClientMessages.sendClientMessage("Joining Catacombs Floor " + finalFl1);
                            } else {
                                ClientMessages.sendClientMessage("Joining Master Mode Catacombs Floor " + finalFl1);
                            }
                        }
                    }, Config.noDowntime_ParameterDelay, TimeUnit.SECONDS);
                }
            }


        }
    }
}