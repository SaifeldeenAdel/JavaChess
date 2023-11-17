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
            this.hasMoved=true;
            return true;
        }
        if(vertical == 2 && horizontal == 0 && !hasMoved) {
            this.hasMoved = true;
            this.justMovedTwoSquares = true;

            return true;
        }
        if(horizontal==1 && vertical ==-1){
            if(squareTo.getPiece()!=null)
                if(squareFrom.getPiece().isWhite()) {
                    return false;
                }
                else{
                    return true;
                }
        }


        if(horizontal == 1 && vertical == 1){
            this.hasMoved =true;
            if(squareTo.getPiece()!=null)
            {
                if(!squareFrom.getPiece().isWhite())
                {
                    return false;
                }
                else{
                    return true;
                }
            }
            else{

               /* if((this.getBoard().getSquare(squareFrom.rank, squareFrom.file + 1).getPiece() != null) || this.getBoard().getSquare(squareFrom.rank, squareFrom.file - 1).getPiece() != null)
                {
                    //check that this piece is a pawn
                    //if yes
                    //
                }*/



            }

        }


        return false;
    }

   /* private boolean checkSquareForPawn(Square squareFrom, Square squareTo)
    {

    }*/

    @Override
    public ArrayList<Square> getAllLegalMoves() {
        return null;
    }
}
