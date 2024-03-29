package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.lang.reflect.Modifier;

public class Bird {
    public static final int MOVEMENT = 100;
    public static final int GRAVITY = -15;
    
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;

    private Texture bird;

    public Bird(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());
    }

    public void update(float dt) {
        if(position.y > 0) {
            velocity.add(0,GRAVITY,0);
        }

        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT*dt,velocity.y,0);
        if(position.y <0) {
            position.y =0 ;
        }

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);


    }
    
    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public void jump() {
        velocity.y = 350;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        bird.dispose();
    }
}

