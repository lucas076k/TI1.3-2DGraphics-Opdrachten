import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
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
    private Button clearBtn;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mirrorBtn = new Button("Mirror");
        clearBtn = new Button("Clear");

        HBox btnBox = new HBox(mirrorBtn, clearBtn);

        mainPane.setCenter(canvas);
        mainPane.setTop(btnBox);


        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        mirrorBtn.setOnAction(event -> {

            draw(graphics);
        });

        clearBtn.setOnAction(event -> {
            graphics.clearRect(-320, -240, (int) canvas.getWidth(), (int) canvas.getHeight());
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

        Rectangle2D square = new Rectangle2D.Double(-50, 100, 100, 100);
        graphics.draw(square);

        Line2D line = new Line2D.Double(-canvas.getWidth()/2, -canvas.getWidth()/2 * 2.5, canvas.getWidth()/2, canvas.getWidth()/2 * 2.5);
        graphics.draw(line);
    }

    public AffineTransform getTransform(){
        AffineTransform affineTransform = new AffineTransform();

        return affineTransform;
    }

    public static void main(String[] args)
    {
        launch(Mirror.class);
    }

}
