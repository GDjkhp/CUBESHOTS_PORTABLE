package com.mygdx.karakters.cubeshots.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class assets_ implements Disposable {
    public static assets_ portal = new assets_();

    // image
    private final String IMAGE_PATH = "resources_/image_/libgdx/texture pack test.atlas";
    private AssetManager manager;
    private TextureAtlas atlas;
    // font
    private BitmapFont font;
    // images that you can use
    private TextureAtlas.AtlasRegion apple;
    private TextureAtlas.AtlasRegion cursor;

    private assets_() {
        // why is this empty?
    }

    public void load(AssetManager manager) {
        this.manager = manager;
        manager.load(IMAGE_PATH, TextureAtlas.class);
        manager.finishLoading();
        atlas = manager.get(IMAGE_PATH, TextureAtlas.class);
        // load assets by type
//        apple = atlas.findRegion("apple");
        cursor = atlas.findRegion("target");
        // load font
        font = new BitmapFont(Gdx.files.internal("mojangles.fnt"));
    }

    @Override
    public void dispose() {
        manager.dispose();
        font.dispose();
    }

    // getters
    /*public TextureAtlas.AtlasRegion getApple() {
        return apple;
    }*/
    public BitmapFont getFont() {
        return font;
    }

    public TextureAtlas.AtlasRegion getCursor() {
        return cursor;
    }
}
