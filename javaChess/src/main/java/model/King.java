package model;

import java.util.ArrayList;

import model.Game.Color;

public class King extends Piece
{
	public King(Color color)
	{
		this.id = "K";
		this.color = color;
	}

	@Override
	public ArrayList<Case> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
