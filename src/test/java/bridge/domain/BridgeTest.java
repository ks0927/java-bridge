package bridge.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("다리 테스트")
class BridgeTest {

    @DisplayName("Bridge 생성")
    @ParameterizedTest
    @MethodSource("provideBridgeMoves")
    void createBridge(List<String> bridgeMoves) {
        Bridge bridge = Bridge.of(bridgeMoves);
        assertThat(bridge).isNotNull();
    }

    static Stream<List<String>> provideBridgeMoves() {
        return Stream.of(
                Arrays.asList("U", "D", "D"),
                Arrays.asList("D", "U", "U")
        );
    }

}