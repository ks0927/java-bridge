package bridge;

import java.util.ArrayList;
import java.util.List;

public class BridgeController {

    private final BridgeGame bridgeGame;
    private final OutputView outputView;
    private final InputView inputView;

    public BridgeController(BridgeGame bridgeGame, OutputView outputView, InputView inputView) {
        this.bridgeGame = bridgeGame;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printGameStart();

        BridgeSize bridgeSize = inputView.readBridgeSize();
        bridgeGame.makeBridge(bridgeSize, new BridgeRandomNumberGenerator());

        List<RoundResultDto> map = new ArrayList<>();
        GameResultDto gameResultDto = gamePlay(bridgeSize, map);
        outputView.printResult(gameResultDto);
    }

    private GameResultDto gamePlay(BridgeSize bridgeSize, List<RoundResultDto> map) {
        int totalTryCount = 1;
        int roundCount = 1;
        while (true) {
            BridgeMove bridgeMove = inputView.readMoving();
            RoundResultDto move = bridgeGame.move(bridgeMove, roundCount);
            map.add(move);
            roundCount++;
            outputView.printMap(map);
            if (roundCount == bridgeSize.getLength() + 1) {
                return new GameResultDto(map, true, totalTryCount);
            }
            if (move.isCanMove() == false) {
                GameCommand gameCommand = inputView.readGameCommand();
                if (gameCommand.equals(GameCommand.RESTART)) {
                    totalTryCount++;
                    bridgeGame.retry(map);
                    roundCount = 1;
                }

                if (gameCommand.equals(GameCommand.QUIT)) {
                    return new GameResultDto(map, false, totalTryCount);
                }
            }
        }
    }
}
