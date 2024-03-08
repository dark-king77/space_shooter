package model;

import java.io.File;
import java.io.FileInputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class smallInfoLabel extends Label{
    private final String font_path = "src/model/resources/kenvector_future.ttf";

    public smallInfoLabel(String text){
        setPrefHeight(50);
        setPrefWidth(130);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("view/resources/blue_info_label.png",130,50,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10,10,10,10));
        setLabelFont();
        setText(text);
    }

    private void setLabelFont(){
        try{
        setFont(Font.loadFont(new FileInputStream(new File(font_path)), 15));
    }
    catch(Exception e){
        setFont(Font.loadFont("verdana",15));
    }
}
}