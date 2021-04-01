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
				/**
				 * Display
				 */
				Case pos = this.cases.get(w).get(h);
				System.out.print(pos.getPiece() == null ? pos.getColor() : pos.getPiece().id);
				if (h == 7)
					System.out.println();
			}
		}
	}

	public Board(Board board)
	{
		/** TODO */
		this.cases = board.getCases();
	}

	public boolean isValidMove()
	{
		/** TODO */
		return true;
	}

	public ArrayList<ArrayList<Case>> getCases()
	{
		/** COPY TODO */
		ArrayList<ArrayList<Case>> cases = new ArrayList<>();
		for (int w = 0; w < Board.WIDTH; w++)
		{
			cases.add(new ArrayList<>());
			for (int h = 0; h < Board.HEIGHT; h++)
				cases.get(w).add(new Case(this.cases.get(w).get(h)));
		}
		return cases;
	}
}
