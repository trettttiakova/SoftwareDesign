package HW5.graph;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class AdjacencyListGraphTest {
    private final String[] correctDefinition =
        """
        5
        0 1
        0 2
        1 2
        1 3
        1 4
        2 4
        """.split("\n");

    private final String[] invalidDefinition =
        """
        5
        0 9
        0 2
        1 2
        1 3
        1 4
        2 4
        """.split("\n");

    @Test
    public void getEdgesTest() {
        var edges = AdjacencyListGraph.getEdges(correctDefinition, 5);

        assertThat(edges)
            .usingRecursiveFieldByFieldElementComparator()
            .isEqualTo(
                List.of(
                    new AdjacencyListGraph.Edge(0, 1),
                    new AdjacencyListGraph.Edge(0, 2),
                    new AdjacencyListGraph.Edge(1, 2),
                    new AdjacencyListGraph.Edge(1, 3),
                    new AdjacencyListGraph.Edge(1, 4),
                    new AdjacencyListGraph.Edge(2, 4)
                )
            );
    }

    @Test
    public void getSizeTest() {
        int size = AdjacencyListGraph.getSize(correctDefinition);

        assertThat(size).isEqualTo(5);
    }

    @Test
    public void getEdgesInvalidTest() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            AdjacencyListGraph.getEdges(invalidDefinition, 5);
        });

        assertThat(thrown.getMessage())
            .isEqualTo("Edge definition contains non-existing vertex");
    }
}
