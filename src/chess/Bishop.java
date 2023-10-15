package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class Bishop
{
	public static Message IsMoveValid(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) 
    {
        if(Chess.PieceInBoard(mov_file, mov_rank) == false)
		{
			return Message.ILLEGAL_MOVE;
		}

		if (Path.IsPathClear(cur_file, cur_rank, mov_file, mov_rank)) 
        {
            for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
            {
                if(rp.pieceFile == mov_file && rp.pieceRank == mov_rank)
                {
                    if((piece_type == PieceType.WB && rp.pieceType.toString().startsWith("W")) || (piece_type == PieceType.BB && rp.pieceType.toString().startsWith("B")))
                    {
		                return Message.ILLEGAL_MOVE;
                    }
                    Chess.return_play.piecesOnBoard.remove(rp);
                    return null;
                }
            }
            return null;
		}
		return Message.ILLEGAL_MOVE;
	}
}