package bridge;

import bridge.service.BridgeMaker;
import bridge.service.BridgeNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BridgeMakerTest {
    @DisplayName("1, 0 값들이 주어질 때 D, U로 이루어진 다리를 반환")
    @Test
    void makeBridge() {
        BridgeNumberGenerator generator = new TestBridgeNumberGenerator(Arrays.asList(1, 0, 0, 1, 0, 1));
        BridgeMaker bridgeMaker = new BridgeMaker(generator);

        List<String> bridge = bridgeMaker.makeBridge(6);
        assertThat(bridge).containsExactly("U", "D", "D", "U", "D", "U");
    }

    @DisplayName("다리 숫자 중 1, 0이외의 숫자가 주어질 때 예외 발생")
    @Test
    void makeBridgeIllegalNumber() {
        BridgeNumberGenerator generator = new TestBridgeNumberGenerator(Arrays.asList(3, 0, 0, 1, 0, 1));
        BridgeMaker bridgeMaker = new BridgeMaker(generator);

        assertThatThrownBy(() -> bridgeMaker.makeBridge(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리의 숫자는 1 또는 0만 가능합니다.");
    }

    private class TestBridgeNumberGenerator implements BridgeNumberGenerator {

        List<Integer> integerList;
        public TestBridgeNumberGenerator(List<Integer> asList) {
            integerList = new ArrayList<>(asList);
        }

        @Override
        public int generate() {
            return integerList.remove(0);
        }
    }
}