import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

public class FadingImage extends Application {
    private ResizableCanvas canvas;
    private BufferedImage imp;
    private BufferedImage fadingGuy;
    private float blend1 = 0.01f;
    private float blend2 = 0.99f;
    private float opacity = 0.01f;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        try {
            imp = ImageIO.read(getClass().getResource("/images/impostor.jpg"));
            fadingGuy = ImageIO.read(getClass().getResource("/images/fading-guy.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Fading image");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());


        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, blend2));
        graphics.drawImage(fadingGuy, 0, 0, 500, 500, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, blend1));
        graphics.drawImage(imp, 0, 0, 500, 500, null);


    }


    public void update(double deltaTime) {
        blend1 += opacity;
        blend2 -= opacity;

        if (blend1 >= 1.00f || blend1 < 0.00f) opacity = -opacity;
    }

    public static void main(String[] args) {
        launch(FadingImage.class);
    }

}