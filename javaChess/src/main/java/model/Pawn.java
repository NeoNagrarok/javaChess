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
	}

	@Override
	public ArrayList<Case> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
