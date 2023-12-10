package bridge.view;

import bridge.domain.Direction;
import bridge.dto.GameResultDto;
import bridge.dto.RoundResultDto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputResolver {
    public static final String START_PREFIX = "[";
    public static final String ROUND_SUFFIX = "|";
    public static final String END_SUFFIX = "]";
    public static final String SUCCESS_MARK = " O ";
    public static final String BLANK_MARK = "   ";
    public static final String FAIL_MARK = " X ";
    public static final String SUCCESS_MESSAGE = "성공";
    public static final String FAIL_MESSAGE = "실패";
    private static final String GAME_RESULT_MESSAGE = "최종 게임 결과";
    private static final String GAME_RESULT_SUCCESS_PREFIX = "게임 성공 여부: ";
    private static final String GAME_RESULT_TOTAL_COUNT_PREFIX = "총 시도한 횟수: ";

    public String resolveRoundResult(List<RoundResultDto> roundResultDtos) {
        Map<Direction, StringBuilder> positions = initializePositions();

        for (RoundResultDto roundResultDto : roundResultDtos) {
            appendMarkByDirection(positions, roundResultDto);
        }
        return formatOutput(positions);
    }

    private Map<Direction, StringBuilder> initializePositions() {
        return Arrays.stream(Direction.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> new StringBuilder(START_PREFIX),
                        (existing, replacement) -> existing,
                        () -> new TreeMap<>(Comparator.comparingInt(Direction::getPositionCode).reversed())
                ));
    }

    private void appendMarkByDirection(Map<Direction, StringBuilder> positions, RoundResultDto roundResultDto) {
        positions.keySet()
                .forEach(direction -> appendMarkToPosition(positions.get(direction), roundResultDto, direction));
    }

    private void appendMarkToPosition(StringBuilder position, RoundResultDto roundResultDto, Direction direction) {
        String mark = BLANK_MARK;
        if (roundResultDto.getBridgeMove().equals(direction)) {
            mark = makeMark(roundResultDto);
        }
        position.append(mark).append(ROUND_SUFFIX);
    }

    private String formatOutput(Map<Direction, StringBuilder> positions) {
        return positions.values().stream()
                .map(this::formatPosition)
                .collect(Collectors.joining("\n"));
    }

    private String formatPosition(StringBuilder position) {
        return position.substring(0, position.length() - 1) + END_SUFFIX;
    }

    private String makeMark(RoundResultDto roundResultDto) {
        if (roundResultDto.isCanMove()) {
            return SUCCESS_MARK;
        }
        return FAIL_MARK;
    }

    public String resolveGameResult(GameResultDto gameResultDto) {
        StringBuilder result = new StringBuilder();
        result.append(GAME_RESULT_MESSAGE).append("\n");
        result.append(resolveRoundResult(gameResultDto.getGameResult())).append("\n");
        result.append(GAME_RESULT_SUCCESS_PREFIX).append(makeResult(gameResultDto)).append("\n");
        result.append(GAME_RESULT_TOTAL_COUNT_PREFIX).append(gameResultDto.getTotalCount()).append("\n");
        return result.toString();
    }

    private String makeResult(GameResultDto gameResultDto) {
        if (gameResultDto.isSuccess()) {
            return SUCCESS_MESSAGE;
        }
        return FAIL_MESSAGE;
    }
}
