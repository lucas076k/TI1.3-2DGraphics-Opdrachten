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

public class Colors extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        int x = 0;

        for (int i = 0; i < 13; i++) {
            Rectangle2D newRect = new Rectangle2D.Double(x, 216, 49, 49);

            switch (i) {
                case 0:
                    graphics.setColor(Color.BLACK);
                    break;
                case 1:
                    graphics.setColor(Color.BLUE);
                    break;
                case 2:
                    graphics.setColor(Color.CYAN);
                    break;
                case 3:
                    graphics.setColor(Color.DARK_GRAY);
                    break;
                case 4:
                    graphics.setColor(Color.GRAY);
                    break;
                case 5:
                    graphics.setColor(Color.GREEN);
                    break;
                case 6:
                    graphics.setColor(Color.LIGHT_GRAY);
                    break;
                case 7:
                    graphics.setColor(Color.MAGENTA);
                    break;
                case 8:
                    graphics.setColor(Color.ORANGE);
                    break;
                case 9:
                    graphics.setColor(Color.PINK);
                    break;
                case 10:
                    graphics.setColor(Color.RED);
                    break;
                case 11:
                    graphics.setColor(Color.WHITE);
                    break;
                case 12:
                    graphics.setColor(Color.YELLOW);
                    break;
            }

            graphics.fill(newRect);
            graphics.draw(newRect);
            x += 50;
        }

    }


    public static void main(String[] args)
    {
        launch(Colors.class);
    }

}
