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

    public boolean isValidMove(Square squareFrom, Square squareTo){
        Board clonedBoard = this.board.clone();
        Square clonedSquareFrom = clonedBoard.getSquare(squareFrom.rank, squareFrom.file);
        Square cloneSquareTo = clonedBoard.getSquare(squareTo.rank, squareTo.file);
        clonedBoard.testMove(clonedSquareFrom, cloneSquareTo);
//        System.out.println("checking move");
        if (this.kingIsInCheck(clonedBoard)){
            return false;
        } else{
            return true;
        }
    }

    public ArrayList<Square> getAllValidMovesFromSquare(Square squareFrom){
        ArrayList<Square> validMoves = new ArrayList<>();
        if (squareFrom.getPiece() == null){
            return validMoves;
        }

        if (squareFrom.getPiece().isWhite() && this.playerTurn == Color.WHITE || !squareFrom.getPiece().isWhite() && this.playerTurn == Color.BLACK){
            validMoves = squareFrom.getPiece().getAllLegalMoves();
            int size = validMoves.size();
            ArrayList<Square> removed = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if (!this.isValidMove(squareFrom, validMoves.get(i))) {
                    removed.add(validMoves.get(i));
                }
            }
            for (int i = 0; i < removed.size(); i++){
                validMoves.remove(removed.get(i));
            }
        }
        return validMoves;
    }


    public boolean kingIsInCheck(Board board) {
        for (int rank = 0; rank < Constants.BOARD_HEIGHT; rank++) {
            for (int file = 0; file < Constants.BOARD_WIDTH; file++) {
                Piece piece = board.getSquare(rank, file).getPiece();
                if (piece != null && piece instanceof King && piece.getColor() == this.playerTurn) {
//                    System.out.println("cheking check");
                    if (((King) piece).isInCheck()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckMate(Board board){
        for (int rank = 0; rank < Constants.BOARD_HEIGHT; rank++) {
            for (int file = 0; file < Constants.BOARD_WIDTH; file++) {
                if (!getAllValidMovesFromSquare(board.getSquare(rank, file)).isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isStaleMate(Board board){
        return true;
    }

    public void move(int fileFrom, int rankFrom, int fileTo, int rankTo, PieceType toPromote){
        Square squareFrom;
        Square squareTo;
        try{
            squareFrom = this.board.getSquare(rankFrom, fileFrom);
            squareTo = this.board.getSquare(rankTo, fileTo);
            // Checks if there any valid moves from the current square or if the destination square is not in the valid moves
            if (this.getAllValidMovesFromSquare(squareFrom).isEmpty() || !this.getAllValidMovesFromSquare(squareFrom).contains(squareTo)){
                System.out.println("Invalid move");
            } else if ((rankTo == 7  || rankTo == 0) && squareFrom.getPiece() instanceof Pawn && toPromote == null){
                System.out.println("Invalid move");
            }else {
                board.performMove(squareFrom, squareTo, toPromote);
                switchTurns();
                if (this.kingIsInCheck(board)){
                    if (this.isCheckMate(board)) {
                        System.out.println((playerTurn == Color.WHITE ? Color.BLACK : Color.WHITE) + " won");
                    } else {
                        System.out.println(playerTurn + " is in check");;
                    }
                };

            }

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invaliddd move");
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

                if (moves.length == 2){
                    this.move(fileFrom, rankFrom, fileTo, rankTo, null);
                } else if (moves.length == 3) {
                    this.move(fileFrom, rankFrom, fileTo, rankTo, PieceType.getType(moves[2].charAt(0)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.playFromFile("ChessGame.txt");
        game.display();
//        game.move(1,0,2,2); // valid
//        game.move(6,7,5,5); // valid
//        game.move(2,2,4,3); // valid
//        game.move(5,5,4,3); // valid , Capture
//        game.display();
    }
}
