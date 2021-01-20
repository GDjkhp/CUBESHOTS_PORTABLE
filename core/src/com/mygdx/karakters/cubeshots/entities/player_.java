package com.mygdx.karakters.cubeshots.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.gameobject_;
import com.mygdx.karakters.cubeshots.misc.handler_;

import java.util.Random;

public class player_ extends gameobject_ {
    handler_ handler;
    Random r = new Random();

    // input fix
    Viewport vp;
    OrthographicCamera cam;
    Vector3 mousePos;

    public player_(float x, float y, ID id, handler_ handler, Viewport vp, OrthographicCamera cam, Vector3 mousePos) {
        super(x, y, id);
        this.handler = handler;
        dim.x = 30;
        dim.y = 30;
        createGraphics(Color.CYAN, "fill");
        // input fix
        this.vp = vp;
        this.cam = cam;
        this.mousePos = mousePos;
    }

    public float clamp(float var, float min, float max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    @Override
    public void update() {
        handleInput();
        pos.set(clamp(pos.x, 0, GAME_WIDTH - 30), clamp(pos.y, 0, GAME_HEIGHT - 30));
        if (!MyGdxGame.ldm) handler.addObject(new trail_(pos.x, pos.y, ID.Trail, handler, dim.x, dim.y, Color.CYAN, 0.1f));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(sb);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
    // basic input
    public void handleInput(){
        // input fix test
        cam.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0),
                vp.getScreenX(), vp.getScreenY(), vp.getScreenWidth(), vp.getScreenHeight());

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            pos.add(5, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            pos.add(-5, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            pos.add(0, 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            pos.add(0, -5);
        }
        if (Gdx.input.isTouched() || Gdx.input.isCursorCatched()){
            float mx = (int)mousePos.x,
                    my = (int)mousePos.y; // fix this
            float diffX = pos.x - mx;
            float diffY = pos.y - my;
            float distance = (float) Math.sqrt((pos.x - mx) * (pos.x - mx) + (pos.y - my) * (pos.y - my));
            float pathX = ((-5 / distance) * diffX);
            float pathY = ((-5 / distance) * diffY);
            pos.add(pathX, pathY);
        }
//        Gdx.input.isTouched(Gdx.input.getX()) && Gdx.input.isTouched(Gdx.input.getY());
    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
    }

}
