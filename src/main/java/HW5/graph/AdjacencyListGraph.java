package HW5.graph;

import HW5.drawingApi.DrawingApi;
import org.assertj.core.util.VisibleForTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph extends Graph {
    private final List<Edge> edges;

    public AdjacencyListGraph(DrawingApi drawingApi, Path listFilePath) throws IOException {
        super(drawingApi);

        String definition = Files.readString(listFilePath);
        this.size = getSize(definition.split("\n"));
        this.edges = getEdges(definition.split("\n"));
    }

    @Override
    protected void doDrawGraph() {
        for (int i = 0; i < size; i++) {
            drawVertex(i);
        }

        for (var edge : edges) {
            drawEdge(edge.from(), edge.to());
        }
    }

    @VisibleForTesting
    protected static int getSize(String[] definition) {
        String size = definition[0];
        System.out.println(size);
        return Integer.parseInt(size);
    }

    @VisibleForTesting
    protected static List<Edge> getEdges(String[] definition) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 1; i < definition.length; i++) {
            var edge = definition[i];
            var vertices = edge.split(" ");
            edges.add(new Edge(
                Integer.parseInt(vertices[0]),
                Integer.parseInt(vertices[1]))
            );
        }

        return edges;
    }

    record Edge(int from, int to) {}
}
