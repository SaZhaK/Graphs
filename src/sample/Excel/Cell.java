package sample.Excel;

public class Cell {
    private String content;
    private Cell right;
    private Cell bottom;

    public Cell() {}

    public Cell(String content) {
        this.content = content;
    }

    public Cell getRight() {
        return right;
    }

    public Cell getBottom() {
        return bottom;
    }

    public void setRight(Cell right) {
        this.right = right;
    }

    public void setBottom(Cell bottom) {
        this.bottom = bottom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}