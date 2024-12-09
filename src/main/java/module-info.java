module br.com.opusnet.ProjetoAPIIMatematica {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens br.com.opusnet.projetoapiimatematica to javafx.fxml;
    exports br.com.opusnet.projetoapiimatematica;
    exports br.com.opusnet.projetoapiimatematica.scene;
    opens br.com.opusnet.projetoapiimatematica.scene to javafx.fxml;
    exports br.com.opusnet.projetoapiimatematica.controlls;
    opens br.com.opusnet.projetoapiimatematica.controlls to javafx.fxml;
    exports br.com.opusnet.projetoapiimatematica.controlls.screencontrol;
    opens br.com.opusnet.projetoapiimatematica.controlls.screencontrol to javafx.fxml;
}