package bridge.domain;

import java.util.Arrays;

public enum GameCommand {
    RESTART("R"),
    QUIT("Q");

    private static final String INVALID_COMMAND_MESSAGE = "[ERROR] 게임 재시도 및 종료는 R 과 Q만 가능합니다.";

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand of(String target) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.command.equals(target))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }

}
