package bridge;

import bridge.domain.BridgeMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("유저가 이동할 칸 테스트")
public class BridgeMoveTest {

    @Test
    @DisplayName("BridgeMove의 of 메소드가 U를 입력받았을 때 UP을 반환하는지 테스트")
    void testOfWhenInputIsU() {
        assertThat(BridgeMove.of("U")).isEqualTo(BridgeMove.UP);
    }

    @Test
    @DisplayName("BridgeMove의 of 메소드가 D를 입력받았을 때 DOWN을 반환하는지 테스트")
    void testOfWhenInputIsD() {
        assertThat(BridgeMove.of("D")).isEqualTo(BridgeMove.DOWN);
    }

    @Test
    @DisplayName("BridgeMove의 of 메소드가 잘못된 값을 입력받았을 때 예외를 발생시키는지 테스트")
    void testOfWhenInputIsInvalid() {
        assertThatThrownBy(() -> BridgeMove.of("A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 칸은 U 또는 D만 가능합니다.");
    }

    @Test
    @DisplayName("BridgeMove의 numberToPosition 메소드가 1을 입력받았을 때 U를 반환하는지 테스트")
    void testNumberToPositionWhenInputIs1() {
        assertThat(BridgeMove.numberToPosition(1)).isEqualTo("U");
    }

    @Test
    @DisplayName("BridgeMove의 numberToPosition 메소드가 0을 입력받았을 때 D를 반환하는지 테스트")
    void testNumberToPositionWhenInputIs0() {
        assertThat(BridgeMove.numberToPosition(0)).isEqualTo("D");
    }

    @Test
    @DisplayName("BridgeMove의 numberToPosition 메소드가 잘못된 값을 입력받았을 때 예외를 발생시키는지 테스트")
    void testNumberToPositionWhenInputIsInvalid() {
        assertThatThrownBy(() -> BridgeMove.numberToPosition(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리의 숫자는 1 또는 0만 가능합니다.");
    }
}