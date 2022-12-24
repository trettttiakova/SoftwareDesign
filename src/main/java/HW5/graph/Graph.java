package HW5.graph;

import HW5.drawingApi.DrawingApi;
import HW5.drawingApi.Point;

public abstract class Graph {
    /**
     * Bridge to drawing api
     */
    private DrawingApi drawingApi;
    private final double vertexRadius;
    private final double graphWidth;
    private final double graphHeight;
    private final double MARGIN_COEF = 0.35;

    protected int size;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
        this.graphWidth = drawingApi.getDrawingAreaWidth() * MARGIN_COEF;
        this.graphHeight = drawingApi.getDrawingAreaHeight() * MARGIN_COEF;

        this.vertexRadius = Math.min(graphWidth, graphHeight) / 10;
    }

    public void drawGraph() {
        this.doDrawGraph();
        this.getGraphImage();
    }

    protected abstract void doDrawGraph();

    protected void drawVertex(int vertex) {
        Point p = getVertexPoint(vertex);
        drawingApi.drawCircle(p, vertexRadius);
    }

    protected void drawEdge(int vertex1, int vertex2) {
        Point p1 = getVertexPoint(vertex1);
        Point p2 = getVertexPoint(vertex2);
        drawingApi.drawLine(p1, p2);
    }

    private Point getVertexPoint(int vertex) {
        double angle = 2 * Math.PI / size * vertex;
        double x = drawingApi.getDrawingAreaWidth() / 2.0 + Math.cos(angle) * graphWidth;
        double y = drawingApi.getDrawingAreaHeight() / 2.0 + Math.sin(angle) * graphHeight;

        return new Point(x, y);
    }

    protected void getGraphImage() {
        drawingApi.getImage();
    }
}