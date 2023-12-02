package CaseStudyTicTacToe.Strategies.BotPlayingStrategies;

import CaseStudyTicTacToe.Models.Board;
import CaseStudyTicTacToe.Models.Move;

public interface BotPlayingStrategy {

    Move makeMove(Board board);
}
