package chess;

import java.util.function.BiPredicate;
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
        int top_rank_bound1 = cur_rank + 1;
		int top_rank_bound2 = cur_rank + 2;
		int bot_rank_bound1 = cur_rank - 1;
		int bot_rank_bound2 = cur_rank - 2;

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
			left_file_bound1 = PieceFile.values()[cur_file.ordinal() - 1];
		}
		if(cur_file.ordinal() + 2 <= 7)
		{
			right_file_bound1 = PieceFile.values()[cur_file.ordinal() + 1];
		}
        
		if (piece_type == PieceType.WN) {
			if (Path.IsVacant(left_file_bound1, top_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (Path.IsVacant(left_file_bound2, top_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(left_file_bound2, bot_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(left_file_bound1, bot_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, bot_rank_bound2))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound1, top_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound2, top_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound2, bot_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound1, bot_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, bot_rank_bound2))
            {
				return null;
            }
		} 
        else if (piece_type == PieceType.BN) 
        {
			if (Path.IsVacant(left_file_bound1, top_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (Path.IsVacant(left_file_bound2, top_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(left_file_bound2, bot_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(left_file_bound1, bot_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound1, bot_rank_bound2))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound1, top_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, top_rank_bound2))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound2, top_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, top_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound2, bot_rank_bound1) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound2, bot_rank_bound1))
            {
				return null;
            }
			if (Path.IsVacant(right_file_bound1, bot_rank_bound2) && IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound1, bot_rank_bound2))
            {
				return null;
            }
		}

		return Message.ILLEGAL_MOVE;
	}

    public static boolean IsAppropriatePiece(PieceType piece_name, PieceFile file, int rank, PieceFile file_bound, int rank_bound)
    {
        for(ReturnPiece rp : Chess.return_play.piecesOnBoard)
        {
            if(rp.pieceFile == file && rp.pieceRank == rank)
            {
                if(piece_name == PieceType.WN)
                {
                    if((rp.pieceType.toString().startsWith("B")) || (file == file_bound && rank == rank_bound))
                    {
                        Chess.return_play.piecesOnBoard.remove(rp);
                        return true;
                    }
                }
                else if(piece_name == PieceType.BN)
                {
                    if((rp.pieceType.toString().startsWith("W")) || (file == file_bound && rank == rank_bound))
                    {
                        Chess.return_play.piecesOnBoard.remove(rp);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
