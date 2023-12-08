package bridge;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private final List<BridgeMove> bridgeMoves;

    private Bridge(List<BridgeMove> bridgeMoves) {
        this.bridgeMoves = bridgeMoves;
    }

    public static Bridge of(List<String> bridgeMoves) {
        return new Bridge(bridgeMoves.stream()
                .map(n -> BridgeMove.of(n))
                .collect(Collectors.toList()));
    }

    public boolean compare(BridgeMove bridgeMove, int round) {
        return bridgeMoves.get(round - 1).equals(bridgeMove);
    }
}
