package com.RPG.game.phase1.screens;

import com.RPG.game.RPGMain;
import com.RPG.game.common.Entity;
import com.RPG.game.dialogs.DialogHandler;
import com.RPG.game.phase1.entities.PlayerRPG;
import com.RPG.game.phase2.entities.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;


public class
PhaseOneScreen implements Screen {

    // --- ATTRIBUTES --------------------------------------------------------------------------------------------------
    private RPGMain game;
    SpriteBatch batch;
    BitmapFont font;
    private DialogHandler diag;
    private Stage stage;
    Texture m_imgCharacter;
    OrthographicCamera camera;
    TiledMap map;
    float unitScale;
    OrthogonalTiledMapRenderer renderer;
    private PlayerRPG m_player;
    private AssetManager m_assetManager;
    private ArrayList<Entity> m_entitiesList;
    //private Table root;
    //private DialogueBox dialogueBox;
    public static final String PATH_SPRITESHEET = "core/assets/Sprite/SpriteSheets/spriteSheet.atlas";

    // --- CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public PhaseOneScreen(RPGMain game) {
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
        m_imgCharacter = new Texture("core/assets/Sprite/test-sprites/npc_darkguy.png");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Skin skin = new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json"));
        diag=new DialogHandler(skin);
        float unitScale = 1f;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        m_assetManager = new AssetManager();
        //on ajoute les assets à charger sur la file de l'assetManager
        m_assetManager.load(PATH_SPRITESHEET, TextureAtlas.class);

        //on fait charger tout les assets
        m_assetManager.finishLoading();
        m_entitiesList=new ArrayList<Entity>();
        m_player = new PlayerRPG(m_entitiesList, m_assetManager);
        m_player.setCamera(camera);
        m_player.setGridPosition(20, 20);
        TmxMapLoader tmx = new TmxMapLoader();
        map = tmx.load("core/assets/Maps/EMA_RPG_MAP.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        //initUI();
    }

    // --- METHODS -----------------------------------------------------------------------------------------------------
    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        m_player.updateBehavior();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderer.setView(camera);
        renderer.render();
        batch.begin();
        m_player.draw(batch);
        //diag.activate();
        //diag.chooseFile("Peter");
        //System.out.println("test");
        //diag.test();
        batch.end();

        //stage.act(delta);
        }

        /*public void initUI(){
            Skin skin = new Skin(Gdx.files.internal("core/assets/Skin/glassy/glassy-ui.json"));

            stage = new Stage();
            root = new Table();
            root.setFillParent(true);
            stage.addActor(root);

            dialogueBox = new DialogueBox(skin);
            dialogueBox.animateText("Bienvenu aventurier!\nTu es arrivé à l'EMA");

            root.add(dialogueBox).expand().align(Align.bottom).pad(8f);
        }*/


    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
            batch.dispose();
            m_imgCharacter.dispose();
        }
    }
