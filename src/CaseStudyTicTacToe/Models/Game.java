package CaseStudyTicTacToe.Models;

import CaseStudyTicTacToe.Client;
import CaseStudyTicTacToe.Exceptions.MoreThanOneBotException;
import CaseStudyTicTacToe.Exceptions.PlayerCountDimensionMismatchException;
import CaseStudyTicTacToe.Exceptions.PlayerSymbolNotUniqueException;
import CaseStudyTicTacToe.Strategies.WinningStrategies.WinningStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIdx;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.gameState = getGameState().IN_PROGRESS;
        this.nextMovePlayerIdx =0;
    }

    public  static  Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        this.board.print();
    }

    public void makeMove() {
        Player currentMovePlayer = this.players.get(nextMovePlayerIdx);
        System.out.println("This is "+currentMovePlayer.getName() +"'s turn");

        Move playerMove = currentMovePlayer.makeMove(board);

        if(!validateMove(playerMove)){
            System.out.println("Your move was not valid");
            return;
        }
        int row = playerMove.getCell().getRow();
        int col = playerMove.getCell().getCol();
        Cell boardCell = this.board.getBoard().get(row).get(col);
        boardCell.setCellState(CellState.FILLED);
        boardCell.setPlayer(currentMovePlayer);

        Move finalMoveObj = new Move(boardCell, currentMovePlayer);
        moves.add(finalMoveObj);

        nextMovePlayerIdx++;
        nextMovePlayerIdx %= players.size();

        // check if we have a winner
        if(checkWinner(finalMoveObj)){
            gameState = GameState.WIN;
            this.winner = currentMovePlayer;
        }else if(this.moves.size() == this.board.getSize()* this.board.getSize()){
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy: this.winningStrategies){
            if(winningStrategy.checkWinner(this.board, move)){
                return  true;
            }
        }
        return  false;
    }

    public void undo() {
        // what if there was no move ?
        if(moves.size() ==0){
            return;
        }
        //get values of the last move
        Move lastMove = this.moves.get(moves.size()-1);
        moves.remove(lastMove);
        //undo all the work done in the cell
        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);
        //undo of next player increament
        nextMovePlayerIdx--;
        nextMovePlayerIdx = (nextMovePlayerIdx + players.size()) % players.size();
        // undo all changes done for checkwinner in hashmap
        for(WinningStrategy winningStrategy : this.winningStrategies){
            winningStrategy.handleUndo(board, lastMove);
        }

    }
    private boolean validateMove(Move playerMove) {

        int row = playerMove.getCell().getRow();
        int col = playerMove.getCell().getCol();

        if(row>= board.getSize() || col>= board.getSize()){
            return false;
        }

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            return false;
        }

        return  true;
    }



    public static  class Builder{

        private int boardDimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        private Builder(){
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.boardDimension = 0;
        }

        public Builder setBoardDimension(int boardDimension) {
            this.boardDimension = boardDimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validateBotCount() throws Exception{
            // VALIDATE BOT COUNT
            int botcount =0;
            for(Player player : this.players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botcount++;

                }
            }
            if(botcount>1){
                throw new MoreThanOneBotException();
            }
        }

        private void validatePlayerCount() throws Exception{
            // VALIDATE PLAYER COUNT
            if (players.size() != boardDimension-1){
                throw new PlayerCountDimensionMismatchException();
            }
        }

        private void validateUniqueSymbol() throws Exception{
            // VALIDATE UNIQUE PLAYER SYMBOL IN A GAME
            HashSet<Character> uniqueSymbols = new LinkedHashSet<>();
            for(Player player: this.players){
                if(uniqueSymbols.contains(player.getSymbol().getaChar())){
                    throw  new PlayerSymbolNotUniqueException();
                }
                uniqueSymbols.add(player.getSymbol().getaChar());
            }
        }
        private void validate() throws Exception{
            validateBotCount();
            validatePlayerCount();
            validateUniqueSymbol();
        }
        public Game build() throws Exception{
            validate();
            return new Game(boardDimension,players,winningStrategies);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMovePlayerIdx() {
        return nextMovePlayerIdx;
    }

    public void setNextMovePlayerIdx(int nextMovePlayerIdx) {
        this.nextMovePlayerIdx = nextMovePlayerIdx;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
}
