package com.mygdx.karakters.cubeshots.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.gameobject_;
import com.mygdx.karakters.cubeshots.misc.handler_;

import java.util.Random;

public class spicymenu_ extends gameobject_ {
    handler_ handler;
    Random r = new Random();
    Color col = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1);
    public spicymenu_(float x, float y, ID id, handler_ handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = (r.nextInt(5 - -5) + -5);
        if (velX == 0 && velY == 0) {
            velX = 5;
            velY = 5;
        }
        dim.x = 20;
        dim.y = 20;
        createGraphics(col, "fill");
    }

    @Override
    public void update() {
        pos.add(velX, velY);
        // screen limit
        if (pos.x <= 0 || pos.x >= GAME_WIDTH) velX *= -1;
        if (pos.y <= 0 || pos.y >= GAME_HEIGHT) velY *= -1;
        if (!MyGdxGame.ldm) handler.addObject(new trail_(pos.x, pos.y, ID.Trail, handler, dim.x, dim.y, col, 0.1f));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(sb);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
