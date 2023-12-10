package bridge;

import bridge.domain.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("유저가 이동할 칸 테스트")
public class DirectionTest {

    @Test
    @DisplayName("Direction의 of 메소드가 U를 입력받았을 때 UP을 반환하는지 테스트")
    void testOfWhenInputIsU() {
        assertThat(Direction.of("U")).isEqualTo(Direction.UP);
    }

    @Test
    @DisplayName("Direction의 of 메소드가 D를 입력받았을 때 DOWN을 반환하는지 테스트")
    void testOfWhenInputIsD() {
        assertThat(Direction.of("D")).isEqualTo(Direction.DOWN);
    }

    @Test
    @DisplayName("Direction의 of 메소드가 잘못된 값을 입력받았을 때 예외를 발생시키는지 테스트")
    void testOfWhenInputIsInvalid() {
        assertThatThrownBy(() -> Direction.of("A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 칸은 U 또는 D만 가능합니다.");
    }

    @Test
    @DisplayName("Direction의 numberToPosition 메소드가 1을 입력받았을 때 U를 반환하는지 테스트")
    void testNumberToPositionWhenInputIs1() {
        assertThat(Direction.numberToPosition(1)).isEqualTo("U");
    }

    @Test
    @DisplayName("Direction의 numberToPosition 메소드가 0을 입력받았을 때 D를 반환하는지 테스트")
    void testNumberToPositionWhenInputIs0() {
        assertThat(Direction.numberToPosition(0)).isEqualTo("D");
    }

    @Test
    @DisplayName("Direction의 numberToPosition 메소드가 잘못된 값을 입력받았을 때 예외를 발생시키는지 테스트")
    void testNumberToPositionWhenInputIsInvalid() {
        assertThatThrownBy(() -> Direction.numberToPosition(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리의 숫자는 1 또는 0만 가능합니다.");
    }
}