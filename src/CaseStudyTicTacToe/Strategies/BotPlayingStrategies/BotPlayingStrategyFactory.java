package CaseStudyTicTacToe.Strategies.BotPlayingStrategies;

import CaseStudyTicTacToe.Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyFromFactory(BotDifficultyLevel botDifficultyLevel){
        return new EasyBotPlayingStrategy();

    }
}
