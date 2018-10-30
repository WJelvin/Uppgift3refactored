package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    Board board = new Board(4, 4);
    GridPane grid = new GridPane();
    private int sceneWidth = 500;
    private int sceneHeight = 500;
    EventHandler tileHandler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            Button temp = (Button) e.getSource();
            board.tryToSwitch(temp.getText());
            setScene();
        }
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        setScene();

        board.printBoard();

        primaryStage.setTitle("Tilemaster 2000");
        primaryStage.setScene(new Scene(grid, sceneWidth, sceneHeight));
        primaryStage.show();
    }

//    public void rePaint() {
//        for (Node b : grid.getChildren()) {
//            ((Button) b).setText("lol");
//        }
//    }

    public void setScene() {
        grid.getChildren().removeAll();
        for (int x = 0; x < board.getPuzzleWidth(); x++) {
            for (int y = 0; y < board.getPuzzleHeight(); y++) {
                Button b = new Button(String.valueOf(board.getValue(x, y)));
                if (Integer.parseInt(b.getText()) == board.getNumberOfTiles())
                    b.setText("");
                b.setMinSize(sceneWidth / (double) board.getPuzzleWidth(), sceneHeight / (double) board.getPuzzleWidth());
                b.setOnAction(tileHandler);
                grid.add(b, x, y);
            }
        }
    }
}
