package com.mygdx.karakters.cubeshots.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.gameobject_;
import com.mygdx.karakters.cubeshots.misc.handler_;

public class trail_ extends gameobject_ {
    handler_ handler;
    float alpha = 1, life;
    Color color;

    protected trail_(float x, float y, ID id, handler_ handler, float width, float height, Color color, float life) {
        super(x, y, id);
        this.handler = handler;
        dim.x = width;
        dim.y = height;
        this.life = life;
        this.color = color;
        createGraphics(color, "fill");
    }

    @Override
    public void update() {
        if(alpha > life) {
            alpha -= (life - 0.0001f);
        } else handler.removeObject(this);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sprite.setAlpha(alpha);
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
