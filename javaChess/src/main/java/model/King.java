package model;

import java.util.ArrayList;

import model.Game.Color;

public class King extends Piece
{
	public King(Color color)
	{
		this.id = "K";
		this.color = color;
		this.file = color.toString().toLowerCase() + "_king.png";
	}

	private void goUp(Board board, int x, int y)
	{
		this.checkCase(board, --x, y);
	}

	private void goDown(Board board, int x, int y)
	{
		this.checkCase(board, ++x, y);
	}

	private void goLeft(Board board, int x, int y)
	{
		this.checkCase(board, x, --y);
	}

	private void goRight(Board board, int x, int y)
	{
		this.checkCase(board, x, ++y);
	}

	private void goUpLeft(Board board, int x, int y)
	{
		this.checkCase(board, --x, --y);
	}

	private void goUpRight(Board board, int x, int y)
	{
		this.checkCase(board, --x, ++y);
	}

	private void goDownLeft(Board board, int x, int y)
	{
		this.checkCase(board, ++x, --y);
	}

	private void goDownRight(Board board, int x, int y)
	{
		this.checkCase(board, ++x, ++y);
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
		this.goUpLeft(board, x, y);
		this.goUpRight(board, x, y);
		this.goDownLeft(board, x, y);
		this.goDownRight(board, x, y);
	}
	
}
