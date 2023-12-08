package bridge;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private Bridge bridge;

    public void makeBridge(BridgeSize bridgeSize, BridgeNumberGenerator bridgeNumberGenerator) {
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        List<String> strings = bridgeMaker.makeBridge(bridgeSize.getLength());
        bridge = Bridge.of(strings);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public RoundResultDto move(BridgeMove bridgeMove, int round) {
        if (bridge.compare(bridgeMove, round)) {
            return new RoundResultDto(bridgeMove, true);
        }
        return new RoundResultDto(bridgeMove, false);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry(List<RoundResultDto> map) {
        map.clear();
    }
}
