package com.RPG.game.phase2.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;

public class Player extends Entity
{
    //sert à actualiser la positon de la camera pour qu'elle suive le joueur
    private OrthographicCamera m_camera;

    public Player(OrthographicCamera travellingCamera)
    {
        super();
        m_camera = travellingCamera;
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/test-sprites/spriteSheetTest.atlas"));
        Animation animation_upwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Actor2-2,3"),
                textureAtlas.findRegion("Actor2-1,3"),
                textureAtlas.findRegion("Actor2-0,3"),
                textureAtlas.findRegion("Actor2-1,3"));
        Animation animation_downwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Actor2-2,0"),
                textureAtlas.findRegion("Actor2-1,0"),
                textureAtlas.findRegion("Actor2-0,0"),
                textureAtlas.findRegion("Actor2-1,0"));
        Animation animation_leftWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Actor2-2,1"),
                textureAtlas.findRegion("Actor2-1,1"),
                textureAtlas.findRegion("Actor2-0,1"),
                textureAtlas.findRegion("Actor2-1,1"));
        Animation animation_rightWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Actor2-2,2"),
                textureAtlas.findRegion("Actor2-1,2"),
                textureAtlas.findRegion("Actor2-0,2"),
                textureAtlas.findRegion("Actor2-1,2"));

        addAnimation(animation_upwardWalk);
        addAnimation(animation_downwardWalk);
        addAnimation(animation_rightWalk);
        addAnimation(animation_leftWalk);

        //pour eviter de jouer une fois l'animation au début
        m_elapsedTime = getCurrentAnimation().getAnimationDuration();


        scale(3f);
    }

    public void updateBehavior()
    {
        boolean up, left, right, down;
        up = Gdx.input.isKeyPressed(Input.Keys.Z);
        down = Gdx.input.isKeyPressed(Input.Keys.S);
        left = Gdx.input.isKeyPressed(Input.Keys.Q);
        right = Gdx.input.isKeyPressed(Input.Keys.D);

        //on neutralise les directions opposées
        if(up && down)
        {
            up = false;
            down = false;
        }
        if(right && left)
        {
            right = false;
            left = false;
        }

        if(up)
        {
            if(right)
                move(2.36068f,2.36068f );
            else if(left)
                move(-2.36068f,2.36068f );
            else
                move(0,5 );

            setAnimationIndex(0);
            getCurrentAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }
        if(down)
        {
            if(right)
                move(2.36068f,-2.36068f );
            else if(left)
                move(-2.36068f,-2.36068f );
            else
                move(0,-5 );
            setAnimationIndex(1);
            getCurrentAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }
        if(left && !up && !down)
        {
            move(-5,0 );
            setAnimationIndex(3);
            getCurrentAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }
        if(right && !up && !down)
        {
            move(5,0 );
            setAnimationIndex(2);
            getCurrentAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }
        if(!up && !down && !right && !left)
        {
            getCurrentAnimation().setPlayMode(Animation.PlayMode.NORMAL);
        }


        m_camera.position.set(getX()  + 48, getY()  + 48, 0);
    }
}
