package bridge;

import bridge.domain.BridgeSize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("다리 길이 테스트")
class BridgeSizeTest {
    @DisplayName("다리 길이가 3 이상 20 이하일 때 유효한 다리 길이 생성")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 20})
    void createBridgeSize(int length) {
        BridgeSize bridgeSize = new BridgeSize(length);
        assertThat(bridgeSize.getLength()).isEqualTo(length);
    }

    @DisplayName("다리 길이가 3 미만이거나 20 초과일 때 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {2, 21})
    void createBridgeSizeInvalid(int length) {
        assertThatThrownBy(() -> new BridgeSize(length))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.");
    }
}