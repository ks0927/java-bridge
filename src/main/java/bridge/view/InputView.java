package bridge.view;

import bridge.domain.Direction;
import bridge.domain.BridgeSize;
import bridge.domain.GameCommand;
import camp.nextstep.edu.missionutils.Console;

import java.util.function.Supplier;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    private static final String READ_BRIDGE_SIZE_MESSAGE = "다리의 길이를 입력해주세요.";
    private static final String READ_BRIDGE_MOVE_MESSAGE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String READ_GAME_COMMAND_MESSAGE = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    private final InputMapper inputMapper;

    public InputView(InputMapper inputMapper) {
        this.inputMapper = inputMapper;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public BridgeSize readBridgeSize() {
        System.out.println(READ_BRIDGE_SIZE_MESSAGE);
        return getInputUntilValid(() -> inputMapper.mapToBridgeSize(Console.readLine()));
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public Direction readMoving() {
        System.out.println(READ_BRIDGE_MOVE_MESSAGE);
        return getInputUntilValid(() -> inputMapper.mapToBridgeMove(Console.readLine()));
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public GameCommand readGameCommand() {
        System.out.println(READ_GAME_COMMAND_MESSAGE);
        return getInputUntilValid(() -> inputMapper.mapToGameCommand(Console.readLine()));
    }


    private <T> T getInputUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
