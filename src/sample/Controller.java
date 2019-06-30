package sample;

import Main_Classes.Table;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    public void Start_The_Game(ActionEvent actionEvent)
    {
        Table start = new Table();
        start.Start_Game();


    }
}
