package model;

import java.util.ArrayList;

import model.Game.Color;

public class Pawn extends Piece
{
	public Pawn(Color color)
	{
		this.id = "p";
		this.color = color;
		this.isPawn = true;
		this.file = color.toString().toLowerCase() + "_pawn.png";
	}

	private void goFront(Board board, int x, int y)
	{
		if (this.color == Color.WHITE)
			this.checkCase(board, --x, y);
		else if (this.color == Color.BLACK)
			this.checkCase(board, ++x, y);
	}

	private void goFrontLeft(Board board, int x, int y)
	{
		if (this.color == Color.WHITE)
			this.canTake(board, --x, --y);
		else if (this.color == Color.BLACK)
			this.canTake(board, ++x, --y);
	}

	private void goFrontRight(Board board, int x, int y)
	{
		if (this.color == Color.WHITE)
			this.canTake(board, --x, ++y);
		else if (this.color == Color.BLACK)
			this.canTake(board, ++x, ++y);
	}

	@Override
	protected boolean checkCase(Board board, int x, int y)
	{
		if (x < 0 || x >= Board.WIDTH || y < 0 || y >= Board.HEIGHT)
			return false;
		Case pos = board.getPos(x, y);
		Piece piece = pos.getPiece();
		if (piece != null)
			return false;
		validMoves.add(pos);
		return true;
	}

	private void canTake(Board board, int x, int y)
	{
		if (x < 0 || x >= Board.WIDTH || y < 0 || y >= Board.HEIGHT)
			return;
		Case pos = board.getPos(x, y);
		Piece piece = pos.getPiece();
		if (piece != null && piece.getColor() != this.getColor())
		{
			validMoves.add(pos);
			return;
		}
		return;
	}

	@Override
	protected void makeValidMoves(Board board, Case pos)
	{
		int x = pos.getX();
		int y = pos.getY();

		this.goFront(board, x, y);
		this.goFrontLeft(board, x, y);
		this.goFrontRight(board, x, y);
	}
	
}
