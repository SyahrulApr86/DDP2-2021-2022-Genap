import java.util.Scanner;

public class KalkulatorBMI {
    public static void main(String[] args) {
//      Inisiasi semua variabel yang dibutuhkan
        int bawahNormal = 0;
        int normal = 0;
        int atasNormal = 0;
        int obesitas = 0;
        float bmi = 0;

//        Inisiasi scanner untuk input
        Scanner input = new Scanner(System.in);

//        Header CLI
        System.out.println("Selamat datang di program kalkulator BMI!");
        System.out.println("--------------------------------------------------------");
        System.out.print("Masukkan jumlah mahasiswa yang akan dihitung datanya: ");
        int jumlah = input.nextInt();
        input.nextLine();

//        looping untuk menghitung bmi tiap mahasiswa
        for (int i = 1; i <= jumlah; i++) {
            System.out.printf("--------------------DATA MAHASISWA %d-------------------- \n", i);
            System.out.print("Standar pengukuran apakah yang digunakan? ");
            String standar = input.nextLine();

//          conditinal statement untuk menentukan standar pengukuran
            if (standar.equals("METRIK")) {
                System.out.print("Masukkan massa mahasiswa (kilogram): ");
                float massa = input.nextFloat();

                System.out.print("Masukkan tinggi mahasiswa (sentimeter): ");
                float tinggi = input.nextFloat() / 100;
                input.nextLine();

                bmi = metric(massa, tinggi);

            } else if (standar.equals("IMPERIAL")) {
                System.out.print("Masukkan massa mahasiswa (pon): ");
                float massa = input.nextFloat();

                System.out.print("Masukkan tinggi mahasiswa (inci): ");
                float tinggi = input.nextFloat();
                input.nextLine();

                bmi = imperial(massa, tinggi);
            }

//            conditinal statement untuk menentukan kategori bmi
            if (bmi < 18.5) {
                bawahNormal++;
            } else if (bmi >= 18.5 && bmi <25) {
                normal++;
            } else if (bmi >= 25 && bmi < 30) {
                atasNormal++;
            } else if (bmi >= 30) {
                obesitas++;
            }
        }

//        menampilkan ringkasan hasil perhitungan
        System.out.println("---------------------RINGKASAN DATA---------------------");
        System.out.printf("Berikut merupakan ringkasan hasil pengukuran BMI dari %d mahasiswa.\n", jumlah);
        System.out.printf("Jumlah mahasiswa dengan berat badan di bawah normal: %d\n", bawahNormal);
        System.out.printf("Jumlah mahasiswa dengan berat badan normal: %d\n", normal);
        System.out.printf("Jumlah mahasiswa dengan berat badan di atas normal: %d\n", atasNormal);
        System.out.printf("Jumlah mahasiswa obesitas: %d\n", obesitas);
        System.out.println("--------------------------------------------------------");
        System.out.println("Terima kasih telah menggunakan program kalkulator BMI!");

        input.close();
    }

    /**
     * Metode untuk menghitung BMI dengan sistem metric
     * @param massa massa dari mahasiswa dalam kilogram
     * @param tinggi tinggi dari mahasiswa dalam sentimeter
     * @return bmi dari mahasiswa dalam sistem metric
     */
    public static float metric(float massa, float tinggi) {
        return massa / (tinggi * tinggi);
    }

    /**
     * Metode untuk menghitung BMI dengan sistem imperial
     * @param massa massa dari mahasiswa dalam pon
     * @param tinggi tinggi dari mahasiswa dalam inci
     * @return bmi dari mahasiswa dalam sistem imperial
     */
    public static float imperial(float massa, float tinggi) {
        return 703 * massa / (tinggi * tinggi);
    }
}



