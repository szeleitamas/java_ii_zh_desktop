package grafikus;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ParhuzamosController implements Initializable{
    @FXML
    Label mlabel1;
    @FXML
    Label mlabel2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClock1();
        initClock2();
    }
    protected void initClock1() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            mlabel1.setLayoutX(mlabel1.getLayoutX() + 4);
            if(mlabel1.getLayoutX() == 600) {
                mlabel1.setLayoutX(0);
            }
        }), new KeyFrame(Duration.millis(50)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    protected void initClock2() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            mlabel2.setLayoutX(mlabel2.getLayoutX() - 10);
            if(mlabel2.getLayoutX() == 0) {
                mlabel2.setLayoutX(600);
            }
        }), new KeyFrame(Duration.millis(300)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
