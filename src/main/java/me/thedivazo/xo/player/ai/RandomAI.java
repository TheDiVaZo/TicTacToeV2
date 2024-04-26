package me.thedivazo.xo.player.ai;

import me.thedivazo.xo.board.BoardReadable;
import me.thedivazo.xo.player.PlayerMove;

import java.util.Random;

public class RandomAI implements AI{
    private Random random = new Random();

    @Override
    public PlayerMove doMove(BoardReadable board) {
        int x;
        int y;
        do {
            x = random.nextInt(board.getSize());
            y = random.nextInt(board.getSize());
        }
        while (!board.isPlace(x, y));
        System.out.println("Skibidi dop dop yes yes! I move to "+ x + " " + y);
        return new PlayerMove(x,y);
    }

    @Override
    public String getName() {
        return "RANDOM_AI";
    }
}
