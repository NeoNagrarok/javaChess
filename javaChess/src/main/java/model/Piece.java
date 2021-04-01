package model;

import java.util.ArrayList;

import model.Game.Color;

public abstract class Piece
{
	protected Color color;
	protected boolean isPawn;
	public String id;

	abstract public ArrayList<Case> getValidMoves();

	public void move(Case origin, Case dest)
	{
		dest.setPiece(origin.getPiece());
		origin.deletePiece();
	}
}
