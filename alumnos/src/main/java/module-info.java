module org.utl.dsm.alumnos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fazecast.jSerialComm;


    opens org.utl.dsm.alumnos.vista to javafx.fxml;
    exports org.utl.dsm.alumnos;
}