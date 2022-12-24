package HW5.drawingApi;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class AwtDrawing extends Frame implements DrawingApi {
    private final int width;
    private final int height;
    private final List<Shape> shapes;

    public AwtDrawing(int width, int height) {
        this.width = width;
        this.height = height;
        shapes = new ArrayList<>();
    }

    @Override
    public long getDrawingAreaWidth() {
        return width;
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(Point point, double radius) {
        shapes.add(getCircle(point, radius));
    }

    @Override
    public void drawLine(Point point1, Point point2) {
        shapes.add(getLine(point1, point2));
    }

    @Override
    public void getImage() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setSize(width, height);
        setTitle("AWT");
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (var shape : shapes) {
            g2d.draw(shape);
        }
    }

    private Ellipse2D getCircle(Point point, double radius) {
        point = point.shift(-radius, -radius);

        return new Ellipse2D.Double(point.getX(), point.getY(), 2 * radius, 2 * radius);
    }

    private Line2D getLine(Point point1, Point point2) {
        return new Line2D.Double(
            point1.getX(),
            point1.getY(),
            point2.getX(),
            point2.getY()
        );
    }
}
