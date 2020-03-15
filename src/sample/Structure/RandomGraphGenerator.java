package sample.Structure;

import java.util.ArrayList;
import java.util.List;

import static sample.Constants.Constants.*;

//TODO
public class RandomGraphGenerator {

    public static List<Vertex> createRandomGraph() {

        List<Vertex> vertexes = new ArrayList<>();
        for (int i = 0; i < Math.random() * (RANDOM_GRAPH_GENERATOR_MAX_AMOUNT_OF_VERTEXES - RANDOM_GRAPH_GENERATOR_MIN_AMOUNT_OF_VERTEXES + 1)
                    + RANDOM_GRAPH_GENERATOR_MIN_AMOUNT_OF_VERTEXES; i++) {
            int x = (int) (Math.random() * (RANDOM_GRAPH_GENERATOR_VERTEX_MAX_COORDINATE - RANDOM_GRAPH_GENERATOR_VERTEX_MIN_COORDINATE + 1)
                    + RANDOM_GRAPH_GENERATOR_VERTEX_MIN_COORDINATE);
            int y = (int) (Math.random() * (RANDOM_GRAPH_GENERATOR_VERTEX_MAX_COORDINATE - RANDOM_GRAPH_GENERATOR_VERTEX_MIN_COORDINATE + 1)
                    + RANDOM_GRAPH_GENERATOR_VERTEX_MIN_COORDINATE);
            vertexes.add(new Vertex(x, y));
        }

        for (int i = 0; i < vertexes.size(); i++) {
            for (int j = 0; j < Math.random() * RANDOM_GRAPH_GENERATOR_MAX_AMOUNT_OF_ADJACENT_VERTEXES - 1; j++) {
                int adjacentVertexIdx;
                do {
                    adjacentVertexIdx = (int) (Math.random() * vertexes.size() - 1);
                } while (adjacentVertexIdx == i);

                boolean flag = true;

                for (int k = 0; k < vertexes.get(i).getAmountOfAdjacentVertexes(); k++) {
                    if(vertexes.get(i).getVertex(k) == vertexes.get(adjacentVertexIdx)) {
                        flag = false;
                    }
                }

                if (flag) {
                    vertexes.get(i).addVertex(vertexes.get(adjacentVertexIdx));
                    vertexes.get(adjacentVertexIdx).addVertex(vertexes.get(i));
                }
            }
        }

        return vertexes;
    }
}
