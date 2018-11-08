import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    double w = 600, h = 600;
    GraphicsContext gc;
    public static Thread drawer = null;

    DoublePendulum doublePendulum;

    @FXML
    Canvas canvas;

    @FXML
    Button start;

    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(100,100,100));
        gc.fillRect(0, 0, w, h);

        doublePendulum = new DoublePendulum(gc);
        drawer = doublePendulum.getT();
    }

    public void start(ActionEvent actionEvent) {

        doublePendulum.draw();

    }
}
