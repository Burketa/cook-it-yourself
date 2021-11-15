module CookItYourself {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.swt;
    requires java.sql;
    requires com.jfoenix;
    //requires mail;
    //requires twilio;

    opens Controller;
    opens Model;
    opens utils;
    opens View;
}