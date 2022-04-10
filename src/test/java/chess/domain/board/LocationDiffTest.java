package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LocationDiffTest {

    @Test
    @DisplayName("제자리로 이동할 때 예외 발생")
    void sameLocationException() {
        assertThatThrownBy(() -> new LocationDiff(0, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("방향에 해당하는 단위 벡터를 반환")
    @MethodSource("directionParameter")
    void getDirection(int x, int y, Direction direction) {
        LocationDiff locationDiff = new LocationDiff(x, y);
        assertThat(locationDiff.computeDirection()).isEqualTo(direction);
    }

    public static Stream<Arguments> directionParameter() {
        return Stream.of(
                Arguments.arguments(0, 1, Direction.U),
                Arguments.arguments(0, 3, Direction.U),
                Arguments.arguments(0, -2, Direction.D),
                Arguments.arguments(0, -1, Direction.D),
                Arguments.arguments(1, 0, Direction.R),
                Arguments.arguments(3, 3, Direction.UR),
                Arguments.arguments(-2, -2, Direction.DL)
        );
    }

    @ParameterizedTest
    @DisplayName("방향에 해당하는 단위 벡터를 반환")
    @MethodSource("distanceParameter")
    void computeDistance(int x, int y, int distance) {
        LocationDiff locationDiff = new LocationDiff(x, y);
        assertThat(locationDiff.computeDistance()).isEqualTo(distance);
    }

    public static Stream<Arguments> distanceParameter() {
        return Stream.of(
                Arguments.arguments(0, 1, 1),
                Arguments.arguments(0, 3, 3),
                Arguments.arguments(0, -2, 2),
                Arguments.arguments(0, -1, 1),
                Arguments.arguments(1, 0, 1),
                Arguments.arguments(3, 3, 3),
                Arguments.arguments(-2, -2, 2)
        );
    }

}