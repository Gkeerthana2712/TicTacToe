package CaseStudyTicTacToe.Strategies.BotPlayingStrategies;

import CaseStudyTicTacToe.Models.Board;
import CaseStudyTicTacToe.Models.Cell;
import CaseStudyTicTacToe.Models.CellState;
import CaseStudyTicTacToe.Models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row: board.getBoard()){
            for (Cell cell: row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell, null);
                }

            }
        }
        return null;
    }
}
