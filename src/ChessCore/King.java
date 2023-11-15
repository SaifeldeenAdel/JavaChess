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


        return true;
    }

//    public boolean isInCheck(){
//        for(int rank = 0; rank<Constants.BOARD_HEIGHT; rank++){
//            for(int file =0; file<Constants.BOARD_WIDTH; file++){
//                if(this.getBoard().getSquare(rank, file) )
//            }
//        }
//    }

}
