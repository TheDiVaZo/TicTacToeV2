package me.thedivazo.xo;

import me.thedivazo.xo.board.Board;
import me.thedivazo.xo.board.BoardFactory;
import me.thedivazo.xo.board.printer.BoardPrinter;
import me.thedivazo.xo.game.Game;

public class TicTacToe {
    private Game game;
    private BoardPrinter boardPrinter;

    public TicTacToe(Game game, BoardPrinter boardPrinter) {
        this.game = game;
        this.boardPrinter = boardPrinter;
    }

    public void run() {
        game.togglePlayer();
        while (!game.isGameEnd()) {
            game.togglePlayer();
            boardPrinter.printBoard(game.getBoard());
            while (!game.doMove()) {

            }
        }
        boardPrinter.printMessage(game.getPlayersMap().get(game.getWinner()).getName() + " won!");
    }
}
