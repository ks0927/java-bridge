package bridge.dto;

import bridge.domain.Direction;

public class RoundResultDto {
    private Direction direction;
    private boolean canMove;

    public RoundResultDto(Direction direction, boolean canMove) {
        this.direction = direction;
        this.canMove = canMove;
    }

    public Direction getBridgeMove() {
        return direction;
    }

    public boolean isCanMove() {
        return canMove;
    }
}
