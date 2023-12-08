package bridge;

import bridge.config.BridgeConfig;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BridgeConfig bridgeConfig = new BridgeConfig();
        bridgeConfig.bridgeController().run();
    }
}
