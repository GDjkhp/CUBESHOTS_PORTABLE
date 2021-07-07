package com.mygdx.karakters.cubeshots.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.states.GameScreen;
import com.mygdx.karakters.cubeshots.states.TitleScreen;

import java.util.Random;

public abstract class GameStateManager extends ScreenAdapter implements Constant {

    protected SpriteBatch sb;
    protected OrthographicCamera cam, font_cam;
    protected Viewport vp;
    protected MyGdxGame game;
    protected handler_ handler;
    protected BitmapFont font;
    protected Texture texture;
    protected Sprite sprite;
    protected Pixmap pixmap;
    protected Vector3 mousePos = new Vector3();

    public Random r = new Random();

    // test sound integrity vars
    public static double bpm;
    public double tpm;
    public double spm;
    public static int endBar;

    public boolean metronome = true;

    public static double scoreKeep = 0;
    public static double scoreKeepStep = 0;

    public double difference;
    public double stepDifference;

    // music shenanigans
    public int total_bars = 1;
    public int total_beats = 1;
    public int fourbarticks = 1;

    public int total_bars_steps = 1;
    public int total_steps = 1;
    public int fourbarsteps = 1;

    public boolean metronomeSounds = true;

    // game loop attempt 3
    public float time = 0;
    public float tick = 1 / 100f;
//    public int maxUpdatesPerFrame = 10;

    // fps
    public long lastTimeCounted;
    public float sinceChange;
    public float frameRate;

    public enum GameState{
        TITLE, GAME, END
    }

    public GameState current_state = GameState.TITLE;

    protected GameStateManager (MyGdxGame game){
        // start the f0ken truck
        this.game = game;
        handler = new handler_();
        sb = new SpriteBatch();
        // camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
        font_cam = new OrthographicCamera();
        font_cam.setToOrtho(false, GAME_WIDTH * 2, GAME_HEIGHT * 2);
        // vp
        vp = new FitViewport(GAME_WIDTH, GAME_HEIGHT, cam);
        // font
        font = assets_.portal.getFont();
        // fps
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
    }

    protected void changeScreen(GameState state){
        if (state == GameState.TITLE || state == GameState.END)
            game.setScreen(new TitleScreen(game));
        if (state == GameState.GAME)
            game.setScreen(new GameScreen(game));
    }

    // graphics
    protected void createGraphics(Color color1, Color color2, String type1, String type2) {
        int width = 32, height = 32;
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        // test pos 1
        if (color1 != null) pixmap.setColor(color1);
        if (type1 == "fill") pixmap.fill();
        pixmap.drawRectangle(0, 0, width, height);
        // test pos 2
        if (color2 != null) pixmap.setColor(color2);
        if (type2 == "fill") pixmap.fill();
        pixmap.drawRectangle(0, 0, width, height);
        // register this
        texture = new Texture(pixmap);
        sprite = new Sprite(texture);
    }

    public void resize(int width, int height){
        vp.update(width, height);
        cam.position.set(GAME_WIDTH/2, GAME_HEIGHT/2, 0);
    }

    protected abstract void update();

    public void metronomeCode() {
        // metronome codes
        if (fourbarticks == 4) fourbarticks = 0;
        fourbarticks++;
        total_beats++;
        if (fourbarticks == 1) {
            total_bars++;
            if (game.music && metronomeSounds) audioplayer_.getSound("first_tick").play();
        } else if (game.music && metronomeSounds) audioplayer_.getSound("tick").play();
    }
}
