package javaChess;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Board;
import model.Case;
import model.Game;
import model.Piece;
import model.Game.Color;

public class PrimaryController
{

    private Game game;

    @FXML
    private GridPane board;

    public void initialize()
    {
        this.updateUI();
    }

    private void updateUI()
    {
        this.game = Game.getInstance();
        ArrayList<ArrayList<Case>> cases = game.getCases();
        for (int w = 0; w < Board.WIDTH; w++)
            for (int h = 0; h < Board.HEIGHT; h++)
            {
                Node child = board.getChildren().get(w * Board.WIDTH + h);
                Case pos = cases.get(w).get(h);
                if (pos.getColor() == Color.BLACK)
                    child.setStyle("-fx-background-color : #555");
                Piece piece = pos.getPiece();
                if (piece != null)
                {
                    Image image = null;
                    try {
                        image = new Image(App.class.getResource("pieces/" + piece.getFile()).openStream());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(70);
                    imageView.setFitWidth(70);
                    ((Pane)child).getChildren().add(imageView);
                }
            }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
