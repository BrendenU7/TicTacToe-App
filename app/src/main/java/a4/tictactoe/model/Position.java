package a4.tictactoe.model;


//position on tictactoe board with row and column coordinates.
public class Position {

    private int rowNum;
    private int colNum;

    public Position(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public int getRowNum(){
        return rowNum;
    }
    public int getColNum() {
        return colNum;
    }

}
