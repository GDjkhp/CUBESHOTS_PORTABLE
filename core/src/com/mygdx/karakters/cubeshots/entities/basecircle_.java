package com.mygdx.karakters.cubeshots.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.gameobject_;
import com.mygdx.karakters.cubeshots.misc.handler_;


public class basecircle_ extends gameobject_ {
    private handler_ handler;
    public static int patternVel = 5;
    // north
    private static int northVelY = -patternVel;
    // south
    private static int southVelY = patternVel;
    // east
    private static int eastVelX = patternVel;
    // west
    private static int westVelX = -patternVel;

    // default
    int defaultTimer;
    public basecircle_(float x, float y, ID id, handler_ handler, float velX, float velY, int spawnTimer) {
        super(x, y, id);
        dim.x = 30;
        dim.y = 30;
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        defaultTimer = spawnTimer;
        this.spawnTimer = spawnTimer;
        createGraphics(Color.MAGENTA, "fill");
    }

    @Override
    public void update() {
        if (spawnTimer == 0) {
            if (this.id == ID.BaseCircle) {
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, 0, northVelY));
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, 0, southVelY));
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, eastVelX, 0));
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, westVelX, 0));
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, eastVelX, northVelY));
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, westVelX, northVelY));
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, eastVelX, southVelY));
                handler.addObject(new circlewithpatterns_((int) pos.x + 10, (int) pos.y + 10, ID.CircleWithPatterns, handler, westVelX, southVelY));
                if (velX == 0 && velY == 0)
                    handler.removeObject(this);
                else spawnTimer = defaultTimer;
            }
        } else {
            pos.x += velX;
            pos.y += velY;
            if (this.id == ID.BaseCircle) spawnTimer--;
        }
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
