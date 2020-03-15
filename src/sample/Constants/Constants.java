package sample.Constants;

import javafx.scene.paint.Paint;

import java.awt.*;

public class Constants {

    //Main window constants
    public static final double MAIN_WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final double MAIN_WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static final int VERTEX_RADIUS = 6;
    public static final int MAIN_WINDOW_MENU_HEIGHT = 100;
    public static final int MAIN_WINDOW_MENU_SPAN = 5;
    public static final int MAIN_WINDOW_MENU_BUTTONS_TOP_SPAN = 10;
    public static final int MAIN_WINDOW_BIG_MENU_BUTTONS_HEIGHT = 80;
    public static final int MAIN_WINDOW_BIG_MENU_BUTTONS_WIDTH = 80;
    public static final int MAIN_WINDOW_FUNCTIONS_MENU_WIDTH = 200;
    public static final int MAIN_WINDOW_FUNCTIONS_MENU_BOTTOM_SPAN = 80;
    public static final int MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_TOP_SPAN = 20;
    public static final int MAIN_WINDOW_FUNCTIONS_MENU_BUTTON_SIDE_SPAN = 20;
    public static final int MAIN_WINDOW_FUNCTION_BUTTON_WIDTH = 160;
    public static final int MAIN_WINDOW_FUNCTION_BUTTON_HEIGHT = 40;
    public static final int MAIN_WINDOW_ALERT_DIALOG_WIDTH = 460;
    public static String SELECTED_COLOR = "#63d100";
    public static String UNSELECTED_COLOR = "#cf1717";
    public static int ANIMATION_VELOCITY = 150;

    //New graph window constants
    public static final int NEW_GRAPH_WINDOW_HEIGHT = 500;
    public static final int NEW_GRAPH_WINDOW_WIDTH = 1000;
    public static final int NEW_GRAPH_WINDOW_SIDEBOARD_WIDTH = 200;
    public static final Paint NEW_GRAPH_WINDOW_BORDER_CELL_COLOR = Paint.valueOf("D9D9D9");
    public static final Paint NEW_GRAPH_WINDOW_BORDER_CELL_BORDER_COLOR = Paint.valueOf("404040");
    public static final double NEW_GRAPH_WINDOW_BORDER_WIDTH = 0.5;
    public static final double NEW_GRAPH_WINDOW_CORNER_RADIUS = 5;
    public static int NEW_GRAPH_WINDOW_INITIAL_MATRIX_HEIGHT = 10;
    public static int NEW_GRAPH_WINDOW_INITIAL_MATRIX_WIDTH = 10;

    //Draw graph window
    public static final int DRAW_GRAPH_WINDOW_MENU_WIDTH = 140;

    // Random graph generator
    public static final int RANDOM_GRAPH_GENERATOR_MAX_AMOUNT_OF_VERTEXES = 10;
    public static final int RANDOM_GRAPH_GENERATOR_MIN_AMOUNT_OF_VERTEXES = 5;
    public static final int RANDOM_GRAPH_GENERATOR_MAX_AMOUNT_OF_ADJACENT_VERTEXES = 4;
    public static final int RANDOM_GRAPH_GENERATOR_VERTEX_MIN_COORDINATE = 200;
    public static final int RANDOM_GRAPH_GENERATOR_VERTEX_MAX_COORDINATE = 500;
    public static final int DRAW_NEW_GRAPH_HELP_WINDOW_WIDTH = 400;
    public static final int DRAW_NEW_GRAPH_HELP_WINDOW_HEIGHT = 280;
}
