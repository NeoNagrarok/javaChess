package javaChess;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private boolean isCaseSelected;
    int w;
    int h;
    private ArrayList<Case> validMoves;

    @FXML
    private GridPane board;

    public void initialize()
    {
        this.game = Game.getInstance();
        this.updateUI();
    }

    private void updateUI()
    {
        if (Game.debug)
        {
            ArrayList<Case> checkCasesCurrentTurn = Game.checkCases.get(Game.getTurn());
            for (Case pos : checkCasesCurrentTurn)
                if (pos.getColor() != Color.RED)
                    pos.setColor(Color.ORANGE);
        }
        ArrayList<ArrayList<Case>> cases = this.game.getCases();
        for (int w = 0; w < Board.WIDTH; w++)
            for (int h = 0; h < Board.HEIGHT; h++)
            {
                Node child = board.getChildren().get(w * Board.WIDTH + h);
                Case pos = cases.get(w).get(h);
                if (pos.getColor() == Color.GREEN)
                    child.setStyle("-fx-background-color : #5A5");
                else if (pos.getColor() == Color.RED)
                    child.setStyle("-fx-background-color : #A55");
                else if (pos.getColor() == Color.ORANGE)
                    child.setStyle("-fx-background-color : #A95");
                else if (pos.getColor() == Color.BLACK)
                    child.setStyle("-fx-background-color : #555");
                else if (pos.getColor() == Color.WHITE)
                    child.setStyle("-fx-background-color : #fff");
                ((Pane)child).getChildren().clear();
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

    @FXML
    private void undo()
    {
        Game.getInstance().undo();
        this.updateUI();
    }

    @FXML
    private void onCaseClick(MouseEvent event)
    {
        Pane pane = (Pane) event.getSource();
        Integer w = GridPane.getRowIndex(pane);
        Integer h = GridPane.getColumnIndex(pane);
        if (w == null)
            w = Integer.valueOf(0);
        if (h == null)
            h = Integer.valueOf(0);
        if (!isCaseSelected)
        {
            Case pos = this.game.getCases().get(w).get(h);
            Piece piece = pos.getPiece();
            if (piece != null && piece.getColor() == Game.getTurn())
            {
                this.validMoves = piece.getValidMoves(this.game.getBoard(), pos);
                for (Case validPos : this.validMoves)
                    validPos.setColor(Color.RED);
                this.w = w.intValue();
                this.h = h.intValue();
                this.game.setColor(Color.GREEN, w, h);
                this.isCaseSelected = true;
            }
        }
        else
        {
            Case targetPos = this.game.getBoard().getPos(w, h);
            if (this.validMoves.indexOf(targetPos) != -1)
            {
                Case sourcePos = this.game.getBoard().getPos(this.w, this.h);
                this.game.move(sourcePos, targetPos);
            }
            if ((this.w + this.h) % 2 == 0)
                this.game.setColor(Color.WHITE, this.w, this.h);
            else
                this.game.setColor(Color.BLACK, this.w, this.h);
            for (Case pos : this.validMoves)
                pos.resetColor();
            // if (Game.debug)
            // {
            //     ArrayList<Case> checkCasesCurrentTurn = Game.checkCases.get(Game.getTurn());
            //     for (Case pos : checkCasesCurrentTurn)
            //         pos.setColor(Color.ORANGE);
            // }
            this.validMoves = null;
            this.isCaseSelected = false;
        }
        this.updateUI();
    }
}
