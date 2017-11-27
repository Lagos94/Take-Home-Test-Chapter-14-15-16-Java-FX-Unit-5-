/*By Juan Lagos
* CSC 201
* Professor Tanes Kanchanawanchai
* Take Home Test
* Due Date: 11/26/17*/
//When executing this code, the same exact info/values depicted in Figure 16.46 were tested and worked on 11/26/2017.
//Lines 47, 49, 51, and 53 include remarks with the inputs for each textfield, taken from Figure 16.46.

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class ImageAnimator extends Application {
    private TextField Speed = new TextField();
    private TextField Prefix = new TextField();
    private TextField NumberOfImages = new TextField();
    private TextField URL = new TextField();
    private StackPane imagePane = new StackPane();
    private Timeline animation;
    private int n = 1;

    public void start(Stage primaryStage) {
        final int ColumnCT = 30;
        URL.setPrefColumnCount(ColumnCT);
        Prefix.setPrefColumnCount(ColumnCT);
        Speed.setPrefColumnCount(ColumnCT);
        NumberOfImages.setPrefColumnCount(ColumnCT);

        GridPane infoPane = new GridPane();
        infoPane.setAlignment(Pos.CENTER);
        infoPane.add(new Label("Enter information for animation"), 0, 0);
        infoPane.add(new Label("Animation speed in milliseconds"), 0, 1);
        infoPane.add(Speed, 1, 1); //200
        infoPane.add(new Label("Image file prefix"), 0, 2);
        infoPane.add(Prefix, 1, 2); //L
        infoPane.add(new Label("Number of images"), 0, 3);
        infoPane.add(NumberOfImages, 1, 3); //24
        infoPane.add(new Label("Audio file URL"), 0, 4);
        infoPane.add(URL, 1, 4); //http://www.cs.armstrong.edu/liang/common/audio/anthem/anthem2.mp3

        Button startBtn = new Button("Start Animation");
        BorderPane pane = new BorderPane();
        pane.setBottom(infoPane);
        pane.setCenter(imagePane);
        pane.setTop(startBtn);
        pane.setAlignment(startBtn, Pos.TOP_RIGHT);

        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> nextImage()));
        animation.setCycleCount(Timeline.INDEFINITE);

        startBtn.setOnAction(e -> {
            if (URL.getText().length() > 0) {
                MediaPlayer mediaPlayer = new MediaPlayer(new Media(URL.getText()));
                mediaPlayer.play();
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
            if (Speed.getText().length() > 0)
                animation.setRate(Integer.parseInt(Speed.getText()));
            animation.play();
        });

        Scene scene = new Scene(pane, 600, 300);
        primaryStage.setTitle("Take Home Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getImage() {
        imagePane.getChildren().clear();
        imagePane.getChildren().add(new ImageView(new Image(
                "http://cs.armstrong.edu/liang/common/image/"+Prefix.getText()+n+".gif")));
    }

    private void nextImage() {
        n = n < Integer.parseInt(NumberOfImages.getText()) ? n += 1 : 1;
        getImage();
    }
}