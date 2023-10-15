package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Bishop 
{
	public static ReturnPlay.Message IsMoveValid(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank) 
    {
		if (isPathClear(cur_file, cur_rank, mov_file, mov_rank)) {
            for(ReturnPiece rp : Chess.return_play.piecesOnBoard )
            {
                
            }

			if (Chess.activeBoard.getCell(intCol, intRow).equals("##")
					|| Chess.activeBoard.getCell(intCol, intRow).equals("  ")
					|| Chess.activeBoard.getCell(intCol, intRow).contains("w")
					|| Chess.activeBoard.getCell(intCol, intRow).contains("b")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Used for checkmate, includes allied pieces
	 * 
	 * @return Returns true or false depending on whether or not move is valid
	 */
	@Override
	public boolean validMoveCheck(char curCol, int curRow, char intCol, int intRow) {

		if (isPathClear(curCol, curRow, intCol, intRow)) {

			if (getChessPieceName().equals("wB")) {
				if (Chess.activeBoard.getCell(intCol, intRow).equals("##")
						|| Chess.activeBoard.getCell(intCol, intRow).equals("  ")
						|| Chess.activeBoard.getCell(intCol, intRow).contains("w")) {
					return true;
				}
			} else if (getChessPieceName().equals("bB")) {
				if (Chess.activeBoard.getCell(intCol, intRow).equals("##")
						|| Chess.activeBoard.getCell(intCol, intRow).equals("  ")
						|| Chess.activeBoard.getCell(intCol, intRow).contains("b")) {
					return true;
				}
			}
		}
		return false;
	}
}
