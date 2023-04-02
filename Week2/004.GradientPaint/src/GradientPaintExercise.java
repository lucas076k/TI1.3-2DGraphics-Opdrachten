import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class GradientPaintExercise extends Application {
    private ResizableCanvas canvas;
    private Point2D center;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        center = new Point2D.Double(canvas.getWidth() / 2, canvas.getHeight() / 2);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();

        canvas.setOnMouseDragged(e -> {
            center = new Point2D.Double(e.getX(), e.getY());
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        });

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());


        Color colors[] = new Color[]{
                Color.black, Color.white, Color.yellow
        };

        float fractions[] = new float[]{
                0.0f, 0.5f, 1.0f
        };

        RadialGradientPaint paint = new RadialGradientPaint(center, 160f, fractions, colors);
        graphics.setPaint(paint);
        Rectangle2D rect = new Rectangle2D.Double(0, 0, canvas.getWidth(), canvas.getHeight());
        graphics.fill(rect);

    }


    public static void main(String[] args) {
        launch(GradientPaintExercise.class);
    }

}