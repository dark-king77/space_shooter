package model;

public enum SHIP {
    BLUE("view/resources/shipchooser/playerShip_blue.png","view/resources/shipchooser/blue_life.png"),
    GREEN("view/resources/shipchooser/playerShip_green.png","view/resources/shipchooser/green_life.png"),
    ORANGE("view/resources/shipchooser/playerShip_orange.png","view/resources/shipchooser/orange_life.png"),
    RED("view/resources/shipchooser/playerShip_red.png","view/resources/shipchooser/red_life.png");


    String urlship,urllife;
    private SHIP(String urlship,String urllife){
        this.urlship = urlship;
        this.urllife = urllife;
    }

    public String geturl(){
        return this.urlship;
    }

    public String geturllife(){
        return this.urllife;
    }
}
