package ChessCore;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean hasMoved;
    private boolean justMovedTwoSquares;

    public Pawn(Board board, Square square, Color color) {
        super(board, square, color, PieceType.PAWN);
        this.hasMoved = false;
        this.justMovedTwoSquares = false;
    }

    @Override
    public boolean isValidMove(Square squareFrom, Square squareTo) {
        if (!super.isValidMove(squareFrom, squareTo)){
            return false;
        };
        int horizontal = Math.abs(squareFrom.file - squareTo.file);
        int vertical = this.isWhite() ? squareFrom.rank - squareTo.rank : squareTo.rank - squareFrom.rank;

        if(vertical == 1 && horizontal == 0){
            return true;
        }
        if(vertical == 2 && horizontal == 0 && !hasMoved){

            return true;
        }
        if(horizontal == 1 && vertical == 1){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Square> getAllLegalMoves() {
        return null;
    }
}
