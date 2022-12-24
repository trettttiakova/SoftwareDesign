package HW5.drawingApi;

public class Point {
    private final double x;
    private final double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point shift(double dx, double dy) {
        return new Point(x + dx, y + dy);
    }
}
