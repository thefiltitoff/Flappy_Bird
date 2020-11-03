package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGame;

import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State{

    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    private Texture bg;
    private Texture cloud;
    private Vector2 cloudPos1, cloudPos2;


    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird (50,300);
        camera.setToOrtho(false, MyGame.WIDTH/2,MyGame.HEIGHT/2);
        bg = new Texture("background.png");
        cloud = new Texture("cloud.png");
        cloudPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        cloudPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + cloud.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for(int i =0;i<TUBE_COUNT;i++)
        {
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

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
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x +80;

        for(int i = 0;i<tubes.size;i++)
        {
            Tube tube = tubes.get(i);
            if(camera.position.x - (camera.viewportWidth/2) > tube.getPosTopTube().x
                + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH+ TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds()))
                gsm.set(new PlayState(gsm));
        }
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x-(camera.viewportWidth/2)-50,-500);
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for (Tube tube: tubes)
        {
            sb.draw(tube.getTopTube(),tube.getPosBotTube().x,tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        }
        sb.draw(cloud, cloudPos1.x, cloudPos1.y);
        sb.draw(cloud, cloudPos2.x, cloudPos2.y);

        sb.end();

    }

    @Override
    public void dispode() {
        bg.dispose();
        bird.dispose();
        for(Tube tube:tubes)
        {
            tube.dispose();
        }


    }

    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > cloudPos1.x + cloud.getWidth())
            cloudPos1.add(cloud.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > cloudPos2.x + cloud.getWidth())
            cloudPos2.add(cloud.getWidth() * 2, 0);
    }
}
