module mt.moodtracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens mt.moodtracker to javafx.fxml;
    exports mt.moodtracker;
}