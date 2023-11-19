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

    //moving but not moving 2 positions error ?
    @Override
    public boolean isValidMove(Square squareFrom, Square squareTo) {
        if (!super.isValidMove(squareFrom, squareTo)) {
            return false;
        }
        int horizontal = Math.abs(squareFrom.file - squareTo.file);
        int vertical = this.isWhite() ? squareTo.rank - squareFrom.rank : squareFrom.rank - squareTo.rank;


        if (vertical == 2 && horizontal == 0 && !hasMoved && squareTo.getPiece() == null) {

            this.justMovedTwoSquares = true;

            return true;
        }
        if (vertical == 1 && horizontal == 0 && squareTo.getPiece() == null) {

            return true;
        }
        if (vertical == -2 && horizontal == 0 && !hasMoved && squareTo.getPiece() == null) {

            this.justMovedTwoSquares = true;

            return true;
        }
        if (vertical == -1 && horizontal == 0 && squareTo.getPiece() == null) {
            return true;
        }
        if (vertical == -1 && horizontal == 1) {
            if (squareTo.getPiece() != null) {
                return true;}

//             else {//if enpassant ? -> return true;
//                if (enpassantValid(squareFrom, squareTo)) {
//
//                    System.out.println("enpassant working");
//                    return true;
//                }
//            }
            return false;
        }
        if (horizontal == 1 && vertical == 1) {
            if (squareTo.getPiece() != null) {
                return true;}
//            else {  //if enpassant ? -> return true;
//                if (enpassantValid(squareFrom, squareTo)) {
//                    //check horizontal difference without absolute if negative, instance variable empassantSquare stores pos file -1
//                    System.out.println("enpassant working");
//                    return true;
//                }
//            }
            return false;
        }
        return false;
    }
    public void setHasMoved () {
            this.hasMoved = true;
    }
    //enpassantSquare getter method
//    public boolean enpassantValid (Square squareFrom, Square squareTo){
//        Piece p1 = this.getBoard().getSquare(squareFrom.rank, squareFrom.file + 1).getPiece();
//        Piece p2 = this.getBoard().getSquare(squareFrom.rank, squareFrom.file - 1).getPiece();
//        if ((p1 != null) || p2 != null) {
//            if (p1 instanceof Pawn || p2 instanceof Pawn) {
//                //checks if last move is of a pawn and its destination square is equal to the square beside the current piece
//                if (this.getBoard().lastMove(squareFrom, squareTo).get(0).getValue1() == p1.getPosition() || this.getBoard().lastMove(squareFrom, squareTo).get(0).getValue1() == p2.getPosition()) {
//                    if (this.getBoard().lastMove(squareFrom, squareTo).get(0).getValue1().rank - this.getBoard().lastMove(squareFrom, squareTo).get(0).getValue0().rank == 2) {
//                        return true;
//
//                    }
//
//                }
//            }
//        }
//        return false;
//
//    }

}
