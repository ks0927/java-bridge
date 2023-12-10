package bridge.config;

import bridge.controller.BridgeController;
import bridge.service.BridgeGame;
import bridge.service.BridgeNumberGenerator;
import bridge.service.BridgeRandomNumberGenerator;
import bridge.view.InputMapper;
import bridge.view.InputView;
import bridge.view.OutputResolver;
import bridge.view.OutputView;

public class BridgeConfig {
    public BridgeController bridgeController() {
        return new BridgeController(bridgeGame(), outputView(), inputView());
    }

    public BridgeGame bridgeGame() {
        return new BridgeGame(bridgeNumberGenerator());
    }

    public BridgeNumberGenerator bridgeNumberGenerator() {
        return new BridgeRandomNumberGenerator();
    }

    public OutputView outputView() {
        return new OutputView(outputResolver());
    }

    public OutputResolver outputResolver() {
        return new OutputResolver();
    }

    public InputView inputView() {
        return new InputView(inputMapper());
    }

    public InputMapper inputMapper() {
        return new InputMapper();
    }
}
