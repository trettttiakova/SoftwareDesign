package HW5.graph;

import HW5.drawingApi.DrawingApi;
import org.assertj.core.util.VisibleForTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AdjacencyMatrixGraph extends Graph {
    private final List<List<Integer>> adjMatrix;

    public AdjacencyMatrixGraph(DrawingApi drawingApi, Path matrixFilePath) throws IOException {
        super(drawingApi);
        this.adjMatrix = getSymmetricAdjacencyMatrix(matrixFilePath);
        this.size = adjMatrix.size();
    }

    @Override
    protected void doDrawGraph() {
        for (int i = 0 ; i < adjMatrix.size(); i++) {
            drawVertex(i);
            for (int j = i + 1; j < adjMatrix.size(); j++) {
                if (adjMatrix.get(i).get(j) == 1) {
                    drawEdge(i, j);
                }
            }
        }
    }

    private List<List<Integer>> getSymmetricAdjacencyMatrix(Path matrixFilePath) throws IOException {
        return getSymmetricAdjacencyMatrix(Files.readString(matrixFilePath));
    }

    @VisibleForTesting
    protected static List<List<Integer>> getSymmetricAdjacencyMatrix(String matrixDefinition) {
        List<List<Integer>> adjMatrix = new ArrayList<>();

        var lines = matrixDefinition.split("\n");
        for (var line : lines) {
            List<Integer> parsedLine = Arrays.stream(line.split(" "))
                .map(Integer::parseInt)
                .toList();

            adjMatrix.add(parsedLine);
        }

        for (int i = 0; i < adjMatrix.size(); i++) {
            for (int j = i + 1; j < adjMatrix.size(); j++) {
                if (adjMatrix.get(i).get(j) != 1 && adjMatrix.get(i).get(j) != 0) {
                    throw new IllegalArgumentException(
                        "Adjacency matrix should only contain 0 and 1"
                    );
                }

                if (!Objects.equals(adjMatrix.get(i).get(j), adjMatrix.get(j).get(i))) {
                    throw new IllegalArgumentException(
                        "I can only draw undirected graphs now! :("
                    );
                }
            }
        }

        return adjMatrix;
    }
}
