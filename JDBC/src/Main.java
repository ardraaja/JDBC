import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Register ===");
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();

        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);
        if (user.register()) {
            System.out.println("Registrasi berhasil!");
        } else {
            System.out.println("Registrasi gagal!");
            return;
        }

        System.out.println("\n=== Login ===");
        while (true) {
            System.out.print("Masukkan Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Masukkan Password: ");
            String loginPassword = scanner.nextLine();

            User loginUser = new User(loginUsername, loginPassword);
            if (loginUser.login()) {
                System.out.println("Login berhasil!");
                break;
            } else {
                System.out.println("Login gagal! Coba lagi.");
            }
        }

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Barang");
            System.out.println("3. Update Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Kode Barang: ");
                    String kodeBarang = scanner.nextLine();

                    System.out.print("Masukkan Nama Barang: ");
                    String namaBarang = scanner.nextLine();

                    System.out.print("Masukkan Harga Barang: ");
                    int hargaBarang = scanner.nextInt();

                    System.out.print("Masukkan Jumlah Beli: ");
                    int jumlahBeli = scanner.nextInt();

                    scanner.nextLine(); // Konsumsi newline

                    String noFaktur = "Fak001";
                    Barang barang = new Barang(kodeBarang, namaBarang, hargaBarang, jumlahBeli, noFaktur);

                    if (barang.saveToDatabase()) {
                        System.out.println("Barang berhasil disimpan!");
                    } else {
                        System.out.println("Gagal menyimpan barang.");
                    }
                    break;

                case 2:
                    Barang.readFromDatabase();
                    break;

                case 3:
                    System.out.print("Masukkan Kode Barang yang ingin diupdate: ");
                    String kodeUpdate = scanner.nextLine();

                    System.out.print("Masukkan Nama Barang baru: ");
                    String namaUpdate = scanner.nextLine();

                    System.out.print("Masukkan Harga Barang baru: ");
                    int hargaUpdate = scanner.nextInt();

                    System.out.print("Masukkan Jumlah Beli baru: ");
                    int jumlahUpdate = scanner.nextInt();

                    scanner.nextLine(); // Konsumsi newline

                    String noFakturUpdate = "Fak001";
                    Barang barangUpdate = new Barang(kodeUpdate, namaUpdate, hargaUpdate, jumlahUpdate, noFakturUpdate);

                    if (barangUpdate.updateDatabase()) {
                        System.out.println("Barang berhasil diupdate!");
                    } else {
                        System.out.println("Gagal mengupdate barang.");
                    }
                    break;

                case 4:
                    System.out.print("Masukkan Kode Barang yang ingin dihapus: ");
                    String kodeHapus = scanner.nextLine();

                    if (Barang.deleteFromDatabase(kodeHapus)) {
                        System.out.println("Barang berhasil dihapus!");
                    } else {
                        System.out.println("Gagal menghapus barang.");
                    }
                    break;

                case 5:
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
