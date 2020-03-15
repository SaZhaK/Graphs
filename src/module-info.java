module Graphs {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires javafx.graphics;

    exports sample.GUI;

    opens sample;
}