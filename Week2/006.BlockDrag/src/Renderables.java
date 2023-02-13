import com.sun.javafx.geom.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

public class Renderables {
    private java.awt.Color color;
    private Point2D position;
    private Shape shape;

    public Renderables(Color color, Point2D position) {
        this.color = color;
        this.position = position;
//        this.shape = shape;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
