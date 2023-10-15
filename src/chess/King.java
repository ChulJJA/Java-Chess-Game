package chess;

import java.util.function.BiPredicate;

public class King {
	private boolean hasMoved = false;

	public boolean validMove(char curCol, int curRow, char intCol, int intRow) {
		char leftColBound = (char) ((Character.valueOf(curCol) - 97) - 1 + 97);
		char rightColBound = (char) ((Character.valueOf(curCol) - 97) + 1 + 97);

		int topRowBound = curRow + 1;
		int botRowBound = curRow - 1;

		BiPredicate<Character, Integer> positionW = (c,
				i) -> ((Chess.activeBoard.getCell(c, i).equals("##") || Chess.activeBoard.getCell(c, i).equals("  ")
						|| Chess.activeBoard.getCell(c, i).contains("b")) && (intCol == c && intRow == i)
						&& !ChessBoard.wK_Check[i][Character.valueOf(c) - 97]);
		BiPredicate<Character, Integer> positionB = (c,
				i) -> ((Chess.activeBoard.getCell(c, i).equals("##") || Chess.activeBoard.getCell(c, i).equals("  ")
						|| Chess.activeBoard.getCell(c, i).contains("w")) && (intCol == c && intRow == i)
						&& !ChessBoard.bK_Check[i][Character.valueOf(c) - 97]);

		if (validMoveWithRook(intCol, intRow)) {
			hasMoved = true;
			return true;
		}

		if (getChessPieceName().equals("wK")) {
			if (positionW.test(leftColBound, topRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionW.test(leftColBound, curRow)) {
				hasMoved = true;
				return true;
			}
			if (positionW.test(leftColBound, botRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionW.test(curCol, topRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionW.test(curCol, botRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionW.test(rightColBound, topRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionW.test(rightColBound, curRow)) {
				hasMoved = true;
				return true;
			}
			if (positionW.test(rightColBound, botRowBound)) {
				hasMoved = true;
				return true;
			}
		} else if (getChessPieceName().equals("bK")) {
			if (positionB.test(leftColBound, topRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionB.test(leftColBound, curRow)) {
				hasMoved = true;
				return true;
			}
			if (positionB.test(leftColBound, botRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionB.test(curCol, topRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionB.test(curCol, botRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionB.test(rightColBound, topRowBound)) {
				hasMoved = true;
				return true;
			}
			if (positionB.test(rightColBound, curRow)) {
				hasMoved = true;
				return true;
			}
			if (positionB.test(rightColBound, botRowBound)) {
				hasMoved = true;
				return true;
			}
		}
		return false;
	}

	public boolean validMoveWithRook(char intCol, int intRow) {

		BiPredicate<Character, Integer> positionW = (c,
				i) -> ((Chess.activeBoard.getCell(c, i).equals("##") || Chess.activeBoard.getCell(c, i).equals("  "))
						&& (intCol == c && intRow == i) && !ChessBoard.wK_Check[i][Character.valueOf(c) - 97]);
		BiPredicate<Character, Integer> positionB = (c,
				i) -> ((Chess.activeBoard.getCell(c, i).equals("##") || Chess.activeBoard.getCell(c, i).equals("  "))
						&& (intCol == c && intRow == i) && !ChessBoard.bK_Check[i][Character.valueOf(c) - 97]);

		char c = getCol();
		if (getChessPieceName().equals("wK") && getCol() > intCol) {
			if (Chess.activeBoard.getChessPiece('a', 1) instanceof Rook) {
				if (!hasMoved && !((Rook) Chess.activeBoard.getChessPiece('a', 1)).isHasMoved()) {
					if (((Rook) Chess.activeBoard.getChessPiece('a', 1)).isPathClear('a', 1, getCol(), getRow())) {
						--c;
						if (positionW.test(--c, getRow())) {
							Chess.activeBoard.getChessPiece('a', 1).setColRow(++c, 1);
							Chess.activeBoard.initStateOfBoard();
							return true;
						}
					}
				}
			}
		}
		if (getChessPieceName().equals("wK") && getCol() < intCol) {
			if (Chess.activeBoard.getChessPiece('h', 1) instanceof Rook) {
				if (!hasMoved && !((Rook) Chess.activeBoard.getChessPiece('h', 1)).isHasMoved()) {
					if (((Rook) Chess.activeBoard.getChessPiece('h', 1)).isPathClear('h', 1, getCol(), getRow())) {
						++c;
						if (positionW.test(++c, getRow())) {
							Chess.activeBoard.getChessPiece('h', 1).setColRow(--c, 1);
							Chess.activeBoard.initStateOfBoard();
							return true;
						}
					}
				}
			}
		}
		if (getChessPieceName().equals("bK") && getCol() > intCol) {
			if (Chess.activeBoard.getChessPiece('a', 8) instanceof Rook) {
				if (!hasMoved && !((Rook) Chess.activeBoard.getChessPiece('a', 8)).isHasMoved()) {
					if (((Rook) Chess.activeBoard.getChessPiece('a', 8)).isPathClear('a', 8, getCol(), getRow())) {
						--c;
						if (positionB.test(--c, getRow())) {
							Chess.activeBoard.getChessPiece('a', 8).setColRow(++c, 8);
							Chess.activeBoard.initStateOfBoard();
							return true;
						}
					}
				}
			}
		}
		if (getChessPieceName().equals("bK") && getCol() < intCol) {
			if (Chess.activeBoard.getChessPiece('h', 8) instanceof Rook) {
				if (!hasMoved && !((Rook) Chess.activeBoard.getChessPiece('h', 8)).isHasMoved()) {
					if (((Rook) Chess.activeBoard.getChessPiece('h', 8)).isPathClear('h', 8, getCol(), getRow())) {
						++c;
						if (positionB.test(++c, getRow())) {
							Chess.activeBoard.getChessPiece('h', 8).setColRow(--c, 8);
							Chess.activeBoard.initStateOfBoard();
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isHasMoved() 
    {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) 
    {
		this.hasMoved = hasMoved;
	}
}