package com.mygdx.karakters.cubeshots.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.misc.Constant;

public class DesktopLauncher implements Constant{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "CUBESHOTS: no random splash here";
		config.width = GAME_WIDTH;
		config.height = GAME_HEIGHT;
		config.vSyncEnabled = true;
//		config.addIcon("resources_/image_/icon remastered.png", Files.FileType.Internal);
		config.x = 0;
		config.y = 0;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
