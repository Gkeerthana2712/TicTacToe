package CaseStudyTicTacToe;

import CaseStudyTicTacToe.Controler.GameController;
import CaseStudyTicTacToe.Models.*;
import CaseStudyTicTacToe.Strategies.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args)throws Exception{
        GameController gameController = new GameController();

        try{
            int dimension =3;
            List<Player> playerList = new ArrayList<>();
            playerList.add(new Player(new Symbol('x'), "keerthi", 1L, PlayerType.HUMAN));
            playerList.add(new Bot(new Symbol('o'),"GPT", 2L, BotDifficultyLevel.EASY));
            List<WinningStrategy> winningStrategies = new ArrayList<>();
            Game game = gameController.startGame(dimension, playerList, winningStrategies);

            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                gameController.printBoard(game);
                gameController.makeMove(game);
            }


        }catch (Exception ex){
            System.out.println("There was an exception in creating the game");
            System.out.println("The exception was "+ex);
        }

        System.out.println("Game is been created");



    }
}
