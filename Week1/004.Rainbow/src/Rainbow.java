import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Rainbow extends Application {

    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(1920, 1000);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Rainbow");
        primaryStage.show();
    }

    public void draw(FXGraphics2D graphics) {
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale(-1, -1);
        double radiusBinnen = 10;
        double radiusBuiten = 40;
        double resolution = 0.01;
        float scale = 10;

        for (double j = 0; j < Math.PI; j+=resolution) {
            float x1 = (float) (radiusBinnen * Math.cos(j));
            float y1 = (float) (radiusBinnen * Math.sin(j));
            float x2 = (float) (radiusBuiten * Math.cos(j));
            float y2 = (float) (radiusBuiten * Math.sin(j));
            for(int i = 0; i < 1000; i++) {
                graphics.setColor(Color.getHSBColor((float) (j/Math.PI), 1, 1));
                graphics.draw(new Line2D.Float(x1*scale, y1*scale, x2*scale, y2*scale));
            }
        }
    }

    public static void main(String[] args) {
        launch(Rainbow.class);
    }
}

