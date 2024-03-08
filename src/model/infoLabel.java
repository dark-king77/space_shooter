package model;

import java.io.File;
import java.io.FileInputStream;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class infoLabel extends Label{
    public final static String Font_path = "src/model/resources/kenvector_future.ttf";

    public final static String background = "view/resources/yellow_small_pane.png";

    public infoLabel(String text){
        setPrefHeight(400);
        setPrefWidth(600);
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);
        BackgroundImage backGroundImage= new BackgroundImage(new Image(background,380,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, null);
        setBackground(new Background(backGroundImage));

    }
    private void setLabelFont(){
        try{
        setFont(Font.loadFont(new FileInputStream(new File(Font_path)),23));
        }
        catch(Exception e){
            setFont(Font.font("verdana",23));
        }
    }
}
