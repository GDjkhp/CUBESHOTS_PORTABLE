package com.mygdx.karakters.cubeshots.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.entities.basicenemy_;
import com.mygdx.karakters.cubeshots.entities.player_;
import com.mygdx.karakters.cubeshots.misc.Constant;
import com.mygdx.karakters.cubeshots.misc.GameStateManager;
import com.mygdx.karakters.cubeshots.misc.ID;

public class GameScreen extends GameStateManager implements Constant {
    private final String classID = getClass().getName();
    public GameScreen(MyGdxGame game) {
        super(game);
        Gdx.app.log(classID, "This class is loaded!");
    }
    public void show() {
        // summon test entities
        handler.addObject(new player_(0, 0, ID.Player, handler, vp, cam, mousePos));
        handler.addObject(new basicenemy_(0, 0, ID.BasicEnemy, handler));

        Gdx.app.log(classID, "The game is running");
    }

    public void pause () {
        Gdx.app.log(classID, "The game is paused");
    }

    public void resume () {
        Gdx.app.log(classID, "The game is resumed");
    }

    public void update() {
        // tick stuff here
        handler.update();

        // rotate
        /*sprite.rotate(1f);
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
        */
    }

    public void render(float deltaTime) {
        // tick here
        update();
        // things get rough, the buffer
        Gdx.gl20.glClearColor(Color.DARK_GRAY.r, Color.DARK_GRAY.g, Color.DARK_GRAY.b, Color.DARK_GRAY.a);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // render stuff here
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        handler.render(sb);
        // render font
        sb.setProjectionMatrix(font_cam.combined);
        // end graphics
        sb.end();
    }

}
