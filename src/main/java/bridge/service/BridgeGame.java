package bridge.service;

import bridge.domain.Bridge;
import bridge.domain.Direction;
import bridge.domain.BridgeSize;
import bridge.dto.GameResultDto;
import bridge.dto.RoundResultDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final BridgeNumberGenerator bridgeNumberGenerator;
    private Bridge bridge;
    private BridgeSize bridgeSize;
    private int totalTryCount;
    private int roundCount;
    private List<RoundResultDto> roundResults;

    public BridgeGame(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public void makeBridge(BridgeSize bridgeSize) {
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        List<String> directions = bridgeMaker.makeBridge(bridgeSize.getLength());
        bridge = Bridge.of(directions);
    }

    public void initializeGame(BridgeSize bridgeSize) {
        makeBridge(bridgeSize);
        this.bridgeSize = bridgeSize;
        this.totalTryCount = 1;
        this.roundCount = 1;
        this.roundResults = new ArrayList<>();
    }

    public boolean isGameEnd() {
        return roundCount > bridgeSize.getLength();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public RoundResultDto move(Direction direction) {
        boolean canMove = bridge.compare(direction, roundCount);
        RoundResultDto moveResult = new RoundResultDto(direction, canMove);
        roundResults.add(moveResult);
        roundCount++;
        return moveResult;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        totalTryCount++;
        roundCount = 1;
        roundResults.clear();
    }

    public List<RoundResultDto> getRoundResults() {
        return roundResults;
    }

    public GameResultDto getGameResult() {
        return new GameResultDto(roundResults, isGameEnd(), totalTryCount);
    }

    public void quitGame() {
        roundCount = bridgeSize.getLength() + 1;
    }
}
