package ChessCore;

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

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.display();
        game.move(1,0,2,2); // valid
        game.move(6,7,5,5); // valid
        game.move(2,2,4,3); // valid
        game.move(5,5,4,3); // valid , Capture
        game.display();
    }
}
