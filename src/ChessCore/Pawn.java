package ChessCore;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean hasMoved;
    private boolean justMovedTwoSquares;
    private Square enpassantSquare;

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
        int vertical = squareTo.rank - squareFrom.rank;


        if (vertical == 2 && horizontal == 0 && !hasMoved && squareTo.getPiece() == null && this.isWhite()) {
            this.justMovedTwoSquares = true;
            return true;
        }
        if (vertical == 1 && horizontal == 0 && squareTo.getPiece() == null && this.isWhite()) {

            return true;
        }
        if (vertical == -2 && horizontal == 0 && !hasMoved && squareTo.getPiece() == null && !this.isWhite()) {

            this.justMovedTwoSquares = true;

            return true;
        }
        if (vertical == -1 && horizontal == 0 && squareTo.getPiece() == null && !this.isWhite()) {
            return true;
        }
        if (vertical == -1 && horizontal == 1 && !this.isWhite()) {
            if (squareTo.getPiece() != null) {
                return true;}

             else {//if enpassant ? -> return true;
                if (enpassantValid(squareFrom, squareTo)) {
                    System.out.println("enpassant working");
                    if(squareTo.file - squareFrom.file==1)
                    {
                        Piece pRight = this.getBoard().getSquare(squareFrom.rank, squareFrom.file + 1).getPiece();
                        enpassantSquare.setPiece(pRight);
                        System.out.println("right working");
                        return true;
                    }
                    else{
                        Piece pLeft = this.getBoard().getSquare(squareFrom.rank, squareFrom.file - 1).getPiece();
                        enpassantSquare.setPiece(pLeft);
                        System.out.println("left working");
                        return true;
                    }

                }
            }
            return false;
        }
        if (horizontal == 1 && vertical == 1 && this.isWhite()) {
            if (squareTo.getPiece() != null) {
                return true;}
            else {  //if enpassant ? -> return true;
                if (enpassantValid(squareFrom, squareTo)) {
                    System.out.println("enpassant working");
                    //check horizontal difference without absolute if negative, instance variable empassantSquare stores pos file -1
                    if(squareTo.file - squareFrom.file==1)
                    {
                        Piece pRight = this.getBoard().getSquare(squareFrom.rank, squareFrom.file + 1).getPiece();
                        enpassantSquare.setPiece(pRight);
                        System.out.println("right working");
                        return true;
                    }
                    else{
                        Piece pLeft = this.getBoard().getSquare(squareFrom.rank, squareFrom.file - 1).getPiece();
                        enpassantSquare.setPiece(pLeft);
                        System.out.println("left working");
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public void setHasMoved () {
            this.hasMoved = true;
    }

    public Square getEnpassantSquare(){
        return this.enpassantSquare;
    }
    public boolean enpassantValid (Square squareFrom, Square squareTo){
        if(!Square.outOfBounds(squareFrom.rank, squareFrom.file+1))
        {
            //System.out.println("cr pawn's pos: "+ this.getPosition().rank +this.getPosition().file);
            Piece p1 = this.getBoard().getSquare(squareFrom.rank, squareFrom.file + 1).getPiece();
            if(p1!=null)
            {
                //System.out.println("last move: "+(this.getBoard().lastMove(squareFrom, squareTo).getValue1().rank)+(this.getBoard().lastMove(squareFrom, squareTo).getValue1().file));
                //System.out.println("p1's position"+p1.getPosition().rank + p1.getPosition().file);

                if(p1 instanceof Pawn){
                    if((this.getBoard().lastMove(squareFrom, squareTo).getValue1().rank== p1.getPosition().rank ) && (this.getBoard().lastMove(squareFrom, squareTo).getValue1().file == p1.getPosition().file)){
                        if (this.getBoard().lastMove(squareFrom, squareTo).getValue1().rank - this.getBoard().lastMove(squareFrom, squareTo).getValue0().rank == 2)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        if(!Square.outOfBounds(squareFrom.rank, squareFrom.file-1))
        {

            Piece p2 = this.getBoard().getSquare(squareFrom.rank, squareFrom.file - 1).getPiece();
            if (p2 != null) {
                if (p2 instanceof Pawn) {
                    //checks if last move is of a pawn and its destination square is equal to the square beside the current piece
                    if (this.getBoard().lastMove(squareFrom, squareTo).getValue1() == p2.getPosition()) {
                        if (this.getBoard().lastMove(squareFrom, squareTo).getValue1().rank - this.getBoard().lastMove(squareFrom, squareTo).getValue0().rank == 2) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
        }

    public void promoteTo(Square squareTo, PieceType toPromote){
        if(squareTo.rank==7 || squareTo.rank==0)
        {
            if(toPromote.equals(PieceType.QUEEN)){
                Piece promotedToPiece = new Queen(this.getBoard(),squareTo,this.getColor());
            } else if (toPromote.equals(PieceType.KNIGHT)) {
                Piece promotedToPiece = new Knight(this.getBoard(),squareTo,this.getColor());
            } else if (toPromote.equals(PieceType.ROOK)) {
                Piece promotedToPiece = new Rook(this.getBoard(),squareTo,this.getColor());
            } else if (toPromote.equals(PieceType.BISHOP)) {
                Piece promotedToPiece = new Bishop(this.getBoard(),squareTo,this.getColor());
            }

        }
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
//        }


}
