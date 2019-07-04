package sample;

import Main_Classes.Table;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.swing.event.TreeModelEvent;

public class Controller {

    private static Pane Root;

    public void Start_The_Game(ActionEvent actionEvent)
    {
        Root = root;

        //Print_Victory("Tested");

        Table start = new Table();
        start.Start_Game();

        int hh = 0;

       // System.gc();
        //root.getChildren().removeAll();

    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane root;

    @FXML
    private GridPane MyGridPane;

    @FXML
    private Button Start_Button;


    static public void Print_Victory(String Text_Victory)
    {
        Label label = new Label(Text_Victory);
        Root.getChildren().add(label);

    }
}
