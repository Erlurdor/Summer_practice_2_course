package sample;

import Main_Classes.Player;
import Main_Classes.Table;
import javafx.application.Platform;
import javafx.beans.value.ObservableListValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label8_Luck;

    @FXML
    private Label label9_Diead;

    @FXML
    private Label label3_Luck;

    @FXML
    private Label label7_Name;

    @FXML
    private Label label6_Diead;

    @FXML
    private Label label9_Luck;

    @FXML
    private Label label2_Luck;

    @FXML
    private Label label1_Name;

    @FXML
    private Label label3_Diead;

    @FXML
    private Label label10_Diead;

    @FXML
    private Label time_of_day_Value;

    @FXML
    private Label label5_Role;

    @FXML
    private Label label1_Role;

    @FXML
    private Label label5_Name;

    @FXML
    private Label label4_Role;

    @FXML
    private Pane Pane9;

    @FXML
    private Pane Pane8;

    @FXML
    private Label label2_Name;

    @FXML
    private Label label7_Role;

    @FXML
    private Button Start_Button;

    @FXML
    private Pane Pane10;

    @FXML
    private Label label8_Name;

    @FXML
    private ListView<String> MyListView;

    @FXML
    private Pane Pane5;

    @FXML
    private Label time_of_day;

    @FXML
    private Pane Pane4;

    @FXML
    private Pane Pane7;

    @FXML
    private Pane Pane6;

    @FXML
    private Pane Pane1;

    @FXML
    private Pane Pane3;

    @FXML
    private Pane Pane2;

    @FXML
    private Label label7_Diead;

    @FXML
    private Label label4_Diead;

    @FXML
    private Label label2_Died;

    @FXML
    private Label label5_Luck;

    @FXML
    private Label label3_Name;

    @FXML
    private Label label9_Role;

    @FXML
    private Label label6_Luck;

    @FXML
    private Label label1_Diead;

    @FXML
    private Label label10_Role;

    @FXML
    private Label label8_Diead;

    @FXML
    private Pane root;

    @FXML
    private Label Days;

    @FXML
    private Label label10_Name;

    @FXML
    private Label Days_Value;

    @FXML
    private Label label2_Role;

    @FXML
    private Label label8_Role;

    @FXML
    private Label label4_Name;

    @FXML
    private Label label4_Luck;

    @FXML
    private Label label1_Luck;

    @FXML
    private Label label5_Diead;

    @FXML
    private Label label6_Role;

    @FXML
    private Label label10_Luck;

    @FXML
    private Label label3_Role;

    @FXML
    private Label label6_Name;

    @FXML
    private Label label9_Name;

    @FXML
    private Label label7_Luck;

    @FXML
    private Label win;

    private static Pane Root;



    public void Start_The_Game(ActionEvent actionEvent) {
        Root = root;

        Pane1.setVisible(true);

        Table start = Table.Get_Instance();

        //Label[] label_Roles = new Label[start.getNum_Of_Players()];
       // Label[] label_Died = new Label[start.getNum_Of_Players()];

        //Init_Roles(label_Roles, start);                 //массив полей ролей
       // Init_Died(label_Died, start);

        start.Init();
        Draw_All_Elem(start);


        //int Time = 0;           //время суток. 0 - Ночь, 1 - День
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            int Time = 0;

            @Override
            public void run() {
                Table table = Table.Get_Instance();

                Time = table.Walk(Time);
                Refresh_Field(table);
                //Set_Visible_Panes(false);
                //table.Walk(Time);
                // System.out.println("День");

                if (table.Is_Game_Over() == true)
                {
                    timer.cancel();
                    Refresh_Roles(table);
                    Start_Button.setVisible(true);
                    //Set_Visible_Panes(false);
                }

            }
        };

        timer.schedule(timerTask, 100, 1500);
    }

    private void Refresh_Roles(Table table) {
        Player[] Bots = table.getBots();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label1_Role.setText(Bots[0].Get_Role());
                label2_Role.setText(Bots[1].Get_Role());
                label3_Role.setText(Bots[2].Get_Role());
                label4_Role.setText(Bots[3].Get_Role());
                label5_Role.setText(Bots[4].Get_Role());
                label6_Role.setText(Bots[5].Get_Role());
                label7_Role.setText(Bots[6].Get_Role());
                label8_Role.setText(Bots[7].Get_Role());
                label9_Role.setText(Bots[8].Get_Role());
                label10_Role.setText(Bots[9].Get_Role());
                label10_Role.setText(Bots[9].Get_Role());
            }

        });


    }


    private void Init_Died(Label[] label_died, Table start) {
        label_died[0] = label1_Diead;
        label_died[1] = label2_Died;
        label_died[2] = label3_Diead;
        label_died[3] = label4_Diead;
        label_died[4] = label5_Diead;
        label_died[5] = label6_Diead;
        label_died[6] = label7_Diead;
        label_died[7] = label8_Diead;
        label_died[8] = label9_Diead;
        label_died[9] = label10_Diead;

    }

    private void Init_Roles(Label[] lable_roles, Table start) {
        lable_roles[0] = label1_Role;
        lable_roles[1] = label2_Role;
        lable_roles[2] = label3_Role;
        lable_roles[3] = label4_Role;
        lable_roles[4] = label5_Role;
        lable_roles[5] = label6_Role;
        lable_roles[6] = label7_Role;
        lable_roles[7] = label8_Role;
        lable_roles[8] = label9_Role;
        lable_roles[9] = label10_Role;
    }


    public void Refresh_Field(Table table) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //time_of_day_Value.setText("ПАМАГИИТИ");
                time_of_day_Value.setText(table.getCur_Time());
                Days_Value.setText(String.valueOf(table.getNum_Of_Days()));

                Refresh_Roles_And_Died(table);

                if (table.getCur_Time() == "Ночь")
                {
                    MyListView.getItems().add(table.getEvent_Mafia());
                    MyListView.getItems().add(table.getEvent_Doctor());
                    MyListView.getItems().add(table.getEvent_Policeman());
                }

                if (table.getCur_Time() == "День")
                    MyListView.getItems().add(table.getEvent_Votes());


                if (table.Is_Game_Over() == true)
                {
                    win.setText(table.getVictory());
                    win.setVisible(true);
                }
            }

        });


    }
    private void Refresh_Roles_And_Died(Table table) {
        Player[] Bots = table.getBots();

        if (Bots[0].Get_Diead() == true)
        {
            label1_Diead.setText("True");
            label1_Role.setText(Bots[0].Get_Role());
        }

        if (Bots[1].Get_Diead() == true)
        {
            label2_Died.setText("True");
            label2_Role.setText(Bots[1].Get_Role());
        }

        if (Bots[2].Get_Diead() == true)
        {
            label3_Diead.setText("True");
            label3_Role.setText(Bots[2].Get_Role());
        }

        if (Bots[3].Get_Diead() == true)
        {
            label4_Diead.setText("True");
            label4_Role.setText(Bots[3].Get_Role());
        }

        if (Bots[4].Get_Diead() == true)
        {
            label5_Diead.setText("True");
            label5_Role.setText(Bots[4].Get_Role());
        }

        if (Bots[5].Get_Diead() == true)
        {
            label6_Diead.setText("True");
            label6_Role.setText(Bots[5].Get_Role());
        }

        if (Bots[6].Get_Diead() == true)
        {
            label7_Diead.setText("True");
            label7_Role.setText(Bots[6].Get_Role());
        }

        if (Bots[7].Get_Diead() == true)
        {
            label8_Diead.setText("True");
            label8_Role.setText(Bots[7].Get_Role());
        }

        if (Bots[8].Get_Diead() == true)
        {
            label9_Diead.setText("True");
            label9_Role.setText(Bots[8].Get_Role());
        }

        if (Bots[9].Get_Diead() == true)
        {
            label10_Diead.setText("True");
            label10_Role.setText(Bots[9].Get_Role());
        }

    }

    private void Draw_All_Elem(Table table) {
        Set_Visible_Panes(true);
        Player[] Bots = table.getBots();
        MyListView.getItems().clear();

        label1_Name.setText(Bots[0].Get_Name());
        label2_Name.setText(Bots[1].Get_Name());
        label3_Name.setText(Bots[2].Get_Name());
        label4_Name.setText(Bots[3].Get_Name());
        label5_Name.setText(Bots[4].Get_Name());
        label6_Name.setText(Bots[5].Get_Name());
        label7_Name.setText(Bots[6].Get_Name());
        label8_Name.setText(Bots[7].Get_Name());
        label9_Name.setText(Bots[8].Get_Name());
        label10_Name.setText(Bots[9].Get_Name());

        /*
        label1_Role.setText(Bots[0].Get_Role());
        label2_Role.setText(Bots[1].Get_Role());
        label3_Role.setText(Bots[2].Get_Role());
        label4_Role.setText(Bots[3].Get_Role());
        label5_Role.setText(Bots[4].Get_Role());
        label6_Role.setText(Bots[5].Get_Role());
        label7_Role.setText(Bots[6].Get_Role());
        label8_Role.setText(Bots[7].Get_Role());
        label9_Role.setText(Bots[8].Get_Role());
        label10_Role.setText(Bots[9].Get_Role());
        */
//        for (int i = 0; i < label_roles.length; i++)
//        {
//            label_roles[i].setText("Secret!");
//        }

        label1_Role.setText("Secret!");
        label2_Role.setText("Secret!");
        label3_Role.setText("Secret!");
        label4_Role.setText("Secret!");
        label5_Role.setText("Secret!");
        label6_Role.setText("Secret!");
        label7_Role.setText("Secret!");
        label8_Role.setText("Secret!");
        label9_Role.setText("Secret!");
        label10_Role.setText("Secret!");

        label1_Luck.setText(String.valueOf(Bots[0].Get_Luck()));
        label2_Luck.setText(String.valueOf(Bots[1].Get_Luck()));
        label3_Luck.setText(String.valueOf(Bots[2].Get_Luck()));
        label4_Luck.setText(String.valueOf(Bots[3].Get_Luck()));
        label5_Luck.setText(String.valueOf(Bots[4].Get_Luck()));
        label6_Luck.setText(String.valueOf(Bots[5].Get_Luck()));
        label7_Luck.setText(String.valueOf(Bots[6].Get_Luck()));
        label8_Luck.setText(String.valueOf(Bots[7].Get_Luck()));
        label9_Luck.setText(String.valueOf(Bots[8].Get_Luck()));
        label10_Luck.setText(String.valueOf(Bots[9].Get_Luck()));





    }

    private void Set_Visible_Panes(boolean b) {
        Pane1.setVisible(b);
        Pane2.setVisible(b);
        Pane3.setVisible(b);
        Pane4.setVisible(b);
        Pane5.setVisible(b);
        Pane6.setVisible(b);
        Pane7.setVisible(b);
        Pane8.setVisible(b);
        Pane9.setVisible(b);
        Pane10.setVisible(b);

        MyListView.setVisible(b);
        Start_Button.setVisible(!b);

        Days.setVisible(b);
        Days_Value.setVisible(b);

        time_of_day.setVisible(b);
        time_of_day_Value.setVisible(b);
        win.setVisible(!b);

    }

    static public void Print_Victory(String Text_Victory)
    {
        Label label = new Label(Text_Victory);
        Root.getChildren().add(label);

    }
}
