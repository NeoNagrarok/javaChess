package model;

import model.Game.Color;

public class Case
{
	private Color color;
	private int x;
	private int y;
	private Piece piece = null;

	public Case(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public Case(Case original)
	{
		this.x = original.getX();
		this.y = original.getY();
		this.color = original.getColor();
		this.piece = original.getPiece();
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public Color getColor()
	{
		return this.color;
	}

	public Piece getPiece()
	{
		return this.piece;
	}

	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}

	public void deletePiece()
	{
		this.piece = null;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public void resetColor()
	{
		if ((this.x + this.y) % 2 == 0)
			this.color = Color.WHITE;
		else
			this.color = Color.BLACK;
	}
}
