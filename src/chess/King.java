package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class King {
	private static boolean hasMoved = false;

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
		int bot_rank_bound = cur_rank - 1;

		if (IsCastling(mov_file, mov_rank))
		{
			hasMoved = true;
			return null;
		}

		if (piece_type == PieceType.WK) 
		{
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, cur_rank) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, cur_rank)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, top_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, cur_file, top_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, cur_rank) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, cur_rank)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
		} 
		else if (piece_type == PieceType.BK) 
		{
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, cur_rank) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, cur_rank)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, top_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, cur_file, top_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound))
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, cur_rank) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, cur_rank)) 
			{
				hasMoved = true;
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound)) 
			{
				hasMoved = true;
				return null;
			}
		}
		return Message.ILLEGAL_MOVE;
	}

	public static boolean IsCastling(PieceFile mov_file, int mov_rank) 
	{
		
		return false;
	}

	public static boolean IsAppropriatePiece(PieceType piece_name, PieceFile file, int rank, PieceFile file_bound, int rank_bound)
    {
        if(file_bound == null || rank_bound == 0)
        {
            return false;
        }

        for(ReturnPiece rp : Chess.return_play.piecesOnBoard)
        {
            if(rp.pieceFile == file && rp.pieceRank == rank)
            {
                if(piece_name == PieceType.WK)
                {
                    if((rp.pieceType.toString().startsWith("B")) && (file == file_bound && rank == rank_bound))
                    {
                        Chess.return_play.piecesOnBoard.remove(rp);
                        return true;
                    }
                }
                else if(piece_name == PieceType.BK)
                {
                    if((rp.pieceType.toString().startsWith("W")) && (file == file_bound && rank == rank_bound))
                    {
                        Chess.return_play.piecesOnBoard.remove(rp);
                        return true;
                    }
                }
            }
        }
		
        return false;
    }

	private static boolean IsValidPosition(PieceType piece_type, PieceFile file, int rank, PieceFile file_bound, int rank_bound)
    {
        if(file_bound == null || rank_bound == 0)
        {
            return false;
        }

		if(piece_type == PieceType.WK)
        {
            for(ReturnPiece rp : Chess.return_play.piecesOnBoard)
            {
                if(rp.pieceFile == file && rp.pieceRank == rank)
                {
                    if(rp.pieceType.toString().startsWith("W"))
                    {
                        return false;
                    }
                }
            }
        }
        else if(piece_type == PieceType.WK)
        {
            for(ReturnPiece rp : Chess.return_play.piecesOnBoard)
            {
                if(rp.pieceFile == file && rp.pieceRank == rank)
                {
                    if(rp.pieceType.toString().startsWith("B"))
                    {
                        return false;
                    }
                }
            }
        }

        if(file == file_bound && rank == rank_bound)
        {
            return true;
        }

        return false;
    }
	
	public static boolean isHasMoved() 
    {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) 
    {
		this.hasMoved = hasMoved;
	}
}