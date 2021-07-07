package com.mygdx.karakters.cubeshots.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.gameobject_;
import com.mygdx.karakters.cubeshots.misc.handler_;

public class circlewithpatterns_ extends gameobject_ {
    handler_ handler;
    public circlewithpatterns_(float x, float y, ID id, handler_ handler, float velX, float velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        dim.x = 10;
        dim.y = 10;
        createGraphics(Color.MAGENTA, "fill");
    }

    @Override
    public void update() {
        pos.x += velX;
        pos.y += velY;
        if (pos.x <= 0 || pos.x >= GAME_WIDTH) handler.removeObject(this);
        if (pos.y <= 0 || pos.y >= GAME_HEIGHT) handler.removeObject(this);
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

    }
}
