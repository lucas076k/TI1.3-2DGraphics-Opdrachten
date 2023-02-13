import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Rainbow extends Application {
    private ResizableCanvas canvas;
    private String rbString;
    private String rbPart;


    @Override
    public void start(Stage stage) throws Exception
    {
        rbString = "regenboog";
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Rainbow");
        stage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());


        Font font = graphics.getFont();

        char[] rbChars = rbString.toCharArray();
        for (int i = 0; i < rbString.length(); i++) {
            GlyphVector vector = font.createGlyphVector(graphics.getFontRenderContext(), Character.toString(rbChars[i]));
            AffineTransform transform = new AffineTransform();
            transform.scale(10, 10);
            transform.rotate((-0.5 * Math.PI) + (Math.PI / (rbChars.length - 1)) * i);
            Rectangle2D bounds = vector.getLogicalBounds();
            transform.translate(-bounds.getWidth() / 2, -(bounds.getHeight() / 2) - 7);

            Shape oneletter = transform.createTransformedShape(vector.getOutline());
            oneletter = AffineTransform.getTranslateInstance(canvas.getWidth() / 2, canvas.getHeight() / 2).createTransformedShape(oneletter);

            graphics.setColor(Color.getHSBColor((1f / rbChars.length) * i, 1, 1));
            graphics.fill(oneletter);
            graphics.draw(oneletter);
        }
    }


    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }

}
