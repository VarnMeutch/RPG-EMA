package com.RPG.game.phase2.entities;

import com.RPG.game.common.Entity;
import com.RPG.game.common.hitbox.HitBox;
import com.RPG.game.common.hitbox.RectHitBox;
import com.RPG.game.phase2.ActionHUD;
import com.RPG.game.phase2.entities.projectile.FireBall;
import com.RPG.game.phase2.entities.projectile.FireBolt;
import com.RPG.game.phase2.entities.projectile.Projectile;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
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
    private long m_frameLastSpell;
    private long m_frameCount;
    private HitBox m_hitBox;
    private long m_frameEndIntangibility;
    private int m_health;
    private ActionHUD m_hud;


    public Player(ArrayList<Entity> entitiesList, AssetManager assetManager, OrthographicCamera travellingCamera)
    {
        super(entitiesList, assetManager);
        m_camera = travellingCamera;
        setZ(1f);
        //TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Sprite/SpriteSheets/spriteSheet.atlas"));
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
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

        m_originX = textureAtlas.findRegion("Perso-4,1").getRegionWidth()/2f;
        m_originY = textureAtlas.findRegion("Perso-4,1").getRegionHeight()/2f;
        m_hitBox = new RectHitBox(32,72, -16, -64);
        //height = 128 si on cveut inculre la tête dans la hitbox

        //pour eviter de jouer une fois l'animation au début
        m_elapsedTime = getCurrentAnimation().getAnimationDuration();
        m_frameLastSpell = -1000;
        scale(2f);
        m_frameCount=0;
        m_frameEndIntangibility=0;
        m_health=10;
        m_hud=null;
    }

    public void updateBehavior()
    {
        m_frameCount+=1;
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

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && m_frameCount >  m_frameLastSpell + 24 )
        {
            m_frameLastSpell = m_frameCount;
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            m_camera.unproject(mousePos);
            //Vector3 clickPos = new Vector3();
            float speed = 12f;
            float lifespan = (float) Math.sqrt(Math.pow(mousePos.y - getY(),2)+
                    Math.pow(mousePos.x - getX(),2))*(1/(speed*60));
            FireBall fireBall = new FireBall(m_entitiesList, m_assetManager,
                    0.5f*(float)Math.PI-(float)Math.atan2(mousePos.x - getX(), mousePos.y - getY()),
                    speed, lifespan);
            fireBall.setPosition(getX(), getY());
            m_entitiesList.add(fireBall);

        }

        for(Entity e : m_entitiesList)
        {
            if(e instanceof FireBolt)
            {
                if(((FireBolt) e).testHit(m_hitBox, getX(), getY()))
                {
                    e.destroy();
                    if(m_frameCount > m_frameEndIntangibility)
                    {
                        m_frameEndIntangibility = m_frameCount + 60;
                        m_health-=1;
                    }
                }
            }
        }

        if(m_frameCount < m_frameEndIntangibility)
            setColor(new Color(1,0,0,1));
        else
            setColor(Color.WHITE);

        m_camera.position.set(getX()  + 48, getY()  + 48, 0);
        if(m_hud != null) m_hud.update(m_health);

    }

    public boolean testCollision(HitBox hitBox, float x, float y)
    {
        return m_hitBox.testCollision(hitBox, getX(), getY(), x, y);
    }

    public void drawHitBox(OrthographicCamera camera)
    {
        m_hitBox.drawHitBox(getX(), getY(), new Color(0f,1,0,0.5f), camera);
    }

    public void setHUD(ActionHUD hud) {m_hud = hud;}
}
