import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spirograph extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;

    private Canvas canvas = new Canvas(1920, 1000);
    private Button drawBtn;
    private Button clearBtn;

    private boolean drawBtnState = true;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas.getGraphicsContext2D().translate(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.getGraphicsContext2D().scale(1, -1);

        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));

        topBar.getChildren().add(v1 = new TextField("300"));
        topBar.getChildren().add(v2 = new TextField("1"));
        topBar.getChildren().add(v3 = new TextField("300"));
        topBar.getChildren().add(v4 = new TextField("10"));
        topBar.getChildren().add(drawBtn = new Button("Draw!"));
        topBar.getChildren().add(clearBtn = new Button("Clear!"));

        drawBtn.setOnAction(event -> {
            if (drawBtnState) draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
            else new Alert(Alert.AlertType.ERROR, "Field is not cleared!").showAndWait();
            drawBtnState = !drawBtnState;
        });

        clearBtn.setOnAction(event -> {
            canvas.getGraphicsContext2D().clearRect(-5000, -5000, 10000, 10000);
            drawBtnState = true;
        });

        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Spirograph");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        float a = Float.parseFloat(v1.getText());
        float b = Float.parseFloat(v2.getText());
        float c = Float.parseFloat(v3.getText());
        float d = Float.parseFloat(v4.getText());
        double resolution = 0.00001;
        float scale = 0.2f;

        for (double i = 0; i < 2 * Math.PI; i+= resolution) {
            float x1 = (float) (a * Math.cos(b * i) + c * Math.cos(d * i));
            float y1 = (float) (a * Math.sin(b * i) + c * Math.sin(d * i));
                graphics.setColor(Color.getHSBColor((float) (i/Math.PI), 1, 1));
                graphics.draw(new Line2D.Float(x1*scale, y1*scale, x1*scale, y1*scale));
        }

        /** Formula for drawing spirograph
        x = a × cos(b × φ) + c × cos(d × φ)
        y = a × sin(b × φ) + c × sin(d × φ)
         **/

    }
    
    
    
    public static void main(String[] args) {
        launch(Spirograph.class);
    }

}