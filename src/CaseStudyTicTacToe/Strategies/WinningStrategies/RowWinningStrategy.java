package CaseStudyTicTacToe.Strategies.WinningStrategies;

import CaseStudyTicTacToe.Models.Board;
import CaseStudyTicTacToe.Models.Move;
import CaseStudyTicTacToe.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    HashMap<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();
    // |0| -> x-1, O-> 2
    // |1| -> x-0, O-> 1

    @Override
    public boolean checkWinner(Board board, Move move) {
        // get row,col, symbol
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        // increment value of symbol count by 1
        // I want to increment the count of that symbol in the row by 1

        if(!counts.containsKey(row)){
            counts.put(row, new HashMap<>());

        }

        Map<Symbol, Integer> rowMap = counts.get(row);

        if (!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }
        rowMap.put(symbol,rowMap.get(symbol)+1);

        //check if this is winner
        if(rowMap.get(symbol) == board.getSize()){
            return true;
        }

        return false;
    }


    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> rowMap = counts.get(row);
        rowMap.put(symbol,rowMap.get(symbol)-1);

    }

}
