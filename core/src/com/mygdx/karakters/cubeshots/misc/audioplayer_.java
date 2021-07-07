package com.mygdx.karakters.cubeshots.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class audioplayer_{
    public static String currentMusic = "";
    // TODO: yow implement slick2d, serious bug happened
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();
    public static String soundPath = "resources_/sounds_/";
    public static String musicPath = "resources_/music_/";

    public static void load(String key) {
        try {
            // default sfx
            soundMap.put("null", Gdx.audio.newSound(Gdx.files.internal(soundPath + "null.ogg")));
            soundMap.put("click_sound", Gdx.audio.newSound(Gdx.files.internal(soundPath + "Gun-Reload-Sound-Effect.ogg")));
            soundMap.put("tick", Gdx.audio.newSound(Gdx.files.internal(soundPath + "tick.ogg")));
            soundMap.put("first_tick", Gdx.audio.newSound(Gdx.files.internal(soundPath + "tickstart.ogg")));
            // default music
            musicMap.put("null", Gdx.audio.newMusic(Gdx.files.internal(soundPath + "null.ogg")));
            musicMap.put("music", Gdx.audio.newMusic(Gdx.files.internal(musicPath + "97945.ogg")));
            musicMap.put("shop_music", Gdx.audio.newMusic(Gdx.files.internal(musicPath + "reminds me of elevators.ogg")));
            // new code for custom music
            switch (key) {
                case "dead_meme":
                    musicMap.put(key, Gdx.audio.newMusic(Gdx.files.internal(musicPath + "dead meme.ogg")));
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Music getMusic (String key) {
        // load codes
        if (key != "music" && key != "null" && key != "shop_music" /*&& game_.gameState != game_.STATE.GameBeta*/) {
            if (currentMusic != "") getMusic(currentMusic).dispose();
            currentMusic = key;
//            game_.gameState = game_.STATE.Load;
//            game_.stringsforloading = "loading music: " + key;
//            game_.loadstate -= 25;
            load(key);
//            game_.loadstate += 25;
        }
        return musicMap.get(key);
    }

    public static Sound getSound (String key) {
        return soundMap.get(key);
    }

    public static void dispose() {
        getMusic(currentMusic).dispose();
    }
}
