package bridge.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bridge.domain.BridgeSize;
import bridge.domain.Direction;
import bridge.domain.GameCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("입력 매핑 테스트")
class InputMapperTest {
    private final InputMapper inputMapper = new InputMapper();

    @Test
    @DisplayName("정상적인 숫자 입력을 BridgeSize로 변환")
    public void mapToBridgeSize_success() {
        BridgeSize bridgeSize = inputMapper.mapToBridgeSize("5");
        assertThat(bridgeSize.getLength()).isEqualTo(5);
    }

    @Test
    @DisplayName("숫자가 아닌 입력이 들어오면 예외 발생")
    public void mapToBridgeSize_fail() {
        assertThatThrownBy(() -> inputMapper.mapToBridgeSize("not a number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다리의 길이는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("윗방향 입력을 Direction으로 변환")
    public void mapToBridgeMove1() {
        Direction direction = inputMapper.mapToBridgeMove("U");
        assertThat(direction).isEqualTo(Direction.UP);
    }

    @Test
    @DisplayName("아랫방향 입력을 Direction으로 변환")
    public void mapToBridgeMove2() {
        Direction direction = inputMapper.mapToBridgeMove("D");
        assertThat(direction).isEqualTo(Direction.DOWN);
    }

    @Test
    @DisplayName("재실행 게임 커맨드 입력을 GameCommand로 변환")
    public void mapToGameCommand1() {
        GameCommand gameCommand = inputMapper.mapToGameCommand("R");
        assertThat(gameCommand).isEqualTo(GameCommand.RESTART);
    }

    @Test
    @DisplayName("실행정지 게임 커맨드 입력을 GameCommand로 변환")
    public void mapToGameCommand2() {
        GameCommand gameCommand = inputMapper.mapToGameCommand("Q");
        assertThat(gameCommand).isEqualTo(GameCommand.QUIT);
    }
}