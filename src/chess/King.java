package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class King {
	public static Message IsMoveValid(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file,
			int mov_rank) {
		if (Chess.PieceInBoard(mov_file, mov_rank) == false) {
			return Message.ILLEGAL_MOVE;
		}

		PieceFile left_file_bound = null;
		PieceFile right_file_bound = null;

		if (cur_file.ordinal() - 1 >= 0) {
			left_file_bound = PieceFile.values()[cur_file.ordinal() - 1];
		}
		if (cur_file.ordinal() + 1 <= 7) {
			right_file_bound = PieceFile.values()[cur_file.ordinal() + 1];
		}
		int top_rank_bound = cur_rank + 1;
		int bot_rank_bound = cur_rank - 1;

		if (IsCastling(piece_type, cur_file, cur_rank, mov_file, mov_rank)) {
			return null;
		}

		if (piece_type == PieceType.WK) {
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, cur_rank)
					|| IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, cur_rank)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, top_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, cur_file, top_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, cur_rank)
					|| IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, cur_rank)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound)) {
				return null;
			}
		} else if (piece_type == PieceType.BK) {
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, top_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, cur_rank)
					|| IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, cur_rank)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, left_file_bound, bot_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, top_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, cur_file, top_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, cur_file, bot_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, top_rank_bound)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, cur_rank)
					|| IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, cur_rank)) {
				return null;
			}
			if (IsAppropriatePiece(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound)
					|| IsValidPosition(piece_type, mov_file, mov_rank, right_file_bound, bot_rank_bound)) {
				return null;
			}
		}
		return Message.ILLEGAL_MOVE;
	}

	public static boolean IsCastling(PieceType piece_type, PieceFile cur_file, int cur_rank, PieceFile mov_file,
			int mov_rank) {
		if (Path.IsPathClear(cur_file, cur_rank, mov_file, mov_rank)) {
			if (piece_type == PieceType.WK && mov_file == PieceFile.g && mov_rank == 1) {
				if (Chess.is_wk_moved == false && Chess.is_rwr_moved == false) {
					for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
						if (rp.pieceType == PieceType.WR && rp.pieceFile == PieceFile.h && rp.pieceRank == 1) {
							rp.pieceFile = PieceFile.f;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.WK) {
									rp2.pieceFile = PieceFile.g;
								}
							}
							return true;
						}
					}
				}
			} else if (piece_type == PieceType.WK && mov_file == PieceFile.c && mov_rank == 1) {
				if (Chess.is_wk_moved == false && Chess.is_lwr_moved == false) {
					for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
						if (rp.pieceType == PieceType.WR && rp.pieceFile == PieceFile.a && rp.pieceRank == 1) {
							rp.pieceFile = PieceFile.d;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.WK) {
									rp2.pieceFile = PieceFile.c;
								}
							}
							return true;
						}
					}
				}
			} else if (piece_type == PieceType.BK && mov_file == PieceFile.g && mov_rank == 8) {
				if (Chess.is_bk_moved == false && Chess.is_lbr_moved == false) {
					for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
						if (rp.pieceType == PieceType.BR && rp.pieceFile == PieceFile.h && rp.pieceRank == 8) {
							rp.pieceFile = PieceFile.f;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.BK) {
									rp2.pieceFile = PieceFile.g;
								}
							}
							return true;
						}
					}
				}
			} else if (piece_type == PieceType.BK && mov_file == PieceFile.c && mov_rank == 8) {
				if (Chess.is_bk_moved == false && Chess.is_lbr_moved == false) {
					for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
						if (rp.pieceType == PieceType.BR && rp.pieceFile == PieceFile.a && rp.pieceRank == 8) {
							rp.pieceFile = PieceFile.d;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.BK) {
									rp2.pieceFile = PieceFile.c;
								}
							}
							return true;
						}
					}
				}
			}
		}

		if (Path.IsPathClear(cur_file, cur_rank, mov_file, mov_rank)) {
			for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
				if (rp.pieceFile == mov_file && rp.pieceRank == mov_rank) {
					if (piece_type == PieceType.WK && rp.pieceType == PieceType.WR && Chess.is_wk_moved == false) {
						if (mov_file == PieceFile.a && mov_rank == 1 && Chess.is_lwr_moved == false) {
							rp.pieceFile = PieceFile.d;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.WK) {
									rp2.pieceFile = PieceFile.c;
								}
							}
							return true;
						} else if (mov_file == PieceFile.h && mov_rank == 1 && Chess.is_rwr_moved == false) {
							rp.pieceFile = PieceFile.f;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.WK) {
									rp2.pieceFile = PieceFile.g;
								}
							}
							return true;
						}
					} else if (piece_type == PieceType.BK && rp.pieceType == PieceType.BR && Chess.is_bk_moved) {
						if (mov_file == PieceFile.a && mov_rank == 8 && Chess.is_lbr_moved == false) {
							rp.pieceFile = PieceFile.d;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.BK) {
									rp2.pieceFile = PieceFile.c;
								}
							}
							return true;
						} else if (mov_file == PieceFile.h && mov_rank == 8 && Chess.is_rbr_moved == false) {
							rp.pieceFile = PieceFile.f;
							for (ReturnPiece rp2 : Chess.return_play.piecesOnBoard) {
								if (rp2.pieceType == PieceType.BK) {
									rp2.pieceFile = PieceFile.g;
								}
							}
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public static boolean IsAppropriatePiece(PieceType piece_name, PieceFile file, int rank, PieceFile file_bound,
			int rank_bound) {
		if (file_bound == null || rank_bound == 0) {
			return false;
		}

		for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
			if (rp.pieceFile == file && rp.pieceRank == rank) {
				if (piece_name == PieceType.WK) {
					if ((rp.pieceType.toString().startsWith("B")) && (file == file_bound && rank == rank_bound)) {
						Chess.return_play.piecesOnBoard.remove(rp);
						return true;
					}
				} else if (piece_name == PieceType.BK) {
					if ((rp.pieceType.toString().startsWith("W")) && (file == file_bound && rank == rank_bound)) {
						Chess.return_play.piecesOnBoard.remove(rp);
						return true;
					}
				}
			}
		}

		return false;
	}

	private static boolean IsValidPosition(PieceType piece_type, PieceFile file, int rank, PieceFile file_bound,
			int rank_bound) {
		if (file_bound == null || rank_bound == 0) {
			return false;
		}

		if (piece_type == PieceType.WK) {
			for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
				if (rp.pieceFile == file && rp.pieceRank == rank) {
					if (rp.pieceType.toString().startsWith("W")) {
						return false;
					}
				}
			}
		} else if (piece_type == PieceType.WK) {
			for (ReturnPiece rp : Chess.return_play.piecesOnBoard) {
				if (rp.pieceFile == file && rp.pieceRank == rank) {
					if (rp.pieceType.toString().startsWith("B")) {
						return false;
					}
				}
			}
		}

		if (file == file_bound && rank == rank_bound) {
			return true;
		}

		return false;
	}

	// public static boolean isKingInCheck(PieceType kingPieceType) {
	// ReturnPiece king = null;

	// // Find the king's position
	// for (ReturnPiece piece : Chess.return_play.piecesOnBoard) {
	// if (piece.pieceType == kingPieceType) {
	// king = piece;
	// break;
	// }
	// }

	// if (king == null) return false;

	// for (ReturnPiece piece : Chess.return_play.piecesOnBoard) {
	// if ((kingPieceType == PieceType.WK &&
	// piece.pieceType.toString().startsWith("B")) ||
	// (kingPieceType == PieceType.BK &&
	// piece.pieceType.toString().startsWith("W"))) {
	// if (isValidMoveWithoutMaking(piece, king.pieceFile, king.pieceRank)) {
	// return true;
	// }
	// }
	// }
	// return false;
	// }

	// public static boolean isKingInCheckmate(PieceType kingType, PieceFile
	// kingFile, int kingRank) {
	// for (int i = -1; i <= 1; i++) {
	// for (int j = -1; j <= 1; j++) {
	// if (i == 0 && j == 0) continue; // current pos doesnt count

	// PieceFile targetFile = PieceFile.values()[kingFile.ordinal() + i];
	// int targetRank = kingRank + j;

	// // checks if move is within board boundaries
	// if (targetFile.ordinal() >= 0 && targetFile.ordinal() < 8 && targetRank >= 1
	// && targetRank <= 8) {
	// if (isValidMoveWithoutMaking(kingType, kingFile, kingRank, targetFile,
	// targetRank)) {
	// return false;
	// }
	// }
	// }
	// }

	// return true;
	// }

	// public static boolean isValidMoveWithoutMaking(ReturnPiece piece, PieceFile
	// file, int rank) {
	// switch (piece.pieceType) {
	// case WK:
	// case BK:
	// return King.IsMoveValid(piece.pieceType, piece.pieceFile, piece.pieceRank,
	// file, rank) == null;
	// case WQ:
	// case BQ:
	// return Queen.IsMoveValid(piece.pieceType, piece.pieceFile, piece.pieceRank,
	// file, rank) == null;
	// case WR:
	// case BR:
	// return Rook.IsMoveValid(piece.pieceType, piece.pieceFile, piece.pieceRank,
	// file, rank) == null;
	// case WN:
	// case BN:
	// return Knight.IsMoveValid(piece.pieceType, piece.pieceFile, piece.pieceRank,
	// file, rank) == null;
	// case WB:
	// case BB:
	// return Bishop.IsMoveValid(piece.pieceType, piece.pieceFile, piece.pieceRank,
	// file, rank) == null;
	// case WP:
	// case BP:
	// return Pawn.IsMoveValid(piece.pieceType, piece.pieceFile, piece.pieceRank,
	// file, rank) == null;
	// default:
	// return false;
	// }
	// }

	// private static boolean isKingInCheckAfterMove(ReturnPiece piece, Move move)
	// {
	// ArrayList<ReturnPiece> backup = new
	// ArrayList<>(Chess.return_play.piecesOnBoard);

	// Chess.play(move.toString());

	// boolean isStillInCheck = isKingInCheck(piece.pieceType == PieceType.WK ?
	// PieceType.BK : PieceType.WK);

	// Chess.return_play.piecesOnBoard = backup;

	// return isStillInCheck;
	// // creates backup board, checks if move creates check state, returns value
	// after restoring board
	// }
}