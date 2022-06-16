import java.util.Scanner;

public class Template {
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
    String kodeString = Long.toString(kode);

    // Base Case
    if (kodeString.length() == 1) {
      return getDigit(Integer.parseInt(kodeString)*2);
    } else { // recursive case
      String slicing = kodeString.substring(2);
      return getDigit(Integer.parseInt(kodeString.substring(0,1)) * 2) + jumlahDoublePosisiGanjil(Long.parseLong(slicing));
    }
  }

  /**
   * Jika hasil kali dua digit tersebut menghasilkan angka yang lebih dari 9,
   * tambahkan dua digit angka tersebut untuk mendapatkan angka yang <= 9.
   */
  public static int getDigit(int number) { // int 18
    String numberString = Integer.toString(number); // string "18"
    if (number > 9) {
      return Integer.parseInt(numberString.substring(0, 1)) + Integer.parseInt(numberString.substring(1, 2));
    } else {
      return number;
    }
  }

  /**
   * Dapatkan hasil dari langkah (c)
   * (harus rekursif), boleh menggunakan helper method
   */
  public static int jumlahPosisiGenap(long kode) {
    String kodeString = Long.toString(kode);

    // Base Case
    if (kodeString.length() == 1) {
      return 0;
    } else { // recursive case
      String slicing = kodeString.substring(2);
      return Integer.parseInt(kodeString.substring(1,2)) + jumlahPosisiGenap(Long.parseLong(slicing));
    }
  }
  /** Return true jika kode merupakan prefix yang valid */
  public static boolean cekPrefix(long kode) {
    String kodeString = Long.toString(kode);
    if (kodeString.charAt(0) == '2') {
      return true;
    }
    return kodeString.startsWith("18");
  }
  /** Return panjang kode */
  public static int getPanjangKode(long kode) {
    String kodeString = Long.toString(kode);
    return kodeString.length();
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