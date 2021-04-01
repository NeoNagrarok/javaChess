package model;

import java.util.ArrayList;

import model.Game.Color;

public class Bishop extends Piece
{
	public Bishop(Color color)
	{
		this.id = "b";
		this.color = color;
	}

	@Override
	public ArrayList<Case> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
