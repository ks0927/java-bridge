package bridge.domain;

import java.util.Arrays;

public enum Direction {
    UP("U", 1),
    DOWN("D", 0);

    private static final String INVALID_POSITION_MESSAGE = "[ERROR] 이동할 칸은 U 또는 D만 가능합니다.";
    private static final String INVALID_NUMBER_MESSAGE = "[ERROR] 다리의 숫자는 1 또는 0만 가능합니다.";

    private final String position;
    private final int positionCode;

    Direction(String position, int positionCode) {
        this.position = position;
        this.positionCode = positionCode;
    }

    public static Direction of(String target) {
        return Arrays.stream(values())
                .filter(bridgeMove -> bridgeMove.position.equals(target))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_POSITION_MESSAGE));
    }

    public static String numberToPosition(int number) {
        return Arrays.stream(values())
                .filter(bridgeMove -> bridgeMove.positionCode == number)
                .map(Direction::getPosition)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_NUMBER_MESSAGE));
    }

    public String getPosition() {
        return position;
    }

    public int getPositionCode() {
        return positionCode;
    }
}
