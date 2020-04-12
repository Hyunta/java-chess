package chess.domain;

import chess.domain.position.Position;

public class ChessGame {
	private boolean userEnd;
	private ChessBoard chessBoard;
	private Side turn;

	public ChessGame(ChessBoard chessBoard) {
		this(chessBoard, Side.WHITE);
	}

	public ChessGame(ChessBoard chessBoard, Side side) {
		this.chessBoard = chessBoard;
		this.turn = side;
		this.userEnd = false;
	}

	public void move(Position source, Position target) {
		validateTurn(source, turn);
		chessBoard.move(source, target);
		if (!isEnd()) {
			turn = turn.reverse();
		}
	}

	private void validateTurn(Position source, Side turn) {
		if (!chessBoard.isRightTurn(source, turn)) {
			throw new IllegalArgumentException("본인의 말만 움직일 수 있습니다.");
		}
	}

	public ChessStatus createStatus() {
		return chessBoard.createStatus();
	}

	public boolean isEnd() {
		return chessBoard.isKingDead(turn) || userEnd;
	}

	public boolean isNotEnd() {
		return !this.isEnd();
	}

	public void end() {
		turn = turn.reverse();
		userEnd = true;
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public Side getTurn() {
		return turn;
	}
}
