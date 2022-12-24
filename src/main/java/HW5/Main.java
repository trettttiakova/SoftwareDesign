package HW5;

import HW5.drawingApi.AwtDrawing;
import HW5.drawingApi.DrawingApi;
import HW5.drawingApi.JavaFxDrawing;
import HW5.graph.AdjacencyListGraph;
import HW5.graph.AdjacencyMatrixGraph;
import HW5.graph.Graph;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length != 5) {
            throw new IllegalArgumentException(
                "Arguments should be: <awt|fx> <matrix|list> <filename> <width> <height>"
            );
        }

        int width = getDimension(args[3]);
        int height = getDimension(args[4]);

        DrawingApi api = getDrawingApi(args[0], width, height);
        Graph graph = getGraph(args[1], api, args[2]);

        graph.drawGraph();
    }

    private static DrawingApi getDrawingApi(String option, int width, int height) {
        if (option.equalsIgnoreCase("awt")) {
            return new AwtDrawing(width, height);
        } else if (option.equalsIgnoreCase("fx")) {
            return new JavaFxDrawing(width, height);
        } else {
            throw new IllegalArgumentException("Unknown drawing api options. Use awt or fx");
        }
    }

    private static Graph getGraph(String option, DrawingApi api, String filename) throws IOException {
        Path path = Path.of(filename);
        if (option.equalsIgnoreCase("list")) {
            return new AdjacencyListGraph(api, path);
        } else if (option.equalsIgnoreCase("matrix")) {
            return new AdjacencyMatrixGraph(api, path);
        } else {
            throw new IllegalArgumentException("Unknown graph options. Use list or matrix");
        }
    }

    private static int getDimension(String dimension) {
        try {
            int d = Integer.parseInt(dimension);
            if (d <= 0) {
                throw new IllegalArgumentException("Width and height should be positive");
            }

            return d;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Width and height should be positive integers");
        }
    }
}
