package bridge;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private final List<BridgeMove> bridgeMoves;

    public Bridge(List<String> bridgeMoves) {
        this.bridgeMoves = bridgeMoves.stream()
                .map(n -> BridgeMove.of(n))
                .collect(Collectors.toList());
    }

}
