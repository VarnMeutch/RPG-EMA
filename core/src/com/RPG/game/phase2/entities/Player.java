package com.RPG.game.phase2.entities;

import com.RPG.game.common.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class Player extends Entity
{
    //sert à actualiser la positon de la camera pour qu'elle suive le joueur
    private OrthographicCamera m_camera;
    private float m_timeLastSpell;

    public Player(ArrayList<Entity> entitiesList, AssetManager assetManager, OrthographicCamera travellingCamera)
    {
        super(entitiesList, assetManager);
        m_camera = travellingCamera;
        setZ(1f);
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/SpriteSheets/spriteSheet.atlas"));
        Animation animation_upwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-4,1"),
                textureAtlas.findRegion("Perso-4,2"),
                textureAtlas.findRegion("Perso-4,3"),
                textureAtlas.findRegion("Perso-4,0"));
        Animation animation_downwardWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-1,2"),
                textureAtlas.findRegion("Perso-1,0"),
                textureAtlas.findRegion("Perso-1,1"),
                textureAtlas.findRegion("Perso-1,0"));
        Animation animation_leftWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-2,1"),
                textureAtlas.findRegion("Perso-2,2"),
                textureAtlas.findRegion("Perso-2,3"),
                textureAtlas.findRegion("Perso-2,0"));
        Animation animation_rightWalk = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("Perso-3,1"),
                textureAtlas.findRegion("Perso-3,2"),
                textureAtlas.findRegion("Perso-3,3"),
                textureAtlas.findRegion("Perso-3,0"));

        addAnimation(animation_upwardWalk);
        addAnimation(animation_downwardWalk);
        addAnimation(animation_rightWalk);
        addAnimation(animation_leftWalk);

        m_originX = textureAtlas.findRegion("Perso-4,1").getRegionWidth()/2;
        m_originY = textureAtlas.findRegion("Perso-4,1").getRegionHeight()/2;


        //pour eviter de jouer une fois l'animation au début
        m_elapsedTime = getCurrentAnimation().getAnimationDuration();
        m_timeLastSpell = -0.2f;


        scale(2f);
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
                move(3.5355339f,3.5355339f );
            else if(left)
                move(-3.5355339f,3.5355339f );
            else
                move(0,5 );

            setAnimationIndex(0);
            getCurrentAnimation().setPlayMode(Animation.PlayMode.LOOP);
        }
        if(down)
        {
            if(right)
                move(3.5355339f,-3.5355339f );
            else if(left)
                move(-3.5355339f,-3.5355339f );
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

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && m_timeLastSpell + 0.4f<m_elapsedTime )
        {
            m_timeLastSpell = m_elapsedTime;
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            m_camera.unproject(mousePos);
            //Vector3 clickPos = new Vector3();
            float speed = 12f;
            float lifespan = (float) Math.sqrt(Math.pow((float)mousePos.y - getY(),2)+
                    Math.pow((float)mousePos.x - getX(),2))*(1/(speed*60));
            Projectile fireBall = new Projectile(m_entitiesList, m_assetManager,
                    0.5f*(float)Math.PI-(float)Math.atan2((float)mousePos.x - getX(), (float)mousePos.y - getY()),
                    speed, lifespan, 20);
            fireBall.setPosition(getX(), getY());
            m_entitiesList.add(fireBall);

        }


        m_camera.position.set(getX()  + 48, getY()  + 48, 0);
    }
}
