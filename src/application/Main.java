package application;
import javafx.application.Application;
import javafx.stage.Stage;
import view.viewManager;
public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        viewManager manager = new viewManager();
        primaryStage = manager.getMainStage();
        primaryStage.show();
    }
}
