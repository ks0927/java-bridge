package bridge.dto;

import bridge.domain.BridgeMove;

public class RoundResultDto {
    private BridgeMove bridgeMove;
    private boolean canMove;

    public RoundResultDto(BridgeMove bridgeMove, boolean canMove) {
        this.bridgeMove = bridgeMove;
        this.canMove = canMove;
    }

    public BridgeMove getBridgeMove() {
        return bridgeMove;
    }

    public boolean isCanMove() {
        return canMove;
    }
}
