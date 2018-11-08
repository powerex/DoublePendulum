import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Simulation extends Application {
//    Controller controller = new Controller();

    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/form.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

/*
    @Override
    public void stop() {
        if (controller.drawer != null) {
            controller.drawer.interrupt();
        }
    }

*/
    public static void main(String[] args) {
        launch(args);
    }
}
