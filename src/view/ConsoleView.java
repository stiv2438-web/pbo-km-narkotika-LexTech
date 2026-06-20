package view;

import controller.KnowledgeController;
import model.Putusan;
import model.StatistikPutusan;
import util.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class ConsoleView berfungsi sebagai antarmuka berbasis console
 * untuk menampilkan menu dan menerima input dari pengguna.
 */
public class ConsoleView {

    private Scanner sc = new Scanner(System.in);
    private KnowledgeController controller = new KnowledgeController();

    public ConsoleView() {
        controller.muatDataAwal();
    }
    public void mulai() {
        int pilihan;

        do {
            tampilkanMenu();
            pilihan = InputHandler.validasiInt("Pilih menu: ", sc);

            switch (pilihan) {
                case 1:
                    tambahData();
                    break;
                case 2:
                    tampilkanSemua();
                    break;
                case 3:
                    cariNomor();
                    break;
                case 4:
                    cariNama();
                    break;
                case 5:
                    filterJenis();
                    break;
                case 6:
                    filterPengadilan();
                    break;
                case 7:
                    filterRentangVonis();
                    break;
                case 8:
                    sortByVonis();
                    break;
                case 9:
                    tampilkanStatistik();
                    break;
                case 10:
                    exportStatistik();
                    break;
                case 11:
                    hapusData();
                    break;
                case 12:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }


        } while (pilihan != 12);
    }

    private void tampilkanMenu() {
        System.out.println("\n=== LexTech KMS Narkotika ===");
        System.out.println("1. Tambah Putusan");
        System.out.println("2. Tampilkan Semua Putusan");
        System.out.println("3. Cari Putusan Berdasarkan Nomor");
        System.out.println("4. Cari Putusan Berdasarkan Nama");
        System.out.println("5. Filter Berdasarkan Jenis Narkotika");
        System.out.println("6. Filter Berdasarkan Pengadilan");
        System.out.println("7. Filter Berdasarkan Rentang Vonis");
        System.out.println("8. Sorting Berdasarkan Vonis");
        System.out.println("9. Statistik Putusan");
        System.out.println("10. Export Statistik ke TXT");
        System.out.println("11. Hapus Putusan");
        System.out.println("12. Keluar");
    }

    private void tambahData() {
        String nomor = InputHandler.validasiString("Nomor Perkara: ", sc);
        String pengadilan = InputHandler.validasiString("Pengadilan: ", sc);
        String nama = InputHandler.validasiString("Nama Terdakwa: ", sc);
        String jenis = InputHandler.validasiString("Jenis Narkotika: ", sc);
        int vonis = InputHandler.validasiInt("Vonis Hukuman (bulan): ", sc);
        int denda = InputHandler.validasiInt("Vonis Denda: ", sc);
        String peran = InputHandler.validasiString("Peran Terdakwa: ", sc);

        String[] data = {
                nomor,
                pengadilan,
                nama,
                jenis,
                String.valueOf(vonis),
                String.valueOf(denda),
                peran
        };

        boolean berhasil = controller.tambahPutusan(data);

        if (berhasil) {
            System.out.println("Data berhasil ditambahkan.");
        } else {
            System.out.println("Data gagal ditambahkan.");
        }
    }

    private void tampilkanSemua() {
        ArrayList<Putusan> daftar = controller.getSemuaPutusan();

        if (daftar.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        System.out.println("\n=== Daftar Putusan ===");

        for (Putusan p : daftar) {
            System.out.println(p);
        }
    }

    private void cariNomor() {
        String nomor = InputHandler.validasiString("Masukkan nomor perkara: ", sc);

        Putusan hasil = controller.cariByNomor(nomor);

        if (hasil != null) {
            System.out.println("Data ditemukan:");
            System.out.println(hasil);
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }

    private void cariNama() {
        String nama = InputHandler.validasiString("Masukkan nama terdakwa: ", sc);

        ArrayList<Putusan> hasil = controller.cariByNama(nama);

        if (hasil.isEmpty()) {
            System.out.println("Data dengan nama tersebut tidak ditemukan.");
            return;
        }

        System.out.println("\n=== Hasil Pencarian Nama Terdakwa ===");

        for (Putusan p : hasil) {
            System.out.println(p);
        }
    }

    private void filterJenis() {
        String jenis = InputHandler.validasiString("Masukkan jenis narkotika: ", sc);

        ArrayList<Putusan> hasil = controller.filterByJenis(jenis);

        if (hasil.isEmpty()) {
            System.out.println("Tidak ada data dengan jenis narkotika tersebut.");
            return;
        }

        System.out.println("\n=== Hasil Filter Jenis Narkotika ===");

        for (Putusan p : hasil) {
            System.out.println(p);
        }
    }

    private void filterPengadilan() {
        String pengadilan = InputHandler.validasiString("Masukkan nama pengadilan: ", sc);

        ArrayList<Putusan> hasil = controller.filterByPengadilan(pengadilan);

        if (hasil.isEmpty()) {
            System.out.println("Tidak ada data dari pengadilan tersebut.");
            return;
        }

        System.out.println("\n=== Hasil Filter Pengadilan ===");

        for (Putusan p : hasil) {
            System.out.println(p);
        }
    }

    private void filterRentangVonis() {
        int min = InputHandler.validasiInt("Masukkan vonis minimum (bulan): ", sc);
        int max = InputHandler.validasiInt("Masukkan vonis maksimum (bulan): ", sc);

        ArrayList<Putusan> hasil = controller.filterByRentangVonis(min, max);

        if (hasil.isEmpty()) {
            System.out.println("Tidak ada data dalam rentang vonis tersebut.");
            return;
        }

        System.out.println("\n=== Hasil Filter Rentang Vonis ===");

        for (Putusan p : hasil) {
            System.out.println(p);
        }
    }

    private void sortByVonis() {
        ArrayList<Putusan> hasil = controller.sortByVonis();

        if (hasil.isEmpty()) {
            System.out.println("Belum ada data putusan.");
            return;
        }

        System.out.println("\n=== Data Putusan Berdasarkan Vonis Terendah ===");

        for (Putusan p : hasil) {
            System.out.println(p);
        }
    }

    private void tampilkanStatistik() {
        StatistikPutusan statistik = controller.getStatistik();

        System.out.println("\n=== Statistik Putusan ===");
        statistik.tampilkanLaporan();
    }

    private void exportStatistik() {
        String namaFile = "statistik_putusan.txt";

        boolean berhasil = controller.exportStatistikKeTxt(namaFile);

        if (berhasil) {
            System.out.println("Statistik berhasil diekspor ke file: " + namaFile);
        } else {
            System.out.println("Gagal mengekspor statistik.");
        }
    }

    private void hapusData() {
        String nomor = InputHandler.validasiString("Masukkan nomor perkara yang ingin dihapus: ", sc);

        boolean berhasil = controller.hapusPutusan(nomor);

        if (berhasil) {
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }
}