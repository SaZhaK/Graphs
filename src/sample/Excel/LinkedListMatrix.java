package sample.Excel;

import java.util.ArrayList;
import java.util.List;

public class LinkedListMatrix {
    private int height;
    private int width;

    private Cell first;

    public LinkedListMatrix(int height, int width) {
        if (height < 0 || width < 0) throw new IndexOutOfBoundsException("Cannot create matrix with negative size");

        this.height = height;
        this.width = width;
        first = new Cell("0");

        Cell rowStart = first;
        for (int i = 0; i < height; i++) {
            Cell current = rowStart;

            for (int j = 0; j < width - 1; j++) {
                current.setRight(new Cell("0"));
                current = current.getRight();
            }
            rowStart.setBottom(new Cell("0"));
            rowStart = rowStart.getBottom();
        }

        rowStart = first;
        for (int i = 0; i < height - 1; i++) {
            rowStart = rowStart.getBottom();
        }
        rowStart.setBottom(null);

        rowStart = first;
        for (int i = 0; i < height - 1; i++) {
            Cell current = rowStart;
            Cell prev = rowStart;

            for (int j = 0; j < width - 1; j++) {
                current = current.getRight();
                current.setBottom(prev.getBottom().getRight());
                prev = current;
            }
            rowStart = rowStart.getBottom();
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void insertCell(int row, int column, String content) {
        insertCell(row, column);
        setContent(row, column, content);
    }

    public void insertCell(int row, int column) {
        if (row > this.height || column > this.width || row < 0 || column < 0) throw new IndexOutOfBoundsException();

        Cell rowStart = first;
        Cell current = null;
        for (int i = 0; i < row - 1; i++) {
            current = rowStart;

            for (int j = 0; j < column - 2; j++) {
                current = current.getRight();
            }
            rowStart = rowStart.getBottom();
        }

        Cell newCell = new Cell("0");

        newCell.setBottom(current.getRight().getBottom());
        newCell.setRight(current.getRight().getBottom().getRight());

        current.getRight().setBottom(newCell);
        current.getBottom().setRight(newCell);
        current = current.getBottom();

        while (current.getBottom() != null) {
            current = current.getBottom();
            current.setRight(newCell.getBottom());
            newCell.setRight(newCell.getBottom().getRight());
            newCell = newCell.getBottom();
        }

        newCell.setRight(newCell.getBottom().getRight());

        rowStart = first;
        while (rowStart.getBottom() != null) rowStart = rowStart.getBottom();

        Cell prev = rowStart;

        rowStart.setBottom(new Cell("0"));
        rowStart = rowStart.getBottom();
        for (int i = 0; i < width - 1; i++) {
            if (i == column - 2) {
                rowStart.setRight(newCell.getBottom());
            } else {
                rowStart.setRight(new Cell("0"));
            }
            rowStart = rowStart.getRight();
        }

        rowStart = first;
        while (rowStart.getBottom() != null) rowStart = rowStart.getBottom();
        prev.setBottom(rowStart);

        for (int i = 0; i < width - 1; i++) {
            prev = prev.getRight();
            prev.setBottom(rowStart.getRight());
            rowStart = rowStart.getRight();
        }
        this.height++;
    }

    public void insertRow(int position) {
        if (position > this.height || position < 0) throw new IndexOutOfBoundsException();

        Cell current = first;
        if (position == this.height) {

            for (int i = 0; i < position - 1; i++) {
                current = current.getBottom();
            }

            Cell newCell = new Cell("0");
            current.setBottom(newCell);

            for (int i = 0; i < height - 2; i++) {
                newCell.setRight(new Cell("0"));
                newCell = newCell.getRight();
                current = current.getRight();
                current.setBottom(newCell);
            }
        } else if (position == 1) {
            Cell newCell = new Cell("0");
            this.first = newCell;

            for (int i = 0; i < width; i++) {
                newCell.setBottom(current);
                newCell.setRight(new Cell("0"));
                newCell = newCell.getRight();
                current = current.getRight();
            }
        } else {
            for (int i = 0; i < position - 2; i++) {
                current = current.getBottom();
            }
            Cell newCell = new Cell("0");
            newCell.setBottom(current.getBottom());

            current.setBottom(newCell);

            for (int i = 0; i < width - 1; i++) {
                newCell.setRight(new Cell("0"));
                newCell = newCell.getRight();
                current = current.getRight();
                newCell.setBottom(current.getBottom());
                current.setBottom(newCell);
            }
        }
        this.height++;
    }

    public void insertColumn(int position) {
        if (position > this.width || position < 0) throw new IndexOutOfBoundsException();

        Cell current = first;
        if (position == this.width) {
            for (int i = 0; i < position - 1; i++) {
                current = current.getRight();
            }

            Cell newCell = new Cell("0");
            current.setRight(newCell);

            for (int i = 0; i < height - 1; i++) {
                newCell.setBottom(new Cell("0"));
                newCell = newCell.getBottom();
                current = current.getBottom();
                current.setRight(newCell);
            }
        } else if (position == 1) {
            Cell newCell = new Cell("0");
            this.first = newCell;

            for (int i = 0; i < height; i++) {
                newCell.setRight(current);
                newCell.setBottom(new Cell("0"));
                newCell = newCell.getBottom();
                current = current.getBottom();
            }
        } else {
            for (int i = 0; i < position - 2; i++) {
                current = current.getRight();
            }
            Cell newCell = new Cell("0");
            newCell.setRight(current.getRight());

            current.setRight(newCell);

            for (int i = 0; i < height - 1; i++) {
                newCell.setBottom(new Cell("0"));
                newCell = newCell.getBottom();
                current = current.getBottom();
                newCell.setRight(current.getRight());
                current.setRight(newCell);
            }
        }
        this.width++;
    }

    public void setContent(int row, int column, String content) {
        if (row > this.height || column > this.width || row < 0 || column < 0) throw new IndexOutOfBoundsException();

        Cell current = first;
        for (int i = 0; i < row - 1; i++) {
            current = current.getBottom();
        }

        for (int i = 0; i < column - 1; i++) {
            current = current.getRight();
        }

        current.setContent(content);
    }

    public String getContent(int row, int column) {
        if (row > this.height || column > this.width || row < 0 || column < 0) throw new IndexOutOfBoundsException();

        Cell current = first;
        for (int i = 0; i < row - 1; i++) {
            current = current.getBottom();
        }

        for (int i = 0; i < column - 1; i++) {
            current = current.getRight();
        }

        return current.getContent();
    }

    public List<Cell> getColumn(int idx) {
        if (idx > this.width) throw new IndexOutOfBoundsException();

        Cell current = first;
        for (int i = 0; i < idx - 1; i++) {
            current = current.getRight();
        }

        List<Cell> resultList = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            resultList.add(current);
            current = current.getBottom();
        }

        return resultList;
    }

    public void display() {
        Cell rowStart = first;
        for (int i = 0; i < height; i++) {
            Cell current = rowStart;

            for (int j = 0; j < width; j++) {
                System.out.print(current.getContent() + " ");
                current = current.getRight();
            }
            rowStart = rowStart.getBottom();
            System.out.println();
        }
    }
}