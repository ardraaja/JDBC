import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private int hargaBarang;
    private int jumlahBeli;
    private String noFaktur;

    public Barang(String kodeBarang, String namaBarang, int hargaBarang, int jumlahBeli, String noFaktur) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
        this.noFaktur = noFaktur;
    }

    public boolean saveToDatabase() {
        String query = "INSERT INTO barang (kode_barang, nama_barang, harga_barang, jumlah_beli, no_faktur) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, kodeBarang);
            stmt.setString(2, namaBarang);
            stmt.setInt(3, hargaBarang);
            stmt.setInt(4, jumlahBeli);
            stmt.setString(5, noFaktur);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("data barang sudah ada. Silakan coba lagi.");
            return false;
        }
    }

    public static void readFromDatabase() {
        String query = "SELECT * FROM barang";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Kode Barang: " + rs.getString("kode_barang"));
                System.out.println("Nama Barang: " + rs.getString("nama_barang"));
                System.out.println("Harga Barang: " + rs.getInt("harga_barang"));
                System.out.println("Jumlah Beli: " + rs.getInt("jumlah_beli"));
                System.out.println("No Faktur: " + rs.getString("no_faktur"));
                System.out.println("===========================");
            }

        } catch (SQLException e) {
            System.out.println("Kesalahan saat memproses data. Silakan coba lagi.");
        }
    }

    public boolean updateDatabase() {
        String query = "UPDATE barang SET nama_barang = ?, harga_barang = ?, jumlah_beli = ?, no_faktur = ? WHERE kode_barang = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, namaBarang);
            stmt.setInt(2, hargaBarang);
            stmt.setInt(3, jumlahBeli);
            stmt.setString(4, noFaktur);
            stmt.setString(5, kodeBarang);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Kesalahan saat memproses data. Silakan coba lagi.");
            return false;
        }
    }

    public static boolean deleteFromDatabase(String kodeBarang) {
        String query = "DELETE FROM barang WHERE kode_barang = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, kodeBarang);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Kesalahan saat memproses data. Silakan coba lagi.");
            return false;
        }
    }
}
