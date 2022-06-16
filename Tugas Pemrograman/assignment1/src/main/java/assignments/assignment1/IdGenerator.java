package assignments.assignment1;

import java.util.HashMap;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class IdGenerator {
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /*
     * Code pada method main tidak boleh diganti sama sekali!
     */
    public static void main(String[] args) {
        buildMapCharToValue();
        Scanner input = new Scanner(System.in);
        System.out.println("Selamat Datang di Perpustakaan!");

        boolean shouldTerminateProgram = false;

        while (!shouldTerminateProgram) {
            printMenu();
            System.out.print("Menu yang anda pilih: ");
            int menu = input.nextInt();
            input.nextLine();

            if (menu == 1) {
                System.out.print("Program Studi: ");
                String programStudi = input.nextLine();
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();
                System.out.print("Tanggal Lahir: ");
                String tanggalLahir = input.nextLine();

                System.out.println(generateId(programStudi, angkatan, tanggalLahir));
            } else if (menu == 2) {
                System.out.print("ID Anggota: ");
                String idAnggota = input.nextLine();

                System.out.print("Validitas: ");
                System.out.println(checkValidity(idAnggota));
            } else {
                shouldTerminateProgram = true;
                System.out.println("Sampai jumpa!");
            }
        }

        input.close();
    }

    /*
     * Method buildMapCodeToNumber adalah method untuk membuat mapping reference numbers Code 93
     */
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    /*
     * Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
     */
    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    /*
     * Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
     */
    private static int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    private static void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("Menu yang tersedia:");
        System.out.println("1. Generate ID anggota untuk anggota baru");
        System.out.println("2. Check validitas ID anggota");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
    }

    /*
     * Method generateId adalah method untuk membuat ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     */
    public static String generateId(String programStudi, String angkatan, String tanggalLahir){
        // TODO: Tuliskan implementasi untuk membuat ID keanggotaan perpustakaan
        // Buat variable untuk menampung ID keanggotaan dan membuat Array yang berisi daftar prodi
        String id = "";

        // Handle jika program studi tidak ada di daftar prodi
        if (!programStudi.equals("SIK") &&
                !programStudi.equals("SSI") &&
                !programStudi.equals("MIK") &&
                !programStudi.equals("MTI") &&
                !programStudi.equals("DIK")){
            return "Input tidak valid!";
        }

        if (!isNumeric(angkatan)){
            return "Input tidak valid!";
        }

        // Handle jika angkatan tidak 4 digit dan tidak berada di rentang 2000 - 2021
        if (angkatan.length() != 4 || !(Integer.parseInt(angkatan) >= 2000 && Integer.parseInt(angkatan) <= 2021)){
            return "Input tidak valid!";
        }

        // Handle jika tanggal lahir tidak sesuai format
        if (!validateJavaDate(tanggalLahir)){
            return "Input tidak valid!";
        }

        // Menambahkan program studi dan 2 digit terakhir angkatan ke ID
        id += programStudi;
        id += angkatan.substring(angkatan.length() - 2);

        // Menghilangkan tanda "/" pada tanggal lahir
        tanggalLahir = tanggalLahir.replace("/", "");
        tanggalLahir = tanggalLahir.substring(0, 4) + tanggalLahir.substring(6);

        // Menambahkan tanggal lahir ke ID
        id += tanggalLahir;

        // Menambahkan Checksum C ke ID
        id += createChecksum(id);

        // Menambahkan Checksum K ke ID
        id += createChecksum(id);

        return "ID Anggota: " + id;
    }


    /*
     * Method checkValidity adalah method untuk mengecek validitas ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     */
    public static boolean checkValidity(String idAnggota) {
        // TODO: Tuliskan implementasi untuk mengecek validitas ID keanggotaan perpustakaan
        // Handle jika ID tidak berjumlah 13 karakter
        if (idAnggota.length() != 13) {
            return false;
        }

        // Memisahkan bagian angka dan bagian huruf
        String huruf = idAnggota.substring(0, 3);
        String angka = idAnggota.substring(3, 11);

        // Handle jika huruf tidak upper case dan angka tidak numeric
        if (!isUpper(huruf) || !isNumeric(angka)){
            return false;
        }

        // Menyimpan id tanpa checksum
        String substring1 = idAnggota.substring(0,11);
        String substring2 = idAnggota.substring(0,12);

        // Handle jika checksum C tidak sesuai
        if (createChecksum(substring1) != idAnggota.charAt(11) || createChecksum(substring2) != idAnggota.charAt(12)) {
            return false;
        }

        return true;
    }

    /**
     * Method untuk membuat checksum berdasarkan ID
     * @param id ID yang akan dihitung checksumnya
     * @return checksum dari ID
     */
    public static char createChecksum(String id) {
        // Membuat variabel penampung checksum
        char checksumC;
        int checksumNumC = 0;

        // Menghitung value tiap karakter dan menjumlahkannya
        for (int i = 0; i < id.length(); i++) {
            int val = 0;

            // Mengubah karakter menjadi integer sesuai dengan aturan code 93
            if (isNumeric(Character.toString(id.charAt(i)))) {
                val = (id.charAt(i) - '0');
            } else if (Character.isUpperCase(id.charAt(i))) {
                val = (id.charAt(i) - 'A' + 10);
            }
            val *= id.length() - i;
            checksumNumC += val;
        }
        checksumNumC %= 36;

        // Mengubah checksum dari integer ke char sesuai aturan Code 93
        if (checksumNumC < 10) {
            checksumC = (char) (checksumNumC + '0');
        } else {
            checksumC = (char) (checksumNumC - 10 + 'A');
        }

        return checksumC;
    }

    /**
     * Method untuk mengecek apakah string yang diberikan merupakan angka
     * @param strNum string yang akan dicek
     * @return true jika string merupakan angka
     */
    public static boolean isNumeric(String strNum) {
        // Jika string null
        if (strNum == null) {
            return false;
        }

        // Merubah string menjadi double dan melempar exception jika error
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Method untuk mengecek kevalidan format tanggal lahir
     * @param strDate string yang berisi tanggal lahir
     * @return true jika format tanggal lahir benar
     */
    public static boolean validateJavaDate(String strDate) {
        // Handling jika string kosong
        if (strDate.trim().equals("")) {
            return false;
        } else if (strDate.length() != 10) {
            return false;
        } else if (strDate.charAt(2) != '/') {
            return false;
        } else if (strDate.charAt(5) != '/') {
            return false;
        } else if (!isNumeric(strDate.substring(0, 2))) {
            return false;
        } else if (!isNumeric(strDate.substring(3, 5))) {
            return false;
        } else if (!isNumeric(strDate.substring(6, 10))) {
            return false;
        } else if (Integer.parseInt(strDate.substring(0, 2)) > 31) {
            return false;
        } else if (Integer.parseInt(strDate.substring(3, 5)) > 12) {
            return false;
        } else {
            // Memilih format tanggal yang akan digunakan
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            // Membuat objek date sesuai dengan format
            try {
                Date javaDate = sdfrmt.parse(strDate);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
    }

    /**
     * Method untuk mengecek apakah string yang diberikan merupakan huruf upper case semua
     * @param s string yang akan dicek
     * @return true jika string merupakan huruf upper case semua
     */
    public static boolean isUpper(String s) {
        for(char c : s.toCharArray()) {
            if(! Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
}

// Reference:
// https://beginnersbook.com/2013/05/java-date-format-validation/

