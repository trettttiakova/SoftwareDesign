package HW5.drawingApi;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFxDrawing implements DrawingApi {
    private final int width;
    private final int height;
    private static final List<Node> shapes = new ArrayList<>();

    public JavaFxDrawing(int width, int height) {
        this.width = width;
        this.height = height;
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
        shapes.add(new Circle(point.getX(), point.getY(), radius));
    }

    @Override
    public void drawLine(Point point1, Point point2) {
        shapes.add(new Line(point1.getX(), point1.getY(), point2.getX(), point2.getY()));
    }

    @Override
    public void getImage() {
        Application.launch(FxApplication.class, Integer.toString(width), Integer.toString(height));
    }

    public static class FxApplication extends Application {

        @Override
        public void start(Stage stage) {
            List<String> params = getParameters().getRaw();

            Group root = new Group(shapes);
            Scene scene = new Scene(
                root,
                Integer.parseInt(params.get(0)),
                Integer.parseInt(params.get(1))
            );

            stage.setScene(scene);
            stage.setTitle("JAVA FX");
            stage.show();
        }
    }
}
