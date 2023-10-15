package chess;

import java.util.function.BiPredicate;

public interface Path {

	/**
	 * Predicate to test cell state on board
	 */
    Chess.return_play.piecesOnBoard.contains()
	BiPredicate<Character, Integer> position = (c,
			i) -> (Chess.activeBoard.getCell(c, i).equals("##") || Chess.activeBoard.getCell(c, i).equals("  "));
	/**
	 * Predicate to test if piece is rook or queen for proper movement
	 */
	BiPredicate<Character, Integer> rookOrQueen = (c,
			i) -> (Chess.activeBoard.getCell(c, i).equals("wR") || Chess.activeBoard.getCell(c, i).equals("bR")
					|| Chess.activeBoard.getCell(c, i).equals("wQ") || Chess.activeBoard.getCell(c, i).equals("bQ"));
	/**
	 * Predicate to test if piece is bishop or queen for proper movement
	 */
	BiPredicate<Character, Integer> bishopOrQueen = (c,
			i) -> (Chess.activeBoard.getCell(c, i).equals("wB") || Chess.activeBoard.getCell(c, i).equals("bB")
					|| Chess.activeBoard.getCell(c, i).equals("wQ") || Chess.activeBoard.getCell(c, i).equals("bQ"));
    

	default boolean isPathClear(char curCol, int curRow, char intCol, int intRow) {
		int valOfCurCol = Character.valueOf(curCol) - 97;
		int valOfIntCol = Character.valueOf(intCol) - 97;

		if (rookOrQueen.test(curCol, curRow)) {
			if (curCol == intCol) {
				// moving up or down
				if (intRow < curRow) {
					for (int i = curRow - 1; i > intRow; i--) {
						if (!position.test(curCol, i))
							return false;
					}
					return true;
				}
				if (intRow > curRow) {
					for (int i = curRow + 1; i < intRow; i++) {
						if (!position.test(curCol, i))
							return false;
					}
					return true;
				}
			}
			if (curRow == intRow) {
				// moving left or right
				if (intCol < curCol) {
					for (int i = valOfCurCol - 1; i > valOfIntCol; i--) {
						if (!position.test((char) (i + 97), curRow))
							return false;
					}
					return true;
				}
				if (intCol > curCol) {
					for (int i = valOfCurCol + 1; i < valOfIntCol; i++) {
						if (!position.test((char) (i + 97), curRow))
							return false;
					}
					return true;
				}
			}
		}
		if (bishopOrQueen.test(curCol, curRow)) {
			if (isDiagonal(curCol, curRow, intCol, intRow)) {
				// diagonals top left
				if (intCol < curCol && intRow > curRow) {
					for (int i = curRow + 1; i < intRow; i++) {
						if (!position.test(--curCol, i))
							return false;
					}
					return true;
				}
				// top right
				if (intCol > curCol && intRow > curRow) {
					for (int i = curRow + 1; i < intRow; i++) {
						if (!position.test(++curCol, i))
							return false;
					}
					return true;
				}
				// bottom left
				if (intCol < curCol && intRow < curRow) {
					for (int i = curRow - 1; i > intRow; i--) {
						if (!position.test(--curCol, i))
							return false;
					}
					return true;
				}
				// bottom right
				if (intCol > curCol && intRow < curRow) {
					for (int i = curRow - 1; i > intRow; i--) {
						if (!position.test(++curCol, i))
							return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method to determine if the intended move is a diagonal move which would be a
	 * valid move and invalid otherwise
	 * 
	 * @param curCol Current Col of the piece being moved
	 * @param curRow Current Row of the piece being moved
	 * @param intCol Intended Col of the piece being moved
	 * @param intRow Intended Row of the piece being moved
	 * @return Returns true of false depending on whether the intended position is
	 *         indeed a diagonal position from the current position
	 */
	private boolean isDiagonal(char curCol, int curRow, char intCol, int intRow) {
		int rowDist;
		int colDist;
		int valOfCurCol = Character.valueOf(curCol) - 97;
		int valOfIntCol = Character.valueOf(intCol) - 97;
		// diagonal top left
		if (intCol < curCol && intRow > curRow) {
			rowDist = intRow - curRow;
			colDist = valOfCurCol - valOfIntCol;
			if (rowDist == colDist)
				return true;
		}
		// diagonal top right
		if (intCol > curCol && intRow > curRow) {
			rowDist = intRow - curRow;
			colDist = valOfIntCol - valOfCurCol;
			if (rowDist == colDist)
				return true;

		}
		// diagonal bottom left
		if (intCol < curCol && intRow < curRow) {
			rowDist = curRow - intRow;
			colDist = valOfCurCol - valOfIntCol;
			if (rowDist == colDist)
				return true;

		}
		// diagonal bottom right
		if (intCol > curCol && intRow < curRow) {
			rowDist = curRow - intRow;
			colDist = valOfIntCol - valOfCurCol;
			if (rowDist == colDist)
				return true;

		}
		return false;
	}
}
