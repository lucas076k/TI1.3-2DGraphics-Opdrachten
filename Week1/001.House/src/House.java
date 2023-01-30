import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;

public class House extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.draw(new Line2D.Double(200, 600, 200, 900));
        graphics.draw(new Line2D.Double(200, 900, 500, 900));
        graphics.draw(new Line2D.Double(500, 600, 500, 900));

        graphics.draw(new Line2D.Double(200, 600, 350, 400));
        graphics.draw(new Line2D.Double(350, 400, 500, 600));

        graphics.draw(new Line2D.Double(230, 900, 230, 780));
        graphics.draw(new Line2D.Double(230, 780, 300, 780));
        graphics.draw(new Line2D.Double(300, 900, 300, 780));

        graphics.draw(new Line2D.Double(350, 760, 350, 840));
        graphics.draw(new Line2D.Double(350, 760, 450, 760));
        graphics.draw(new Line2D.Double(450, 760, 450, 840));
        graphics.draw(new Line2D.Double(350, 840, 450, 840));

    }



    public static void main(String[] args) {
        launch(House.class);
    }

}