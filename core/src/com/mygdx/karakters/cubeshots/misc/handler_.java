package com.mygdx.karakters.cubeshots.misc;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.LinkedList;

public class handler_ implements Disposable {
    public LinkedList<gameobject_> object = new LinkedList<gameobject_>();
    public void update(){
        for (int i = 0; i < object.size(); i++/*int i = object.size() - 1; i >= 0; i--*/) {
            gameobject_ tempObject = object.get(i);
            tempObject.update();
        }
    }
    public void render(SpriteBatch sb){
        for (int i = 0; i < object.size(); i++/*int i = object.size() - 1; i >= 0; i--*/) {
            gameobject_ tempObject = object.get(i);
            tempObject.render(sb);
        }
    }
    public void addObject(gameobject_ object){
        /*if (this.object.size() >= 250 *//*change this number to limit trail entities*//*) {
            if (object.getId() != ID.Trail) {
                this.object.add(object);
            }
        } else this.object.add(object);*/
        this.object.add(object);
    }
    public void removeObject(gameobject_ object){
        // memory leak fix, it actually works!
        object.texture.dispose();
        this.object.remove(object);
    }

    // this is still beta
    public gameobject_ getObject(ID id){
        gameobject_ tempObject;
        for (int i = object.size() - 1; i >= 0; i--) {
            tempObject = object.get(i);
            if (tempObject.getId() == id) {
                return tempObject;
            }
        }
        return null;
    }

    @Override
    public void dispose() {

    }
}
