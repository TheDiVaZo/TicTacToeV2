package me.thedivazo.xo.board;

import me.thedivazo.xo.Move;
import me.thedivazo.xo.PlayerSymbol;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Board extends BoardReadable {

    /**
     *
     * @param playerSymbol {@link PlayerSymbol} player
     * @param x coordinate of cell
     * @param y coordinate of cell
     * @return if successful, return True, or return false
     */
    boolean place(PlayerSymbol playerSymbol, int x, int y);

    boolean place(Move move);

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

    List<Move> getMovies();

    Move getLastMove();

    int getSize();





}
