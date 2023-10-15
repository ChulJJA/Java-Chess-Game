package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class Queen {
	public static Message IsMoveValid(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) 
{
        if(Chess.PieceInBoard(mov_file, mov_rank) == false)
        {
            return Message.ILLEGAL_MOVE;
        }

		if (Path.IsPathClear(cur_file, cur_rank, mov_file, mov_rank)) 
        {
			if (piece_type == PieceType.WQ) 
            {
                if(Path.IsVacant(mov_file, mov_rank))
                {
                    return null;
                }
                
                for(ReturnPiece rp : Chess.return_play.piecesOnBoard)
                {
                    if(rp.pieceFile == mov_file && rp.pieceRank == mov_rank && rp.pieceType.toString().startsWith("B"))
                    {
                        Chess.return_play.piecesOnBoard.remove(rp);
                        return null;
                    }
                }
			} 
            else if (piece_type == PieceType.BQ) 
            {
                if(Path.IsVacant(mov_file, mov_rank))
                {
                    return null;
                }
                
                for(ReturnPiece rp : Chess.return_play.piecesOnBoard)
                {
                    if(rp.pieceFile == mov_file && rp.pieceRank == mov_rank && rp.pieceType.toString().startsWith("W"))
                    {
                        Chess.return_play.piecesOnBoard.remove(rp);
                        return null;
                    }
                }
			}
		}
		return Message.ILLEGAL_MOVE;
	}
}
