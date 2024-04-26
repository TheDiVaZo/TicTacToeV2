package me.thedivazo.xo.board;

import me.thedivazo.xo.Move;
import me.thedivazo.xo.PlayerSymbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class ArrayBoard implements Board{

    private List<Move> movies;
    private PlayerSymbol[][] board;
    private int size;
    private int countForWin;
    private PlayerSymbol winner = null;
    public ArrayBoard(int size, int countForWin) {
        this.movies = new ArrayList<>(size*size);
        this.board = new PlayerSymbol[size][size];
        this.size = size;
        this.countForWin = countForWin;
    }

    @Override
    public boolean place(PlayerSymbol playerSymbol, int row, int column) {
        if (row < 0 || row >= size)
            return false;
        if (column < 0 || column >= size)
            return false;
        if (board[row][column] != null) return false;

        board[row][column] = playerSymbol;

        movies.add(new Move(playerSymbol, row, column));

        boolean isMoveWin = lastMoveWinner();
        if (winner == null && isMoveWin) winner = playerSymbol;
        return true;
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
                        predicate.test(i ->{
                            int x = Math.max(row - column, 0) + i;
                            int y = Math.max(column - row, 0) + i;
                            if (x >= size || y >= size) return null;
                            return getCell(x, y);
                        }) ||
                        //Diagonal right check
                        predicate.test(i -> {
                            int x = Math.min(row + column, size) - i;
                            int y = Math.max(column + row - size+1, 0) + i;
                            if (x >= size || y >= size || x < 0 || y < 0) return null;
                            return getCell(x, y);
                        });

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
    public boolean place(Move move) {
        return place(move.playerSymbol(), move.x(), move.y());
    }

    @Override
    public boolean isPlace(int x, int y) {
        return board[x][y] == null;
    }

    @Override
    public boolean isPlace(Move move) {
        return isPlace(move.x(), move.y());
    }

    @Override
    public @Nullable PlayerSymbol getCell(int x, int y) {
        return board[x][y];
    }

    @Override
    public @Nullable PlayerSymbol checkWinner() {
        return winner;
    }

    @Override
    public List<Move> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    @Override
    public Move getLastMove() {
        return movies.get(movies.size() - 1);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCountForWin() {
        return countForWin;
    }
}
