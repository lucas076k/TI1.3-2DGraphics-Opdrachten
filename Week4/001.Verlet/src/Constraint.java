import org.jfree.fx.FXGraphics2D;

public interface Constraint {
    void update();
    void draw(FXGraphics2D g2d);
}