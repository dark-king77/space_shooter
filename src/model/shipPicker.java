package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class shipPicker extends VBox {
    private ImageView circleImage;
    private ImageView shipImage;
    

    private String circleNotChoosen = "view/resources/shipchooser/grey_circle.png";
    private String circleChoosen = "view/resources/shipchooser/ship_choosen.png";

    private SHIP ship;

    private boolean isCircleChoosen;

    public shipPicker(SHIP ship){

        circleImage= new ImageView(circleNotChoosen);
        shipImage = new ImageView(ship.geturl());
        this.ship = ship;
        isCircleChoosen= false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);
    }


    public SHIP getShip(){
        return ship;
    }

    public boolean getIsCircleChoosen(){
        return isCircleChoosen;
    }

    public void setCircleChoosen(boolean isCircleChoosen){
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen:circleNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }


}
