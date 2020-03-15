package sample.GUI;

import sample.Excel.LinkedListMatrix;
import sample.Structure.Vertex;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static sample.Constants.Constants.*;

public class NewGraphFromTableWindow {

    private static List<Vertex> vertexes;
    private static LinkedListMatrix matrix;
    private static List<TextField> matrixCells;
    private static Stage stage;

    public static void createNewGraphFromTableWindow() {

        int cellWidth = (NEW_GRAPH_WINDOW_WIDTH - NEW_GRAPH_WINDOW_SIDEBOARD_WIDTH) / (NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH + 1);
        int cellHeight = NEW_GRAPH_WINDOW_HEIGHT / (NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT + 1);
        int curColimnNumber = 1;
        int curRowNumber = 1;

        matrix = new LinkedListMatrix(NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT, NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH);
        matrixCells = new ArrayList<>();
        List<TextField> firstRowCells = new ArrayList<>();
        List<TextField> firstColumnCell = new ArrayList<>();

        BorderWidths border = new BorderWidths(NEW_GRAPH_WINDOW_BORDER_WIDTH);
        CornerRadii radius = new CornerRadii(NEW_GRAPH_WINDOW_CORNER_RADIUS);

        // Empty first cell
        TextField firstCell = new TextField();
        firstCell.setMaxSize(cellWidth, cellHeight);
        firstCell.setMinSize(cellWidth, cellHeight);
        firstCell.setLayoutX(0);
        firstCell.setLayoutY(0);
        firstCell.setBackground(new Background(new BackgroundFill(NEW_GRAPH_WINDOW_BORDER_CELL_COLOR, radius, Insets.EMPTY)));
        firstCell.setBorder(new Border(new BorderStroke(NEW_GRAPH_WINDOW_BORDER_CELL_BORDER_COLOR, BorderStrokeStyle.SOLID, radius, border)));
        firstCell.setEditable(false);

        for (int i = 0; i < NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT + 1; i++) {
            if (i == 0) {
                for (int j = 1; j <= NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH + 1; j++) {
                    TextField columnNumber = new TextField(String.valueOf(curColimnNumber++));
                    columnNumber.setMaxSize(cellWidth, cellHeight);
                    columnNumber.setMinSize(cellWidth, cellHeight);
                    columnNumber.setLayoutX(j * cellWidth);
                    columnNumber.setLayoutY(i * cellHeight);
                    columnNumber.setEditable(false);
                    columnNumber.setStyle("-fx-font-weight: bold");
                    columnNumber.setBackground(new Background(new BackgroundFill(NEW_GRAPH_WINDOW_BORDER_CELL_COLOR, radius, Insets.EMPTY)));
                    columnNumber.setBorder(new Border(
                            new BorderStroke(NEW_GRAPH_WINDOW_BORDER_CELL_BORDER_COLOR, BorderStrokeStyle.SOLID, radius, border)));
                    firstRowCells.add(columnNumber);
                }
            } else {
                for (int j = 0; j < NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH + 1; j++) {
                    TextField text;
                    if (j == 0) {
                        text = new TextField(String.valueOf(curRowNumber++));
                        text.setStyle("-fx-font-weight: bold");
                        text.setBackground(new Background(new BackgroundFill(NEW_GRAPH_WINDOW_BORDER_CELL_COLOR, radius, Insets.EMPTY)));
                        text.setBorder(new Border(
                                new BorderStroke(NEW_GRAPH_WINDOW_BORDER_CELL_BORDER_COLOR, BorderStrokeStyle.SOLID, radius, border)));
                        text.setEditable(false);
                        text.setMaxSize(cellWidth, cellHeight);
                        text.setMinSize(cellWidth, cellHeight);
                        text.setLayoutX((j) * cellWidth);
                        text.setLayoutY(i * cellHeight);
                        firstColumnCell.add(text);
                    } else {
                        text = new TextField(matrix.getContent(i, j));
                        text.setMaxSize(cellWidth, cellHeight);
                        text.setMinSize(cellWidth, cellHeight);
                        text.setLayoutX((j) * cellWidth);
                        text.setLayoutY(i * cellHeight);
                        matrixCells.add(text);
                    }
                }
            }
        }

        //Create button
        Button createButton = new Button("Create");
        createButton.setLayoutX(NEW_GRAPH_WINDOW_WIDTH - NEW_GRAPH_WINDOW_SIDEBOARD_WIDTH + 10);
        createButton.setLayoutY(10);
        createButton.setMaxSize(NEW_GRAPH_WINDOW_SIDEBOARD_WIDTH - 20, 30);
        createButton.setMinSize(NEW_GRAPH_WINDOW_SIDEBOARD_WIDTH - 20, 30);
        createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createMatrix();
                MainWindow.createFromTable();
                stage.close();
            }
        });

        stage = new Stage();
        stage.setTitle("New graph");

        Group group = new Group();
        group.getChildren().add(firstCell);
        group.getChildren().add(createButton);

        for (int i = 0; i < NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH; i++) {
            group.getChildren().add(firstRowCells.get(i));
        }

        for (int i = 0; i < NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT; i++) {
            group.getChildren().add(firstColumnCell.get(i));
        }

        for (int i = 0; i < NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT * NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH; i++) {
            group.getChildren().add(matrixCells.get(i));
        }

        Scene scene = new Scene(group, NEW_GRAPH_WINDOW_WIDTH, NEW_GRAPH_WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static void createMatrix() {
        vertexes = new ArrayList<>();

        int counter = 0;
        for (int i = 0; i < NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT * NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH; i++) {
            if (Integer.parseInt(matrixCells.get(i).getText()) == 1) {
                ++counter;
            }
        }

        for (int i = 0; i < counter / 2; i++) {
            vertexes.add(new Vertex((int) (Math.random() * (500 - 100 + 1) + 100),
                    (int) (Math.random() * (500 - 100 + 1) + 100)));
        }

        for (int i = 0; i < NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT; i++) {
            for (int j = 0; j < NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH; j++) {
                if (Integer.parseInt(matrixCells.get(i * NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH + j).getText()) == 1) {
                    vertexes.get(i).addVertex(vertexes.get(j));
                }
            }
        }
    }

    public static List<Vertex> getVertexes() {
        return vertexes;
    }
}