package ChessCore;

import java.util.ArrayList;

public class Board {
    private Square[][] squares = new Square[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
    private Color playerTurn;

    public Board(){
        playerTurn = Color.WHITE;
        for(int rank =0;rank<Constants.BOARD_HEIGHT; rank++){
            for (int file = 0; file < Constants.BOARD_WIDTH; file++) {
                squares[rank][file] = new Square(rank, file);
            }
        }
        initialisePieces();
    }
    public void initialisePieces(){
        for(int i = 0; i< Constants.BOARD_WIDTH; i++){
            squares[1][i].setPiece(new Pawn(this, squares[1][i], Color.WHITE));
            squares[6][i].setPiece(new Pawn(this, squares[1][i], Color.BLACK));
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
        squares[7][0].setPiece(new Rook(this, squares[0][0], Color.BLACK));
        squares[7][1].setPiece(new Knight(this, squares[0][1], Color.BLACK));
        squares[7][2].setPiece(new Bishop(this, squares[0][2], Color.BLACK));
        squares[7][3].setPiece(new Queen(this, squares[0][3], Color.BLACK));
        squares[7][4].setPiece(new King(this, squares[0][4], Color.BLACK));
        squares[7][5].setPiece(new Bishop(this, squares[0][5], Color.BLACK));
        squares[7][6].setPiece(new Knight(this, squares[0][6], Color.BLACK));
        squares[7][7].setPiece(new Rook(this, squares[0][7], Color.BLACK));

//        squares[5][1].setPiece(new Bishop(this, squares[5][1], Color.BLACK));
//        squares[3][3].setPiece(new Bishop(this, squares[3][3], Color.WHITE));

    }

//    public boolean isValidState(){
//
//    }

    public ArrayList<Square> getAllValidMovesFromSquare(Square square){
        ArrayList<Square> validMoves = new ArrayList<>();
        if (square.getPiece().isWhite() && this.playerTurn == Color.WHITE || !square.getPiece().isWhite() && this.playerTurn == Color.BLACK){
            validMoves = square.getPiece().getAllLegalMoves();
        }
        return validMoves;
    }

    public void move(Square squareFrom, Square squareTo){
        // TODO
    }
    public Square getSquare(int rank, int file){
        return this.squares[rank][file];
    }

    public void displayBoard(){
        ArrayList<Square> legal = squares[3][3].getPiece().getAllLegalMoves();
//        ArrayList<Square> legal = new ArrayList<>();
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
        board.displayBoard();
    }
}
