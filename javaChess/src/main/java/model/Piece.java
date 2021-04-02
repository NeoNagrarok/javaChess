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
	protected boolean isNormalMove = true;

	public Piece(){}

	public Piece(Piece origin)
	{
		this.id = origin.id;
		this.color = origin.getColor();
		this.isPawn = origin.getIsPawn();
		this.file = origin.getFile();
		this.alreadyMoved = origin.getAlreadyMoved();
	}

	public ArrayList<Case> getValidMoves(Board board, Case pos)
	{
		this.isNormalMove = true;
		this.validMoves = new ArrayList<>();
		this.makeValidMoves(board, pos);
		return this.validMoves;
	}

	public ArrayList<Case> getCheckCases(Board board, Case pos)
	{
		this.isNormalMove = false;
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
				if (!this.isNormalMove || !this.isAlwaysCheck(board.getPos(x, y)))
					validMoves.add(pos);
			return false;
		}

		if (!this.isNormalMove || !this.isAlwaysCheck(board.getPos(x, y)))
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

	public boolean getAlreadyMoved()
	{
		return this.alreadyMoved;
	}

	protected boolean isAlwaysCheck(Case targetPos)
	{
		// /** TODO for tests Only */
		// return false;
		if (Game.getTurn() != Game.kingInCheck)
			return false;
		Color saveKingInCheck = Game.kingInCheck;
		System.out.println("Save : " + saveKingInCheck);
		Game newGame = Game.copy();
		Board newBoard = newGame.getBoard();
		newGame.move(
			newBoard.getPos(this.pos.getX(), this.pos.getY()),
			newBoard.getPos(targetPos.getX(), targetPos.getY())
		);
		System.out.println("New save : " + Game.kingInCheck);
		boolean result = false;
		if (saveKingInCheck == Game.kingInCheck)
			result = true;
		Game.kingInCheck = saveKingInCheck;
		Game.switchTurn();
		System.out.println(result);
		System.out.println("-----------------------------");
		return result;
	}
}
