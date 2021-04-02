package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
public class Game
{
	static public boolean debug = true;

	private Board board;
	private ArrayList<Board> boardHistory;
	public enum Color
	{
		BLACK,
		WHITE,
		GREEN,
		RED,
		ORANGE
	}
	static private Color turn = Color.WHITE;
	static private Game instance = null;
	// static ArrayList<Case> checkCases;
	static public HashMap<Color, ArrayList<Case>> checkCases;
	static public Color kingInCheck = Color.GREEN;

	private Game()
	{
		this.board = new Board();
		this.boardHistory = new ArrayList<>();
		this.snapshotBoard();
		this.makeCheckState();
	}

	static public Game getInstance()
	{
		if (Game.instance == null)
			Game.instance = new Game();
		return Game.instance;
	}

	static public Color getTurn()
	{
		return Game.turn;
	}

	static public void switchTurn()
	{
		switch (Game.turn)
		{
			case BLACK:
				Game.turn = Color.WHITE;
			break;
			case WHITE:
				Game.turn = Color.BLACK;
			break;
			default:
				Game.turn = Color.WHITE;
			break;
		}
	}

	public Board getBoard()
	{
		return this.board;
	}

	public void newGame()
	{
		Game.instance = new Game();
	}

	public void snapshotBoard()
	{
		this.boardHistory.add(new Board(this.board));
	}

	public void undo()
	{
		int index = this.boardHistory.size() - 1;
		this.board = this.boardHistory.get(index);
		this.boardHistory.remove(index);
		if (Game.turn == Color.BLACK)
			Game.turn = Color.WHITE;
		else if (Game.turn == Color.WHITE)
			Game.turn = Color.BLACK;
		this.makeCheckState();
	}

	public ArrayList<ArrayList<Case>> getCases()
	{
		return this.board.getCases();
	}

	public void setColor(Color color, int w, int h)
	{
		this.board.setColor(color, w, h);
	}

	public void move(Case sourcePos, Case targetPos)
	{
		if (sourcePos == null)
			return;
		Piece piece = sourcePos.getPiece();
		if (piece != null && piece.getIsPawn())
			((Pawn)piece).move();
		targetPos.setPiece(piece);
		sourcePos.deletePiece();
		Game.switchTurn();
		this.makeCheckState();
		this.snapshotBoard();
	}

	public void makeCheckState()
	{
		Game.checkCases = new HashMap<>();
		Game.checkCases.put(Color.BLACK, new ArrayList<>());
		Game.checkCases.put(Color.WHITE, new ArrayList<>());
		Game.kingInCheck = Color.GREEN;
		for (int w = 0; w < Board.WIDTH; w++)
			for (int h = 0; h < Board.HEIGHT; h++)
			{
				Case pos = this.board.getPos(w, h);
				pos.resetColor();
				Piece piece = pos.getPiece();
				if (piece != null)
				{
					ArrayList<Case> validMoves = piece.getCheckCases(
						this.board, pos
					);
					for (Case posCheck : validMoves)
					{
						Game.checkCases.get(piece.getColor()).add(posCheck);
						Piece pieceChecked = posCheck.getPiece();
						if (pieceChecked != null &&  pieceChecked.id == "K")
							Game.kingInCheck = pieceChecked.getColor();

					}
				}
			}
		System.out.println(Game.checkCases.get(Color.BLACK).size());
		Game.checkCases.replace(Color.BLACK, Game.checkCases.get(Color.BLACK), Game.removeDuplicates(Game.checkCases.get(Color.BLACK)));
		System.out.println(Game.checkCases.get(Color.BLACK).size());
		System.out.println("-----------------------------");
		System.out.println(Game.checkCases.get(Color.WHITE).size());
		Game.checkCases.replace(Color.WHITE, Game.checkCases.get(Color.WHITE), Game.removeDuplicates(Game.checkCases.get(Color.WHITE)));
		System.out.println(Game.checkCases.get(Color.WHITE).size());
		System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
  
        // Create a new LinkedHashSet
        Set<T> set = new LinkedHashSet<>();
  
        // Add the elements to set
        set.addAll(list);
  
        // Clear the list
        list.clear();
  
        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);
  
        // return the list
        return list;
    }

}
