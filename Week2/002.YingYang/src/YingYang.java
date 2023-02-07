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

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Ellipse2D bgBlack = new Ellipse2D.Double(100, 50, 400, 400);
        graphics.setColor(Color.black);
        graphics.fill(bgBlack);
        graphics.draw(bgBlack);

        Rectangle2D whiteRect = new Rectangle2D.Double(100, 50, 200, 500);
        graphics.setColor(Color.WHITE);
        graphics.fill(whiteRect);
        graphics.draw(whiteRect);

        Ellipse2D whiteUp = new Ellipse2D.Double(200, 50, 200, 200);
        graphics.setColor(Color.white);
        graphics.fill(whiteUp);
        graphics.draw(whiteUp);

        Ellipse2D downBlack = new Ellipse2D.Double(200, 253, 197, 197);
        graphics.setColor(Color.black);
        graphics.fill(downBlack);
        graphics.draw(downBlack);

        Ellipse2D bgOutline = new Ellipse2D.Double(100, 50, 400, 400);
        graphics.setColor(Color.black);
        graphics.setStroke(new BasicStroke(4)); //Changes (outer)line width
        graphics.draw(bgOutline);

        Ellipse2D blackDot = new Ellipse2D.Double(280, 130, 40, 40);
        graphics.setColor(Color.black);
        graphics.fill(blackDot);
        graphics.draw(blackDot);

        Ellipse2D whiteDot = new Ellipse2D.Double(280, 330, 40, 40);
        graphics.setColor(Color.white);
        graphics.fill(whiteDot);
        graphics.draw(whiteDot);
    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
