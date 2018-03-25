package com.starfish.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.starfish.StarfishCollector;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Starfish Collector";
		config.height = 600;
		config.width = 800;
		new LwjglApplication(new StarfishCollector(), config);
	}
}
