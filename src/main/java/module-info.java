module com.example.thirddatastructureproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thirddatastructureproject to javafx.fxml;
    exports com.example.thirddatastructureproject;
}