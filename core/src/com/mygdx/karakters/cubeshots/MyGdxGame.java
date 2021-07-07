package com.mygdx.karakters.cubeshots;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.karakters.cubeshots.misc.audioplayer_;
import com.mygdx.karakters.cubeshots.misc.assets_;
import com.mygdx.karakters.cubeshots.states.GameScreen;
import com.mygdx.karakters.cubeshots.states.TitleScreen;

public class MyGdxGame extends Game{
	// options
	public static boolean ldm = false;
	public static boolean music = false;

	@Override
	public void create() {
		assets_.portal.load(new AssetManager());
		audioplayer_.load("");
		setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		super.dispose();
		assets_.portal.dispose();
		audioplayer_.dispose();
	}
}
