import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Eindopdracht extends Application {

    private TextField f1;
    private ResizableCanvas canvas;
    private Point2D position;
    private Random random = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        position = new Point2D.Double(canvas.getWidth()/2,canvas.getHeight()/2);

        canvas.setOnMouseDragged(e ->
        {
            position = new Point2D.Float((int) e.getX(), (int) e.getY());
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        });

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setBackground(Color.white);
        Point2D focus = new Point2D.Float((float) (canvas.getWidth()/2), (float) (canvas.getHeight()/2));
        float[] distance = {0.0f, 0.3f, 0.9f};
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
        float radius = 100;
        Point2D center = new Point2D.Float((float) (position.getX()), (float) (position.getY()));


        Font font = new Font("Arial", Font.PLAIN, 100);
        Shape shape = font.createGlyphVector(graphics.getFontRenderContext(), "Hallo kerel").getOutline();
        AffineTransform tx = graphics.getTransform();
        Rectangle r = shape.getBounds();
        tx.translate(position.getX()-250, position.getY()+25);
        graphics.transform(tx);
        graphics.setColor(Color.black);
        graphics.draw(shape);
        graphics.fill(shape);

        RadialGradientPaint p = new RadialGradientPaint(center, radius, focus, distance, colors, MultipleGradientPaint.CycleMethod.REFLECT);
        graphics.setPaint(p);

//        graphics.setClip(null);
    }


    public static void main(String[] args)
    {
        launch(Eindopdracht.class);
    }

}