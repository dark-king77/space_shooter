package view;


import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SHIP;
import model.smallInfoLabel;
import model.spaceRunnerButton;

public class gameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int height = 800;
    private static final int width = 600;

    private final static String background_path = "view/resources/blue.png";

    private final String meteor_brown_image = "view/resources/meteor_brown.png";
    private final String meteor_grey_image = "view/resources/meteor_grey.png";

    private final static String blast_stage1 = "view/resources/shipBlastEffect/blast_stage1.png";
    private final static String blast_stage2 = "view/resources/shipBlastEffect/blast_stage2.png";
    private final static String blast_stage3 = "view/resources/shipBlastEffect/blast_stage3.png";
    private  static int[] blast_stages_brown;
    private  static int[] blast_stages_grey;
    private ImageView[] brown_meteors;
    private ImageView[] grey_meteors;
    Random RandomPositionGenerater;

    private ImageView star;
    private ImageView ammo;
    private smallInfoLabel pointsLabel;
    private smallInfoLabel bulletsLabel;
    private ImageView[] playeLifes;
    private int playerLife;
    private int Points;
    private final static String gold_star_path = "view/resources/star_gold.png";
    private final static String ammo_path = "view/resources/ammo.png";

    private final static int star_radius = 12;
    private final static int ship_radius = 27;
    private final static int meteor_radius = 20;
    private final static int laser_radius = 4;
    private final static int ammo_radius = 25;

    private GridPane gridpane1;
    private GridPane gridpane2;

    private Stage menuStage;

    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angel;
    private AnimationTimer gametimer;

    private static String laser_path = "view/resources/laser.png"; 
    private ArrayList<ImageView> laser = new ArrayList<ImageView>();
    private int bullets = 100;

    gameViewManager(){
        initializeStage();
        createKeyListener();
        RandomPositionGenerater = new Random();
    }

    private void createKeyListener() {
        gameScene.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = true;
            }
            else if(e.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = true;
            }
            if(e.getCode() == KeyCode.SPACE && bullets > 0){
                ImageView laser_image=new ImageView(new Image(laser_path,9,30,false,true));
                laser_image.setLayoutX(ship.getLayoutX()+45);
                laser_image.setLayoutY(ship.getLayoutY()-25);
                laser.add(laser_image);
                gamePane.getChildren().add(laser_image);
                bullets -= 2;
                bulletsLabel.setText("AMMO : "+bullets);
            }
        });
        gameScene.setOnKeyReleased((KeyEvent e)->{
            if(e.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = false;
            }
            else if(e.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = false;
            }
        });

    }

    private void initializeStage(){
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,width,height);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }


    public void createNewGame(Stage mainStage,SHIP choosenship){
        this.menuStage = mainStage;
        this.menuStage.close();
        gameStage.show();
        createBackground();
        createShip(choosenship);
        createGameLoop();
        createGameElements(choosenship);
    }

    private void createGameElements(SHIP ship){
        this.playerLife = 2;

        this.star = new ImageView(gold_star_path);
        setNewElementPosition(star);
        gamePane.getChildren().add(star);

        ammo = new ImageView(new Image(ammo_path,35,33,false,true));
        setNewElementPosition(ammo);
        gamePane.getChildren().add(ammo);

        pointsLabel = new smallInfoLabel("POINTS : 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);
        bulletsLabel = new smallInfoLabel("AMMO : 100");
        bulletsLabel.setLayoutX(20);
        bulletsLabel.setLayoutY(20);
        gamePane.getChildren().add(bulletsLabel);
        playeLifes = new ImageView[3];

        for(int i = 0;i < playeLifes.length;i++){
            playeLifes[i] = new ImageView(ship.geturllife());
            playeLifes[i].setLayoutX(455 + (i*50));
            playeLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playeLifes[i]);
        }

        brown_meteors = new ImageView[6];
        blast_stages_brown = new int[6];
        for(int i =0 ;i < brown_meteors.length;i++){
            brown_meteors[i] = new ImageView(meteor_brown_image);
            setNewElementPosition(brown_meteors[i]);
            gamePane.getChildren().add(brown_meteors[i]);
        }

        grey_meteors = new ImageView[6];
        blast_stages_grey = new int[6];
        for(int i =0 ;i < grey_meteors.length;i++){
            grey_meteors[i] = new ImageView(meteor_grey_image);
            setNewElementPosition(grey_meteors[i]);
            gamePane.getChildren().add(grey_meteors[i]);
        }
    }

    private void moveGameElements(){
        star.setLayoutY(star.getLayoutY()+4);
        if(star.getLayoutY()>1024){
                setNewElementPosition(star);
            }

        ammo.setLayoutY(ammo.getLayoutY()+4);
        if(ammo.getLayoutY()>1024){
                setNewElementPosition(ammo);
            }
        
        for(int i= 0;i<brown_meteors.length;i++){
            brown_meteors[i].setLayoutY(brown_meteors[i].getLayoutY()+3);
            brown_meteors[i].setRotate(brown_meteors[i].getRotate()+2);
            if(brown_meteors[i].getLayoutY()>1024){
                setNewElementPosition(brown_meteors[i]);
            }
        }

        for(int i= 0;i<grey_meteors.length;i++){
            grey_meteors[i].setLayoutY(grey_meteors[i].getLayoutY()+3);
            grey_meteors[i].setRotate(grey_meteors[i].getRotate()+2);
            if(grey_meteors[i].getLayoutY()>1024){
                setNewElementPosition(grey_meteors[i]);
            }
        }

        ArrayList<ImageView> temp = (ArrayList<ImageView>) laser.clone();
        for(ImageView i :temp){
            i.setLayoutY(i.getLayoutY()-6);
            if(i.getLayoutY()<0){
                gamePane.getChildren().remove(i);
                laser.remove(i);
            }
        }
    }

    private void setNewElementPosition(ImageView image){
        image.setLayoutX(RandomPositionGenerater.nextInt(522));
        image.setLayoutY(-(RandomPositionGenerater.nextInt(3200)+600));
    }

    private void createShip(SHIP choosenShip){
        ship = new ImageView(choosenShip.geturl());
        ship.setLayoutX(width/2);
        ship.setLayoutY(height-90);
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop(){
        gametimer = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                moveShip();
                movebackGround();
                moveGameElements();
                checkIfElementsCollide();
                shipBlast();
            }
            
        };

        gametimer.start();
    }

    private void moveShip(){
        if(isLeftKeyPressed && !isRightKeyPressed){
            if(angel > -30){
                angel -= 5;
            }
            ship.setRotate(angel);
           if(ship.getLayoutX()>-20){
                ship.setLayoutX(ship.getLayoutX()-3);
            }
        }
        if(!isLeftKeyPressed && isRightKeyPressed){
            if(angel < 30){
                angel += 5;
            }
            ship.setRotate(angel);
           if(ship.getLayoutX()<522){
                ship.setLayoutX(ship.getLayoutX()+3);
            }
        }
        if(!isLeftKeyPressed && !isRightKeyPressed){
            if(angel < 0){
                angel += 5;
            }
            if(angel > 0){
                angel -= 5;
            }
            ship.setRotate(angel);
        }
        if(isLeftKeyPressed && isRightKeyPressed){
            if(angel < 0){
                angel += 5;
            }
            if(angel > 0){
                angel -= 5;
            }
            ship.setRotate(angel);
        }
    }

    private void createBackground(){
        gridpane1 = new GridPane();
        gridpane2 = new GridPane();
        for(int i = 0;i< 12;i++){
            ImageView backgroundImage1 = new ImageView(background_path);
            GridPane.setConstraints(backgroundImage1,i%3,i/3);
            ImageView backgroundImage2 = new ImageView(background_path);
            GridPane.setConstraints(backgroundImage2,i%3,i/3);
            gridpane1.getChildren().add(backgroundImage1);
            gridpane2.getChildren().add(backgroundImage2);
        }
        gridpane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridpane1,gridpane2);
    }

    private void movebackGround(){
        gridpane1.setLayoutY(gridpane1.getLayoutY()+0.5);
        gridpane2.setLayoutY(gridpane2.getLayoutY()+0.5);

        if(gridpane1.getLayoutY()>= 1024){
            gridpane1.setLayoutY(-1024);
        }

        if(gridpane2.getLayoutY()>= 1024){
            gridpane2.setLayoutY(-1024);
        }
    }

    private void checkIfElementsCollide(){
        if(ship_radius+star_radius > calculatDistance(ship.getLayoutX()+49,ship.getLayoutY()+37,star.getLayoutX()+15,star.getLayoutY()+15)){
            setNewElementPosition(star);
            this.Points ++;
            String textToSet = "POINTS : ";
            pointsLabel.setText(textToSet+Points);
        }

        if(ship_radius+ammo_radius > calculatDistance(ship.getLayoutX()+49,ship.getLayoutY()+37,ammo.getLayoutX()+18,ammo.getLayoutY()+17)){
            setNewElementPosition(ammo);
            this.bullets += 10;
            String textToSet = "AMMO : ";
            bulletsLabel.setText(textToSet+bullets);
        }

        for(int i=0; i< brown_meteors.length;i++){
            if(0 == blast_stages_brown[i]){
            if(meteor_radius+ship_radius > calculatDistance(ship.getLayoutX()+49,ship.getLayoutY()+37,brown_meteors[i].getLayoutX()+20,brown_meteors[i].getLayoutY()+20) && 0 == blast_stages_brown[i]){
                blast_stages_brown[i] = 1;
                removeLife();
            }
            ArrayList<ImageView> temp = (ArrayList<ImageView>) laser.clone();
            for(ImageView top_laser:temp){
                if(laser_radius+meteor_radius > calculatDistance(top_laser.getLayoutX()+5,top_laser.getLayoutY(),brown_meteors[i].getLayoutX()+20,brown_meteors[i].getLayoutY()+20)){
                    blast_stages_brown[i] = 1;
                    gamePane.getChildren().remove(top_laser);
                    laser.remove(top_laser);
                }
            }
        }
    }

        for(int i=0; i< grey_meteors.length;i++){
            if(0 == blast_stages_grey[i]){
            if(meteor_radius+ship_radius > calculatDistance(ship.getLayoutX()+49,ship.getLayoutY()+37,grey_meteors[i].getLayoutX()+20,grey_meteors[i].getLayoutY()+20)){
                blast_stages_grey[i] = 1;
                removeLife();
            }
            ArrayList<ImageView> temp = (ArrayList<ImageView>) laser.clone();
            for(ImageView top_laser:temp){
                if(laser_radius+meteor_radius > calculatDistance(top_laser.getLayoutX()+5,top_laser.getLayoutY(),grey_meteors[i].getLayoutX()+20,grey_meteors[i].getLayoutY()+20)){
                    blast_stages_grey[i] = 1;
                    gamePane.getChildren().remove(top_laser);
                    laser.remove(top_laser);
                }
            }
        }
    }

    }

    private void shipBlast(){

        for(int i = 0; i < blast_stages_brown.length;i++){
            switch(blast_stages_brown[i]){
                case 1:
                brown_meteors[i].setImage(new Image(blast_stage1,45,40,false,true));
                blast_stages_brown[i] = 2;
                break;
                case 2:
                brown_meteors[i].setImage(new Image(blast_stage2,45,40,false,true));
                blast_stages_brown[i] = 3;
                break;
                case 3:
                brown_meteors[i].setImage(new Image(blast_stage3,45,40,false,true));
                blast_stages_brown[i] = 4;
                break;
                case 4:
                brown_meteors[i].setImage(new Image(meteor_brown_image));
                blast_stages_brown[i] = 0;
                setNewElementPosition(brown_meteors[i]);
            }
        }
        for(int i = 0; i < blast_stages_grey.length;i++){
            switch(blast_stages_grey[i]){
                case 1:
                grey_meteors[i].setImage(new Image(blast_stage1,45,40,false,true));
                blast_stages_grey[i] = 2;
                break;
                case 2:
                grey_meteors[i].setImage(new Image(blast_stage2,45,40,false,true));
                blast_stages_grey[i] = 3;
                break;
                case 3:
                grey_meteors[i].setImage(new Image(blast_stage3,45,40,false,true));
                blast_stages_grey[i] = 4;
                break;
                case 4:
                grey_meteors[i].setImage(new Image(meteor_grey_image));
                blast_stages_grey[i] = 0;
                setNewElementPosition(grey_meteors[i]);
            }
        }
    }

    private void removeLife(){
        gamePane.getChildren().remove(playeLifes[playerLife]);
        playerLife--;
        if(playerLife < 0){
            
            smallInfoLabel gameover = new smallInfoLabel("GAME OVER");
            gameover.setLayoutX(250);
            gameover.setLayoutY(350);
            gamePane.getChildren().add(gameover);
            gametimer.stop();
            shipBlast();
            spaceRunnerButton CONTINUE = new spaceRunnerButton("CONTINUE");
            CONTINUE.setLayoutX(220);
            CONTINUE.setLayoutY(450);
            CONTINUE.setOnAction((ActionEvent e)->{
                gameStage.close();
                menuStage.show();
            });
            gamePane.getChildren().add(CONTINUE);

        }
    }

    private double calculatDistance(double x1,double y1,double x2,double y2){
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
}


