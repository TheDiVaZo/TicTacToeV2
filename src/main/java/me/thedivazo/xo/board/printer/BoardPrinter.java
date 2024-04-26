package me.thedivazo.xo.board.printer;

import me.thedivazo.xo.board.BoardReadable;

public interface BoardPrinter {
    void printBoard(BoardReadable board);
    void printMessage(String message);
}
