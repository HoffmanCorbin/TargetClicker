package com.target.clicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.target.clicker.Menu.MainMenu;

public class TargetClicker extends Game {
	SpriteBatch batch;
	Texture img;
	static public Skin gameSkin;

	@Override
	public void create () {


		//gameSkin =  new Skin(Gdx.files.internal("default/skin/uiskin.json"));
		gameSkin = new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
		this.setScreen(new MainMenu(this,gameSkin));

		GdxNativesLoader.load();
		//Gdx.graphics.setWindowedMode(1280,720);

		//this.setScreen(new TestGame());

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

	}
}
