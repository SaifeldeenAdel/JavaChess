package ChessCore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ChessGame {
    private Board board;
    private Map<Square, Square> squares;
    private Color playerTurn;

    public ChessGame(){
        board = new Board();
        playerTurn = Color.WHITE;
    }

    public ArrayList<Square> getAllValidMovesFromSquare(Square square){
        ArrayList<Square> validMoves = new ArrayList<>();
        if (square.getPiece() == null){
            return validMoves;
        }
        if (square.getPiece().isWhite() && this.playerTurn == Color.WHITE || !square.getPiece().isWhite() && this.playerTurn == Color.BLACK){
            validMoves = square.getPiece().getAllLegalMoves();
        }
        return validMoves;
    }

    public void move(int fileFrom, int rankFrom, int fileTo, int rankTo){
        Square squareFrom;
        Square squareTo;
        try{
            squareFrom = this.board.getSquare(rankFrom, fileFrom);
            squareTo = this.board.getSquare(rankTo, fileTo);
            // Checks if there any valid moves from the current square or if the destination square is not in the valid moves
            if (this.getAllValidMovesFromSquare(squareFrom).isEmpty() || !this.getAllValidMovesFromSquare(squareFrom).contains(squareTo)){
                System.out.println("Invalid move");
            } else {
                board.performMove(squareFrom, squareTo);
                switchTurns();
            }

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid Move");
        }
    }

    public void switchTurns(){
        this.playerTurn = this.playerTurn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public void display(){
        this.board.displayBoard();
    }

    public void playFromFile(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int counter = 1;
            while((line = reader.readLine()) != null){
//                System.out.println(line);
                String[] moves = line.split(",");
                int fileFrom = (int)moves[0].charAt(0) - 97;
                int rankFrom = (int)moves[0].charAt(1) - 49;
                int fileTo = (int)moves[1].charAt(0) - 97;
                int rankTo = (int)moves[1].charAt(1) - 49;
//                System.out.println(fileFrom + " " + rankFrom);
                System.out.print("Move " + counter++ + " --> ");
                this.move(fileFrom, rankFrom, fileTo, rankTo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.playFromFile("ChessGame.txt");
//        game.display();
//        game.move(1,0,2,2); // valid
//        game.move(6,7,5,5); // valid
//        game.move(2,2,4,3); // valid
//        game.move(5,5,4,3); // valid , Capture
//        game.display();
    }
}
