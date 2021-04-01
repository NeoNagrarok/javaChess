package model;

import java.util.ArrayList;

import model.Game.Color;

public class Rook extends Piece
{
	public Rook(Color color)
	{
		this.id = "r";
		this.color = color;
		this.file = color.toString().toLowerCase() + "_rook.png";
	}

	private void goUp(Board board, int x, int y)
	{
		while (this.checkCase(board, --x, y));
	}

	private void goDown(Board board, int x, int y)
	{
		while (this.checkCase(board, ++x, y));
	}

	private void goLeft(Board board, int x, int y)
	{
		while (this.checkCase(board, x, --y));
	}

	private void goRight(Board board, int x, int y)
	{
		while (this.checkCase(board, x, ++y));
	}

	@Override
	protected void makeValidMoves(Board board, Case pos)
	{
		int x = pos.getX();
		int y = pos.getY();

		this.goUp(board, x, y);
		this.goDown(board, x, y);
		this.goLeft(board, x, y);
		this.goRight(board, x, y);
	}
	
}
