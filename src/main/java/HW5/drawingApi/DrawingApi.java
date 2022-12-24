package HW5.drawingApi;

public interface DrawingApi {
    long getDrawingAreaWidth();

    long getDrawingAreaHeight();

    void drawCircle(Point point, double radius);

    void drawLine(Point point1, Point point2);

    void getImage();
}
