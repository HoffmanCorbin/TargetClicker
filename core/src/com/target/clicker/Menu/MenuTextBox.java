package com.target.clicker.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;

public class MenuTextBox extends TextArea {
    public MenuTextBox(String text, Skin skin, float gridSize, float componentNumberVertical, float componentNumberHorizontal) {
        super(text, skin);
        this.setAlignment(Align.center);
        this.setY((Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * ((componentNumberVertical) / gridSize))));
        this.setHeight(30);
        this.setX((Gdx.graphics.getWidth() * (componentNumberHorizontal / 3)) - 200);
        this.setWidth(Gdx.graphics.getWidth() / 8);
    }
}
