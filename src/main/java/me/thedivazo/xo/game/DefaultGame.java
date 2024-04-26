package me.thedivazo.xo.game;

import me.thedivazo.xo.board.Board;
import me.thedivazo.xo.board.BoardFactory;
import me.thedivazo.xo.board.BoardReadable;
import me.thedivazo.xo.PlayerSymbol;
import me.thedivazo.xo.player.PlayerMove;
import me.thedivazo.xo.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;

public class DefaultGame implements Game {

    protected final Map<PlayerSymbol, Player> playersMap;
    protected PlayerSymbol currentPlayer;
    protected Board board;

    public DefaultGame(Player one, Player two, BoardFactory boardFactory) {
        currentPlayer = PlayerSymbol.X;
        playersMap = Map.of(
                PlayerSymbol.X, one,
                PlayerSymbol.O, two
        );
        this.board = boardFactory.createBoard();
    }

    @Override
    public Map<PlayerSymbol, Player> getPlayersMap() {
        return playersMap;
    }

    @Override
    public BoardReadable getBoard() {
        return board;
    }

    @Override
    public PlayerSymbol getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public boolean doMove() {
        PlayerMove playerMove = playersMap.get(currentPlayer).doMove(board);
        return board.place(currentPlayer, playerMove.x(), playerMove.y());
    }

    @Override
    public void togglePlayer() {
        if (currentPlayer == PlayerSymbol.X) currentPlayer = PlayerSymbol.O;
        else currentPlayer = PlayerSymbol.X;
    }

    @Override
    public boolean isGameEnd() {
        return board.checkWinner() != null;
    }

    @Override
    public PlayerSymbol getWinner() {
        return board.checkWinner();
    }
}
