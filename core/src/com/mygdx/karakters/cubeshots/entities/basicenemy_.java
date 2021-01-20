package com.mygdx.karakters.cubeshots.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.karakters.cubeshots.MyGdxGame;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.gameobject_;
import com.mygdx.karakters.cubeshots.misc.handler_;

public class basicenemy_ extends gameobject_  {
    handler_ handler;

    public basicenemy_(float x, float y, ID id, handler_ handler){
        super(x, y, id);
        this.handler = handler;
        dim.x = 10;
        dim.y = 10;
//        sprite = new Sprite(assets_.portal.getApple());
        createGraphics(Color.RED, "fill");
        this.velX = 5;
        this.velY = 5;
    }

    @Override
    public void update() {
        pos.add(velX, velY);
        // screen limit
        if (pos.x <= 0 || pos.x >= GAME_WIDTH) velX *= -1;
        if (pos.y <= 0 || pos.y >= GAME_HEIGHT) velY *= -1;
        if (!MyGdxGame.ldm) handler.addObject(new trail_(pos.x, pos.y, ID.Trail, handler, dim.x, dim.y, Color.RED, 0.1f));
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

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
