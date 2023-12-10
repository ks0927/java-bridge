package bridge.controller;

import bridge.domain.BridgeSize;
import bridge.domain.Direction;
import bridge.domain.GameCommand;
import bridge.dto.RoundResultDto;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

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
        bridgeGame.initializeGame(bridgeSize);

        while(!bridgeGame.isGameEnd()) {
            playRound();
        }

        outputView.printResult(bridgeGame.getGameResult());
    }

    private void playRound() {
        Direction direction = inputView.readMoving();
        RoundResultDto moveResult = bridgeGame.move(direction);
        outputView.printMap(bridgeGame.getRoundResults());

        if (!moveResult.isCanMove()) {
            handleGameCommand();
        }
    }

    private void handleGameCommand() {
        GameCommand gameCommand = inputView.readGameCommand();
        if (gameCommand.equals(GameCommand.RESTART)) {
            bridgeGame.retry();
        }
        if (gameCommand.equals(GameCommand.QUIT)) {
            bridgeGame.quitGame();
        }
    }
}
