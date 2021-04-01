package model;

import java.util.ArrayList;

import model.Game.Color;

public class Knight extends Piece
{
	public Knight(Color color)
	{
		this.id = "k";
		this.color = color;
	}

	@Override
	public ArrayList<Case> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
