package com.mygdx.karakters.cubeshots;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.karakters.cubeshots.misc.assets_;
import com.mygdx.karakters.cubeshots.misc.audioplayer_;
import com.mygdx.karakters.cubeshots.states.TitleScreen;

public class MyGdxGame extends Game{
//	SpriteBatch batch;
//	Texture img;
//	ShapeRenderer shapeRenderer;

	// options
	public static boolean ldm = false;
	public static boolean music = false;

	@Override
	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

		assets_.portal.load(new AssetManager());
		audioplayer_.load("");

		setScreen(new TitleScreen(this));

//		shapeRenderer = new ShapeRenderer();
	}

//	@Override
//	public void render () {
/*		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/

		/*// grid
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.WHITE);
		for (int a = 0, b = 0; a <= 630 && b <= 470; a += 10) {
			shapeRenderer.rect(a, b, 10, 10);
			if (a == 630) {
				a = -10;
				b += 10;
			}
		}
		shapeRenderer.end();

		// pixels
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		int x = (((int)(Math.random() * 640)) / 10) * 10, y = (((int)(Math.random() * 480)) / 10) * 10;
		shapeRenderer.rect(x, y, 10, 10);
		shapeRenderer.end();*/
//	}

	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();

//		shapeRenderer.dispose();
		super.dispose();
		assets_.portal.dispose();
		audioplayer_.dispose();
	}
}
