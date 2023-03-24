
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class AngryBirds extends Application {

    private ResizableCanvas canvas;
    private World world;
    private MousePicker mousePicker;
    private Camera camera;
    private boolean debugSelected = false;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private Body floor;
    private Convex floorConvex;
    Vector2 vector2 = new Vector2(0,0);
    private Body bird = new Body();
    private Body piggie = new Body();
    private Body longBlock1 = new Body();
    private Body longBlock2 = new Body();
    private Body longBlock3 = new Body();

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();

        // Add debug button
        javafx.scene.control.CheckBox showDebug = new CheckBox("Show debug");
        showDebug.setOnAction(e -> {
            debugSelected = showDebug.isSelected();
        });
        mainPane.setTop(showDebug);

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        camera = new Camera(canvas, g -> draw(g), g2d);
        mousePicker = new MousePicker(canvas);

        floor = new Body();
        floorConvex = Geometry.createRectangle(100, 2.2);
        floor.getTransform().setTranslation(0, -4);
        floor.setMass(MassType.INFINITE);
        floor.addFixture(floorConvex);
        world.addBody(floor);

        gameObjects.add(new GameObject("/images/angry-birds-floor.jpg", floor, vector2, 4));

        bird.addFixture(Geometry.createCircle(0.30));
        bird.getTransform().setTranslation(-7,2.4);
        bird.setMass(MassType.NORMAL);
        bird.getFixture(0).setRestitution(0.3);
        world.addBody(bird);
        gameObjects.add(new GameObject("/images/red-angry-bird.png", bird, new Vector2(0,0), 0.15));

        piggie.addFixture(Geometry.createCircle(0.20));
        piggie.getTransform().setTranslation(5.5, 5);
        piggie.setMass(MassType.NORMAL);
        piggie.getFixture(0).setRestitution(0.3);
        world.addBody(piggie);
        gameObjects.add(new GameObject("/images/piggie.png", piggie, new Vector2(0, 0), 0.08));

        longBlock1.addFixture(Geometry.createRectangle(0.22, 1.6));
        longBlock1.getTransform().setTranslation(5, 0);
        longBlock1.setMass(MassType.NORMAL);
        longBlock1.getFixture(0).setRestitution(0.05);
        world.addBody(longBlock1);
        gameObjects.add(new GameObject("/images/long-block.jpg", longBlock1, new Vector2(0, 0), 1));

        longBlock2.addFixture(Geometry.createRectangle(0.22, 1.6));
        longBlock2.getTransform().setTranslation(6.2, 0);
        longBlock2.setMass(MassType.NORMAL);
        longBlock2.getFixture(0).setRestitution(0.05);
        world.addBody(longBlock2);
        gameObjects.add(new GameObject("/images/long-block.jpg", longBlock2, new Vector2(0, 0), 1));

        longBlock3.addFixture(Geometry.createRectangle(0.22, 1.6));
        longBlock3.getTransform().setTranslation(3.6, -5.6);
        longBlock3.setMass(MassType.NORMAL);
        longBlock3.getFixture(0).setRestitution(0.05);
        longBlock3.rotate(Math.toRadians(90.0f));
        world.addBody(longBlock3);
        gameObjects.add(new GameObject("/images/long-block.jpg", longBlock3, new Vector2(0, 0), 1));

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Angry Birds");
        stage.show();
        draw(g2d);
    }

    public void init() {
        world = new World();
        world.setGravity(new Vector2(0, -9.8));
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.CYAN);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform originalTransform = graphics.getTransform();

        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));
        graphics.scale(1, -1);

        for (GameObject go : gameObjects) {
            go.draw(graphics);
        }

        if (debugSelected) {
            graphics.setColor(Color.blue);
            DebugDraw.draw(graphics, world, 100);
        }

        graphics.setTransform(originalTransform);
    }

    public void update(double deltaTime) {
        mousePicker.update(world, camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()), 100);
//        canvas.setOnMouseReleased(event -> {
//
//        });
        world.update(deltaTime);
    }

    public static void main(String[] args) {
        launch(AngryBirds.class);
    }

}