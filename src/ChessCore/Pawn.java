package ChessCore;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn(Board board, Square square, Color color) {
        super(board, square, color, PieceType.PAWN);
        this.hasMoved = false;
    }

    @Override
    public boolean isValidMove(Square squareFrom, Square squareTo) {
        if (!super.isValidMove(squareFrom, squareTo)) {
            return false;
        }
        int horizontal = Math.abs(squareFrom.file - squareTo.file);
        int vertical = squareTo.rank - squareFrom.rank;

        // Forward movements
        if (vertical == 2 && horizontal == 0 && !hasMoved && squareTo.getPiece() == null && this.isWhite()) {
            return true;
        }
        if (vertical == 1 && horizontal == 0 && squareTo.getPiece() == null && this.isWhite()) {
            return true;
        }
        if (vertical == -2 && horizontal == 0 && !hasMoved && squareTo.getPiece() == null && !this.isWhite()) {
            return true;
        }
        if (vertical == -1 && horizontal == 0 && squareTo.getPiece() == null && !this.isWhite()) {
            return true;
        }

        // Diagonal movements
        if (vertical == 1 && horizontal == 1 && squareTo.getPiece() != null && this.isWhite()) {
            return true;
        }
        if (vertical == -1 && horizontal == 1 && squareTo.getPiece() != null && !this.isWhite()) {
            return true;
        }

        // Checks enpassant validity
        if (enpassantValid(squareFrom, squareTo)){
            return true;
        }

        return false;
    }
    public void setHasMoved () {
            this.hasMoved = true;
    }

    public boolean isPromoting(Square squareFrom, Square squareTo){
        if(this.isWhite() && squareTo.rank==7 || !this.isWhite() && squareTo.rank == 0){
            return true;
        }
        return false;
    }

    public boolean enpassantValid (Square squareFrom, Square squareTo){
        if (this.getBoard().getLastMove() == null) return false;
        int horizontal = Math.abs(squareFrom.file - squareTo.file);
        int vertical = squareTo.rank - squareFrom.rank;

        // If it's a diagonal move and there's no piece in the destination square
        if (((horizontal == 1 && vertical == 1 && this.isWhite()) || (vertical == -1 && horizontal == 1 && !this.isWhite())) && squareTo.getPiece() == null){
            Piece p1 = null;
            Piece p2 = null;
            // Getting pawns next to current pawn, avoiding out of bounds
            if(squareFrom.file > 0){
                p1 =this.getBoard().getSquare(squareFrom.rank, squareFrom.file - 1).getPiece();
            }
            if(squareFrom.file < 7){
                p2 = this.getBoard().getSquare(squareFrom.rank, squareFrom.file + 1).getPiece();
            }
            if (p1 instanceof Pawn) {
                //checks if last move is of the pawn and its destination square is equal to the square beside the current piece
                if (this.getBoard().getLastMove().get(1) == p1.getPosition()) {
                    // Check if the last move was a two square move made by that pawn
                    if (Math.abs(this.getBoard().getLastMove().get(1).rank - this.getBoard().getLastMove().get(0).rank) == 2) {
                        this.getBoard().setEnpassantSquare(this.getBoard().getLastMove().get(1));
                        return true;
                    }

                }
            }
            if (p2 instanceof Pawn){
                //checks if last move is of the pawn and its destination square is equal to the square beside the current piece
                if (this.getBoard().getLastMove().get(1) == p2.getPosition()) {
                    // Check if the last move was a two square move made by that pawn
                    if (Math.abs(this.getBoard().getLastMove().get(1).rank - this.getBoard().getLastMove().get(0).rank) == 2) {
                        this.getBoard().setEnpassantSquare(this.getBoard().getLastMove().get(1));
                        return true;
                    }

                }
            }
        }
        return false;
    }

}
