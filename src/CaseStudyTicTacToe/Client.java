package CaseStudyTicTacToe;

import CaseStudyTicTacToe.Controler.GameController;
import CaseStudyTicTacToe.Models.*;
import CaseStudyTicTacToe.Strategies.WinningStrategies.ColumnWinningStrategy;
import CaseStudyTicTacToe.Strategies.WinningStrategies.DiagonalWinningStrategy;
import CaseStudyTicTacToe.Strategies.WinningStrategies.RowWinningStrategy;
import CaseStudyTicTacToe.Strategies.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args)throws Exception{

        Scanner scn = new Scanner(System.in);
        GameController gameController = new GameController();

        try{
            int dimension =3;
            List<Player> playerList = new ArrayList<>();
            playerList.add(new Player(new Symbol('x'), "keerthi", 1L, PlayerType.HUMAN));
            playerList.add(new Bot(new Symbol('o'),"GPT", 2L, BotDifficultyLevel.EASY));
            List<WinningStrategy> winningStrategies = new ArrayList<>();
            Game game = gameController.startGame(dimension, playerList, winningStrategies);
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColumnWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());
            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                gameController.printBoard(game);
                System.out.println("Want to undo ? y/n :");
                String undoAns = scn.nextLine();

                if(undoAns.equals("y")){
                    gameController.undo(game);
                    continue;
                }
                gameController.makeMove(game);
            }
            gameController.printBoard(game);
            if(game.getGameState().equals(GameState.WIN)){
                System.out.println("Game is over winner is -"+game.getWinner().getName());
            }else{
                System.out.println("Game is draw");
            }

        }catch (Exception ex){
            System.out.println("There was an exception in creating the game");
            System.out.println("The exception was "+ex);
        }
        //System.out.println("Game is been created");

    }
}
