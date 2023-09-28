package MineSweeper.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        System.out.println("Oyunun Boyutlarını Seçiniz : ");
        System.out.print("Satır No: ");
        int row = inp.nextInt();
        System.out.print("Sütun No: ");
        int col = inp.nextInt();


        MineSweeper mine = new MineSweeper(row,col);
        mine.run();




    }

}