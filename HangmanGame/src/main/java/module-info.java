module com.example.hangmangame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hangmangame to javafx.fxml;
    exports com.example.hangmangame;
}