import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Mirror extends Application {
    ResizableCanvas canvas;
    private Button mirrorBtn;
    private boolean isMirrored = false;
    private FXGraphics2D graphics;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mirrorBtn = new Button("Mirror");

        HBox btnBox = new HBox(mirrorBtn);

        mainPane.setCenter(canvas);
        mainPane.setTop(btnBox);

        graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        mirrorBtn.setOnAction(event -> {
            isMirrored = !isMirrored;
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        });

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mirror");
        primaryStage.show();
    }

    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
        graphics.scale(1,-1);

        graphics.draw(new Line2D.Double(-canvas.getWidth()/2, 0, canvas.getWidth()/2, 0));
        graphics.draw(new Line2D.Double(0, -canvas.getHeight()/2, 0, canvas.getHeight()/2));

//        Shape square = new Rectangle2D.Double(-50,100,100,100);
//        double resolution = 0.1;
//        double a = 2.5;
//
//        for (double i = 0; i < 1000; i += resolution)
//        {
//            double x1 =  i;
//            double y1 =  a*x1;
//            double x2 =  i+ resolution;
//            double y2 =  a*x2;
//            graphics.draw(new Line2D.Double(x1, y1, x2, y2));
//        }

//        if (isMirrored) {
//            AffineTransform transformSquare = new AffineTransform();
//            transformSquare.translate(0, 150);
//            graphics.draw(transformSquare.createTransformedShape(square));
//
//            AffineTransform mirror = new AffineTransform();
//            mirror.concatenate(new AffineTransform((2 / (1 + a * a)) - 1, (2 * a) / (1 + a * a), (2 * a) / (1 + a * a), ((2 * a * a) / (1 + a * a)) - 1, 0, 0));
//            graphics.draw(mirror.createTransformedShape(transformSquare.createTransformedShape(square)));
//        } else {
//            graphics.draw(square);
//        }

        Line2D line = new Line2D.Double(-canvas.getWidth()/2, -canvas.getWidth()/2 * 2.5, canvas.getWidth()/2, canvas.getWidth()/2 * 2.5);
        graphics.draw(line);

        Rectangle2D square = new Rectangle2D.Double(-50, 100, 100, 100);
        if (isMirrored) {
            AffineTransform transform = new AffineTransform();
            //Fix code for mirroring square. Square is outside canvas.
            double theta = Math.atan2(line.getY2() - line.getY1(), line.getX2() - line.getX1());
            transform.translate(-line.getX1(), -line.getY1());
            transform.rotate(-theta);
            transform.scale(1, -1);
            transform.rotate(theta);
            transform.translate(line.getX1(), line.getY1());

            Shape transformedSquare = transform.createTransformedShape(square);
            graphics.draw(transformedSquare);
        } else {
            graphics.draw(square);
        }
        this.graphics = graphics;
    }

    public static void main(String[] args)
    {
        launch(Mirror.class);
    }
}