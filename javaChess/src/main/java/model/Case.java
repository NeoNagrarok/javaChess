package model;

import model.Game.Color;

public class Case
{
	private Color color;
	private int x;
	private int y;
	// private Piece piece;
	public enum Check
	{
		NONE,
		BLACK,
		WHITE,
		BOTH
	}
	private Check check = Check.NONE;

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
		this.check = original.getCheck();
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public Check getCheck()
	{
		return this.check;
	}

	public Color getColor()
	{
		return this.color;
	}
}
