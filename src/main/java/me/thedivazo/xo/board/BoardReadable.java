package me.thedivazo.xo.board;

import me.thedivazo.xo.Move;
import me.thedivazo.xo.PlayerSymbol;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface BoardReadable {
    /**
     * check if placed cell
     * @param x coordinate of cell
     * @param y coordinate of cell
     * @return if placed, return True, or return false
     */
    boolean isPlace(int x, int y);

    boolean isPlace(Move move);

    /**
     * Returns a cell to the field
     * @param x - coordinate
     * @param y - coordinate
     * @return {@link PlayerSymbol} element or {@code null} if cell is empty
     */
    @Nullable
    PlayerSymbol getCell(int x, int y);

    /**
     * Check if there is a winner
     * @return {@link PlayerSymbol} element or {@code null} if there is no winner
     */
    @Nullable
    PlayerSymbol checkWinner();

    List<Move> getMovies();

    Move getLastMove();

    int getSize();
    int getCountForWin();
}
