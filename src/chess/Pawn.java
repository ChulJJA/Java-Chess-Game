package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;


public class Pawn {
	private boolean enPassant = false;
	private int numOfEnPassantTurns = 0;

	public static Message IsMoveValid(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) 
	{
		if(Chess.PieceInBoard(mov_file, mov_rank) == false)
		{
			return Message.ILLEGAL_MOVE;
		}

		PieceFile left_file_bound = null;
		PieceFile right_file_bound = null;

		if(cur_file.ordinal() - 1 >= 0)
		{
			left_file_bound = PieceFile.values()[cur_file.ordinal() - 1];
		}
		if(cur_file.ordinal() + 1 <= 7)
		{
			right_file_bound = PieceFile.values()[cur_file.ordinal() + 1];
		}
		int top_rank_bound = cur_rank + 1;
		int bottom_rank_bound = cur_rank - 1;

		if (piece_type == PieceType.WP) 
		{
			for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
			{
				if(Path.IsVacant(mov_file, mov_rank))
				{
					if(cur_file == mov_file && cur_rank + 1 == mov_rank)
					{
						return null;
					}
				}
				if(cur_rank == 2 && Path.IsVacant(mov_file, mov_rank))
				{
					if(cur_file == mov_file && cur_rank + 2 == mov_rank)
					{
						return null;
					}
				}
				if(rp.pieceFile == right_file_bound && rp.pieceRank == top_rank_bound)
				{
					if(mov_file == right_file_bound && mov_rank == top_rank_bound && rp.pieceType.toString().startsWith("B"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
						return null;
					}
				}
				if(rp.pieceFile == left_file_bound && rp.pieceRank == top_rank_bound)
				{
					if(mov_file == left_file_bound && mov_rank == top_rank_bound && rp.pieceType.toString().startsWith("B"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
						return null;
					}
				}
			}
		}
		else if(piece_type == PieceType.BP)
		{
			for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
			{
				if(Path.IsVacant(mov_file, mov_rank))
				{
					if(cur_file == mov_file && cur_rank - 1 == mov_rank)
					{
						return null;
					}
				}
				if(cur_rank == 7 && Path.IsVacant(mov_file, mov_rank))
				{
					if(cur_file == mov_file && cur_rank - 2 == mov_rank)
					{
						return null;
					}
				}
				if(rp.pieceFile == right_file_bound && rp.pieceRank == bottom_rank_bound)
				{
					if(mov_file == right_file_bound && mov_rank == bottom_rank_bound && rp.pieceType.toString().startsWith("W"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
						return null;
					}
				}
				if(rp.pieceFile == left_file_bound && rp.pieceRank == bottom_rank_bound)
				{
					if(mov_file == left_file_bound && mov_rank == bottom_rank_bound && rp.pieceType.toString().startsWith("W"))
					{
						Chess.return_play.piecesOnBoard.remove(rp);
						return null;
					}
				}
			}
		}
			
		return Message.ILLEGAL_MOVE;
	}

	public static boolean CheckForPromotion(PieceType piece_type, int cur_rank) 
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