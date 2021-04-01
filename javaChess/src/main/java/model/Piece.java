package model;

import java.util.ArrayList;

import model.Game.Color;

public abstract class Piece
{
	public String id;

	protected Color color;
	protected boolean isPawn;
	protected ArrayList<Case> validMoves;
	protected String file;

	public ArrayList<Case> getValidMoves(Board board, Case pos)
	{
		this.validMoves = new ArrayList<>();
		this.makeValidMoves(board, pos);
		return this.validMoves;
	}

	abstract protected void makeValidMoves(Board board, Case pos);

	protected boolean checkCase(Board board, int x, int y)
	{
		if (x < 0 || x > Board.WIDTH || y < 0 || y > Board.HEIGHT)
			return false;
		Case pos = board.getCases().get(x).get(y);
		Piece piece = pos.getPiece();
		if (piece != null && piece.getColor() == this.getColor())
		{
			validMoves.add(pos);
			return false;
		}

		validMoves.add(pos);
		return true;
	}

	public void move(Case origin, Case dest)
	{
		dest.setPiece(origin.getPiece());
		origin.deletePiece();
	}

	public Color getColor()
	{
		return this.color;
	}

	public String getFile()
	{
		return this.file;
	}
}
