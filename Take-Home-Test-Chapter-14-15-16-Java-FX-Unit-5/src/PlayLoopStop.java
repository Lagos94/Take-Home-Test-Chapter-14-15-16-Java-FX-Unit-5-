/*By Juan Lagos
* CSC 201
* Professor Tanes Kanchanawanchai
* Take Home Test
* Due Date: 11/26/17*/

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class PlayLoopStop extends Application {
    public void start(Stage primaryStage) {
        Button play = new Button("Play");
        Button loop = new Button("Loop");
        Button stop = new Button("Stop");

        HBox mainPane = new HBox(85);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(1, 1, 1, 1));
        mainPane.getChildren().addAll(play, loop, stop);

        final String website = "http://www.cs.armstrong.edu/liang/common/audio/anthem/anthem2.mp3";
        AudioClip audioClip = new AudioClip(website);

        play.setOnAction(e-> {
            if (audioClip.isPlaying()) {audioClip.stop();}
            audioClip.setCycleCount(1);
            audioClip.play();
        });
        stop.setOnAction(e -> {
            audioClip.stop();
        });
        loop.setOnAction(e -> {
            if (audioClip.isPlaying()) {audioClip.stop(); }
            {audioClip.setCycleCount(AudioClip.INDEFINITE); audioClip.play(); }
        });

        Scene mainStage = new Scene(mainPane);
        primaryStage.setTitle("Now Playing: Anthem 2");
        primaryStage.setScene(mainStage);
        primaryStage.show();
    }
}