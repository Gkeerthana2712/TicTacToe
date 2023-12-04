package CaseStudyTicTacToe.Strategies.WinningStrategies;

import CaseStudyTicTacToe.Models.Board;
import CaseStudyTicTacToe.Models.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Move move);
}
