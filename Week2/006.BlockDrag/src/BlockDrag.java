import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class BlockDrag extends Application {
    ResizableCanvas canvas;

    ArrayList<Renderables> renderables;
    private double x;
    private double y;

    Point2D point2D;

    @Override
    public void start(Stage primaryStage) throws Exception {
        renderables = new ArrayList<>();
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        System.out.println(renderables);
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
        graphics.scale(1, -1);

        x = -320;
        y = 25;

        for (int i = 0; i < 10; i++) {
            Rectangle2D newRect = new Rectangle2D.Double(x, y, 64, 64);

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
                    graphics.setColor(Color.GRAY);
                    break;
                case 4:
                    graphics.setColor(Color.GREEN);
                    break;
                case 5:
                    graphics.setColor(Color.MAGENTA);
                    break;
                case 6:
                    graphics.setColor(Color.ORANGE);
                    break;
                case 7:
                    graphics.setColor(Color.PINK);
                    break;
                case 8:
                    graphics.setColor(Color.RED);
                    break;
                case 9:
                    graphics.setColor(Color.WHITE);
                    break;
                case 10:
                    graphics.setColor(Color.YELLOW);
                    break;
            }
            Renderables rect = new Renderables(graphics.getColor(), point2D = new Point2D.Double(x, y));
            if (renderables.size() < 10) renderables.add(rect);
            graphics.fill(newRect);
            graphics.setColor(Color.black);
            graphics.draw(newRect);
            x += 65;
        }
    }


    public static void main(String[] args) {
        launch(BlockDrag.class);
    }

    private void mousePressed(MouseEvent e) {
        renderables.get(0).getPosition();
        point2D = new Point2D.Double(e.getX(), e.getY());
        renderables.get(0).setPosition(point2D);
    }

    private void mouseReleased(MouseEvent e) {

    }

    private void mouseDragged(MouseEvent e) {
    }

}
