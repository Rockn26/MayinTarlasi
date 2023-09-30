package MineSweeper.src;

import java.util.Random;
import java.util.Scanner;


// 5- Proje MineSweeper sınıfı içerisinde tasarlandı
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


    // 6- Sınıf içerisinde ilgili metotlar (mesela oyun başlatma, oyunu kaybetme gibi fonksiyonların kontrolü) tanımlandı.
    public void run() {
        int success = 0;
        mineMap();
        printMap();
        boardMap();
        while (game) {
            // 9 - Kullanıcıdan işaretlemek istediği satır ve sütun bilgisi alınıyor.
            System.out.print("SATIR NO : ");
            row = inp.nextInt();
            System.out.print("SÜTUN NO : ");
            col = inp.nextInt();

            // 10 - Kullanıcının seçtiği nokta dizinin sınırları içerisinde olup olmadığı kontrol ediliyor,
            // değilse uyarı mesajı veriliyor ve tekrar giriş isteniyor
            if (row < 0 || row >= rowNumber || col < 0 || col >= colNumber) {
                System.out.println("Hatalı indis seçimi ! Tekrar girin.");
                continue;
            }

            // 15 - Kullanıcının oyunu kaybetme ya da kazanma durumunda uygun mesajlar kullanıcıya gösteriliyor

            if (map[row][col].equals("*")) {
                // 13- Kullanıcı mayına bastığında oyunu kaybedecek şekilde kontrol yapılmış.
                System.out.println("KAYBETTİNİZ");
                break;
            } else {
                if (!this.map[row][col].equals("-")) {
                    System.out.println("Bu hamleyi zaten yaptınız !");
                } else {
                    check();
                    success++;
                }
            }
            // 14 - Tüm noktalar mayınsız bir şekilde seçilirse oyunu kazanmanın kontrolü yapılıyor.
            if (success == (size - (size / 4))) {
                System.out.println("TEBRİKLER, KAZANDINIZ ! ");
                game = false;
            }
        }

    }

    public void check() {

        // 12 - Girilen noktada mayın yoksa etrafındaki mayın sayısı veya 0 değeri yerine yazılmış.
        if (map[row][col].equals("-")) {
            int count = 0;

            // Sağ
            if (col < colNumber - 1 && map[row][col + 1].equals("*")) {
                count++;
            }

            // Sol
            if (col > 0 && map[row][col - 1].equals("*")) {
                count++;
            }

            // Üst
            if (row > 0 && map[row - 1][col].equals("*")) {
                count++;
            }

            // Alt
            if (row < rowNumber - 1 && map[row + 1][col].equals("*")) {
                count++;
            }

            // Sağ üst çapraz
            if (row > 0 && col < colNumber - 1 && map[row - 1][col + 1].equals("*")) {
                count++;
            }

            // Sol üst çapraz
            if (row > 0 && col > 0 && map[row - 1][col - 1].equals("*")) {
                count++;
            }

            // Sağ alt çapraz
            if (row < rowNumber - 1 && col < colNumber - 1 && map[row + 1][col + 1].equals("*")) {
                count++;
            }

            // Sol alt çapraz
            if (row < rowNumber - 1 && col > 0 && map[row + 1][col - 1].equals("*")) {
                count++;
            }

            // 11 -Kullanıcı her hamle yaptığında oyun alanı güncelleniyor
            String counter = String.valueOf(count);
            map[row][col] = counter;
            board[row][col] = counter;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
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


    // 8 - Diziye uygun sayıda rastgele mayın yerleştirildi.
    public void randomMine() {
        int randR, randC, count = 0;
        while (count != (size / 4)) {
            randR = r.nextInt(rowNumber);
            randC = r.nextInt(colNumber);
            if (!map[randR][randC].equals("*")) {
                map[randR][randC] = "*";
                count++;
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

    public void boardMap() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


}

