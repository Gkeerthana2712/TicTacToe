package CaseStudyTicTacToe.Controler;


import CaseStudyTicTacToe.Models.Game;
import CaseStudyTicTacToe.Models.GameState;
import CaseStudyTicTacToe.Models.Player;
import CaseStudyTicTacToe.Strategies.WinningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public  Game startGame(int dimensionBoard, List<Player> players, List<WinningStrategy> winningStrategies) throws Exception{
        return Game.getBuilder()
                .setBoardDimension(dimensionBoard)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }
    public void makeMove(Game game){
         game.makeMove();
    }
    public void printBoard(Game game){
          game.printBoard();
    }
    public GameState checkState(Game game){
        return  game.getGameState();

    }
    public void getWinner(Game game){

    }

    public void undo(Game game) {
        game.undo();
    }
}
