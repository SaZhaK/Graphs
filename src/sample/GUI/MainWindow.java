package sample.GUI;

import sample.Structure.RandomGraphGenerator;
import sample.Structure.Vertex;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static sample.Constants.Constants.*;

public class MainWindow extends Application {

    private static GraphicsContext context;
    private static List<Button> vertexButtons;
    private static List<Vertex> vertexes;
    private static Group group;
    private static final FileChooser fileChooser = new FileChooser();

    private static Vertex selectedVertex1;
    private static Vertex selectedVertex2;

    private static CheckBox showTrajectory;
    private static CheckBox showAnimation;

    @Override
    public void start(Stage stage) throws Exception {

        Canvas canvas = new Canvas(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        context = canvas.getGraphicsContext2D();
        vertexButtons = new ArrayList<>();
        group = new Group();

        // Drawing GUI
        drawGUI();

        //Creating buttons
        //Save button
        Button save = new Button("Save");
        save.setAlignment(Pos.BOTTOM_CENTER);
        save.setStyle("-fx-background-image: url('Save_button.png');" +
                "-fx-background-size: 60px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 10px 5px;");
        save.setLayoutX(10);
        save.setLayoutY(MAIN_WINDOW_MENU_BUTTONS_TOP_SPAN);
        save.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        save.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                save(stage);
            }
        });

        //Open button
        Button open = new Button("Open");
        open.setAlignment(Pos.BOTTOM_CENTER);
        open.setStyle("-fx-background-image: url('Open_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 5px;");
        open.setLayoutX(100);
        open.setLayoutY(MAIN_WINDOW_MENU_BUTTONS_TOP_SPAN);
        open.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        open.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        open.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                open(stage);
            }
        });

        //Draw graph button
        Button drawGraph = new Button("New");
        drawGraph.setAlignment(Pos.BOTTOM_CENTER);
        drawGraph.setStyle("-fx-background-image: url('Draw_graph_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 5px;");
        drawGraph.setLayoutX(190);
        drawGraph.setLayoutY(MAIN_WINDOW_MENU_BUTTONS_TOP_SPAN);
        drawGraph.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        drawGraph.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        drawGraph.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NewDrawGraphWindow.createNewDrawGraphWindow();
            }
        });

        //Create table button
        Button createGraphFromTableButton = new Button("Table");
        createGraphFromTableButton.setAlignment(Pos.BOTTOM_CENTER);
        createGraphFromTableButton.setStyle("-fx-background-image: url('Table_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 10px;");
        createGraphFromTableButton.setLayoutX(280);
        createGraphFromTableButton.setLayoutY(MAIN_WINDOW_MENU_BUTTONS_TOP_SPAN);
        createGraphFromTableButton.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        createGraphFromTableButton.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        createGraphFromTableButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createNewGraphFromTableWindow();
            }
        });

        //Generate random graph button
        Button generateRandomGraphButton = new Button("Random");
        generateRandomGraphButton.setAlignment(Pos.BOTTOM_CENTER);
        generateRandomGraphButton.setStyle("-fx-background-image: url('Random_graph_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 5px;");
        generateRandomGraphButton.setLayoutX(370);
        generateRandomGraphButton.setLayoutY(MAIN_WINDOW_MENU_BUTTONS_TOP_SPAN);
        generateRandomGraphButton.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        generateRandomGraphButton.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        generateRandomGraphButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                generateRandomGraph();
            }
        });

        //Clear button
        Button clear = new Button("Clear");
        clear.setAlignment(Pos.BOTTOM_CENTER);
        clear.setStyle("-fx-background-image: url('Clear_button.png');" +
                "-fx-background-size: 50px;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: 15px 5px;");
        clear.setLayoutX(460);
        clear.setLayoutY(MAIN_WINDOW_MENU_BUTTONS_TOP_SPAN);
        clear.setMaxSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        clear.setMinSize(MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH, MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT);
        clear.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clear();
            }
        });

        //Functions
        //Functions text
        Text functionsText = new Text("Functions:");
        functionsText.setLayoutX(MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_SIDE_SPAN);
        functionsText.setLayoutY(MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_TOP_SPAN * 2);
        functionsText.setFont(Font.font("Helvetica bold", 25));

        //Simplify button
        Button imitateMovementButton = new Button("Imitate movement");
        imitateMovementButton.setLayoutX(MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_SIDE_SPAN);
        imitateMovementButton.setLayoutY(MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_TOP_SPAN * 3);
        imitateMovementButton.setMaxSize(MAIN_WINDOW_FUNCTION_BUTTON_WIDTH, MAIN_WINDOW_FUNCTION_BUTTON_HEIGHT);
        imitateMovementButton.setMinSize(MAIN_WINDOW_FUNCTION_BUTTON_WIDTH, MAIN_WINDOW_FUNCTION_BUTTON_HEIGHT);
        imitateMovementButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(vertexes == null || vertexes.isEmpty()) createAlertDialog();
                else ImitateMovement();
            }
        });

        //Show trajectory checkbox
        showTrajectory = new CheckBox("Show trajectory");
        showTrajectory.setSelected(false);
        showTrajectory.setLayoutX(MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_SIDE_SPAN);
        showTrajectory.setLayoutY(MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_TOP_SPAN * 5 + 10);

        //Show animation checkbox
        showAnimation = new CheckBox("Show animation");
        showAnimation.setSelected(false);
        showAnimation.setLayoutX(MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_SIDE_SPAN);
        showAnimation.setLayoutY(MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_TOP_SPAN * 7 + 5);

        //Adding elements to scene
        group.getChildren().add(canvas);
        group.getChildren().add(imitateMovementButton);
        group.getChildren().add(clear);
        group.getChildren().add(save);
        group.getChildren().add(open);
        group.getChildren().add(drawGraph);
        group.getChildren().add(generateRandomGraphButton);
        group.getChildren().add(createGraphFromTableButton);
        group.getChildren().add(functionsText);
        group.getChildren().add(showTrajectory);
        group.getChildren().add(showAnimation);

        Scene scene = new Scene(group, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

        stage.setTitle("G-Tool");
        stage.getIcons().add(new Image("file:Icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Functions
    //Imitate movement
    private static void ImitateMovement() {

        List<Vertex> newVertexes = new ArrayList<>();

        for (Vertex vertex : vertexes) {
            if (vertex.isSelected()) {
                selectedVertex1 = new Vertex(vertex.getX(), vertex.getY());
                selectedVertex1.setSelectionState(true);
                newVertexes.add(selectedVertex1);
                selectedVertex1 = null;
            } else {
                bypass(vertex);

                if (selectedVertex1 == null) selectedVertex1 = new Vertex(vertex.getX(), vertex.getY());

                newVertexes.add(selectedVertex1);

                selectedVertex1 = null;
                selectedVertex2 = null;
                resetVertexesState();
            }
        }

        for (int i = 0; i < vertexes.size(); i++) {
            Vertex oldVertex = vertexes.get(i);
            Vertex newVertex = newVertexes.get(i);

            for (int j = 0; j < oldVertex.getAmountOfAdjacentVertexes(); j++) {
                newVertex.addVertex(newVertexes.get(vertexes.indexOf(oldVertex.getVertex(j))));
            }
        }

        if (showAnimation.isSelected()) {
            playAnimation(newVertexes);
        } else {
            clearResultField();
        }

        if (showTrajectory.isSelected()) {
            displayOldVertexesInNewField(vertexes);
            displayMovementTrajectory(newVertexes);
        } else {
            clearResultField();
        }

        displayNewEdges(newVertexes);
        displayNewVertexes(newVertexes);
    }

    private static void bypass(Vertex vertex) {
        if (!vertex.isVisited()) {
            vertex.setVisitState(true);
            if (vertex.isSelected()) {
                if (selectedVertex1 == null) {
                    selectedVertex1 = new Vertex(vertex.getX(), vertex.getY());
                } else {
                    selectedVertex2 = new Vertex(vertex.getX(), vertex.getY());
                    selectedVertex1 = new Vertex((selectedVertex1.getX() + selectedVertex2.getX()) / 2,
                            (selectedVertex1.getY() + selectedVertex2.getY()) / 2);
                }
            } else if (!vertex.isSelected()) {
                for (int i = 0; i < vertex.getAmountOfAdjacentVertexes(); i++) {
                    bypass(vertex.getVertex(i));
                }
            }
        }
    }

    private static void resetVertexesState() {
        for (Vertex vertex : vertexes) {
            vertex.setVisitState(false);
        }
    }

    //Animation
    private static void playAnimation(List<Vertex> newVertexes) {
        double[] x = new double[vertexes.size()];
        double[] y = new double[vertexes.size()];

        double[] x1 = new double[vertexes.size()];
        double[] y1 = new double[vertexes.size()];

        double[] x2 = new double[vertexes.size()];
        double[] y2 = new double[vertexes.size()];

        boolean[] reversed = new boolean[vertexes.size()];
        boolean[] finished = new boolean[vertexes.size()];

        for (int i = 0; i < vertexes.size(); i++) {
            x[i] = vertexes.get(i).getX();
            y[i] = vertexes.get(i).getY();

            x1[i] = vertexes.get(i).getX();
            y1[i] = vertexes.get(i).getY();

            x2[i] = newVertexes.get(i).getX();
            y2[i] = newVertexes.get(i).getY();

            if (x1[i] > x2[i]) reversed[i] = true;
            else reversed[i] = false;

            finished[i] = false;
        }


        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(10),
                        ae -> {
                            clearResultField();
                            displayNewEdges(newVertexes);
                            displayNewVertexes(newVertexes);

                            if (showTrajectory.isSelected()) {
                                displayMovementTrajectory(newVertexes);
                                displayOldVertexesInNewField(vertexes);
                            }

                            for (int i = 0; i < vertexes.size(); i++) {
                                if (!finished[i]) {
                                    context.beginPath();
                                    if (vertexes.get(i).isSelected()) context.setFill(Paint.valueOf(SELECTED_COLOR));
                                    else context.setFill(Paint.valueOf(UNSELECTED_COLOR));
                                    context.fillOval((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + x[i] - VERTEX_RADIUS,
                                            y[i] - VERTEX_RADIUS, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
                                    context.stroke();
                                    context.closePath();

                                    if (!reversed[i]) {
                                        x[i] += (x2[i] - x1[i]) / ANIMATION_VELOCITY;
                                        if (x[i] >= x2[i]) finished[i] = true;
                                    } else {
                                        x[i] -= (x1[i] - x2[i]) / ANIMATION_VELOCITY;
                                        if (x[i] <= x2[i]) finished[i] = true;
                                    }
                                    y[i] = y1[i] + (x[i] - x1[i]) * (y2[i] - y1[i]) / (x2[i] - x1[i]);
                                }
                            }
                        }
                )
        );

        timeline.setCycleCount((int) (countMaxDistance(vertexes, newVertexes) / 0.6));
        timeline.play();
    }

    private static double countMaxDistance(List<Vertex> vertexes, List<Vertex> newVertexes) {
        double maxDistance = Integer.MIN_VALUE;

        for (int i = 0; i < vertexes.size(); i++) {
            double curDistance = Math.sqrt(Math.pow(vertexes.get(i).getX() - newVertexes.get(i).getX(), 2) +
                    Math.pow(vertexes.get(i).getY() - newVertexes.get(i).getY(), 2));
            if (curDistance > maxDistance) {
                maxDistance = curDistance;
            }
        }

        return maxDistance;
    }

    //Clear
    private static void clear() {
        double halfWindow = (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2;
        context.clearRect(0, MAIN_WINDOW_MENU_HEIGHT, halfWindow - 2, MAIN_WINDOW_HEIGHT - MAIN_WINDOW_MENU_HEIGHT);
        context.clearRect(halfWindow + 2, MAIN_WINDOW_MENU_HEIGHT, halfWindow - 2, MAIN_WINDOW_HEIGHT - MAIN_WINDOW_MENU_HEIGHT);
        for (Button vertexButton : vertexButtons) {
            vertexButton.setVisible(false);
        }
        vertexButtons = new ArrayList<>();
        System.gc();
    }

    private static void clearResultField() {
        double halfWindow = (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2;
        context.clearRect(halfWindow + 2, MAIN_WINDOW_MENU_HEIGHT, halfWindow - 2, MAIN_WINDOW_HEIGHT - MAIN_WINDOW_MENU_HEIGHT);
    }

    //Generate random graph
    private static void generateRandomGraph() {
        clear();
        vertexes = RandomGraphGenerator.createRandomGraph();
        displayEdges(vertexes);
        displayVertexes(vertexes);
        for (Button vertexButton : vertexButtons) {
            group.getChildren().add(vertexButton);
        }
    }

    //Save
    private static void save(Stage stage) {
        fileChooser.setInitialDirectory(new File("/home/sazha/Desktop"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);

                for (Vertex vertex : vertexes) {
                    fileWriter.write(vertex.getX() + " " + vertex.getY() + " ");
                }
                fileWriter.write("\n");

                for (Vertex vertex : vertexes) {
                    for (int i = 0; i < vertex.getAmountOfAdjacentVertexes(); i++) {
                        fileWriter.write(vertexes.indexOf(vertex.getVertex(i)) + " ");
                    }
                    fileWriter.write("\n");
                }

                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Open
    private static void open(Stage stage) {
        fileChooser.setInitialDirectory(new File("/home/sazha/Desktop"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                vertexes = new ArrayList<>();
                vertexButtons = new ArrayList<>();

                String[] coordinates = bufferedReader.readLine().split(" ");

                for (int i = 0; i < coordinates.length; i += 2) {
                    vertexes.add(new Vertex(Double.parseDouble(coordinates[i]), Double.parseDouble(coordinates[i + 1])));
                }

                int idx = 0;
                String curString = bufferedReader.readLine();
                while (curString != null) {
                    String[] vertexIndexes = curString.split(" ");

                    for (int j = 0; j < vertexIndexes.length; j++) {
                        vertexes.get(idx).addVertex(vertexes.get(Integer.parseInt(vertexIndexes[j])));
                    }

                    ++idx;
                    curString = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        clear();
        displayEdges(vertexes);
        displayVertexes(vertexes);
        for (Button vertexButton : vertexButtons) {
            group.getChildren().add(vertexButton);
        }
    }


    // Creating graph
    public static void createFromTable() {
        clear();
        vertexes = NewGraphFromTableWindow.getVertexes();
        displayEdges(vertexes);
        displayVertexes(vertexes);
        for (Button vertexButton : vertexButtons) {
            group.getChildren().add(vertexButton);
        }
    }

    public static void createNewGraphFromDraw() {
        clear();
        vertexes = NewDrawGraphWindow.getVertexes();
        displayEdges(vertexes);
        displayVertexes(vertexes);
        for (Button vertexButton : vertexButtons) {
            group.getChildren().add(vertexButton);
        }
    }

    //Opening new window
    private static void createNewGraphFromTableWindow() {
        NewGraphFromTableWindow.createNewGraphFromTableWindow();
    }


    //Displaying graph
    private static void displayEdges(List<Vertex> vertexes) {
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

    private static void displayVertexes(List<Vertex> vertexes) {
        for (Vertex vertex : vertexes) {
            Button button = new Button();
            button.setLayoutX(vertex.getX() - VERTEX_RADIUS);
            button.setLayoutY(vertex.getY() - VERTEX_RADIUS);
            button.setShape(new Circle(VERTEX_RADIUS * 2));
            button.setMaxSize(VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
            button.setMinSize(VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
            button.setStyle("-fx-background-color: " + UNSELECTED_COLOR);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!vertex.isSelected()) {
                        button.setStyle("-fx-background-color: " + SELECTED_COLOR);
                        vertex.setSelectionState(true);
                    } else {
                        button.setStyle("-fx-background-color: " + UNSELECTED_COLOR);
                        vertex.setSelectionState(false);
                    }
                }
            });

            button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Tooltip tooltip = new Tooltip(vertex.getInformation());
                    tooltip.setFont(Font.font("Arial", 15));
                    button.setTooltip(tooltip);
                }
            });

            vertexButtons.add(button);
        }
    }

    private static void displayNewVertexes(List<Vertex> newVertexes) {
        for (Vertex vertex : newVertexes) {
            if (!vertex.isSelected()) {
                context.beginPath();
                context.setFill(Paint.valueOf(UNSELECTED_COLOR));
                context.fillOval((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + vertex.getX() - VERTEX_RADIUS,
                        vertex.getY() - VERTEX_RADIUS, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
                context.stroke();
                context.closePath();
            }
        }

        for (Vertex vertex : newVertexes) {
            if (vertex.isSelected()) {
                context.beginPath();
                context.setFill(Paint.valueOf(SELECTED_COLOR));
                context.fillOval((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + vertex.getX() - VERTEX_RADIUS,
                        vertex.getY() - VERTEX_RADIUS, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
                context.stroke();
                context.closePath();
            }
        }
    }

    private static void displayNewEdges(List<Vertex> vertexes) {
        double shift = (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2;
        for (Vertex vertex : vertexes) {
            context.setStroke(Color.BLACK);
            for (int i = 0; i < vertex.getAmountOfAdjacentVertexes(); i++) {
                context.beginPath();
                context.moveTo(shift + vertex.getX(), vertex.getY());
                context.lineTo(shift + vertex.getVertex(i).getX(), vertex.getVertex(i).getY());
                context.stroke();
                context.closePath();
            }
        }
    }

    private static void displayOldVertexesInNewField(List<Vertex> vertexes) {
        for (Vertex vertex : vertexes) {
            if (!vertex.isSelected()) {
                context.beginPath();
                context.setFill(Paint.valueOf("#ffd000"));
                context.fillOval((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + vertex.getX() - VERTEX_RADIUS,
                        vertex.getY() - VERTEX_RADIUS, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
                context.stroke();
                context.closePath();
            }
        }

        for (Vertex vertex : vertexes) {
            if (vertex.isSelected()) {
                context.beginPath();
                context.setFill(Paint.valueOf(SELECTED_COLOR));
                context.fillOval((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + vertex.getX() - VERTEX_RADIUS,
                        vertex.getY() - VERTEX_RADIUS, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
                context.stroke();
                context.closePath();
            }
        }
    }

    private static void displayMovementTrajectory(List<Vertex> newVertexes) {
        for (int i = 0; i < vertexes.size(); i++) {
            context.beginPath();
            context.setStroke(Paint.valueOf("#ffd000"));
            context.moveTo((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + vertexes.get(i).getX(),
                    vertexes.get(i).getY());
            context.lineTo((MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2 + newVertexes.get(i).getX(),
                    newVertexes.get(i).getY());
            context.stroke();
            context.closePath();
        }
    }


    //Drawing GUI
    private static void drawGUI() {
        drawDelimiter();
        drawMenuBoard();
        drawFunctionsMenuBoard();
        drawFunctionsMenuDelimiter();
    }

    private static void drawDelimiter() {
        double halfWindow = (MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH) / 2;
        context.beginPath();
        context.moveTo(halfWindow, MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN);
        context.lineTo(halfWindow, MAIN_WINDOW_HEIGHT - MAIN_WINDOW_MENU_SPAN);
        context.stroke();
        context.closePath();
    }

    private static void drawFunctionsMenuDelimiter() {
        context.beginPath();
        context.moveTo(MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH,
                MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_TOP_SPAN * 3 - 10);
        context.lineTo(MAIN_WINDOW_WIDTH,
                MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN + MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_TOP_SPAN * 3 - 10);
        context.stroke();
        context.closePath();
    }

    private static void drawMenuBoard() {
        context.beginPath();
        context.setFill(Paint.valueOf("#B0B0B0"));
        context.fillRect(0, 0, MAIN_WINDOW_WIDTH, MAIN_WINDOW_MENU_HEIGHT);
        context.stroke();
        context.closePath();
    }

    private static void drawFunctionsMenuBoard() {
        context.beginPath();
        context.setFill(Paint.valueOf("#B0B0B0"));
        context.fillRect(MAIN_WINDOW_WIDTH - MAIN_WINDOW_FUNCTIONS_MENU_WIDTH, MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN,
                MAIN_WINDOW_FUNCTIONS_MENU_WIDTH,
                MAIN_WINDOW_HEIGHT - (MAIN_WINDOW_MENU_HEIGHT + MAIN_WINDOW_MENU_SPAN + MAIN_WINDOW_FUNCTIONS_MENU_BOTTOM_SPAN));
        context.stroke();
        context.closePath();
    }


    //Alert dialog
    private static void createAlertDialog() {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().setMaxWidth(MAIN_WINDOW_ALERT_DIALOG_WIDTH);
            alert.getDialogPane().setMinWidth(MAIN_WINDOW_ALERT_DIALOG_WIDTH);
            alert.setTitle("Warning!");
            alert.setHeaderText("Impossible operation");
            alert.setContentText("You have to create a graph before applying any operations to it");

            alert.showAndWait();
    }


    //Main
    public static void main(String[] args) {
        launch(args);
    }
}
