package ChessCore;

public enum PieceType {
    PAWN('P'), ROOK('R'), KNIGHT('N'), BISHOP('B'), QUEEN('Q'), KING('K');

    private final char value;
    private PieceType(char value) {
        this.value = value;
    }
    public char getSymbol(){
        return this.value;
    }

}
