package com.mygdx.karakters.cubeshots.misc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class gameobject_ implements Disposable, Constant {
    protected ID id;
    protected Vector2 pos, dim;
    protected float velX, velY;
    protected int health;
    protected Texture texture;
    protected Sprite sprite;
    protected Pixmap pixmap;
    protected BitmapFont font;
    protected OrthographicCamera font_cam, cam;
    protected int spawnTimer;
    public abstract void update();
    public abstract void render(SpriteBatch sb);
    public abstract Rectangle getBounds();

    public gameobject_ (float x, float y, ID id) {
        pos = new Vector2();
        pos.x = x;
        pos.y = y;
        dim = new Vector2();
        health = 0;
        this.id = id;
        // test font
        font = assets_.portal.getFont();
        // test cams
        font_cam = new OrthographicCamera();
        font_cam.setToOrtho(false, GAME_WIDTH * 2, GAME_HEIGHT * 2);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
    }

    // graphics
    protected void createGraphics(Color color, String type){
        int width = 8, height = 8;
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        // top
        pixmap.setColor(color);
        if (type == "fill") pixmap.fill();
        pixmap.drawRectangle(0, 0, width, height);
        // bottom
        /*pixmap.setColor(color);
        if (type == "fill") pixmap.fill();
        pixmap.drawRectangle(0, 0, width, height);*/
        texture = new Texture(pixmap);
        sprite = new Sprite(texture);
        sprite.setPosition(pos.x, pos.y);
        sprite.setSize(dim.x, dim.y);
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getDim() {
        return dim;
    }

    public void setDim(Vector2 dim) {
        this.dim = dim;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public void setPixmap(Pixmap pixmap) {
        this.pixmap = pixmap;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
