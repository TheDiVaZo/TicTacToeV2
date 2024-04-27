package me.thedivazo.xo;

import me.thedivazo.xo.board.*;
import me.thedivazo.xo.board.printer.BoardPrinter;
import me.thedivazo.xo.board.printer.ConsoleBoardPrinter;
import me.thedivazo.xo.game.DefaultGame;
import me.thedivazo.xo.game.Game;
import me.thedivazo.xo.player.Player;
import me.thedivazo.xo.player.ai.RandomAI;
import me.thedivazo.xo.player.human.ConsoleHumanPlayer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter field size ");
        int size = scanner.nextInt();
        int countForWin;
        if (size > 15) countForWin = 5;
        else {
            countForWin = 3;
        }
        System.out.println("Start game");
        BoardFactory boardFactory = () -> size <= 4 ? new LongBoard(size, countForWin) : new BigIntBoard(size, countForWin);
        BoardPrinter boardPrinter = new ConsoleBoardPrinter();
        Player one = new ConsoleHumanPlayer();
        Player two = new RandomAI();
        Game game = new DefaultGame(one, two, boardFactory);
        TicTacToe ticTacToe = new TicTacToe(game, boardPrinter);
        ticTacToe.run();
    }
}