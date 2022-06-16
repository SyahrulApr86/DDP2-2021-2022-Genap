import java.util.Scanner;

public class ValidasiImeiNew {

    /** Return true jika kode memenuhi ketentuan */
    public static boolean isValid(long kode) {
        return cekPrefix(kode)
                && (getPanjangKode(kode) == 11)
                && ((jumlahDoublePosisiGanjil(kode) + jumlahPosisiGenap(kode)) % 10 == 0);
    }

    /**
     * Dapatkan hasil dari langkah (b)
     * (harus rekursif), boleh menggunakan helper method
     */
    public static int jumlahDoublePosisiGanjil(long kode) {
        // Mengubah kode menjadi string
        String str = String.valueOf(kode);

        // Base case
        if (str.length() == 1) {
            return getDigit(Integer.parseInt(str)*2);
        } else { // Recursive case
            return getDigit(Integer.parseInt(str.substring(0, 1))*2) + jumlahDoublePosisiGanjil(Long.parseLong(str.substring(2)));
        }
    }

    /**
     * Jika hasil kali dua digit tersebut menghasilkan angka yang lebih dari 9,
     * tambahkan dua digit angka tersebut untuk mendapatkan angka yang <= 9.
     */
    public static int getDigit(int number) {
        // Jika angka lebih dari 9, tambahkan dua digit tersebut
        if (number>9) {
            return Integer.parseInt(String.valueOf(number).substring(0, 1)) + Integer.parseInt(String.valueOf(number).substring(1));
        }
        return number;
    }

    /**
     * Dapatkan hasil dari langkah (c)
     * (harus rekursif), boleh menggunakan helper method
     */
    public static int jumlahPosisiGenap(long kode) {
        // Mengubah kode menjadi string
        String str = String.valueOf(kode);
        // Base case
        if (str.length() == 1) {
            return 0;
        } else { // Recursive case
            return Integer.parseInt(str.substring(1, 2)) + jumlahPosisiGenap(Long.parseLong(str.substring(2)));
        }
    }

    /** Return true jika kode merupakan prefix yang valid */
    public static boolean cekPrefix(long kode) {
        String kodeString = String.valueOf(kode);
        return kodeString.charAt(0) == '2' || kodeString.startsWith("18");
    }

    /** Return panjang kode */
    public static int getPanjangKode(long kode) {
        return String.valueOf(kode).length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n-- > 0) {
            long kode = sc.nextLong();
            System.out.println(isValid(kode) ? "YES" : "NO");
        }
        sc.close();
    }
}