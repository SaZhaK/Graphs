package sample.GUI;

import sample.Structure.Vertex;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static sample.Constants.Constants.*;

public class NewDrawGraphWindow {
    private static Stage stage;
    private static GraphicsContext context;
    private static List<Vertex> vertexes;
    private static List<Button> vertexButtons;
    private static Group group;

    private static boolean eraseMode = false;
    private static boolean addInfoMode = false;
    private static Text selectedMode;

    public static void createNewDrawGraphWindow() {
        Canvas canvas = new Canvas((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 1,
                MAIN_WINDOW_HEIGHT / 2);
        context = canvas.getGraphicsContext2D();
        vertexes = new ArrayList<>();
        vertexButtons = new ArrayList<>();
        stage = new Stage();

        //Drawing GUI
        drawDelimiter();

        //Paint button
        Button paintButton = new Button("Paint");
        paintButton.setAlignment(Pos.BOTTOM_CENTER);
        paintButton.setStyle("-fx-background-image: url('Paint_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 5px;");
        paintButton.setLayoutX((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 30);
        paintButton.setLayoutY(20);
        paintButton.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        paintButton.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        paintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eraseMode = false;
                addInfoMode = false;

                selectedMode.setText("Paint mode");
            }
        });

        //Erase button
        Button eraseButton = new Button("Erase");
        eraseButton.setAlignment(Pos.BOTTOM_CENTER);
        eraseButton.setStyle("-fx-background-image: url('Erase_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 5px;");
        eraseButton.setLayoutX((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 30);
        eraseButton.setLayoutY(120);
        eraseButton.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        eraseButton.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        eraseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eraseMode = true;
                addInfoMode = false;

                selectedMode.setText("Erase mode");
            }
        });

        //Add information button
        Button addInfoButton = new Button("Add info");
        addInfoButton.setAlignment(Pos.BOTTOM_CENTER);
        addInfoButton.setStyle("-fx-background-image: url('Add_text_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 5px;");
        addInfoButton.setLayoutX((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 30);
        addInfoButton.setLayoutY(220);
        addInfoButton.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        addInfoButton.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        addInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                eraseMode = false;
                addInfoMode = true;

                selectedMode.setText("Add info mode");
            }
        });

        //Help button
        Button helpButton = new Button();
        helpButton.setAlignment(Pos.BOTTOM_CENTER);
        helpButton.setStyle("-fx-background-image: url('Help_button.png');" +
                "-fx-background-size: 40px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 1px 1px;");
        helpButton.setLayoutX((MAIN_WINDOW_WIDTH) / 2 - 10);
        helpButton.setLayoutY(MAIN_WINDOW_HEIGHT / 2 - 50);
        helpButton.setMaxSize(40, 40);
        helpButton.setMinSize(40, 40);
        helpButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().setMaxWidth(DRAW_NEW_GRAPH_HELP_WINDOW_WIDTH);
                alert.getDialogPane().setMinWidth(DRAW_NEW_GRAPH_HELP_WINDOW_WIDTH);
                alert.getDialogPane().setMaxHeight(DRAW_NEW_GRAPH_HELP_WINDOW_HEIGHT);
                alert.getDialogPane().setMinHeight(DRAW_NEW_GRAPH_HELP_WINDOW_HEIGHT);
                alert.setTitle("Help");
                alert.setHeaderText("Use of draw graph function");
                alert.setContentText(" - Select \"Paint\" mode and create a vertex by clicking on the canvas\n" +
                                    " - Drag mouse from vertex to other vertex to create an edge between them\n" +
                                    " - Select \"Erase\" mode and delete a vertex by clicking on the it\n" +
                                    " - Select \"Add info\" mode and add information to a vertex by clicking on it\n");

                alert.showAndWait();
            }
        });

        //Selected mode text
        Text selectedModeText = new Text("Selected mode:");
        selectedModeText.setLayoutX((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 10);
        selectedModeText.setLayoutY(350);
        selectedModeText.setFont(Font.font("Helvetica bold", 15));

        //Selected mode text
        selectedMode = new Text("Paint mode");
        selectedMode.setLayoutX((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 15);
        selectedMode.setLayoutY(370);
        selectedMode.setFont(Font.font("Helvetica bold", 15));

        //Adding new vertex
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double x = mouseEvent.getSceneX();
                double y = mouseEvent.getSceneY();

                createVertex(x, y);
            }
        });

        //Setting on close operation
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                MainWindow.createNewGraphFromDraw();
                stage.close();
            }
        });

        group = new Group();

        Scene scene = new Scene(group, (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + DRAW_GRAPH_WINDOW_MENU_WIDTH
                , MAIN_WINDOW_HEIGHT / 2);

        group.getChildren().add(canvas);
        group.getChildren().add(paintButton);
        group.getChildren().add(eraseButton);
        group.getChildren().add(addInfoButton);
        group.getChildren().add(helpButton);
        group.getChildren().add(selectedModeText);
        group.getChildren().add(selectedMode);

        stage.setTitle("Draw new graph");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static void createVertex(double x, double y) {
        //Creating button
        Button button = new Button();
        button.setLayoutX(x - VERTEX_RADIUS);
        button.setLayoutY(y - VERTEX_RADIUS);
        button.setShape(new Circle((VERTEX_RADIUS + 2) * 2));
        button.setMaxSize((VERTEX_RADIUS + 2) * 2, (VERTEX_RADIUS + 2) * 2);
        button.setMinSize((VERTEX_RADIUS + 2) * 2, (VERTEX_RADIUS + 2) * 2);
        button.setStyle("-fx-background-color: " + UNSELECTED_COLOR);

        Vertex newVertex = new Vertex(x, y);

        final double[] stopX = new double[1];
        final double[] stopY = new double[1];
        //Adding new edge
        button.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                context.beginPath();
                context.clearRect(0, 0, (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2, MAIN_WINDOW_HEIGHT);
                context.moveTo(x, y);
                context.lineTo(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                context.stroke();
                context.closePath();

                displayEdges();

                stopX[0] = mouseEvent.getSceneX();
                stopY[0] = mouseEvent.getSceneY();
            }
        });

        button.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                for (int i = 0; i < vertexes.size(); i++) {
                    if ((Math.abs(vertexes.get(i).getX() - stopX[0]) < VERTEX_RADIUS * 2) &&
                            (Math.abs(vertexes.get(i).getY() - stopY[0]) < VERTEX_RADIUS * 2)) {
                        newVertex.addVertex(vertexes.get(i));
                        vertexes.get(i).addVertex(newVertex);
                        displayEdges();
                    } else {
                        context.beginPath();
                        context.clearRect(0, 0, (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2, MAIN_WINDOW_HEIGHT);
                        context.closePath();
                        displayEdges();
                    }
                }

            }
        });

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (eraseMode) {
                    for (int i = 0; i < newVertex.getAmountOfAdjacentVertexes(); i++) {
                        newVertex.getVertex(i).removeVertex(newVertex);
                    }

                    vertexButtons.get(vertexes.indexOf(newVertex)).setVisible(false);
                    vertexButtons.remove(vertexes.indexOf(newVertex));
                    vertexes.remove(newVertex);


                    context.beginPath();
                    context.clearRect(0, 0, (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2, MAIN_WINDOW_HEIGHT);
                    context.closePath();
                    displayEdges();
                } else if (addInfoMode) {
                    TextInputDialog dialog = new TextInputDialog();

                    dialog.setTitle("Add information");
                    dialog.setHeaderText("Additional information on this vertex:");
                    dialog.setContentText("Text:");

                    Optional<String> inputText = dialog.showAndWait();

                    inputText.ifPresent(name -> {
                        newVertex.addInformation(inputText.get());
                    });
                }
            }
        });

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Tooltip tooltip = new Tooltip(newVertex.getInformation());
                tooltip.setFont(Font.font("Arial", 15));
                button.setTooltip(tooltip);
            }
        });

        vertexButtons.add(button);
        vertexes.add(newVertex);
        group.getChildren().add(button);
    }

    private static void displayEdges() {
        for (Vertex vertex : vertexes) {
            context.setStroke(Color.BLACK);
            for (int i = 0; i < vertex.getAmountOfAdjacentVertexes(); i++) {
                context.beginPath();
                context.moveTo(vertex.getX(), vertex.getY());
                context.lineTo(vertex.getVertex(i).getX(), vertex.getVertex(i).getY());
                context.stroke();
                context.closePath();
            }
        }
    }

    public static List<Vertex> getVertexes() {
        for (int i = 0; i < vertexes.size(); i++) {
            vertexes.get(i).setY(vertexes.get(i).getY() + MAIN_WINDOW_MENU_HEIGHT);
        }
        return vertexes;
    }

    public static void drawDelimiter() {
        context.beginPath();
        context.setFill(Paint.valueOf("#B0B0B0"));
        context.moveTo((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 1, 0);
        context.lineTo((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + 1, MAIN_WINDOW_HEIGHT / 2);
        context.stroke();
        context.closePath();
    }
}
