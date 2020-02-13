package com.target.clicker.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

/***
 * This simply makes the program's menus easier to create.
 */

public class MenuLabel extends Label {
    public MenuLabel(CharSequence text, Skin skin, float gridSize, float componentNumberVertical, float componentNumberHorizontal) {
        super(text, skin);

        this.setAlignment(Align.left);
        this.setY((Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*((componentNumberVertical)/gridSize) )));
        this.setHeight(30);
        this.setX((Gdx.graphics.getWidth()*(componentNumberHorizontal/3))-200);
        this.setFontScale(1.2f);
        this.setWidth(Gdx.graphics.getWidth()/3);
    }
}
