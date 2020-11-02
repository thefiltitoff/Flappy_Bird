package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;

import com.mygdx.game.sprites.Bird;

public class PlayState extends State{

    private Bird bird;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird (50,300);
        camera.setToOrtho(false, MyGame.WIDTH/2,MyGame.HEIGHT/2);
        bg = new Texture("background.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched())
        {
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

        bird.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x-(camera.viewportWidth/2)-50,-500);
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        sb.end();

    }

    @Override
    public void dispode() {

    }
}
