package xyz.lucasallegri.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import xyz.lucasallegri.launcher.Language;
import xyz.lucasallegri.launcher.LauncherConstants;
import xyz.lucasallegri.logging.KnightLog;

public class DiscordInstance {
	
	private static final String CLIENT_ID = "626524043209867274";
	private static final DiscordEventHandlers EVENT_HANDLER = new DiscordEventHandlers();
	
	public static void start() {
		DiscordRPC.discordInitialize(CLIENT_ID, EVENT_HANDLER, true);
		setPresence(Language.getValue("presence.starting"));
		KnightLog.log.info("DiscordInstance is now running.");
	}
	
	public static void setPresence(String details) {
		DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(Language.getValue("presence.using"));
		presence.setDetails(details);
		presence.setBigImage("icon-512", Language.getValue("presence.image_desc", LauncherConstants.VERSION));
		DiscordRPC.discordUpdatePresence(presence.build());
		KnightLog.log.info("Updating presence detail to: " + details);
	}
	
	public static void stop() {
		DiscordRPC.discordShutdown();
	}

}
