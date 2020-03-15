package sample.Structure;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private double x;
    private double y;
    private String information;
    private boolean selectionState = false;
    private boolean visitState = false;

    private List<Vertex> adjacentVertexes = new ArrayList<>();

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getInformation() {
        return information;
    }

    public void addInformation(String information) {
        this.information = information;
    }

    public void addVertex(Vertex newVertex) {
        adjacentVertexes.add(newVertex);
    }

    public void removeVertex(Vertex vertexToRemove) {
        adjacentVertexes.remove(vertexToRemove);
    }

    public Vertex getVertex(int idx) {
        return adjacentVertexes.get(idx);
    }

    public int getAmountOfAdjacentVertexes() {
        return adjacentVertexes.size();
    }

    public boolean isSelected() {
        return selectionState;
    }

    public void setSelectionState(boolean selectionState) {
        this.selectionState = selectionState;
    }

    public boolean isVisited() {
        return visitState;
    }

    public void setVisitState(boolean visitState) {
        this.visitState = visitState;
    }
}
