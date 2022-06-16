import java.io.*;
import java.util.*;

public class HealthWorthinessChecker {
    private static int MINIMUM_TINGKAT_KESEHATAN = 70;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner inputFile = null;
        PrintWriter outputFile = null;
        int jumlahMahasiswa = 0;

        System.out.println("Selamat datang di program Health Worthiness Checker.");
        System.out.println("-------------------------------------------------------");

        System.out.print("Silakan masukkan nama file masukan: ");
        String inputFileName = in.next();
        System.out.print("Silakan masukkan nama file keluaran: ");
        String outputFileName = in.next();

        System.out.println("-------------------------------------------------------");

        in.close();

        try {
            inputFile = new Scanner(new File(inputFileName));
            System.out.println("Data sedang diproses, harap menunggu...");

            String line = inputFile.nextLine();

            jumlahMahasiswa = Integer.valueOf(line);
            // TODO: Implementasi inisiasi array penyimpanan data
            Mahasiswa[] daftarMahasiswa = new Mahasiswa[jumlahMahasiswa]; // inisialisasi array

            int baris = 0;
            int index = 0;
            String nama = null;
            while (inputFile.hasNextLine()) {
                line = inputFile.nextLine();
                // TODO: Implementasi penyimpanan data mahasiswa ke array sesuai spesifikasi
                if (baris%2==0){ // baris genap setelah baris pertama
                    nama = line;
                } else { // baris ganjil setelah baris pertama
                    String[] indikator = line.split(" ");
                    int tingkatKesehatan = 0;

                    // Menghitung tingkat kesehatan
                    for (int j = 0; j < indikator.length; j++) {
                        tingkatKesehatan += Integer.parseInt(indikator[j]);
                    }

                    tingkatKesehatan *= 2;
                    daftarMahasiswa[index] = new Mahasiswa(nama, tingkatKesehatan); // inisialisasi objek mahasiswa
                    index++;
                }
                baris++;
            }

            // Proses data mahasiswa
            System.out.println("\nLOG:");
            for (int i = 0; i < jumlahMahasiswa; i++) {
                try {
                    // TODO: Implementasi pengecekan tingkat kesehatan mahasiswa
                    // Mengecek apakah Mahasiswa layak atau tidak
                    if (daftarMahasiswa[i].getStatus().equals("Layak mengikuti program")) {
                        System.out.printf("%s: LAYAK\n", daftarMahasiswa[i].getNama());
                    } else { // Mahasiswa tidak layak
                        throw new HealthinessUnworthyException("Tidak layak mengikuti program");
                    }
                } catch (HealthinessUnworthyException e) {
                    // TODO: Implementasi output ketika mahasiswa tidak memenuhi kelayakan
                    // Handling ketika throw HealthinessUnworthyException
                    System.out.printf("%s: TIDAK LAYAK\n", daftarMahasiswa[i].getNama());
                }
            }

            // Output ke teks
            outputFile = new PrintWriter(new File(outputFileName));

            outputFile.write("Nama Mahasiswa          | Status\n");
            outputFile.write("-------------------------------------------------------\n");
            // TODO: Implementasi output data mahasiswa ke file output
            // print data mahasiswa ke file output
            for (int i = 0; i < jumlahMahasiswa; i++) {
                outputFile.write(daftarMahasiswa[i].toString());
            }
            System.out.println("\nData mahasiswa berhasil diproses!");

        } catch (FileNotFoundException e) {
            // TODO: Implementasi output ketika file tidak ditemukan
            // Handling ketika file tidak ditemukan
            System.out.println("ERROR: File masukan tidak ditemukan.");
        } finally {
            if (inputFile != null) {
                inputFile.close();
            }
            if (outputFile != null) {
                outputFile.close();
            }
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Terima kasih telah menggunakan program Health Worthiness Checker.");
    }
}
