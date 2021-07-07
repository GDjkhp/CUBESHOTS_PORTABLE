package com.mygdx.karakters.cubeshots.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.entities.basecircle_;
import com.mygdx.karakters.cubeshots.entities.basicenemy_;
import com.mygdx.karakters.cubeshots.entities.heart_;
import com.mygdx.karakters.cubeshots.entities.player_;
import com.mygdx.karakters.cubeshots.misc.Constant;
import com.mygdx.karakters.cubeshots.misc.GameStateManager;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.audioplayer_;

public class GameScreen extends GameStateManager implements Constant {
    private final String classID = getClass().getName();
    public GameScreen(MyGdxGame game) {
        super(game);
        Gdx.app.log(classID, "This class is loaded!");
    }
    public void show() {
        // summon test entities
        handler.addObject(new player_(0, 0, ID.Player, handler, vp, cam, mousePos));
        // test sound
        if (game.music) audioplayer_.getMusic("dead_meme").play();

        bpm = 125;
//        endBar = 84;
        // music logic time
        tpm = (60000/bpm) / 10;
//        spm = tpm * 4 / 16;

        Gdx.app.log(classID, "The game is running");
    }

    public void pause() {
        Gdx.app.log(classID, "The game is paused");
    }

    public void resume() {
        Gdx.app.log(classID, "The game is resumed");
    }

    // vars for gameloop fix
    public long lastTime = TimeUtils.nanoTime();
    double amountOfTicks = 100.0;
    double ns = 1000000000 / amountOfTicks;
    public double delta = 0;

    public void update() {
        // tick stuff here
        handler.update();

        long now = TimeUtils.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;

        while (delta >= 1) {
            delta--;
            // spawn code
            scoreKeep++;
            scoreKeepStep++;
            if (scoreKeep >= tpm) {
                if (metronome) metronomeCode();
                difference = scoreKeep - tpm;
                scoreKeep = difference;
                /*handler.addObject(new heart_(r.nextInt(GAME_WIDTH - 10), r.nextInt(GAME_HEIGHT - 10),
                        ID.HeartFriend, handler, 0, 0));*/
                handler.addObject(new basecircle_(r.nextInt(GAME_WIDTH - 10), r.nextInt(GAME_HEIGHT - 10),
                        ID.BaseCircle, handler, 0, 0, 0));
            }
            // steps
            /*if (scoreKeepStep >= spm) {
                stepsBeta();
                stepDifference = scoreKeepStep - spm;
                scoreKeepStep = stepDifference;
            }*/
        }

        // rotate
        /*sprite.rotate(1f);
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
        */
    }

    public void render(float delta) {
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
