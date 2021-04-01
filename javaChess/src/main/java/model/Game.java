package model;

import java.util.ArrayList;

public class Game
{
	private Board board;
	private ArrayList<Board> boardHistory;
	public enum Color
	{
		BLACK,
		WHITE
	}
	private Color turn = Color.WHITE;
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

	public Color getTurn()
	{
		return this.turn;
	}

	public void switchTurn()
	{
		switch (this.turn)
		{
			case BLACK:
				this.turn = Color.WHITE;
			break;
			case WHITE:
				this.turn = Color.BLACK;
			break;
			default:
				this.turn = Color.WHITE;
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

}
