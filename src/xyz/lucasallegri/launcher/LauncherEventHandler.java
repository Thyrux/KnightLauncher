package xyz.lucasallegri.launcher;

import xyz.lucasallegri.launcher.mods.Mods;
import xyz.lucasallegri.launcher.settings.Settings;
import xyz.lucasallegri.logging.KnightLog;
import xyz.lucasallegri.util.ProcessUtil;
import xyz.lucasallegri.util.SteamUtil;
import java.awt.event.ActionEvent;

public class LauncherEventHandler {
	
	public static void launchEvent(ActionEvent action) {
		
		Thread launchThread = new Thread(new Runnable(){
			public void run() {
				
				ProgressBar.showBar();
				ProgressBar.showState();
				
				Mods.mount();
				
				if(Mods.modSetupFinished) {
					if(Settings.gamePlatform.startsWith("Steam")) {
						
						try {
							SteamUtil.startGameById("99900");
						} catch (Exception e) {
							KnightLog.log.severe(e.getLocalizedMessage());
						}
						
					} else {
						
						ProcessUtil.startApplication(LauncherConstants.STANDALONE_CLIENT_ARGS);
						
					}
					
					LauncherGUI.launcherGUIForm.dispose();
				}
			}
		});
		launchThread.start();
		
	}
	
	public static void rebuildEvent(ActionEvent action) {
		new Thread(new Runnable(){
			public void run() { Mods.rebuildJars(); }
		});
	}

}
