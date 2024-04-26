package me.thedivazo.xo.board.printer;

import me.thedivazo.xo.board.BoardReadable;
import me.thedivazo.xo.Move;
import me.thedivazo.xo.PlayerSymbol;

import java.util.Map;

public class ConsoleBoardPrinter implements BoardPrinter {
    private static final String blankToken = "*";
    private static final Map<PlayerSymbol, String> playerTokens = Map.of(
            PlayerSymbol.X, "X",
            PlayerSymbol.O, "O"
    );

    public void printBoard(BoardReadable boardReadable) {
        String[][] board = new String[boardReadable.getSize()][boardReadable.getSize()];
        for (int i = 0; i < boardReadable.getSize(); i++) {
            for (int j = 0; j < boardReadable.getSize(); j++) {
                board[i][j] = blankToken;
            }
        }
        for (Move movie : boardReadable.getMovies()) {
            board[movie.y()][movie.x()] = playerTokens.get(movie.playerSymbol());
        }
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
