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
        for (int y = 0; y < boardReadable.getSize(); y++) {
            for (int x = 0; x < boardReadable.getSize(); x++) {
                PlayerSymbol playerSymbol = boardReadable.getCell(x, y);
                System.out.print((playerSymbol == null ? blankToken : playerTokens.get(playerSymbol)) + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
