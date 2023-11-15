package ChessCore;

public class Square {
    public int rank;
    public int file;
    public Piece piece;

    public Square(int rank, int file){
        this.file = file;
        this.rank = rank;
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public static boolean outOfBounds(int rank, int file){
        return file < 0 || file > 7 || rank < 0 || rank > 7;
    }
}
