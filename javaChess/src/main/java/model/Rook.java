package model;

import java.util.ArrayList;

import model.Game.Color;

public class Rook extends Piece
{
	public Rook(Color color)
	{
		this.id = "r";
		this.color = color;
	}

	@Override
	public ArrayList<Case> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
