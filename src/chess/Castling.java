package chess;
import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Castling{
    static Boolean isCastling(PieceType piece_type, PieceFile piece_file, int piece_rank, PieceFile dest_file, int dest_rank, ArrayList<ReturnPiece> pieces_on_board)
    {
        if(piece_type == PieceType.WB)
        {
            if(Chess.is_wr2_moved == false && (piece_file == PieceFile.e && piece_rank == 1) && (dest_file == PieceFile.g && dest_rank == 1))
            {
                for(ReturnPiece piece : pieces_on_board)
                {
                    if((piece.pieceFile == PieceFile.f && piece.pieceRank == 1) || (piece.pieceFile == PieceFile.g && piece.pieceRank == 1))
                    {
                        return false;
                    }
                }
            }
            else if((piece_file == PieceFile.e && piece_rank == 1) && (dest_file == PieceFile.c && dest_rank == 1))
            {
                for(ReturnPiece piece : pieces_on_board)
                {
                    if(Chess.is_wr1_moved == false && (piece.pieceFile == PieceFile.b && piece.pieceRank == 1) || (piece.pieceFile == PieceFile.c && piece.pieceRank == 1) || (piece.pieceFile == PieceFile.d && piece.pieceRank == 1))
                    {
                        return false;
                    }
                }
            }
        }
        else if(piece_type == PieceType.BB)
        {
            if((piece_file == PieceFile.e && piece_rank == 8) && (dest_file == PieceFile.g && dest_rank == 8))
            {
                for(ReturnPiece piece : pieces_on_board)
                {
                    if(Chess.is_br2_moved == false && (piece.pieceFile == PieceFile.f && piece.pieceRank == 8) || (piece.pieceFile == PieceFile.g && piece.pieceRank == 8))
                    {
                        return false;
                    }
                }
            }
            else if((piece_file == PieceFile.e && piece_rank == 8) && (dest_file == PieceFile.c && dest_rank == 8))
            {
                for(ReturnPiece piece : pieces_on_board)
                {
                    if(Chess.is_br1_moved == false && (piece.pieceFile == PieceFile.b && piece.pieceRank == 8) || (piece.pieceFile == PieceFile.c && piece.pieceRank == 8) || (piece.pieceFile == PieceFile.d && piece.pieceRank == 8))
                    {
                        return false;
                    }
                }
            }
        }

        return false;
    }
   
}
