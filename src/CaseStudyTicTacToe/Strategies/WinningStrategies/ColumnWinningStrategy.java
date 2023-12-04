package CaseStudyTicTacToe.Strategies.WinningStrategies;

import CaseStudyTicTacToe.Models.Board;
import CaseStudyTicTacToe.Models.Move;
import CaseStudyTicTacToe.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements  WinningStrategy{
    HashMap<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();
    // |0| -> x-1, O-> 2
    // |1| -> x-0, O-> 1
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        // increment value of symbol count by 1
        // I want to increment the count of that symbol in the col by 1

        if(!counts.containsKey(col)){
            counts.put(col, new HashMap<>());

        }

        Map<Symbol, Integer> colMap = counts.get(col);

        if (!colMap.containsKey(symbol)){
            colMap.put(symbol,0);
        }
        colMap.put(symbol,colMap.get(symbol)+1);

        //check if this is winner
        if(colMap.get(symbol) == board.getSize()){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> colMap = counts.get(col);
        colMap.put(symbol,colMap.get(symbol)-1);
    }
}
