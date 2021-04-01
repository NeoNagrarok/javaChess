package model;

import java.util.ArrayList;

import model.Game.Color;

public class Knight extends Piece
{
	public Knight(Color color)
	{
		this.id = "k";
		this.color = color;
		this.file = color.toString().toLowerCase() + "_knight.png";
	}

	private void goUpLeft(Board board, int x, int y)
	{
		this.checkCase(board, (x - 2), --y);
	}

	private void goUpRight(Board board, int x, int y)
	{
		this.checkCase(board, (x - 2), ++y);
	}

	private void goLeftUp(Board board, int x, int y)
	{
		this.checkCase(board, --x, (y - 2));
	}

	private void goRightUp(Board board, int x, int y)
	{
		this.checkCase(board, --x, (y + 2));
	}

	private void goDownLeft(Board board, int x, int y)
	{
		this.checkCase(board, (x + 2), --y);
	}

	private void goDownRight(Board board, int x, int y)
	{
		this.checkCase(board, (x + 2), ++y);
	}

	private void goLeftDown(Board board, int x, int y)
	{
		this.checkCase(board, ++x, (y - 2));
	}

	private void goRightDone(Board board, int x, int y)
	{
		this.checkCase(board, ++x, (y + 2));
	}

	@Override
	protected void makeValidMoves(Board board, Case pos)
	{
		int x = pos.getX();
		int y = pos.getY();

		this.goUpLeft(board, x, y);
		this.goUpRight(board, x, y);
		this.goLeftUp(board, x, y);
		this.goRightUp(board, x, y);
		this.goDownLeft(board, x, y);
		this.goDownRight(board, x, y);
		this.goLeftDown(board, x, y);
		this.goRightDone(board, x, y);
	}
	
}
