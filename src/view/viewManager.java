package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.SHIP;
import model.infoLabel;
import model.shipPicker;
import model.spaceRunnerButton;
import model.spaceRunnerSubScene;

public class viewManager {
    private static final int height = 678;
    private static final int width = 944;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private gameViewManager gameStage;
    
    private static final int menu_button_startx = 100;
    private static final int menu_button_starty = 150;
    private static final int  logo_x = 370;
    private static final int  logo_y = 45;
    List<spaceRunnerButton> menuButtons;
    List<shipPicker> shipList;
    private SHIP choosenShip;
    private spaceRunnerSubScene creditSubScene,shipChooserSubScene,scoreSubScene,helpSubScene,sceneToHide;
    public viewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,width,height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createbackground();
        creatButton();
        createLogo();
    }


    private void showSubScene(spaceRunnerSubScene temp){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }
        temp.moveSubScene();
        sceneToHide= temp;
    }
    private void createSubScenes(){
        creditSubScene = new spaceRunnerSubScene();
        mainPane.getChildren().add(creditSubScene);

        helpSubScene = new spaceRunnerSubScene();
        mainPane.getChildren().add(helpSubScene);
        
        scoreSubScene = new spaceRunnerSubScene();
        mainPane.getChildren().add(scoreSubScene);
        createShipChooserSubScene();

    } 

    public void createShipChooserSubScene(){
        shipChooserSubScene = new spaceRunnerSubScene();
        mainPane.getChildren().add(shipChooserSubScene);
        infoLabel chooseShipLabel = new infoLabel("Choose Your Ship");
        chooseShipLabel.setLayoutX(0);
        chooseShipLabel.setLayoutY(-150);
        shipChooserSubScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserSubScene.getPane().getChildren().add(createShipsToChoose());
        shipChooserSubScene.getPane().getChildren().add(createStartButtonToPlay());
    }

    public HBox createShipsToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipList = new ArrayList<shipPicker>();
        for(SHIP ship:SHIP.values()){
            shipPicker ship_to_pick = new shipPicker(ship);
            box.getChildren().add(ship_to_pick);
            ship_to_pick.setOnMouseClicked((MouseEvent e)->{
                for(shipPicker ship1 :shipList){
                    ship1.setCircleChoosen(false);
                }
                ship_to_pick.setCircleChoosen(true);
                this.choosenShip = ship_to_pick.getShip();
            });
            shipList.add(ship_to_pick);
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }

    private spaceRunnerButton createStartButtonToPlay(){
        spaceRunnerButton start = new spaceRunnerButton("START");
        start.setLayoutX(350);
        start.setLayoutY(300);
        start.setOnAction((ActionEvent e)->{
            if(choosenShip != null){
            gameStage = new gameViewManager();
            gameStage.createNewGame(mainStage, choosenShip);}
        });
        return start;
    }
    public Stage getMainStage(){
        return mainStage;
    }

    private void creatButton(){
        
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
        

    }
    private void createbackground(){
            Image backgroundImage = new Image("view/resources/blue.png",256,256,false,true);
            BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
            mainPane.setBackground(new Background(background));
    }

    private void addMenuButton(spaceRunnerButton button){
        button.setLayoutX(menu_button_startx);
        button.setLayoutY(menu_button_starty + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }


    private void createStartButton(){
        spaceRunnerButton startButton = new spaceRunnerButton("PLAY");
        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e) {
                showSubScene(shipChooserSubScene);
            }});
    }

    private void createScoresButton(){
        spaceRunnerButton scoresButton = new spaceRunnerButton("SCORES");
        addMenuButton(scoresButton);
        scoresButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e) {
                showSubScene(scoreSubScene);
            }});
    }


    private void createHelpButton(){
        spaceRunnerButton helpButton = new spaceRunnerButton("HELP");
        addMenuButton(helpButton);
        helpButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e) {
                showSubScene(helpSubScene);
            }});
    }

    private void createCreditsButton(){
        spaceRunnerButton creditsButton = new spaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);
        creditsButton.setOnAction((ActionEvent e)->{
            showSubScene(creditSubScene);
        });
    }

    private void createExitButton(){
        spaceRunnerButton exitButton = new spaceRunnerButton("EXIT");
        addMenuButton(exitButton);
        exitButton.setOnAction((ActionEvent e)->{
            mainStage.close();
        });
    }

    private void createLogo(){ImageView logo;
        logo = new ImageView("view/resources/logo.png");
        logo.setLayoutX(logo_x);
        logo.setLayoutY(logo_y);
        mainPane.getChildren().add(logo);


        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
    }
}

