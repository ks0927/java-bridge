package bridge.dto;

import java.util.List;

public class GameResultDto {
    private List<RoundResultDto> gameResult;
    private boolean isSuccess;
    private int totalCount;

    public GameResultDto(List<RoundResultDto> gameResult, boolean isSuccess, int totalCount) {
        this.gameResult = gameResult;
        this.isSuccess = isSuccess;
        this.totalCount = totalCount;
    }

    public List<RoundResultDto> getGameResult() {
        return gameResult;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
