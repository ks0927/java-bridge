package bridge;

public class InputMapper {
    private static final String INVALID_BRIDGE_SIZE_TYPE_MESSAGE = "[ERROR] 다리의 길이는 숫자여야 합니다.";

    public BridgeSize mapToBridgeSize(String input) {
        int bridgeSize;
        try {
            bridgeSize = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BRIDGE_SIZE_TYPE_MESSAGE);
        }
        return new BridgeSize(bridgeSize);
    }
}
