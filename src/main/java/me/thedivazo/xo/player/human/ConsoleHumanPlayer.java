package me.thedivazo.xo.player.human;

import me.thedivazo.xo.board.BoardReadable;
import me.thedivazo.xo.player.Player;
import me.thedivazo.xo.player.PlayerMove;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleHumanPlayer implements Player {
    public static final AtomicInteger nextId = new AtomicInteger(0);

    private final int id;

    public ConsoleHumanPlayer() {
        id = nextId.getAndAdd(1);
    }

    @Override
    public PlayerMove doMove(BoardReadable board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter traverse coordinates. Example: 5 5");
        int[] movies = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        return new PlayerMove(movies[0], movies[1]);
    }

    @Override
    public String getName() {
        return "Player "+id;
    }
}
