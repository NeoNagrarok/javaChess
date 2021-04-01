package model;

import java.util.ArrayList;

import model.Game.Color;

public class Queen extends Piece
{
	public Queen(Color color)
	{
		this.id = "Q";
		this.color = color;
	}

	@Override
	public ArrayList<Case> getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
