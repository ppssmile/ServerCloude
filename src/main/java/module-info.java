module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.transport.sctp;
    requires io.netty.transport;
    requires io.netty.buffer;

    opens org.example to javafx.fxml;
    exports org.example;
}
