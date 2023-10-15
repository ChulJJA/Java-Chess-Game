package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Pawn {
	private boolean enPassant = false;
	private int numOfEnPassantTurns = 0;

	public static ReturnPlay.Message IsMoveValid(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) 
	{
		if(Chess.PieceInBoard(mov_file, mov_rank) == false)
		{
			return ReturnPlay.Message.ILLEGAL_MOVE;
		}

		PieceFile left_file_bound = PieceFile.values()[cur_file.ordinal() - 1];
		PieceFile right_file_bound = PieceFile.values()[cur_file.ordinal() + 1];
		int top_rank_bound = cur_rank + 1;
		int bottom_rank_bound = cur_rank - 1;

		if (IsEnpassantValid(mov_file, mov_rank))
		{
			return null;
		}

		if (piece_type == PieceType.WP) 
		{
			for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
			{
				if(cur_file == mov_file)
				{
					if(cur_rank + 2 == cur_rank && cur_rank != 1)
					{
						return ReturnPlay.Message.ILLEGAL_MOVE;
					}
					if(rp.pieceFile == mov_file && rp.pieceRank == mov_rank)
					{
						return ReturnPlay.Message.ILLEGAL_MOVE;
					}
				}

				if(rp.pieceFile == right_file_bound && rp.pieceRank == top_rank_bound)
				{
					if(rp.pieceType.toString().contains("b"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
					}
				}
				else if(rp.pieceFile == left_file_bound && rp.pieceRank == top_rank_bound)
				{
					if(rp.pieceType.toString().contains("b"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
					}
				}
			}
		}
		else if(piece_type == PieceType.BP)
		{
			for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
			{
				if(cur_file == mov_file)
				{
					if(cur_rank + 2 == cur_rank && cur_rank != 7)
					{
						return ReturnPlay.Message.ILLEGAL_MOVE;
					}
					if(rp.pieceFile == mov_file && rp.pieceRank == mov_rank)
					{
						return ReturnPlay.Message.ILLEGAL_MOVE;
					}
				}

				if(rp.pieceFile == right_file_bound && rp.pieceRank == bottom_rank_bound)
				{
					if(rp.pieceType.toString().contains("w"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
					}
				}
				else if(rp.pieceFile == left_file_bound && rp.pieceRank == bottom_rank_bound)
				{
					if(rp.pieceType.toString().contains("w"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
					}
				}
			}
		}
			


		return null;
	}

	 public static boolean IsEnpassantValid(PieceFile mov_file, int mov_rank) 
	 {
	// 	PieceFile left_file = PieceFile.values()[mov_file.ordinal() - 1];
	// 	PieceFile right_file = PieceFile.values()[mov_file.ordinal() + 1];
	// 	ReturnPiece mov_piece;

	// 	if (GetPieceType() ==  PieceType.WP) {
	// 		if (left_file.toString().charAt(0) - 1 >= 'a') 
	// 		{
	// 			for(ReturnPiece rp : Chess.return_play.piecesOnBoard)
	// 			{
	// 				if((rp.pieceFile == left_file && rp.pieceRank == GetPieceRank()) instanceof Pawn)
	// 				{

	// 				}
	// 			}
	// 			if (Chess.activeBoard.getChessPiece(left_file, GetPieceRank()) instanceof Pawn) {
	// 				if (((Pawn) Chess.activeBoard.getChessPiece(leftCol, getRow())).IsEnPassant()) {
	// 					if (Chess.activeBoard.getCell(leftCol, getRow()).contains("b")) {
	// 						if (mov_file == leftCol && mov_rank == getRow() + 1) {
	// 							Chess.activeBoard.getChessPieces()
	// 									.remove(Chess.activeBoard.getChessPiece(leftCol, getRow()));
	// 							Chess.activeBoard.initStateOfBoard();
	// 							return true;
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 		if (++rightCol <= 'h') {
	// 			if (Chess.activeBoard.getChessPiece(rightCol, getRow()) instanceof Pawn) {
	// 				if (((Pawn) Chess.activeBoard.getChessPiece(rightCol, getRow())).IsEnPassant()) {
	// 					if (Chess.activeBoard.getCell(rightCol, getRow()).contains("b")) {
	// 						if (mov_file == rightCol && mov_rank == getRow() + 1) {
	// 							Chess.activeBoard.getChessPieces()
	// 									.remove(Chess.activeBoard.getChessPiece(rightCol, getRow()));
	// 							Chess.activeBoard.initStateOfBoard();
	// 							return true;
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}
	// 	if (getChessPieceName().equals("bp")) {
	// 		if (--leftCol >= 'a') {
	// 			if (Chess.activeBoard.getChessPiece(leftCol, getRow()) instanceof Pawn) {
	// 				if (((Pawn) Chess.activeBoard.getChessPiece(leftCol, getRow())).IsEnPassant()) {
	// 					if (Chess.activeBoard.getCell(leftCol, getRow()).contains("w")) {
	// 						if (mov_file == leftCol && mov_rank == getRow() - 1) {
	// 							Chess.activeBoard.getChessPieces()
	// 									.remove(Chess.activeBoard.getChessPiece(leftCol, getRow()));
	// 							Chess.activeBoard.initStateOfBoard();
	// 							return true;
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 		if (++rightCol <= 'h') {
	// 			if (Chess.activeBoard.getChessPiece(rightCol, getRow()) instanceof Pawn) {
	// 				if (((Pawn) Chess.activeBoard.getChessPiece(rightCol, getRow())).IsEnPassant()) {
	// 					if (Chess.activeBoard.getCell(rightCol, getRow()).contains("w")) {
	// 						if (mov_file == rightCol && mov_rank == getRow() - 1) {
	// 							Chess.activeBoard.getChessPieces()
	// 									.remove(Chess.activeBoard.getChessPiece(rightCol, getRow()));
	// 							Chess.activeBoard.initStateOfBoard();
	// 							return true;
	// 						}
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}
	 	return false;
	 }

	public boolean CheckForPromotion(int cur_rank, PieceType piece_type) 
	{
		if (piece_type == PieceType.WP) 
		{
			if (cur_rank == 7)
			{
				return true;
			}
		}
		else if (piece_type == PieceType.BP) 
		{
			if (cur_rank == 0)
			{
				return true;
			}
		}

		return false;
	}

	public boolean IsEnPassant() 
	{
		return enPassant;
	}

	public void setEnPassant(boolean enPassant) 
	{
		this.enPassant = enPassant;
	}

	public int getNumOfEnPassantTurns() 
	{
		return numOfEnPassantTurns;
	}

	public void incrementEnPassantTurns() 
	{
		numOfEnPassantTurns++;
	}
}