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
	protected Case pos;
	protected boolean alreadyMoved = false;

	public ArrayList<Case> getValidMoves(Board board, Case pos)
	{
		this.pos = pos;
		this.validMoves = new ArrayList<>();
		this.makeValidMoves(board, pos);
		return this.validMoves;
	}

	public ArrayList<Case> getCheckCases(Board board, Case pos)
	{
		this.pos = pos;
		this.validMoves = new ArrayList<>();
		this.makeValidMoves(board, pos);
		return this.validMoves;
	}

	abstract protected void makeValidMoves(Board board, Case pos);

	protected boolean checkCase(Board board, int x, int y)
	{
		if (x < 0 || x >= Board.WIDTH || y < 0 || y >= Board.HEIGHT)
			return false;
		Case pos = board.getPos(x, y);
		Piece piece = pos.getPiece();
		if (piece != null)
		{
			if (piece.getColor() != this.getColor())
				if (!this.isAlwaysCheck(this.pos, board.getPos(x, y)))
					validMoves.add(pos);
			return false;
		}

		if (!this.isAlwaysCheck(this.pos, board.getPos(x, y)))
			validMoves.add(pos);
		return true;
	}

	public Color getColor()
	{
		return this.color;
	}

	public String getFile()
	{
		return this.file;
	}

	public boolean getIsPawn()
	{
		return this.isPawn;
	}

	protected boolean isAlwaysCheck(Case sourcePos, Case targetPos)
	{
		/** TODO for tests Only */
		return false;
		// if (Game.getTurn() != Game.kingInCheck)
		// 	return false;
		// Color saveKingInCheck = Game.kingInCheck;
		// Game game = Game.getInstance();
		// game.move(sourcePos, targetPos);
		// boolean result = saveKingInCheck == Game.kingInCheck;
		// game.undo();
		// return result;
	}
}
