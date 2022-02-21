package com.target.clicker.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.target.clicker.TargetClicker;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.samples = 4;
		config.resizable=false;
		config.width = 1280;
		config.height = 720;

		new World(new Vector2(0f,0f), false);
		new LwjglApplication(new TargetClicker(), config);
	}
}
