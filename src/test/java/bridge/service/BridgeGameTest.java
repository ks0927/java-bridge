package bridge.service;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.BridgeSize;
import bridge.domain.Direction;
import bridge.dto.RoundResultDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("다리게임 서비스 테스트")
class BridgeGameTest {
    // 0 1 0 즉, D U D 생성 제네레이터.
    private final BridgeNumberGenerator bridgeNumberGenerator = new TestBridgeNumberGenerator(Arrays.asList(0, 1, 0));
    private final BridgeGame bridgeGame = new BridgeGame(bridgeNumberGenerator);

    @Test
    @DisplayName("게임 초기화 테스트")
    void initializeGame() {
        bridgeGame.initializeGame(new BridgeSize(3));

        assertThat(bridgeGame.isGameEnd()).isFalse();
        assertThat(bridgeGame.getRoundResults()).isEmpty();
    }

    @Test
    @DisplayName("게임 진행 테스트")
    void move() {
        bridgeGame.initializeGame(new BridgeSize(3));
        RoundResultDto moveResult = bridgeGame.move(Direction.DOWN);

        assertThat(moveResult.isCanMove()).isTrue();
    }

    @Test
    @DisplayName("이동 실패후 게임 재시작 테스트")
    void retry() {
        bridgeGame.initializeGame(new BridgeSize(3));
        bridgeGame.move(Direction.UP);
        bridgeGame.retry();

        assertThat(bridgeGame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("이동 실패후 게임 종료 테스트")
    void quitGame() {
        bridgeGame.initializeGame(new BridgeSize(3));
        bridgeGame.move(Direction.DOWN);
        bridgeGame.move(Direction.UP);
        bridgeGame.move(Direction.DOWN);

        assertThat(bridgeGame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("정상 플레이 후 게임 종료 테스트")
    void successGame() {
        bridgeGame.initializeGame(new BridgeSize(3));
        bridgeGame.move(Direction.UP);
        bridgeGame.quitGame();

        assertThat(bridgeGame.isGameEnd()).isTrue();
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