module com.groupi.groupi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.groupi.groupi2 to javafx.fxml;
    exports com.groupi.groupi2;
}