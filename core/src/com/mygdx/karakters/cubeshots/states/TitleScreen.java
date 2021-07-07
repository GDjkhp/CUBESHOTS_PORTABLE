package com.mygdx.karakters.cubeshots.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.entities.*;
import com.mygdx.karakters.cubeshots.misc.GameStateManager;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.audioplayer_;

public class TitleScreen extends GameStateManager {

    public TitleScreen(MyGdxGame game) {
        super(game);
    }

    public void show(){
        // summon
        for (int i = 0; i < 5; i++)
            handler.addObject(new basicenemy_(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), ID.BasicEnemy, handler));
        for (int i = 0; i < 5; i++)
            handler.addObject(new fastenemy_(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), ID.FastEnemy, handler));
        for (int i = 0; i < 5; i++)
            handler.addObject(new hardenemy_(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), ID.HardEnemy, handler));
//        for (int i = 1; i <= 15; i++)
//            handler.addObject(new spicymenu_(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), ID.Spicy, handler));
        handler.addObject(new player_(0, 0, ID.Player, handler, vp, cam, mousePos));
        handler.addObject(new CURSOR_POINTER(0, 0, ID.CURSOR, vp, cam, mousePos));
        // test sound
        if (game.music) {
            audioplayer_.getMusic("dead_meme").play();
            bpm = 125;
            tpm = (60000/bpm) / 10;
        }
    }

    // vars for gameloop fix
    public long lastTime = TimeUtils.nanoTime();
    double amountOfTicks = 100.0;
    double ns = 1000000000 / amountOfTicks;
    public double delta = 0;

    public void update() {
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
                /*handler.addObject(new basecircle_(r.nextInt(GAME_WIDTH - 10), r.nextInt(GAME_HEIGHT - 10),
                        ID.BaseCircle, handler, 0, 0, 0));*/
            }
            // steps
            /*if (scoreKeepStep >= spm) {
                stepsBeta();
                stepDifference = scoreKeepStep - spm;
                scoreKeepStep = stepDifference;
            }*/
        }
    }

    public void render(float delta) {
        // fps
        long deltas = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();
        sinceChange += deltas;
        if(sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }

        // FIXME: loop this tick
        time += Gdx.graphics.getRawDeltaTime();
//        int updatesThisFrame = 0;
        while (time >= tick /*&& updatesThisFrame < maxUpdatesPerFrame*/) {
//            update(); // lock step
//            updatesThisFrame++;
            time -= tick;
        }
        update(); // frame step

        // things get rough, the buffer
        Gdx.gl20.glClearColor(Color.DARK_GRAY.r, Color.DARK_GRAY.g, Color.DARK_GRAY.b, Color.DARK_GRAY.a);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // graphics
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        handler.render(sb); // top or bottom?

        // bound box graphics
        sb.setProjectionMatrix(cam.combined);
        /*createGraphics(Color.GREEN, null, "", "");
        sprite.setPosition(GAME_WIDTH / 3, 100);
        sprite.setSize(200, 50);
        sprite.draw(sb);

        createGraphics(Color.GREEN, null, "", "");
        sprite.setPosition(GAME_WIDTH / 3, 175);
        sprite.setSize(200, 50);
        sprite.draw(sb);

        createGraphics(Color.GREEN, null, "", "");
        sprite.setPosition(GAME_WIDTH / 3, 250);
        sprite.setSize(200, 50);
        sprite.draw(sb);

        createGraphics(Color.GREEN, null, "", "");
        sprite.setPosition(GAME_WIDTH / 3, 325);
        sprite.setSize(200, 50);
        sprite.draw(sb);*/

        // fonts for debug
        sb.setProjectionMatrix(font_cam.combined);
        font.draw(sb, "game_", 0, GAME_HEIGHT * 2);
        font.draw(sb, "fps: " + (int)frameRate, 0, GAME_HEIGHT * 2 - 30);
        font.draw(sb, "objects: " + handler.object.size(), 0, GAME_HEIGHT * 2 - 60);
        font.draw(sb, "Viewport Screen Pos: " + vp.getScreenX() + ", " + vp.getScreenY(), 0, GAME_HEIGHT * 2 - 90);
        font.draw(sb, "Viewport Screen Dim: " + vp.getScreenWidth() + ", " + vp.getScreenHeight(), 0, GAME_HEIGHT * 2 - 120);
        font.draw(sb, "Viewport Screen Mouse: " + mousePos.x + ", " + mousePos.y, 0, GAME_HEIGHT * 2 - 150);
        font.draw(sb, "player_ Pos: " + handler.getObject(ID.Player).getPos(), 0, GAME_HEIGHT * 2 - 180);
        font.draw(sb, "", 0, GAME_HEIGHT * 2 - 210);
        font.draw(sb, "", 0, GAME_HEIGHT * 2 - 240);

        // fonts for menu
        sb.setProjectionMatrix(cam.combined);
        /*font.draw(sb, "play_", GAME_WIDTH / 3 + 10, 350);
        font.draw(sb, "help_", GAME_WIDTH / 3 + 10, 275);
        font.draw(sb, "sound_", GAME_WIDTH / 3 + 10, 200);
        font.draw(sb, "quit_", GAME_WIDTH / 3 + 10, 125);*/

        sb.end();
    }


}



