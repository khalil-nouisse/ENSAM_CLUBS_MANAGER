/*module com.example.ensam_clubs2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;
    //requires org.xerial.sqlitejdbc;

    opens com.ensam to javafx.fxml;
    exports com.ensam;
    exports com.ensam.Controllers;
    exports com.ensam.Controllers.Admin;
    exports com.ensam.Controllers.User;
    exports com.ensam.Backend.manager;
    exports com.ensam.Backend.model;
    exports com.ensam.Backend.database;
    exports com.ensam.Views;
}*/
module com.example.ensam_clubs2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    exports com.ensam.Controllers.Admin to javafx.fxml; // Allow FXML to access this package
    exports com.ensam.Controllers.User to javafx.fxml;  // Add if needed for other controllers
    exports com.ensam.Controllers to javafx.fxml;
    opens com.ensam.Controllers.Admin to javafx.fxml;   // Allow reflective access for FXML

    // Opens the package containing your controller classes to JavaFX
    opens com.ensam.Controllers to javafx.fxml;
    opens com.ensam.Backend.model to javafx.base;
    opens com.ensam.Backend.database to javafx.base;

    exports com.ensam;
}