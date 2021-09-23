package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, MyGame.WIDTH / 2, MyGame.HEIGHT / 2);
        background = new Texture("background.png");
        playBtn = new Texture("button.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(playBtn,camera.position.x - playBtn.getWidth() / 2, camera.position.y-50);
        sb.end();
    }

    @Override
    public void dispode() {
        background.dispose();
        playBtn.dispose();

    }
}
