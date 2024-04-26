package me.thedivazo.xo.game;

import me.thedivazo.xo.board.BoardReadable;
import me.thedivazo.xo.PlayerSymbol;
import me.thedivazo.xo.player.Player;

import java.util.Map;

public interface Game {
    BoardReadable getBoard();
    PlayerSymbol getCurrentPlayer();
    boolean doMove();
    void togglePlayer();
    boolean isGameEnd();
    PlayerSymbol getWinner();
    Map<PlayerSymbol, Player> getPlayersMap();
}
