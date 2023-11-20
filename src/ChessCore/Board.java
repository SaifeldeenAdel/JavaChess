package ChessCore;

import java.util.ArrayList;
import org.javatuples.Pair;
import java.util.List;

public class Board implements Cloneable{
    private Square[][] squares = new Square[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];

    public Board(){
        for(int rank =0;rank<Constants.BOARD_HEIGHT; rank++){
            for (int file = 0; file < Constants.BOARD_WIDTH; file++) {
                squares[rank][file] = new Square(rank, file);
            }
        }
        initialisePieces();
        lastMove(null,null);
    }

    public void initialisePieces(){
        for(int i = 0; i< Constants.BOARD_WIDTH; i++){
            squares[1][i].setPiece(new Pawn(this, squares[1][i], Color.WHITE));
            squares[6][i].setPiece(new Pawn(this, squares[6][i], Color.BLACK));
        }
        // White Pieces
        squares[0][0].setPiece(new Rook(this, squares[0][0], Color.WHITE));
        squares[0][1].setPiece(new Knight(this, squares[0][1], Color.WHITE));
        squares[0][2].setPiece(new Bishop(this, squares[0][2], Color.WHITE));
        squares[0][3].setPiece(new Queen(this, squares[0][3], Color.WHITE));
        squares[0][4].setPiece(new King(this, squares[0][4], Color.WHITE));
        squares[0][5].setPiece(new Bishop(this, squares[0][5], Color.WHITE));
        squares[0][6].setPiece(new Knight(this, squares[0][6], Color.WHITE));
        squares[0][7].setPiece(new Rook(this, squares[0][7], Color.WHITE));

        // Black Pieces
        squares[7][0].setPiece(new Rook(this, squares[7][0], Color.BLACK));
        squares[7][1].setPiece(new Knight(this, squares[7][1], Color.BLACK));
        squares[7][2].setPiece(new Bishop(this, squares[7][2], Color.BLACK));
        squares[7][3].setPiece(new Queen(this, squares[7][3], Color.BLACK));
        squares[7][4].setPiece(new King(this, squares[7][4], Color.BLACK));
        squares[7][5].setPiece(new Bishop(this, squares[7][5], Color.BLACK));
        squares[7][6].setPiece(new Knight(this, squares[7][6], Color.BLACK));
        squares[7][7].setPiece(new Rook(this, squares[7][7], Color.BLACK));

//        squares[2][1].setPiece(new Pawn(this, squares[2][1], Color.WHITE));
//    //        squares[2][4].setPiece(new Queen(this, squares[2][4], Color.BLACK));
//        squares[2][3].setPiece(new Pawn(this, squares[2][3], Color.BLACK));

    }

    public Board clone(){
        try{
            Board clonedBoard = (Board)super.clone();
            clonedBoard.squares = new Square[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
            for(int rank = 0; rank<Constants.BOARD_HEIGHT; rank++){
                for(int file = 0; file<Constants.BOARD_WIDTH; file++){
                    clonedBoard.squares[rank][file] = this.squares[rank][file].clone(clonedBoard);
                }
            }
            return clonedBoard;
        } catch(CloneNotSupportedException e){
            return null;
        }
    }

    public Square getSquare(int rank, int file){
        return this.squares[rank][file];
    }

    public void performMove(Square squareFrom, Square squareTo, PieceType toPromote){
//        System.out.println(squareFrom.rank + " " + squareFrom.file);
        Piece movingPiece = squareFrom.getPiece();
        Piece capturedPiece = squareTo.getPiece();
        squareFrom.removePiece();
        squareTo.setPiece(movingPiece);
        movingPiece.setPosition(squareTo);

        if (capturedPiece != null){
            System.out.println("Captured " + capturedPiece.getType().name().charAt(0) +capturedPiece.getType().name().substring(1).toLowerCase());
        }
        if(movingPiece instanceof Pawn) {
            ((Pawn) movingPiece).setHasMoved();
            if (((Pawn) movingPiece).getEnpassantSquare() != null)
                ((Pawn) movingPiece).getEnpassantSquare().removePiece();

            if (toPromote != null) {
//                ((Pawn) movingPiece).promoteTo(toPromote);
            }
        }
        else{
            //???
        }
        lastMove(squareFrom,squareTo);
//        return true;
    }

    public Pair <Square, Square> lastMove(Square squareFrom, Square squareTo)
    {
        Pair <Square, Square> lastPieceMove = new Pair<>(squareFrom,squareTo);
        return lastPieceMove;
    }

    // Function that doesn't have any print statements to test the move in the cloned boards
    public void testMove(Square squareFrom, Square squareTo){
        Piece movingPiece = squareFrom.getPiece();
        squareFrom.removePiece();
        squareTo.setPiece(movingPiece);
        movingPiece.setPosition(squareTo);
    }


    public void displayBoard(){
//        System.out.println(((King)squares[7][4].getPiece()).isInCheck());
//        ArrayList<Square> legal = squares[6][3].getPiece().getAllLegalMoves();
        ArrayList<Square> legal = new ArrayList<>();
        for(int rank = Constants.BOARD_HEIGHT -1; rank >=0 ; rank--){
            System.out.println("------------------------------------");
            System.out.print(" " + rank + " |");
            for(int file = 0; file<Constants.BOARD_WIDTH; file++){
                if (squares[rank][file].getPiece() == null){
                    if(legal.contains(squares[rank][file])){
                        System.out.print(" x |");
                    }else{
                        System.out.print("   |");
                    }
                } else {
                    if(legal.contains(squares[rank][file])){
                        System.out.print(" k |");
                    }else{
//                        System.out.print("   |");
                        System.out.print(squares[rank][file].getPiece().toString() + "|");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("-----a---b---c---d---e---f---g---h--");
    }

    public static void main(String[] args) {
        Board board = new Board();
        Board clone = board.clone();

        board.displayBoard();
    }
}
