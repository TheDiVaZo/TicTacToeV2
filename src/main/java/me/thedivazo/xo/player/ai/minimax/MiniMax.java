package me.thedivazo.xo.player.ai.minimax;

import me.thedivazo.xo.Move;
import me.thedivazo.xo.PlayerSymbol;
import me.thedivazo.xo.board.BoardReadable;

/**
 * @author TheDiVaZo
 * created on 03.05.2024
 */
public class MiniMax {
    PlayerSymbol ourPlayer;
    PlayerSymbol oppPlayer;

    public MiniMax() {
    }

    public Move findOptimalMove(BoardReadable boardReadable, PlayerSymbol playerSymbol, int depth) {
        this.ourPlayer = playerSymbol;
        this.oppPlayer = playerSymbol == PlayerSymbol.X? PlayerSymbol.O : PlayerSymbol.X;
        Score score = miniMax(boardReadable, ourPlayer, depth);
        return score.move();
    }

    private Score miniMax(BoardReadable boardReadable, PlayerSymbol player, int depth) {
        int bestScore = (player == ourPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move move = null;
        if (depth == 0 || boardReadable.checkWinner() != null) {
            bestScore =
        }
    }

    private int evaluate(BoardReadable boardReadable) {
        int score = 0;
        int x1 = boardReadable.getSize();
        int y1 = boardReadable.getSize();

        int x2 = 0;
        int y2 = boardReadable.getSize();

        int x3 = boardReadable.getSize();
        int y3 = 0;

        int x4 = 0;
        int y4 = 0;

        for (Move movie : boardReadable.getMovies()) {
            x1 = Math.min(x1, movie.x());
            y1 = Math.min(y1, movie.y());

            x2 = Math.max(x2, movie.x());
            y2 = Math.min(y2, movie.y());

            x3 = Math.min(x3, movie.x());
            y3 = Math.max(y3, movie.y());

            x4 = Math.max(x4, movie.x());
            y4 = Math.max(y4, movie.y());
        }

    }


}
