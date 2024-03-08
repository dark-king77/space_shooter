package model;

import java.io.FileInputStream;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class spaceRunnerButton extends Button{
    private final String font_path = "model/resources/kenvector_future.ttf";
    private final String button_pressed_style = "-fx-background-colour: transparent; -fx-background-image: url('/model/resources/yellow_button_pressed.png');";
    private final String button_free_style = "-fx-background-colour: transparent; -fx-background-image: url('/model/resources/yellow_button.png');";


    public spaceRunnerButton(String text){
        setText(text);
        setbuttonfont();
        setPrefHeight(49);
        setPrefWidth(190);
        setStyle(button_free_style);
        initializebuttonlistener();
    }

    private void setbuttonfont(){
        try {
            setFont(Font.loadFont(new FileInputStream(font_path),23));
        } catch (Exception e) {
            setFont(Font.font("Verdana",23));
        }
    }

    private void setButtonPressedStyle(){
        setStyle(button_pressed_style);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }

    private void setButtonFreeStyle(){
        setStyle(button_free_style);
        setPrefHeight(49);
        setLayoutY(getLayoutY()-4); 
    }

    private void initializebuttonlistener(){
        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
              if(event.getButton().equals(MouseButton.PRIMARY)){
                setButtonPressedStyle();
              }
            }
            
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
              if(event.getButton().equals(MouseButton.PRIMARY)){
                setButtonFreeStyle();
              }
            }
            
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
            
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
            
        });
    }
}
