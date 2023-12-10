package bridge.domain;

public class BridgeSize {
    private static final String INVALID_RANGE_MESSAGE = "[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.";

    private final int length;

    public BridgeSize(int length) {
        validate(length);
        this.length = length;
    }

    private void validate(int target) {
        if (InvalidRange(target)) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }
    }

    private boolean InvalidRange(int target) {
        return target < 3 || target > 20;
    }

    public int getLength() {
        return length;
    }
}
