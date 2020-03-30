package chess.domain.piece.rook;

import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Rook extends Piece {
	private static final String WHITE_ROOK = "\u2656";
	private static final String BLACK_ROOK = "\u265c";

	public Rook(Team team, Position position) {
		super(new RookStrategy(), team, position);
	}

	public static Rook of(Team team, Position position) {
		return new Rook(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_ROOK;
		}
		return BLACK_ROOK;
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> dto) {
		strategy.validateMove(from, to, dto);
		this.position = to;
		return this;
	}
}

