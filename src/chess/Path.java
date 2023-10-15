package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Path {
	static boolean IsPathClear(PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) {
		int cur_file_val = cur_file.ordinal();
		int mov_file_val = mov_file.ordinal();

		if (IsRookOrQueen(cur_file, cur_rank)) {
			if (cur_file == mov_file) {
				if (mov_rank < cur_rank) {
					for (int i = cur_rank - 1; i > mov_rank; i--) {
						if (IsVacant(cur_file, i) == false)
							return false;
					}
					return true;
				}
				if (mov_rank > cur_rank) {
					for (int i = cur_rank + 1; i < mov_rank; i++) {
						if (IsVacant(cur_file, i) == false)
							return false;
					}
					return true;
				}
			}
			if (cur_rank == mov_rank) {
				if (mov_file_val < cur_file_val) {
					for (int i = cur_file_val - 1; i > mov_file_val; i--) {
						if (IsVacant(PieceFile.values()[i], cur_rank) == false)
							return false;
					}
					return true;
				}
				if (mov_file_val > cur_file_val) {
					for (int i = cur_file_val + 1; i < mov_file_val; i++) {
						if (IsVacant(PieceFile.values()[i], cur_rank) == false)
							return false;
					}
					return true;
				}
			}
		}
		if (IsBishopOrQueen(cur_file, cur_rank)) {
			if (IsDiagonal(cur_file, cur_rank, mov_file, mov_rank)) {
				if (mov_file_val < cur_file_val && mov_rank > cur_rank) {
					for (int i = cur_rank + 1; i < mov_rank; i++) {
						if (IsVacant(PieceFile.values()[--cur_file_val], i) == false)
							return false;
					}
					return true;
				}
				if (mov_file_val > cur_file_val && mov_rank > cur_rank) {
					for (int i = cur_rank + 1; i < mov_rank; i++) {
						if (IsVacant(PieceFile.values()[++cur_file_val], i) == false)
							return false;
					}
					return true;
				}
				if (mov_file_val < cur_file_val && mov_rank < cur_rank) {
					for (int i = cur_rank - 1; i > mov_rank; i--) {
						if (IsVacant(PieceFile.values()[--cur_file_val], i) == false)
							return false;
					}
					return true;
				}
				if (mov_file_val > cur_file_val && mov_rank < cur_rank) {
					for (int i = cur_rank - 1; i > mov_rank; i--) {
						if (IsVacant(PieceFile.values()[++cur_file_val], i) == false)
							return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	private static boolean IsDiagonal(PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) {
		int file_dist;
        int rank_dist;
		int cur_file_val = cur_file.ordinal();
		int mov_file_val = mov_file.ordinal();
        
		if (mov_file_val < cur_file_val && mov_rank > cur_rank) {
			rank_dist = mov_rank - cur_rank;
			file_dist = cur_file_val - mov_file_val;
			if (rank_dist == file_dist)
				return true;
		}
		if (mov_file_val > cur_file_val && mov_rank > cur_rank) {
			rank_dist = mov_rank - cur_rank;
			file_dist = mov_file_val - cur_file_val;
			if (rank_dist == file_dist)
				return true;

		}
		if (mov_file_val < cur_file_val && mov_rank < cur_rank) {
			rank_dist = cur_rank - mov_rank;
			file_dist = cur_file_val - mov_file_val;
			if (rank_dist == file_dist)
				return true;

		}
		if (mov_file_val > cur_file_val && mov_rank < cur_rank) {
			rank_dist = cur_rank - mov_rank;
			file_dist = mov_file_val - cur_file_val;
			if (rank_dist == file_dist)
				return true;

		}
		return false;
	}

    public static boolean IsVacant(PieceFile file, int rank)
    {
        for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
        {
            if(rp.pieceFile == file && rp.pieceRank == rank)
            {
                return false;
            }
        }
        return true;
    }
    private static boolean IsRookOrQueen(PieceFile file, int rank)
    {
        for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
        {
            if(rp.pieceFile == file && rp.pieceRank == rank)
            {
                if(rp.pieceType == PieceType.WR || rp.pieceType == PieceType.WQ || rp.pieceType == PieceType.BR || rp.pieceType == PieceType.BQ)
                {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean IsBishopOrQueen(PieceFile file, int rank)
    {
        for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
        {
            if(rp.pieceFile == file && rp.pieceRank == rank)
            {
                if(rp.pieceType == PieceType.WB || rp.pieceType == PieceType.WQ || rp.pieceType == PieceType.BB || rp.pieceType == PieceType.BQ)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
