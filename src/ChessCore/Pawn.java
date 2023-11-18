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
        if (!super.isValidMove(squareFrom, squareTo)){
            return false;
        }
        int horizontal = Math.abs(squareFrom.file - squareTo.file);
        int vertical = this.isWhite() ? squareTo.rank - squareFrom.rank: squareFrom.rank - squareTo.rank ;


        if(vertical ==2 && horizontal == 0 &&!hasMoved)
        {

            this.justMovedTwoSquares = true;

            return true;
        }
        if( vertical==1 && horizontal ==0){

            return true;
        }
        if(vertical == -2 && horizontal == 0 && !hasMoved) {

            this.justMovedTwoSquares = true;

            return true;
        }
        if(vertical==-1 && horizontal ==0){
            return true;
        }
        if(horizontal==1 && vertical ==-1){
            System.out.println("diagonal check B");
            if(squareTo.getPiece()!=null){
                if(squareTo.getPiece().isWhite()){
                    return true;
                }
            }
//            else {//if enpassant ? -> return true;
//                if (enpassantValid(squareFrom, squareTo)) {
//                    System.out.println("enpassant working");
//                    return true;
//                }
//            }
            return false;
        }
        if(horizontal == 1 && vertical == 1){
            System.out.println("diagonal check W");
            if(squareTo.getPiece()!=null){
                if (!squareTo.getPiece().isWhite()) {
                    return true;
                }
            }
//            else {  //if enpassant ? -> return true;
                if (enpassantValid(squareFrom, squareTo)) {
                   System.out.println("enpassant working");
                   return true;
                }
             }
            return false;
        }


        return false;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    //    public boolean enpassantValid (Square squareFrom, Square squareTo){
//        Piece p1 = this.getBoard().getSquare(squareFrom.rank, squareFrom.file + 1).getPiece();
//        Piece p2 = this.getBoard().getSquare(squareFrom.rank, squareFrom.file - 1).getPiece();
//        if(( p1!= null) || p2!= null){
//            if(p1.getType().getSymbol() =='P' || p2.getType().getSymbol()=='P'){
//                //checks if last move is of a pawn and its destination square is equal to the square beside the current piece
//                if(this.getBoard().lastMove(squareFrom,squareTo).get(1).getValue1()==p1.getPosition() || this.getBoard().lastMove(squareFrom,squareTo).get(1).getValue1()==p2.getPosition()){
//                    if(this.getBoard().lastMove(squareFrom,squareTo).get(1).getValue1().rank-this.getBoard().lastMove(squareFrom,squareTo).get(0).getValue0().rank==2){
//                        return true;
//                    }
//
//                }
//            }
//        }
//        return false;
//
//    }

   /* private boolean checkSquareForPawn(Square squareFrom, Square squareTo)
    {

    }*/

    /*@Override
    public ArrayList<Square> getAllLegalMoves() {
        return null;
    }*/
}
