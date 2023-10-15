// package chess;

// import chess.ReturnPiece.PieceFile;
// import chess.ReturnPiece.PieceType;

// public abstract class Piece {
// 	private PieceType piece_type;
// 	private PieceFile piece_file;
// 	private int piece_rank;

// 	public Piece(PieceType piece_type, PieceFile piece_file, int piece_rank) 
// 	{
// 		super();
// 		this.piece_type = piece_type;
// 		this.piece_file = piece_file;
// 		this.piece_rank = piece_rank;
// 	}

// 	public abstract boolean IsMoveValid(PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank);

// 	// public abstract boolean ValidMoveCheck(PieceFile cur_file, int cur_rank, PieceFile mov_file, int mov_rank);

// 	public PieceType GetPieceType() 
// 	{
// 		return piece_type;
// 	}

// 	public void SetPieceType(PieceType piece_type) 
// 	{
// 		this.piece_type = piece_type;
// 	}

// 	public PieceFile GetPieceFile() 
// 	{
// 		return piece_file;
// 	}

// 	public void SetPieceFile(PieceFile piece_file) 
// 	{
// 		this.piece_file = piece_file;
// 	}

// 	public int GetPieceRank() 
// 	{
// 		return piece_rank;
// 	}

// 	public void SetPieceRank(int piece_rank) 
// 	{
// 		this.piece_rank = piece_rank;
// 	}

// 	public void SetPieceFileAndRank(PieceFile piece_file, int piece_rank) 
// 	{
// 		this.piece_file = piece_file;
// 		this.piece_rank = piece_rank;
// 	}

// 	public boolean PieceInBoard(PieceFile mov_file, int mov_rank) 
// 	{
// 		if (mov_file.ordinal() < 0 || mov_file.ordinal() > 7 || mov_rank < 0 || mov_rank > 7) 
// 		{
// 			return false;
// 		}

// 		return true;
// 	}
// }
