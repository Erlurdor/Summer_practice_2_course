package sample;

import Main_Classes.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {



    private static Pane Root;

    public void Start_The_Game(ActionEvent actionEvent)
    {
        Root = root;

        //Print_Victory("Tested");
        Table start = Table.Get_Instance();
        //Table start = new Table();
        start.Init();

        //int Time = 0;           //время суток. 0 - Ночь, 1 - День
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Table table = Table.Get_Instance();

                table.Walk(0);

                table.Walk(1);
                // System.out.println("День");

                if (table.Is_Game_Over() == true)
                    timer.cancel();
            }
        };

        timer.schedule(timerTask,0,500);

        //if (start.Is_Game_Over() == true)
        //    timer.cancel();

        // Таймер

        //java.util.Timer timer = new java.util.Timer();
        //java.util.Timer timer = new java.util.Timer();
        //timer.schedule(new MyTimerTask(),  0, 1000);
        // Таймер
        /*
        javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
           start.Walk(0);
           System.out.println("Ночь");

           start.Walk(1);
           System.out.println("День");
        });

        timer.start();
*/
        //Executors a =
        /*
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

        timer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // your code here
                start.Walk(0);
                System.out.println("Ночь");
                start.Walk(1);
                System.out.println("День");

                //if (start.Is_Game_Over() == true)
                    //timer.shutdown();
            }
        }, 0, 1000, TimeUnit.SECONDS);
*/
/*
       timer.scheduleAtFixedRate(Action(Time), 0, 1*1000);

       TimerTask Action = new TimerTask() {
            @Override
            public void run() {
                Time = start.Walk(Time);
            }
        }
*/
       // System.gc();
        //root.getChildren().removeAll();

    }
/*
    class MyTimerTask extends TimerTask {
        public void run() {
            //System.out.println("BANG!!!");
            Table table = Table.Get_Instance();

        }
    }
    */


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
