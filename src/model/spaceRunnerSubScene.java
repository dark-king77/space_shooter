package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class spaceRunnerSubScene extends SubScene{
    private boolean isHidden;
    private final String background_image = "/model/resources/yellow_panel.png";
    public spaceRunnerSubScene(){
        super(new AnchorPane(),600,400);
        prefWidth(600);
        prefHeight(400);
        BackgroundImage image = new BackgroundImage(new Image(background_image,600,400,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);
        AnchorPane root = (AnchorPane)this.getRoot();
        root.setBackground(new Background(image));
        setLayoutX(1024);
        setLayoutY(180);
        isHidden = true;
    }


    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden){
        transition.setToX(-696);
        isHidden = false;
    }
        else{
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane)this.getRoot();
    }
}
