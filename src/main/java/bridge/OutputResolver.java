package bridge;

import java.util.List;

public class OutputResolver {
    public static final String START_PREFIX = "[";
    public static final String ROUND_SUFFIX = "|";
    public static final String END_SUFFIX = "]";
    public static final String SUCCESS_MARK = " O ";
    public static final String BLANK_MARK = "   ";
    public static final String FAIL_MARK = " X ";
    private static final String GAME_RESULT_MESSAGE = "최종 게임 결과\n";

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

    public String resolveGameResult(GameResultDto gameResultDto) {
        String result = GAME_RESULT_MESSAGE;
        result += resolveRoundResult(gameResultDto.getGameResult());
        result += "게임 성공 여부: " + makeResult(gameResultDto) + "\n";
        result += "총 시도한 횟수: " + gameResultDto.getTotalCount() + "\n";
        return result;
    }

    private String makeResult(GameResultDto gameResultDto) {
        if (gameResultDto.isSuccess()) {
            return "성공";
        }
        return "실패";
    }
}
