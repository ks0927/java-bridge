package bridge;

import java.util.List;

public class OutputResolver {
    public static final String START_PREFIX = "[";
    public static final String ROUND_SUFFIX = "|";
    public static final String END_SUFFIX = "]";

    public static final String SUCCESS_MARK = " O ";
    public static final String BLANK_MARK = "   ";
    public static final String FAIL_MARK = " X ";

    public String resolveRoundResult(List<RoundResultDto> roundResultDtos) {
        String upPosition = START_PREFIX;
        String downPosition = START_PREFIX;
        for (RoundResultDto roundResultDto : roundResultDtos) {
            if (roundResultDto.getBridgeMove().equals(BridgeMove.UP)) {
                upPosition += makeMark(roundResultDto) + ROUND_SUFFIX;
                downPosition += BLANK_MARK + ROUND_SUFFIX;
            }
            if (roundResultDto.getBridgeMove().equals(BridgeMove.DOWN)) {
                upPosition += BLANK_MARK + ROUND_SUFFIX;
                downPosition += makeMark(roundResultDto) + ROUND_SUFFIX;
            }
        }
        return upPosition.substring(0, upPosition.length() - 1) + END_SUFFIX + "\n" +
                downPosition.substring(0, downPosition.length() - 1) + END_SUFFIX;
    }

    private String makeMark(RoundResultDto roundResultDto) {
        if (roundResultDto.isCanMove()) {
            return SUCCESS_MARK;
        }
        return FAIL_MARK;
    }
}
