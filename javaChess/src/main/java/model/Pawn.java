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
		{
			this.checkCase(board, --x, y);
			if (!this.alreadyMoved)
				this.checkCase(board, --x, y);
		}
		else if (this.color == Color.BLACK)
		{
			this.checkCase(board, ++x, y);
			if (!this.alreadyMoved)
				this.checkCase(board, ++x, y);
		}
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

	private void goFrontLeftCheck(Board board, int x, int y)
	{
		if (this.color == Color.WHITE)
			this.canCheck(board, --x, --y);
		else if (this.color == Color.BLACK)
			this.canCheck(board, ++x, --y);
	}

	private void goFrontRightCheck(Board board, int x, int y)
	{
		if (this.color == Color.WHITE)
			this.canCheck(board, --x, ++y);
		else if (this.color == Color.BLACK)
			this.canCheck(board, ++x, ++y);
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
		if (!this.isAlwaysCheck(this.pos, board.getPos(x, y)))
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
			if (!this.isAlwaysCheck(this.pos, board.getPos(x, y)))
				validMoves.add(pos);
		return;
	}

	private void canCheck(Board board, int x, int y)
	{
		if (x < 0 || x >= Board.WIDTH || y < 0 || y >= Board.HEIGHT)
			return;
		Case pos = board.getPos(x, y);
		if (!this.isAlwaysCheck(this.pos, board.getPos(x, y)))
			validMoves.add(pos);
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
	

	private void makeCheckCases(Board board, Case pos)
	{
		int x = pos.getX();
		int y = pos.getY();

		this.goFrontLeftCheck(board, x, y);
		this.goFrontRightCheck(board, x, y);
	}

	public void move()
	{
		this.alreadyMoved = true;
	}

	@Override
	public ArrayList<Case> getCheckCases(Board board, Case pos)
	{
		this.validMoves = new ArrayList<>();
		this.makeCheckCases(board, pos);
		return this.validMoves;
	}

}
