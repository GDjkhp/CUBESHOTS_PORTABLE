package com.mygdx.karakters.cubeshots.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.karakters.cubeshots.misc.ID;
import com.mygdx.karakters.cubeshots.misc.assets_;
import com.mygdx.karakters.cubeshots.misc.gameobject_;

public class CURSOR_POINTER extends gameobject_ {
    Sprite base = new Sprite(assets_.portal.getCursor());
    int dispX, dispY;

    // input
    Viewport vp;
    OrthographicCamera cam;
    Vector3 mousePos;

    public CURSOR_POINTER(float x, float y, ID id, Viewport vp, OrthographicCamera cam, Vector3 mousePos) {
        super(x, y, id);
        dim.x = 30;
        dim.y = 30;
        // upscale code
        base.setSize(30, 30);
        sprite = base;
        // input fix
        this.vp = vp;
        this.cam = cam;
        this.mousePos = mousePos;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        // input fix test
        cam.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0),
                vp.getScreenX(), vp.getScreenY(), vp.getScreenWidth(), vp.getScreenHeight());

        dispX = (int)mousePos.x;
        dispY = (int)mousePos.y; // fix this

        sprite.setPosition(dispX - 15, (dispY) - 15);
        sprite.draw(sb);
        sb.setProjectionMatrix(font_cam.combined);
        font.draw(sb, "x = " + dispX, dispX * 2, ((dispY) - 20)*2);
        font.draw(sb, "y = " + dispY, dispX * 2, ((dispY) - 40)*2);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void dispose() {

    }

}