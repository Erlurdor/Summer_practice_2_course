package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Forms/MainForm.fxml"));
        primaryStage.setTitle("Mafia");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        //Timer timer = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate( {    }, 0, 500, TimeUnit.MILLISECONDS)
        //Timer timer = new Timer();

                /*Executors.newSingleThreadScheduledExecutor()

        exec.scheduleAtFixedRate( {

                //кодыч/
               //условие выхода/
                exec.shutdown() //команда для завершения таймера


    }, 0, 500, TimeUnit.MILLISECONDS)
    */

        /*
        Table start = new Table();
        start.Start_Game();
        */
    }
}
