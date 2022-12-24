package HW5.graph;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class AdjacencyMatrixGraphTest {
    @Test
    public void getAdjacencyMatrixTest() {
        String matrix =
            """
            0 1 0 0 0
            1 0 1 1 1
            0 1 0 0 1
            0 1 0 0 1
            0 1 1 1 0
            """;

        var adjMatrix = AdjacencyMatrixGraph.getSymmetricAdjacencyMatrix(matrix);
        assertThat(adjMatrix)
            .usingRecursiveFieldByFieldElementComparator()
            .isEqualTo(
                List.of(
                    List.of(0, 1, 0, 0, 0),
                    List.of(1, 0, 1, 1, 1),
                    List.of(0, 1, 0, 0, 1),
                    List.of(0, 1, 0, 0, 1),
                    List.of(0, 1, 1, 1, 0)
                )
            );
    }

    @Test
    public void getAdjacencyMatrixNonSymmetricTest() {
        String matrix =
            """
            0 1 1 1 1
            1 0 1 1 1
            0 1 0 0 1
            0 1 0 0 1
            0 1 1 1 0
            """;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            AdjacencyMatrixGraph.getSymmetricAdjacencyMatrix(matrix);
        });

        assertThat(thrown.getMessage())
            .isEqualTo("I can only draw undirected graphs now! :(");
    }

    @Test
    public void getAdjacencyMatrixInvalidTest() {
        String matrix =
            """
            0 9 1 1 1
            1 0 1 1 1
            0 1 0 0 1
            0 1 0 0 1
            0 1 1 1 0
            """;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            AdjacencyMatrixGraph.getSymmetricAdjacencyMatrix(matrix);
        });

        assertThat(thrown.getMessage())
            .isEqualTo("Adjacency matrix should only contain 0 and 1");
    }
}
