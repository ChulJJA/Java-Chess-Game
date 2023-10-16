/* 
 * author: Chul Seung Lee
 *
 */

package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

class ReturnPiece {
	static enum PieceType {
		WP, WR, WN, WB, WQ, WK,
		BP, BR, BN, BB, BK, BQ
	};

	static enum PieceFile {
		a, b, c, d, e, f, g, h
	};

	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank; // 1..8

	public String toString() {
		return "" + pieceFile + pieceRank + ":" + pieceType;
	}

	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece) other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {
		ILLEGAL_MOVE, DRAW,
		RESIGN_BLACK_WINS, RESIGN_WHITE_WINS,
		CHECK, CHECKMATE_BLACK_WINS, CHECKMATE_WHITE_WINS,
		STALEMATE
	};

	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {

	enum Player {
		white, black
	}

	static ReturnPlay return_play = new ReturnPlay();
	static Player current_player;



	static ArrayList<PieceType> white_pieces = new ArrayList<PieceType>();
	static ArrayList<PieceType> black_peices = new ArrayList<PieceType>();

	static boolean is_wk_moved = false;
	static boolean is_bk_moved = false;
	static boolean is_lwr_moved = false;
	static boolean is_rwr_moved = false;
	static boolean is_lbr_moved = false;
	static boolean is_rbr_moved = false;
	static boolean isCastling = false;
	static boolean enPassant = false;
	static boolean is_wk_checked = false;
	static boolean is_bk_checked = false;
	static boolean is_wk_checkmate = false;
	static boolean is_bk_checkmate = false;

	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for
	 *         details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */

		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return_play.message = ReturnPlay.Message.ILLEGAL_MOVE;

		if (move.length() < 4) {
			return return_play;
		}

		if (move.equals("resign")) {
			if (current_player == Player.white) {
				return_play.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
			} else {
				return_play.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
			}
			start();
			return return_play;
		}

		PieceFile current_piece_file = PieceFile.valueOf(String.valueOf(move.charAt(0)));
		int current_piece_rank = move.charAt(1) - '0';
		PieceFile move_piece_file = PieceFile.valueOf(String.valueOf(move.charAt(3)));
		int move_piece_rank = move.charAt(4) - '0';

		for (ReturnPiece rp : return_play.piecesOnBoard) {
			if (rp.pieceFile == current_piece_file && rp.pieceRank == current_piece_rank) {
				if ((current_player == Player.white && rp.pieceType == PieceType.WP)
						|| (current_player == Player.black && rp.pieceType == PieceType.BP)) {
					return_play.message = Pawn.IsMoveValid(rp.pieceType, current_piece_file, current_piece_rank,
							move_piece_file, move_piece_rank, enPassant);
					if (return_play.message == null) {
						IsCheck(rp.pieceType, current_player, current_piece_file, current_piece_rank, move_piece_file,
								move_piece_rank);
					}
					if (return_play.message == null && (current_piece_rank - move_piece_rank == 2
							|| current_piece_rank - move_piece_rank == -2)) {
						enPassant = true;
					} else {
						enPassant = false;
					}
					if (Pawn.CheckForPromotion(rp.pieceType, move_piece_rank)) {
						if (current_player == Player.white) {
							if (move.length() == 5) {
								rp.pieceType = PieceType.WQ;
							} else if (move.charAt(6) == 'N') {
								rp.pieceType = PieceType.WN;
							} else if (move.charAt(6) == 'B') {
								rp.pieceType = PieceType.WB;
							} else if (move.charAt(6) == 'R') {
								rp.pieceType = PieceType.WR;
							} else if (move.charAt(6) == 'Q') {
								rp.pieceType = PieceType.WQ;
							}
						} else if (current_player == Player.black) {
							if (move.length() == 5) {
								rp.pieceType = PieceType.BQ;
							} else if (move.charAt(6) == 'N') {
								rp.pieceType = PieceType.BN;
							} else if (move.charAt(6) == 'B') {
								rp.pieceType = PieceType.BB;
							} else if (move.charAt(6) == 'R') {
								rp.pieceType = PieceType.BR;
							} else if (move.charAt(6) == 'Q') {
								rp.pieceType = PieceType.BQ;
							}
						}

					}
				} else if ((current_player == Player.white && rp.pieceType == PieceType.WB)
						|| (current_player == Player.black && rp.pieceType == PieceType.BB)) {
					return_play.message = Bishop.IsMoveValid(rp.pieceType, current_piece_file, current_piece_rank,
							move_piece_file, move_piece_rank);
					if (return_play.message == null) {
						enPassant = false;
						IsCheck(rp.pieceType, current_player, current_piece_file, current_piece_rank, move_piece_file,
								move_piece_rank);
					}
				} else if ((current_player == Player.white && rp.pieceType == PieceType.WR)
						|| (current_player == Player.black && rp.pieceType == PieceType.BR)) {
					return_play.message = Rook.IsMoveValid(rp.pieceType, current_piece_file, current_piece_rank,
							move_piece_file, move_piece_rank);
					if (return_play.message == null) {
						enPassant = false;
						IsCheck(rp.pieceType, current_player, current_piece_file, current_piece_rank, move_piece_file,
								move_piece_rank);
					}
				} else if ((current_player == Player.white && rp.pieceType == PieceType.WN)
						|| (current_player == Player.black && rp.pieceType == PieceType.BN)) {
					return_play.message = Knight.IsMoveValid(rp.pieceType, current_piece_file, current_piece_rank,
							move_piece_file, move_piece_rank);
					if (return_play.message == null) {
						enPassant = false;
						IsCheck(rp.pieceType, current_player, current_piece_file, current_piece_rank, move_piece_file,
								move_piece_rank);
					}
				} else if ((current_player == Player.white && rp.pieceType == PieceType.WQ)
						|| (current_player == Player.black && rp.pieceType == PieceType.BQ)) {
					return_play.message = Queen.IsMoveValid(rp.pieceType, current_piece_file, current_piece_rank,
							move_piece_file, move_piece_rank);
					if (return_play.message == null) {
						enPassant = false;
						IsCheck(rp.pieceType, current_player, current_piece_file, current_piece_rank, move_piece_file,
								move_piece_rank);
					}
				} else if ((current_player == Player.white && rp.pieceType == PieceType.WK)
						|| (current_player == Player.black && rp.pieceType == PieceType.BK)) {
					return_play.message = King.IsMoveValid(rp.pieceType, current_piece_file, current_piece_rank,
							move_piece_file, move_piece_rank);
					if (rp.pieceType == PieceType.WK) {
						is_wk_moved = true;
					} else if (rp.pieceType == PieceType.BK) {
						is_bk_moved = true;
					}
					if (return_play.message == null) {
						enPassant = false;
						IsCheck(rp.pieceType, current_player, current_piece_file, current_piece_rank, move_piece_file,
								move_piece_rank);
					}
				}
				if (return_play.message == null && isCastling == false) {
					rp.pieceFile = move_piece_file;
					rp.pieceRank = move_piece_rank;
					break;
				} else if (isCastling == true) {
					isCastling = false;
				}
			}
		}

		if (move.length() == 11 && move.substring(6, 11).equals("draw?")) {
			if (current_player == Player.white) {
				return_play.message = ReturnPlay.Message.DRAW;
			} else {
				return_play.message = ReturnPlay.Message.DRAW;
			}
			start();
			return return_play;
		} else if (move.length() == 13 && move.substring(8, 13).equals("draw?")) {
			if (current_player == Player.white) {
				return_play.message = ReturnPlay.Message.DRAW;
			} else {
				return_play.message = ReturnPlay.Message.DRAW;
			}
			start();
			return return_play;
		}
		if (is_bk_checked && current_player == Player.white) {
			is_bk_checked = false;
			IsCheckmate(PieceType.BK, current_player, move_piece_file, move_piece_rank);
			if(is_bk_checkmate == true)
			{
				return_play.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
			}
			else
			{
				return_play.message = ReturnPlay.Message.CHECK;
			}
		} else if (is_wk_checked && current_player == Player.black) {
			is_wk_checked = false;
			IsCheckmate(PieceType.BK, current_player, move_piece_file, move_piece_rank);
			if(is_wk_checkmate == true)
			{
				return_play.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
			}
			else
			{
				return_play.message = ReturnPlay.Message.CHECK;
			}
		}
		if (current_player == Player.white && return_play.message != ReturnPlay.Message.ILLEGAL_MOVE) {
			current_player = Player.black;
		} else if (current_player == Player.black && return_play.message != ReturnPlay.Message.ILLEGAL_MOVE) {
			current_player = Player.white;
		}

		return return_play;
	}

	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		current_player = Player.white;
		is_wk_moved = false;
		is_bk_moved = false;
		is_lwr_moved = false;
		is_rwr_moved = false;
		is_lbr_moved = false;
		is_rbr_moved = false;
		isCastling = false;
		enPassant = false;
		is_wk_checked = false;
		is_bk_checked = false;
		is_wk_checkmate = false;
		is_bk_checkmate = false;
		return_play.piecesOnBoard = new ArrayList<ReturnPiece>();
		PieceDivder();

		ReturnPiece piece_WR = new ReturnPiece();
		piece_WR.pieceType = ReturnPiece.PieceType.WR;
		piece_WR.pieceFile = ReturnPiece.PieceFile.a;
		piece_WR.pieceRank = 1;
		ReturnPiece piece_WN = new ReturnPiece();
		piece_WN.pieceType = ReturnPiece.PieceType.WN;
		piece_WN.pieceFile = ReturnPiece.PieceFile.b;
		piece_WN.pieceRank = 1;
		ReturnPiece piece_WB = new ReturnPiece();
		piece_WB.pieceType = ReturnPiece.PieceType.WB;
		piece_WB.pieceFile = ReturnPiece.PieceFile.c;
		piece_WB.pieceRank = 1;
		ReturnPiece piece_WQ = new ReturnPiece();
		piece_WQ.pieceType = ReturnPiece.PieceType.WQ;
		piece_WQ.pieceFile = ReturnPiece.PieceFile.d;
		piece_WQ.pieceRank = 1;
		ReturnPiece piece_WK = new ReturnPiece();
		piece_WK.pieceType = ReturnPiece.PieceType.WK;
		piece_WK.pieceFile = ReturnPiece.PieceFile.e;
		piece_WK.pieceRank = 1;
		ReturnPiece piece_WB2 = new ReturnPiece();
		piece_WB2.pieceType = ReturnPiece.PieceType.WB;
		piece_WB2.pieceFile = ReturnPiece.PieceFile.f;
		piece_WB2.pieceRank = 1;
		ReturnPiece piece_WN2 = new ReturnPiece();
		piece_WN2.pieceType = ReturnPiece.PieceType.WN;
		piece_WN2.pieceFile = ReturnPiece.PieceFile.g;
		piece_WN2.pieceRank = 1;
		ReturnPiece piece_WR2 = new ReturnPiece();
		piece_WR2.pieceType = ReturnPiece.PieceType.WR;
		piece_WR2.pieceFile = ReturnPiece.PieceFile.h;
		piece_WR2.pieceRank = 1;
		ReturnPiece piece_WP = new ReturnPiece();
		piece_WP.pieceType = ReturnPiece.PieceType.WP;
		piece_WP.pieceFile = ReturnPiece.PieceFile.a;
		piece_WP.pieceRank = 2;
		ReturnPiece piece_WP2 = new ReturnPiece();
		piece_WP2.pieceType = ReturnPiece.PieceType.WP;
		piece_WP2.pieceFile = ReturnPiece.PieceFile.b;
		piece_WP2.pieceRank = 2;
		ReturnPiece piece_WP3 = new ReturnPiece();
		piece_WP3.pieceType = ReturnPiece.PieceType.WP;
		piece_WP3.pieceFile = ReturnPiece.PieceFile.c;
		piece_WP3.pieceRank = 2;
		ReturnPiece piece_WP4 = new ReturnPiece();
		piece_WP4.pieceType = ReturnPiece.PieceType.WP;
		piece_WP4.pieceFile = ReturnPiece.PieceFile.d;
		piece_WP4.pieceRank = 2;
		ReturnPiece piece_WP5 = new ReturnPiece();
		piece_WP5.pieceType = ReturnPiece.PieceType.WP;
		piece_WP5.pieceFile = ReturnPiece.PieceFile.e;
		piece_WP5.pieceRank = 2;
		ReturnPiece piece_WP6 = new ReturnPiece();
		piece_WP6.pieceType = ReturnPiece.PieceType.WP;
		piece_WP6.pieceFile = ReturnPiece.PieceFile.f;
		piece_WP6.pieceRank = 2;
		ReturnPiece piece_WP7 = new ReturnPiece();
		piece_WP7.pieceType = ReturnPiece.PieceType.WP;
		piece_WP7.pieceFile = ReturnPiece.PieceFile.g;
		piece_WP7.pieceRank = 2;
		ReturnPiece piece_WP8 = new ReturnPiece();
		piece_WP8.pieceType = ReturnPiece.PieceType.WP;
		piece_WP8.pieceFile = ReturnPiece.PieceFile.h;
		piece_WP8.pieceRank = 2;

		ReturnPiece piece_BR = new ReturnPiece();
		piece_BR.pieceType = ReturnPiece.PieceType.BR;
		piece_BR.pieceFile = ReturnPiece.PieceFile.a;
		piece_BR.pieceRank = 8;
		ReturnPiece piece_BN = new ReturnPiece();
		piece_BN.pieceType = ReturnPiece.PieceType.BN;
		piece_BN.pieceFile = ReturnPiece.PieceFile.b;
		piece_BN.pieceRank = 8;
		ReturnPiece piece_BB = new ReturnPiece();
		piece_BB.pieceType = ReturnPiece.PieceType.BB;
		piece_BB.pieceFile = ReturnPiece.PieceFile.c;
		piece_BB.pieceRank = 8;
		ReturnPiece piece_BQ = new ReturnPiece();
		piece_BQ.pieceType = ReturnPiece.PieceType.BQ;
		piece_BQ.pieceFile = ReturnPiece.PieceFile.d;
		piece_BQ.pieceRank = 8;
		ReturnPiece piece_BK = new ReturnPiece();
		piece_BK.pieceType = ReturnPiece.PieceType.BK;
		piece_BK.pieceFile = ReturnPiece.PieceFile.e;
		piece_BK.pieceRank = 8;
		ReturnPiece piece_BB2 = new ReturnPiece();
		piece_BB2.pieceType = ReturnPiece.PieceType.BB;
		piece_BB2.pieceFile = ReturnPiece.PieceFile.f;
		piece_BB2.pieceRank = 8;
		ReturnPiece piece_BN2 = new ReturnPiece();
		piece_BN2.pieceType = ReturnPiece.PieceType.BN;
		piece_BN2.pieceFile = ReturnPiece.PieceFile.g;
		piece_BN2.pieceRank = 8;
		ReturnPiece piece_BR2 = new ReturnPiece();
		piece_BR2.pieceType = ReturnPiece.PieceType.BR;
		piece_BR2.pieceFile = ReturnPiece.PieceFile.h;
		piece_BR2.pieceRank = 8;
		ReturnPiece piece_BP = new ReturnPiece();
		piece_BP.pieceType = ReturnPiece.PieceType.BP;
		piece_BP.pieceFile = ReturnPiece.PieceFile.a;
		piece_BP.pieceRank = 7;
		ReturnPiece piece_BP2 = new ReturnPiece();
		piece_BP2.pieceType = ReturnPiece.PieceType.BP;
		piece_BP2.pieceFile = ReturnPiece.PieceFile.b;
		piece_BP2.pieceRank = 7;
		ReturnPiece piece_BP3 = new ReturnPiece();
		piece_BP3.pieceType = ReturnPiece.PieceType.BP;
		piece_BP3.pieceFile = ReturnPiece.PieceFile.c;
		piece_BP3.pieceRank = 7;
		ReturnPiece piece_BP4 = new ReturnPiece();
		piece_BP4.pieceType = ReturnPiece.PieceType.BP;
		piece_BP4.pieceFile = ReturnPiece.PieceFile.d;
		piece_BP4.pieceRank = 7;
		ReturnPiece piece_BP5 = new ReturnPiece();
		piece_BP5.pieceType = ReturnPiece.PieceType.BP;
		piece_BP5.pieceFile = ReturnPiece.PieceFile.e;
		piece_BP5.pieceRank = 7;
		ReturnPiece piece_BP6 = new ReturnPiece();
		piece_BP6.pieceType = ReturnPiece.PieceType.BP;
		piece_BP6.pieceFile = ReturnPiece.PieceFile.f;
		piece_BP6.pieceRank = 7;
		ReturnPiece piece_BP7 = new ReturnPiece();
		piece_BP7.pieceType = ReturnPiece.PieceType.BP;
		piece_BP7.pieceFile = ReturnPiece.PieceFile.g;
		piece_BP7.pieceRank = 7;
		ReturnPiece piece_BP8 = new ReturnPiece();
		piece_BP8.pieceType = ReturnPiece.PieceType.BP;
		piece_BP8.pieceFile = ReturnPiece.PieceFile.h;
		piece_BP8.pieceRank = 7;

		return_play.piecesOnBoard.add(piece_WR);
		return_play.piecesOnBoard.add(piece_WN);
		return_play.piecesOnBoard.add(piece_WB);
		return_play.piecesOnBoard.add(piece_WQ);
		return_play.piecesOnBoard.add(piece_WK);
		return_play.piecesOnBoard.add(piece_WB2);
		return_play.piecesOnBoard.add(piece_WN2);
		return_play.piecesOnBoard.add(piece_WR2);
		return_play.piecesOnBoard.add(piece_WP);
		return_play.piecesOnBoard.add(piece_WP2);
		return_play.piecesOnBoard.add(piece_WP3);
		return_play.piecesOnBoard.add(piece_WP4);
		return_play.piecesOnBoard.add(piece_WP5);
		return_play.piecesOnBoard.add(piece_WP6);
		return_play.piecesOnBoard.add(piece_WP7);
		return_play.piecesOnBoard.add(piece_WP8);

		return_play.piecesOnBoard.add(piece_BR);
		return_play.piecesOnBoard.add(piece_BN);
		return_play.piecesOnBoard.add(piece_BB);
		return_play.piecesOnBoard.add(piece_BQ);
		return_play.piecesOnBoard.add(piece_BK);
		return_play.piecesOnBoard.add(piece_BB2);
		return_play.piecesOnBoard.add(piece_BN2);
		return_play.piecesOnBoard.add(piece_BR2);
		return_play.piecesOnBoard.add(piece_BP);
		return_play.piecesOnBoard.add(piece_BP2);
		return_play.piecesOnBoard.add(piece_BP3);
		return_play.piecesOnBoard.add(piece_BP4);
		return_play.piecesOnBoard.add(piece_BP5);
		return_play.piecesOnBoard.add(piece_BP6);
		return_play.piecesOnBoard.add(piece_BP7);
		return_play.piecesOnBoard.add(piece_BP8);
	}

	private static void PieceDivder() {
		white_pieces.add(PieceType.WB);
		white_pieces.add(PieceType.WK);
		white_pieces.add(PieceType.WN);
		white_pieces.add(PieceType.WP);
		white_pieces.add(PieceType.WQ);
		white_pieces.add(PieceType.WR);

		black_peices.add(PieceType.BB);
		black_peices.add(PieceType.BK);
		black_peices.add(PieceType.BN);
		black_peices.add(PieceType.BP);
		black_peices.add(PieceType.BQ);
		black_peices.add(PieceType.BR);
	}

	public static boolean PieceInBoard(PieceFile mov_file, int mov_rank) {
		if (mov_file == null || mov_file.ordinal() < 0 || mov_file.ordinal() > 7 || mov_rank < 1 || mov_rank > 8) {
			return false;
		}
		return true;
	}

	public static boolean IsCheck(PieceType piece_type, Player cur_player, PieceFile cur_file, int cur_rank,
			PieceFile mov_file, int mov_rank) {
		ReturnPlay.Message msg = ReturnPlay.Message.ILLEGAL_MOVE;

		if (cur_player == Player.white) {
			ReturnPiece tmp_BK = null;
			for (ReturnPiece rp : return_play.piecesOnBoard) {
				if (rp.pieceType == PieceType.BK) {
					tmp_BK = rp;
					break;
				}
			}
			if (piece_type == PieceType.WP) {
				msg = Pawn.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_BK.pieceFile, tmp_BK.pieceRank);
			} else if (piece_type == PieceType.WB) {
				msg = Bishop.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_BK.pieceFile, tmp_BK.pieceRank);
			} else if (piece_type == PieceType.WR) {
				msg = Rook.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_BK.pieceFile, tmp_BK.pieceRank);
			} else if (piece_type == PieceType.WN) {
				msg = Knight.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_BK.pieceFile, tmp_BK.pieceRank);
			} else if (piece_type == PieceType.WQ) {
				msg = Queen.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_BK.pieceFile, tmp_BK.pieceRank);
			} else if (piece_type == PieceType.WK) {
				msg = King.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_BK.pieceFile, tmp_BK.pieceRank);
			}
		} else if (cur_player == Player.black) {
			ReturnPiece tmp_WK = null;
			for (ReturnPiece rp : return_play.piecesOnBoard) {
				if (rp.pieceType == PieceType.WK) {
					tmp_WK = rp;
					break;
				}
			}
			if (piece_type == PieceType.BP) {
				msg = Pawn.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_WK.pieceFile, tmp_WK.pieceRank);
			} else if (piece_type == PieceType.BB) {
				msg = Bishop.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_WK.pieceFile, tmp_WK.pieceRank);
			} else if (piece_type == PieceType.BR) {
				msg = Rook.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_WK.pieceFile, tmp_WK.pieceRank);
			} else if (piece_type == PieceType.BN) {
				msg = Knight.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_WK.pieceFile, tmp_WK.pieceRank);
			} else if (piece_type == PieceType.BQ) {
				msg = Queen.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_WK.pieceFile, tmp_WK.pieceRank);
			} else if (piece_type == PieceType.BK) {
				msg = King.IsMoveValidCheck(piece_type, mov_file, mov_rank, tmp_WK.pieceFile, tmp_WK.pieceRank);
			}
		}

		if (msg == null && current_player == Player.white) {
			is_bk_checked = true;
			return true;
		} else if (msg == null && current_player == Player.black) {
			is_wk_checked = true;
			return true;
		}

		return false;
	}

	public static boolean IsCheckmate(PieceType piece_type, Player cur_player, PieceFile cur_file, int cur_rank) {
		PieceFile left_file_bound = null;
		PieceFile right_file_bound = null;
		ReturnPlay.Message is_checkmate = null;

		if (cur_file.ordinal() - 1 >= 0) {
			left_file_bound = PieceFile.values()[cur_file.ordinal() - 1];
		}
		if (cur_file.ordinal() + 1 <= 7) {
			right_file_bound = PieceFile.values()[cur_file.ordinal() + 1];
		}
		int top_rank_bound = cur_rank - 1;
		int bot_rank_bound = cur_rank + 1;

		if (cur_player == Player.white) {		
			ReturnPiece tmp_BK = null;
			for (ReturnPiece rp : return_play.piecesOnBoard) {
				if (rp.pieceType == PieceType.BK) {
					tmp_BK = rp;
					break;
				}
			}
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, tmp_BK.pieceFile, top_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, right_file_bound, top_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, right_file_bound, tmp_BK.pieceRank);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, right_file_bound, bot_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, tmp_BK.pieceFile, bot_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, left_file_bound, bot_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, left_file_bound, tmp_BK.pieceRank);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_BK.pieceFile, tmp_BK.pieceRank, left_file_bound, top_rank_bound);

			if(is_checkmate == null)
			{
				return false;
			}
			else if(is_checkmate == ReturnPlay.Message.ILLEGAL_MOVE)
			{
				is_bk_checkmate = true;
				return true;
			}
		}
		else if(cur_player == Player.black)
		{
			ReturnPiece tmp_WK = null;
			for (ReturnPiece rp : return_play.piecesOnBoard) {
				if (rp.pieceType == PieceType.WK) {
					tmp_WK = rp;
					break;
				}
			}	
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, tmp_WK.pieceFile, top_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, right_file_bound, top_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, right_file_bound, tmp_WK.pieceRank);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, right_file_bound, bot_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, tmp_WK.pieceFile, bot_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, left_file_bound, bot_rank_bound);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, left_file_bound, tmp_WK.pieceRank);
			is_checkmate = King.IsMoveValidCheck(piece_type, tmp_WK.pieceFile, tmp_WK.pieceRank, left_file_bound, top_rank_bound);

			if(is_checkmate == null)
			{
				return false;
			}
			else if(is_checkmate == ReturnPlay.Message.ILLEGAL_MOVE)
			{
				is_wk_checkmate = true;
				return true;
			}
		}

		return false;
	}
}
