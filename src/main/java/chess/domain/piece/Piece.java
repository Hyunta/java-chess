package chess.domain.piece;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;
import java.util.Objects;

public abstract class Piece {
    protected final Team team;
    private final Name name;
    private boolean isFirst;

    public Piece(Team team, Name name) {
        this.team = team;
        this.name = name;
        isFirst = true;
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public boolean isEmpty() {
        return team.isNone();
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public void changeNotFirst() {
        isFirst = false;
    }

    public String getNameIndex() {
        return name.getName(team);
    }

    public String getName() {
        return name.name();
    }

    public String getTeam() {
        return team.name();
    }

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract boolean isMovableDirection(Direction direction);

    public abstract boolean isMovableDistance(LocationDiff locationDiff);

    public abstract double getScore();

    public abstract void checkPawnMovable(Direction direction, Piece targetPiece);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return isFirst == piece.isFirst && team == piece.team && name == piece.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, name, isFirst);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", name=" + name +
                '}';
    }
}
