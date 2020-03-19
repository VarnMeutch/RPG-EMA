package com.rpgtest.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class rpgtest extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("on-sous-estime-souvent-le-pouvoir-d-un-sourire-surtout-sur-une-chevre_c6b01beb028d36fe15b28e1b68a1558ca9f912c0.jpg");
		System.out.println("Bonjour de Julien !");

		System.out.println("Bonjour de la chèvre !");

		System.out.println("Voici mon code");
		System.out.println("Un ligne...");
		System.out.println("Une autre...");
		System.out.println("Wow presque aussi bourré que le mec qui faisait les sous-titres");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		System.out.println("Ligne de test");
	}
}
