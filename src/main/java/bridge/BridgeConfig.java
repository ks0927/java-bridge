package bridge;

public class BridgeConfig {
    public BridgeController bridgeController() {
        return new BridgeController(bridgeGame(), outputView(), inputView());
    }

    public BridgeGame bridgeGame() {
        return new BridgeGame();
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
