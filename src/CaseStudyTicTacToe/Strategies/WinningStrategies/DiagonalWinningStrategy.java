package CaseStudyTicTacToe.Strategies.WinningStrategies;

import CaseStudyTicTacToe.Models.Board;
import CaseStudyTicTacToe.Models.Move;
import CaseStudyTicTacToe.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{

    private Map<Symbol, Integer> leftDiagonalCount = new HashMap<>();
    private Map<Symbol, Integer> rightDiagonalCount = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //left diagonal
        if(row == col){
            if(!leftDiagonalCount.containsKey(symbol)){
                leftDiagonalCount.put(symbol, 0);
            }

            leftDiagonalCount.put(symbol, leftDiagonalCount.get(symbol)+1);

            if(leftDiagonalCount.get(symbol) == board.getSize()){
                return true;
            }
        }

        //right diagonal
        if(row + col == board.getSize()-1){
            if(!rightDiagonalCount.containsKey(symbol)){
                rightDiagonalCount.put(symbol, 0);
            }

            rightDiagonalCount.put(symbol, rightDiagonalCount.get(symbol)+1);

            if(rightDiagonalCount.get(symbol) == board.getSize()){
                return true;
            }
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        //left diagonal
        if(row == col){
            leftDiagonalCount.put(symbol, leftDiagonalCount.get(symbol)-1);
        }
        //right diagonal
        if(row + col == board.getSize()-1){
            rightDiagonalCount.put(symbol, rightDiagonalCount.get(symbol)-1);
        }
    }
}
