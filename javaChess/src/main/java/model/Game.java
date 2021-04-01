package model;

import java.util.ArrayList;
public class Game
{
	private Board board;
	private ArrayList<Board> boardHistory;
	public enum Color
	{
		BLACK,
		WHITE, GREEN, RED
	}
	static private Color turn = Color.WHITE;
	static private Game instance = null;

	private Game()
	{
		this.board = new Board();
		this.boardHistory = new ArrayList<>();
		this.snapshotBoard();
	}

	static public Game getInstance()
	{
		return Game.instance == null ? new Game() : Game.instance;
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
		this.board = this.boardHistory.get(this.boardHistory.size() - 1);
	}

	public ArrayList<ArrayList<Case>> getCases()
	{
		return this.board.getCases();
	}

	public void setColor(Color color, int w, int h)
	{
		this.board.setColor(color, w, h);
	}

}
