package minefinder.multiplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MineFinderMultiPlayer extends Application implements Observer {

    Modell modell = new Modell(this);
    MineButton buttons[][] = new MineButton[16][16];
    Label label;

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button();
        label = new Label();
        GridPane gridPane = new GridPane();

        button.setText("New Game");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().add(label);
        vBox.getChildren().add(button);
        vBox.getChildren().add(gridPane);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                MineButton mineButton = new MineButton(i, j);
                mineButton.setMinSize(30, 30);
                gridPane.add(mineButton, j, i);

                mineButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        MineButton b = (MineButton) event.getSource();
                        modell.peek(b.x, b.y);
                    }
                });
                buttons[i][j] = mineButton;
            }
        }

        Scene scene = new Scene(vBox, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void revealedField(int x, int y, int value, int player) {
        buttons[x][y].setText("" + value);
        buttons[x][y].setDisable(true);
    }

    @Override
    public void gameStateChanged(int player, int remainingMines, int redScore, int blueScore) {
        label.setText("" + player + ", " + remainingMines + ", " + redScore + ", " + blueScore);
    }

}
