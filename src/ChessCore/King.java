package ChessCore;

public class King extends Piece {

    public King(Board board, Square square, Color color) {
        super(board, square, color, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Square squareFrom, Square squareTo) {
        if (!super.isValidMove(squareFrom, squareTo)){
            return false;
        }
        int deltaX = Math.abs(squareTo.file - squareFrom.file);
        int deltaY = Math.abs(squareTo.rank - squareFrom.rank);

        // Check if the move is within the king's range (one square in any direction)
        if ((deltaX <= 1 && deltaY <= 1) && (deltaX + deltaY > 0)) {
            // Ensure the destination square is either empty or has an opponent's piece
            return true;
        } else {
            return false;
        }
    }

    public boolean isInCheck(){
        for(int rank = 0; rank<Constants.BOARD_HEIGHT; rank++){
            for(int file =0; file<Constants.BOARD_WIDTH; file++){
                Piece piece = this.getBoard().getSquare(rank, file).getPiece();
                if(piece != null){
                    if(piece.getAllLegalMoves().contains(this.getPosition())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
