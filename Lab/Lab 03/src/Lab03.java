import java.util.Arrays;
import java.util.Scanner;

public class Lab03 {
    // Declare variables
    static int pointer = 0;
    static String[][] playlist = new String[1000][4];
    static Scanner in = new Scanner(System.in);
    static int jumlahMusik = 0;
    static int index = 0;

    public static void main(String[] args) {
        System.out.println("Selamat Datang di Pacilfy!");

        // TODO:
        // loop inisialisasi playlist minimal 1 lagu dimasukkan
        int pilihan = 1;
        while (pilihan != 0) {
            addMusic();
            System.out.println("Lanjut menambahkan lagu?");
            System.out.println("[1] Lanjut");
            System.out.println("[0] Berhenti");
            System.out.print("Perintah: ");

            pilihan = in.nextInt();
            in.nextLine();

            // handle input
            while (pilihan != 0 && pilihan != 1) {
                System.out.println("Perintah tidak valid");
                System.out.print("Perintah: ");
                pilihan = in.nextInt();
                in.nextLine();
            }
        }


        System.out.println("Pacilfy siap dimulai");

        System.out.println("\nSELAMAT DATANG DI\n");
        System.out.println(" /$$$$$$$                     /$$ /$$  /$$$$$$");
        System.out.println("| $$__  $$                   |__/| $$ /$$__  $$");
        System.out.println("| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$| $$  \\__//$$   /$$");
        System.out.println("| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$$$   | $$  | $$");
        System.out.println("| $$____/  /$$$$$$$| $$      | $$| $$| $$_/   | $$  | $$");
        System.out.println("| $$      /$$__  $$| $$      | $$| $$| $$     | $$  | $$");
        System.out.println("| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$     |  $$$$$$$");
        System.out.println("|__/      \\_______/ \\_______/|__/|__/|__/      \\____  $$");
        System.out.println("                                               /$$  | $$");
        System.out.println("                                              |  $$$$$$/");
        System.out.println("                                               \\______/");


        int command;
        while (true){
            display();
            System.out.print("Command (0 untuk exit) : ");
            command = Integer.parseInt(in.nextLine());
            if (command == 1){
                prevMusic();
            }
            else if (command == 2){
                addMusic();
            }
            else if (command == 3){
                detailsMusic();
            }
            else if (command == 4){
                deleteMusic();
            }
            else if (command == 5){
                nextMusic();

            }
            else if (command == 0){
                break;
            }
            else {
                System.out.println("Maaf, command yang anda masukan salah");
            }
        }

        System.out.println("Terima kasih sudah menggunakan Pacilfy!");
    }

    private static void nextMusic() {
        // jika pointer sudah mencapai lagu terakhir, pointer kembali ke 0
        if (pointer+1 == index){
            pointer = -1;
        }
        pointer++;

        // jika array ke-pointer kosong, pointer bertambah 1
        while (playlist[pointer][0] == null){
            if (pointer+1 == index){
                pointer = -1;
            }
            pointer++;
        }
    }

    private static void deleteMusic() {
        // jika jumlah musik hanya 1, tidak ada yang dihapus
        if (jumlahMusik == 1){
            System.out.println("Minimal ada satu musik dalam sistem");
        } else {
            Arrays.fill(playlist[pointer], null);
            jumlahMusik--;

            // lanjut ke musik selanjutnya
            nextMusic();
        }
    }

    private static void detailsMusic() {
        System.out.print("Judul yang ingin dicari: ");
        String judul = in.nextLine();

        // iterasi array dan membandingkan judul
        for (int i = 0; i < index; i++){
            if (playlist[i][0] != null){
                if (playlist[i][0].equalsIgnoreCase(judul)){
                    System.out.println("Data lagu:");
                    System.out.println("Judul : " + playlist[i][0]);
                    System.out.println("Artist: " + playlist[i][1]);
                    System.out.println("Album : " + playlist[i][2]);
                    System.out.println("Tahun : " + playlist[i][3]);
                    break;
                }
            }
            // jika tidak ditemukan sampai lagu terakhir, tampilkan pesan
            if (i == index-1){
                System.out.println("Lagu tidak ditemukan");
            }
        }

    }

    private static void prevMusic() {
        // jika pointer sudah mencapai awal array, pointer kembali ke akhir array
        if (pointer == 0){
            pointer = index;
        }

        pointer--;
        // jika array ke-pointer kosong, pointer berkurang 1
        while (playlist[pointer][0] == null){
            if (pointer == 0){
                pointer = index;
            }
            pointer--;
        }
    }


    private static void addMusic() {
        // prompt input
        System.out.println("Silahkan masukkan lagu Anda");
        System.out.print("Judul : ");
        String judul = in.nextLine();
        playlist[index][0] = judul;

        System.out.print("Artist: ");
        String artis = in.nextLine();
        playlist[index][1] = artis;

        System.out.print("Album : ");
        String album = in.nextLine();
        playlist[index][2] = album;

        System.out.print("Tahun : ");
        String tahun = in.nextLine();
        playlist[index][3] = tahun;

        index++;
        jumlahMusik++;
    }


    private static void display() {
        System.out.println();
        System.out.println("Currently Playing");

        String displayedMusic = " " + playlist[pointer][1] + " - " + playlist[pointer][0] + " ";
        String command = "|[1] prev |[2] add music |[3] details |[4] delete music |[5] next|";

        if (displayedMusic.length() < command.length()){
            int width = 62;
            String s = displayedMusic;

            int padSize = width - s.length();
            int padStart = s.length() + padSize / 2;

            s = String.format("%" + padStart + "s", s);
            s = String.format("%-" + width  + "s", s);


            System.out.println(new String(new char[66]).replace("\0", "="));
            System.out.println("= "+ s +" =");
            System.out.println(new String(new char[66]).replace("\0", "="));
            System.out.println(command);

            return;
        }

        System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
        System.out.println("=" + displayedMusic + "=");
        System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
        System.out.println(command);
    }

}
