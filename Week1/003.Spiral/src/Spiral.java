import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spiral extends Application {
    private Canvas canvas;
    private double n;
    private double r;
    private double teta;
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {

        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
        graphics.scale(1,-1);
        
        n = 1;
        r = 40;
        teta = n * r;
        x1 = r * Math.cos(teta);
        y1 = r * Math.sin(teta);


        double resolution = 0.001;
        double scale = 10.0;

        for (double i = 0; i < 20; i += resolution) {
            x1 = i * Math.cos(i);
            y1 = i * Math.sin(i);
            x2 = i * Math.cos(i + resolution);
            y2 = i * Math.sin(i + resolution);
            graphics.draw(new Line2D.Double(x1 * scale, y1 * scale, x2 * scale, y2 * scale));
        }
    }


    public static void main(String[] args) {
        launch(Spiral.class);
    }

}
