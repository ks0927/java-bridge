package bridge.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("게임 재시작/종료 테스트")
class GameCommandTest {

    @DisplayName("유효한 게임 명령어 생성")
    @ParameterizedTest
    @ValueSource(strings = {"R", "Q"})
    void createGameCommand(String command) {
        GameCommand gameCommand = GameCommand.of(command);
        assertThat(gameCommand).isNotNull();
    }

    @DisplayName("유효하지 않은 게임 명령어 입력 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C", "D"})
    void createGameCommandInvalid(String command) {
        assertThatThrownBy(() -> GameCommand.of(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임 재시도 및 종료는 R 과 Q만 가능합니다.");
    }
}