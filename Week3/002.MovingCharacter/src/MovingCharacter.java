import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class MovingCharacter extends Application {
    private ResizableCanvas canvas;
    private BufferedImage image;
    private BufferedImage running[] = new BufferedImage[4];
    private BufferedImage jumping[] = new BufferedImage[7];
    private BufferedImage[] animation = new BufferedImage[7];


    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

//        try {
//            image = ImageIO.read(getClass().getResource("/resources/sprite.png"));
            this.image = ImageIO.read(new File("Week3/002.MovingCharacter/resources/images/sprite.png"));
        for (int i = 0; i < 7; i++) {
            running[i] = image.getSubimage(64 * i, 64, 64, 64);
            jumping[i] = image.getSubimage(64 * i, 320, 64, 64);
        }
        
        animation = running;

//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Moving Character");
        stage.show();
        draw(g2d);
    }

    private void MouseClick(MouseEvent event){
        this.animation = jumping;

    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
    }


    public void update(double deltaTime)
    {
    }

    public static void main(String[] args)
    {
        launch(MovingCharacter.class);
    }

}
