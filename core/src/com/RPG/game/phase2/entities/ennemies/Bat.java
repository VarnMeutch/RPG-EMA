package com.RPG.game.phase2.entities.ennemies;

import com.RPG.game.RPGMain;
import com.RPG.game.common.Entity;
import com.RPG.game.common.hitbox.HitBox;
import com.RPG.game.common.hitbox.RectHitBox;
import com.RPG.game.phase2.entities.Player;
import com.RPG.game.phase2.entities.projectile.FireBall;
import com.RPG.game.phase2.entities.projectile.FireBolt;
import com.RPG.game.phase2.screens.PhaseTwoScreen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Bat extends Entity
{
    private HitBox m_hitBox;
    private long m_frameLastAttack;

    private Player m_player;

    private long m_frameNextJump;
    private float m_jumpDirection;
    private float m_jumpSpeed;

    private int m_health;
    private boolean m_dying;
    private long m_frameEndHurt; //indique quand l'entité pourra de nouveau prendre des dégats
    private long m_frameEndDying; //indique quand l'entité serra détruite dans le cas où m_dying est vraie

    private final long m_attackRate = 100; //nombre de frame entre chaques attaques
    private final long m_hurtDuration = 20; //indique combien de temps dure l'état après une blessure
    private final int m_maxHealth = 3;
    private final long m_dyingDuration = 60;

    public Bat(float x, float y, ArrayList<Entity> entitiesList, AssetManager assetManager)
    {
        super(entitiesList, assetManager);
        setX(x);
        setY(y);
        setZ(2f);
        TextureAtlas textureAtlas = assetManager.get(PhaseTwoScreen.PATH_SPRITESHEET);
        Animation animation = new Animation<TextureRegion>(1/10f,
                textureAtlas.findRegion("OeilVolant1"),
                textureAtlas.findRegion("OeilVolant2"),
                textureAtlas.findRegion("OeilVolant3"),
                textureAtlas.findRegion("OeilVolant4"));
        animation.setPlayMode(Animation.PlayMode.LOOP);
        addAnimation(animation);
        setScale(2f);
        m_originX = 31;
        m_originY = 7;

        m_frameLastAttack = RPGMain.random.nextInt(30);
        m_frameEndHurt = -1;
        m_frameNextJump = 0;
        m_jumpDirection = 0;
        m_jumpSpeed = 4.5f;
        m_health = m_maxHealth;
        for(Entity e : m_entitiesList)
        {
            if(e instanceof Player)
            {
                //on enrgesitre le premier objet (et le seul) de type player trouvé comme la cible
                //il faut donc que Player soit ajouté à la liste avant
                m_player = (Player) e;
                break;
            }
        }

        m_hitBox = new RectHitBox(62*getScale(), 14*getScale(), -m_originX*getScale(), -m_originY*getScale());
    }

    @Override
    public void updateBehavior()
    {
        m_frameCount+=1;

        for (Entity e : m_entitiesList)
        {
            if(e instanceof FireBall && !m_dying)
            {
                if(((FireBall) e).testHit(m_hitBox, getX(), getY()) )
                {
                    //si la boule de feu est en train d'exploser, bat meurt, sinon prend 1 dégat
                    //on vérifie dans ce cas que la chauve souris n'a pas pris de dégat recemment
                    if(m_frameCount > m_frameEndHurt && !((FireBall) e).getExploding())
                    {

                        m_health-=1;
                        m_frameEndHurt =  m_frameCount + m_hurtDuration;
                        m_frameLastAttack += m_hurtDuration;

                        //après un coup, la chauve souris bouge immédiatement
                        m_frameNextJump = m_frameCount;
                        /*m_frameNextJump = m_frameCount + m_hurtDuration*2;
                        float player_direction = (float)Math.PI/2f-(float)Math.atan2(m_player.getX() - getX(), m_player.getY() - getY());
                        m_jumpDirection =  (float) (-player_direction + (float)RPGMain.random.nextGaussian()*Math.PI/3);*/

                    }
                    if(((FireBall) e).getExploding()) m_health = 0;

                }
            }
            //lorsque deux Bat entre en collision, elles s'éloigne imméditement l'une d'autre
            if(e instanceof Bat && e!=this)
            {
                if(m_hitBox.testCollision(((Bat) e).m_hitBox,getX(),getY(),e.getX(),e.getY()))
                {
                    m_frameNextJump+=(int)(RPGMain.random.nextGaussian()*2f + 10f );
                    float bat_direction = (float)Math.PI/2f-(float)Math.atan2(e.getX() - getX(), e.getY() - getY());
                    m_jumpDirection = (float) (-bat_direction + (float)RPGMain.random.nextGaussian()*Math.PI/6);

                }
            }

        }

        if(m_health <=0 && !m_dying)
        {
            m_dying = true;
            m_frameEndDying = m_frameCount + m_dyingDuration;
            getCurrentAnimation().setPlayMode(Animation.PlayMode.NORMAL);

        }
        if(m_dying)
        {
            if(m_frameCount > m_frameEndDying)
            {
                m_destroy = true;
            }
            else
            {
                m_elapsedTime = 0.1f;
                setColor(new Color(0.8f, 0.2f ,0.2f,0.2f + ((float)( m_frameEndDying -m_frameCount))/(float)(m_dyingDuration)));
            }
        }




        if(!m_dying )
        {
            if(m_frameCount <= m_frameEndHurt)
            {
                setColor(new Color(0.8f, 0.2f ,0.2f,1));
                m_jumpSpeed = 6f;
            }
            else
            {
                setColor(Color.WHITE);
                m_jumpSpeed = 4.5f;
            }


            if(m_frameCount > m_attackRate + m_frameLastAttack)
            {
                float direction=0.5f*(float)Math.PI-(float)Math.atan2(m_player.getX() - getX(), m_player.getY() - getY());
                direction += (RPGMain.random.nextGaussian() * Math.PI) / 18;
                FireBolt fireBolt = new FireBolt(m_entitiesList,m_assetManager, direction, 7, 300);
                fireBolt.setPosition(getX(), getY());
                m_entitiesList.add(fireBolt);
                m_frameLastAttack = m_frameCount;
            }

            if(m_frameCount > m_frameNextJump)
            {
                float player_distance  = (float)Math.sqrt(Math.pow(m_player.getY() - getY(),2)+Math.pow(m_player.getX()- getX(),2));
                float player_direction = (float)Math.PI/2f-(float)Math.atan2(m_player.getX() - getX(), m_player.getY() - getY());
                float sigma = (float)Math.PI;
                //sigma *= (float) Math.exp(-0.5f*Math.pow(player_distance-10f,2));
                if(player_distance < 200) sigma/=8;
                if(player_distance > 500) sigma/=8;
                float m = player_direction;
                if(player_distance < 200) m*=-1;
                //m_jumpDirection = (float)RPGMain.random.nextGaussian()*sigma + m;
                if(RPGMain.random.nextFloat() > 0.5)
                    m_jumpDirection = (float)RPGMain.random.nextGaussian()*sigma + (m-(float)Math.PI/4);
                else
                    m_jumpDirection = (float)RPGMain.random.nextGaussian()*sigma + (m+(float)Math.PI/4);
                m_frameNextJump+=(int)(RPGMain.random.nextGaussian()*2f + 10f );
            }

            move((float)(m_jumpSpeed*Math.cos(m_jumpDirection)), (float)(m_jumpSpeed*Math.sin(m_jumpDirection)));
        }


    }


    public void drawHitBox(OrthographicCamera camera)
    {
        m_hitBox.drawHitBox(getX(), getY(), new Color(1,0,0,0.5f), camera);
    }
}
