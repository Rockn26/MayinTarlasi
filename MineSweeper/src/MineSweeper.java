package MineSweeper.src;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber, colNumber, row, col, size;
    boolean game = true;
    String[][] map;
    String[][] board;
    Scanner inp = new Scanner(System.in);
    Random r = new Random();


    MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new String[rowNumber][colNumber];
        this.board = new String[rowNumber][colNumber];
        size = (rowNumber * colNumber);
    }

    public void run() {
        int success = 0;
        mineMap();
        printMap();
        while (game) {
            System.out.print("SATIR NO : ");
            row = inp.nextInt();
            System.out.print("SÜTUN NO : ");
            col = inp.nextInt();
            if (row < 0 || row >= rowNumber || col < 0 || col >= colNumber) {
                System.out.println("Hatalı indis seçimi ! Tekrar girin.");
                continue;
            }
            if (map[row][col].equals("*")) {
                System.out.println("KAYBETTİNİZ");
                break;
            }else {
                check();
                success++;
            }
            if (success == (size - (size/4))){
                System.out.println("TEBRİKLER, KAZANDINIZ ! ");
                game = false;
            }
        }

    }

    public void check(){
        int count = 0;
        for (int i = (row-1); i <(row+2); i++) {
            for (int j = (col-1); j < (col+2); j++) {
                if (i < 0 || i >= this.rowNumber || j <0 || j >= this.colNumber){
                    continue;
                }
                if (this.map[i][j].equals("*")){
                    count++;
                }
            }
        }

        String counter = Integer.toString(count);
        this.board[row][col] = counter;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void mineMap() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                map[i][j] = "-";
                board[i][j] = "-";
            }
        }
    }

    public void randomMine() {
        int randR, randC;
        for (int i = 0; i < (this.size / 4); i++) {
            randR = r.nextInt(rowNumber);
            randC = r.nextInt(colNumber);
            if (!this.map[randR][randC].equals("*")) {
                this.map[randR][randC] = "*";
            }
        }
    }

    public void printMap() {
        randomMine();
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                if (!this.map[i][j].equals("*")) {
                    this.map[i][j] = "-";
                }
                System.out.print(this.map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Mayın Tarlasına Hoş Geldiniz");
        System.out.println("============================");
    }


}
