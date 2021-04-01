package model;

import java.util.ArrayList;

import model.Game.Color;

public abstract class Piece
{
	private Color color;
	private boolean isPawn;

	abstract public ArrayList<Case> getValidMoves();

	public void move(Case origin, Case dest)
	{
		dest.setPiece(origin.getPiece());
		origin.deletePiece();
	}
}
