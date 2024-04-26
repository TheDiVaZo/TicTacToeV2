package me.thedivazo.xo.player;

import me.thedivazo.xo.board.BoardReadable;

public interface Player {
    PlayerMove doMove(BoardReadable board);
    String getName();
}
