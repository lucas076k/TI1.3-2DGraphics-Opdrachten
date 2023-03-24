import com.sun.javafx.geom.Shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Renderable extends Rectangle2D.Double {
    private java.awt.Color color;
    private double x;
    private double y;
    private double width;
    private double height;

    public Renderable(Color color, double x, double y, double width, double height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setPos(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        setRect(x, y, width, height);
    }
}
