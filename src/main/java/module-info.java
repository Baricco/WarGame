module com.generalAssets {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.generalAssets to javafx.fxml;
    exports com.generalAssets;
}
