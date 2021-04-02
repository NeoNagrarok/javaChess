package model;

import java.util.ArrayList;

import model.Game.Color;

public class Board
{
	static final public int WIDTH = 8;
	static final public int HEIGHT = 8;

	private ArrayList<ArrayList<Case>> cases;

	public Board()
	{
		this.cases = new ArrayList<>();
		for (int w = 0; w < Board.WIDTH; w++)
		{
			this.cases.add(new ArrayList<>());
			for(int h = 0; h < Board.HEIGHT; h++)
			{
				if ((w + h) % 2 == 0)
					this.cases.get(w).add(new Case(w, h, Color.WHITE));
				else
					this.cases.get(w).add(new Case(w, h, Color.BLACK));
				PieceFactory.makePiece(this.cases.get(w).get(h));
			}
		}
		this.displayConsole();
	}

	public void displayConsole()
	{
		for (ArrayList<Case> column : this.cases)
			for (Case pos : column)
			{
				System.out.print(pos.getPiece() == null ? pos.getColor() : pos.getPiece().id);
					if (pos.getY() == 7)
						System.out.println();
			}
	}
	
	public Board(Board board)
	{
		this.cases = board.getCases();
	}

	public ArrayList<ArrayList<Case>> getCases()
	{
		ArrayList<ArrayList<Case>> cases = new ArrayList<>();
		for (int w = 0; w < Board.WIDTH; w++)
		{
			cases.add(new ArrayList<>());
			for (int h = 0; h < Board.HEIGHT; h++)
				cases.get(w).add(new Case(this.cases.get(w).get(h)));
		}
		return cases;
	}

	public void setColor(Color color, int w, int h)
	{
		this.cases.get(w).get(h).setColor(color);
	}

	public Case getPos(int x, int y)
	{
		return this.cases.get(x).get(y);
	}
}
