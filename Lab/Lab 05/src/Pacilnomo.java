import java.util.Scanner;

public class Pacilnomo {
	private static Aset[] portofolio;
	private static double earnings;

	private static void printSeparator() {
		System.out.println("=".repeat(64));
	}
	
	private static void daftarAset() {
		printSeparator();
		System.out.printf("Kamu memiliki %d total aset:\n", portofolio.length);
		for(Aset a : portofolio) {
			System.out.println("- " + a);
		}
		printSeparator();
	}

	private static void infoPortofolio() {
		int jumlahSaham = 0, jumlahObligasi = 0;
		double netWorth = 0;

		// TODO implementasikan perhitungan total valuasi aset, yaitu nilai semua aset dan pendapatan dari dividen dan bunga
		// Iterasi setiap aset pada portofolio dan menghitung jumlah saham dan obligasi
		for (Aset a : portofolio) {
			if (a instanceof Saham) {
				jumlahSaham++;
			} else if (a instanceof Obligasi) {
				jumlahObligasi++;
			}
			// Menghitung nilai semua aset
			netWorth += a.getHarga() * a.getJumlah();
		}
		// Menghitung nilai pendapatan dari dividen dan bunga
		netWorth += earnings;


		printSeparator();
		System.out.printf("" +
		"Info Portofolio\n" +
		"Jumlah Jenis Saham: %d\n" +
		"Jumlah Jenis Obligasi: %d\n" +
		"Total Nilai Portofolio: %.2f\n", jumlahSaham, jumlahObligasi, netWorth);
		printSeparator();
	}

	private static void nextYear() {
		// TODO implementasikan pemanggilan nextYear untuk setiap aset pada portofolio
		// Iterasi setiap aset pada portofolio dan memanggil nextYear
		for (Aset a : portofolio) {
			a.nextYear();
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Silakan masukkan banyak aset yang tersedia: ");
		int banyakAset = Integer.parseInt(in.nextLine());
		
		portofolio = new Aset[banyakAset];
		
		for(int i = 0; i < banyakAset; i++) {
			System.out.printf("Aset %d: ", i + 1);
			String[] inp = in.nextLine().split("\\s+");
			String namaAset = inp[0], jenisAset = inp[1]; 
			int jumlah = Integer.parseInt(inp[2]);
			double harga = Double.parseDouble(inp[3]);

			
			if(jenisAset.equals("SAHAM")) {
				// TODO tambahkan aset saham ke portofolio berdasarkan input user
				// Buat Objek Saham baru
				double pertumbuhan = Double.parseDouble(inp[4]);
				double dividen = Double.parseDouble(inp[5]);
				portofolio[i] = new Saham(namaAset, jumlah, harga, pertumbuhan, dividen);

			} else if(jenisAset.equals("OBLIGASI")) {
				// TODO tambahkan aset obligasi ke portofolio berdasarkan input user
				// Buat Objek Obligasi baru
				double bunga = Double.parseDouble(inp[4]);
				int maturity = Integer.parseInt(inp[5]);
				portofolio[i] = new Obligasi(namaAset, jumlah, harga, bunga, maturity);
			}
		}

		System.out.print("Selamat datang di...");
		System.out.print("\n" +
		"\n" +
		" /$$$$$$$                     /$$ /$$\n" +
		"| $$__  $$                   |__/| $$\n" +
		"| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$ /$$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$\n" +
		"| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$__  $$ /$$__  $$| $$_  $$_  $$ /$$__  $$\n" +
		"| $$____/  /$$$$$$$| $$      | $$| $$| $$  \\ $$| $$  \\ $$| $$ \\ $$ \\ $$| $$  \\ $$\n" +
		"| $$      /$$__  $$| $$      | $$| $$| $$  | $$| $$  | $$| $$ | $$ | $$| $$  | $$\n" +
		"| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$  | $$|  $$$$$$/| $$ | $$ | $$|  $$$$$$/\n" +
		"|__/      \\_______/ \\_______/|__/|__/|__/  |__/ \\______/ |__/ |__/ |__/ \\______/\n\n");
		
		while(true) {
			System.out.printf("" +
			"Silakan pilih salah satu opsi berikut:\n" +
			"[1] Daftar aset\n" +
			"[2] Info portofolio\n" +
			"[3] Lanjut ke tahun berikutnya\n" +
			"[*] Keluar\n");
			printSeparator();
			System.out.print("Input: ");
			String pilihan = in.nextLine();
			if(pilihan.equals("1")) {
				daftarAset();
			} else if(pilihan.equals("2")) {
				infoPortofolio();
			} else if(pilihan.equals("3")) {
				nextYear();
				System.out.println("Setahun telah berlalu...");
				printSeparator();
			} else {
				System.out.println("Terima kasih telah menggunakan layanan Pacilnomo ~ !");
				break;
			}
		}
		
		in.close();
	}
	
	public static void addToEarnings(double jumlah) {
		earnings += jumlah;
	}
}
