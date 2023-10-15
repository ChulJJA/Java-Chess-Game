package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class Knight {
	public static Message IsMoveValid(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) 
    {
        if(Chess.PieceInBoard(mov_file, mov_rank) == false)
        {
            return Message.ILLEGAL_MOVE;
        }

		PieceFile left_file_bound1 = null;
		PieceFile left_file_bound2 = null;
		PieceFile right_file_bound1 = null;
		PieceFile right_file_bound2 = null;
        int top_rank_bound1 = 0;
		int top_rank_bound2 = 0;
		int bot_rank_bound1 = 0;
		int bot_rank_bound2 = 0;

        if(cur_file.ordinal() - 1 >= 0)
		{
			left_file_bound1 = PieceFile.values()[cur_file.ordinal() - 1];
		}
		if(cur_file.ordinal() + 1 <= 7)
		{
			right_file_bound1 = PieceFile.values()[cur_file.ordinal() + 1];
		}
        if(cur_file.ordinal() - 2 >= 0)
		{
			left_file_bound2 = PieceFile.values()[cur_file.ordinal() - 2];
		}
		if(cur_file.ordinal() + 2 <= 7)
		{
			right_file_bound2 = PieceFile.values()[cur_file.ordinal() + 2];
		}
        if(cur_rank + 1 < 9)
        {
            top_rank_bound1 = cur_rank + 1;
        }
        if(cur_rank + 2 < 9)
        {
            top_rank_bound2 = cur_rank + 2;
        }
        if(cur_rank - 1 > 0)
        {
            bot_rank_bound1 = cur_rank - 1;
        }
        if(cur_rank - 2 > 0)
        {
            bot_rank_bound2 = cur_rank - 2;
        }
        
		if (piece_type == PieceType.WN) 
        {
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, top_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, top_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, bot_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, bot_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound1, bot_rank_bound2))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, top_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, top_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, bot_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, bot_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound1, bot_rank_bound2))
            {
				return null;
            }
		} 
        else if (piece_type == PieceType.BN) 
        {
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, top_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, top_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, bot_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, bot_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound1, bot_rank_bound2))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, top_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, top_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, bot_rank_bound1) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, bot_rank_bound2) || IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound1, bot_rank_bound2))
            {
				return null;
            }
		}

		return Message.ILLEGAL_MOVE;
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
                if(piece_name == PieceType.WN)
                {
                    if((rp.pieceType.toString().startsWith("B")) && (file == file_bound && rank == rank_bound))
                    {
                        Chess.return_play.piecesOnBoard.remove(rp);
                        return true;
                    }
                }
                else if(piece_name == PieceType.BN)
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

        if(piece_type == PieceType.WN)
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
        else if(piece_type == PieceType.WN)
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
}
