package me.thedivazo.xo.board;

import me.thedivazo.xo.Move;
import me.thedivazo.xo.PlayerSymbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class LongBoard implements Board {

    private final int blank = 0b00;
    /**
     * The 2 bit representation of the token X.
     */
    private final int x = 0b01;
    /**
     * The 2 bit representation of the token O.
     */
    private final int o = 0b10;

    private long board = 0;

    private final int size;
    private final int countForWin;

    private @Nullable PlayerSymbol winner = null;

    private final List<Move> movies;

    public LongBoard(int size, int countForWin) {
        this.size = size;
        this.countForWin = countForWin;
        this.movies = new ArrayList<>(size * size);
    }

    private LongBoard(long board, int size, int countForWin, @Nullable PlayerSymbol winner, List<Move> movies) {
        this.board = board;
        this.size = size;
        this.countForWin = countForWin;
        this.winner = winner;
        this.movies = new ArrayList<>(movies);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCountForWin() {
        return countForWin;
    }

    @Override
    public boolean place(@NotNull PlayerSymbol playerSymbol, int row, int column) {
            if (row < 0 || row >= size)
                return false;
            if (column < 0 || column >= size)
                return false;

        if (!positionEmpty(row, column))
            return false;

        setPosition(row, column, getPlayer(playerSymbol));

        movies.add(new Move(playerSymbol, row, column));

        boolean isMoveWin = lastMoveWinner();
        if (winner == null && isMoveWin) winner = playerSymbol;
        return true;
    }

    private int getPlayer(PlayerSymbol playerSymbol) {
        if (playerSymbol == null) return blank;
        return switch (playerSymbol) {
            case O -> o;
            case X -> x;
        };
    }

    private @Nullable PlayerSymbol getPlayer(int player) {
        return switch (player) {
            case x -> PlayerSymbol.X;
            case o -> PlayerSymbol.O;
            default -> null;
        };
    }

    /**
     * Sets the given cell to the given value. Note
     * that the given value shouldn't exceed 2 bits in length.
     * (i.e. the values 0, 1, 2 and 3).
     *
     * @param row    The cell row.
     * @param column The cell column.
     * @param value  The value to set the cell to.
     */
    private void setPosition(int row, int column, int value) {
        int pos = (row + (column * size)) * 2;
        long mask = 0b11 << pos;
        mask = ~mask;

        board = (board & mask) | (value << pos);
    }

    private boolean positionEmpty(int row, int column) {
        return getPosition(row, column) == blank;
    }

    private int getPosition(int row, int column) {
        int pos = (row + (column * size)) * 2;
        long mask = 0b11 << pos;
        return (int) ((board & mask) >> pos);
    }

    @Override
    public boolean place(Move move) {
        return place(move.playerSymbol(), move.x(), move.y());
    }

    @Override
    public boolean isPlace(int x, int y) {
        return positionEmpty(x, y);
    }

    @Override
    public boolean isPlace(Move move) {
        return isPlace(move.x(), move.y());
    }

    @Override
    public @Nullable PlayerSymbol getCell(int x, int y) {
        return getPlayer(getPosition(x, y));
    }

    @Override
    public @Nullable PlayerSymbol checkWinner() {
        return winner;
    }

    public boolean lastMoveWinner() {
        Move lastMove = getLastMove();
        if (lastMove == null) return false;
        int row = lastMove.x();
        int column = lastMove.y();
        Predicate<IntFunction<PlayerSymbol>> predicate = getIntFunctionPredicate(lastMove);
        return
                //Horizontal check
                predicate.test(i -> getCell(i, column)) ||
                //Vertical check
                predicate.test(i -> getCell(row, i)) ||
                //Diagonal left check
                predicate.test(i -> getCell(Math.max(row - column, 0) + i, Math.max(column - row, 0) + i)) ||
                //Diagonal right check
                predicate.test(i -> getCell(Math.max(row + column - size, 0) + i, Math.min(row + column, size) - i));

    }

    private @NotNull Predicate<IntFunction<PlayerSymbol>> getIntFunctionPredicate(Move lastMove) {
        PlayerSymbol playerSymbol = lastMove.playerSymbol();

        return ps -> {
            int count = 0;
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                if (flag && count == countForWin) return true;
                PlayerSymbol psResult = ps.apply(i);
                if (!flag && psResult == playerSymbol) {
                    count = 1;
                    flag = true;
                } else if (flag && psResult != playerSymbol) {
                    count = 0;
                    flag = false;
                }
                else if (flag) count++;
            }
            return false;
        };
    }

    @Override
    public List<Move> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    @Override
    public Move getLastMove() {
        return movies.isEmpty() ? null : movies.get(movies.size() - 1);
    }

    @Override
    public LongBoard clone() {
        return new LongBoard(board, size, countForWin, winner, movies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongBoard longBoard)) return false;
        return board == longBoard.board && size == longBoard.size && countForWin == longBoard.countForWin && winner == longBoard.winner && Objects.equals(movies, longBoard.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, size, countForWin, winner, movies);
    }
}
