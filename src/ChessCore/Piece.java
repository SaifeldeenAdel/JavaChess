package ChessCore;

import java.util.ArrayList;

public abstract class Piece {
    private Board board;
    private Square square;
    private Color color;
    private PieceType type;
    private boolean available;

    public Piece(Board board, Square square, Color color, PieceType type){
        this.board = board;
        this.square = square;
        this.color = color;
        this.type = type;
        this.available = true;
    }

    // Function to be overridden by other pieces
    public boolean isValidMove(Square squareFrom, Square squareTo) {
        if(Square.outOfBounds(squareTo.rank, squareTo.file)){
            return false;
        }
        // Makes sure the destination square is not null or doesn't have a piece that has the same color
        if (squareTo.getPiece() != null){
            if ((squareTo.getPiece().isWhite() && this.isWhite()) || (!squareTo.getPiece().isWhite() && !this.isWhite())){
                return false;
            }
            if (squareTo.getPiece().isKing()){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Square> getAllLegalMoves() {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        for(int i = 0; i<8;i++){
            for (int j =0;j<8;j++){
                if (this.isValidMove(this.getPosition(), this.getBoard().getSquare(j, i))){
                    possibleMoves.add(this.getBoard().getSquare(j,i));
                    System.out.println(j + " " + i);
                }
            }
        }
        return possibleMoves;
    }

    public boolean isWhite(){
        return this.color == Color.WHITE;
    }

    public boolean isKing(){
        return this.type == PieceType.KING;
    }

    public Square getPosition() {
        return square;
    }

    public void setPosition(Square square) {
        this.square = square;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Board getBoard() {
        return board;
    }

    public String toString(){
        String out = "";
        out += this.isWhite()? "W-": "B-";
        out += this.type.getSymbol();
        return out;
    }
}
