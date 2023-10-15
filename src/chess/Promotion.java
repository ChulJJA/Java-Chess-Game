package chess;

import java.util.ArrayList;
import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Promotion {
    static Boolean isPromotion(PieceType piece_type, PieceFile piece_file, int piece_rank, PieceFile dest_file, int dest_rank, ArrayList<ReturnPiece> pieces_on_board)
    {
        if(piece_type == PieceType.WP)
        {
            if(piece_rank == 7)
            {
                if(dest_rank == 8)
                {
                    return true;
                }
            }
        }
        else if(piece_type == PieceType.BP)
        {
            if(piece_rank == 2)
            {
                if(dest_rank == 1)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
