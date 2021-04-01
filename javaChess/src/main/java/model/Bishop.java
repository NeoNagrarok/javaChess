package model;

import java.util.ArrayList;

import model.Game.Color;

public class Bishop extends Piece
{
	public Bishop(Color color)
	{
		this.id = "b";
		this.color = color;
		this.file = color.toString().toLowerCase() + "_bishop.png";
	}

	private void goUpLeft(Board board, int x, int y)
	{
		while (this.checkCase(board, --x, --y));
	}

	private void goUpRight(Board board, int x, int y)
	{
		while (this.checkCase(board, --x, ++y));
	}

	private void goDownLeft(Board board, int x, int y)
	{
		while (this.checkCase(board, ++x, --y));
	}

	private void goDownRight(Board board, int x, int y)
	{
		while (this.checkCase(board, ++x, ++y));
	}

	@Override
	protected void makeValidMoves(Board board, Case pos)
	{
		int x = pos.getX();
		int y = pos.getY();

		this.goUpLeft(board, x, y);
		this.goUpRight(board, x, y);
		this.goDownLeft(board, x, y);
		this.goDownRight(board, x, y);
	}
	
}
