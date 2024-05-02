module com.assets {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.desktop;
    requires java.logging;
    requires javafx.graphics;
    requires javafx.base;

    opens com.assets.gameAssets to javafx.fxml;
    opens com.assets.generalAssets to javafx.fxml;
    exports com.assets.gameAssets;
    exports com.assets.generalAssets;
}
