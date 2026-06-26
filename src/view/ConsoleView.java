package view;

import controller.KnowledgeController;
import model.Putusan;
import model.StatistikPutusan;
import util.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ConsoleView bertanggung jawab menampilkan seluruh antarmuka
 * aplikasi melalui terminal.
 *
 * Class ini menangani tampilan menu, daftar putusan,
 * serta seluruh output yang diterima pengguna.
 *
 * @author Earland Khansadevlin Herdian
 */
public class ConsoleView {

    private Scanner sc = new Scanner(System.in);
    private KnowledgeController controller = new KnowledgeController();

    /**
     * Membuat objek ConsoleView dan memuat seluruh data
     * putusan yang tersimpan sebelum aplikasi dijalankan.
     *
     * @author Earland Khansadevlin Herdian
     */
    public ConsoleView() {
        controller.muatDataAwal();
    }

    /**
     * Menjalankan aplikasi berbasis terminal.
     *
     * Method ini akan terus menampilkan menu utama hingga
     * pengguna memilih keluar dari aplikasi.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Menampilkan menu utama aplikasi pada terminal.
     *
     * Menu berisi seluruh fitur yang dapat dipilih pengguna,
     * seperti tambah data, pencarian, filter, statistik,
     * ekspor laporan, penghapusan data, dan keluar aplikasi.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Meminta pengguna mengisi data putusan baru
     * kemudian meneruskannya ke controller untuk disimpan.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Menampilkan seluruh data putusan yang tersedia.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Melakukan pencarian putusan berdasarkan
     * nomor perkara.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Melakukan pencarian putusan berdasarkan
     * nama terdakwa.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Menampilkan putusan berdasarkan
     * jenis narkotika yang dipilih pengguna.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Menampilkan putusan berdasarkan
     * nama pengadilan.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Menampilkan putusan yang memiliki
     * lama vonis pada rentang tertentu.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Menampilkan seluruh data putusan yang telah
     * diurutkan berdasarkan lama vonis.
     *
     * @author Earland Khansadevlin Herdian
     */
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

    /**
     * Menampilkan ringkasan statistik seluruh
     * data putusan yang tersedia.
     *
     * @author Earland Khansadevlin Herdian
     */
    private void tampilkanStatistik() {
        StatistikPutusan statistik = controller.getStatistik();

        System.out.println("\n=== Statistik Putusan ===");
        statistik.tampilkanLaporan();
    }

    /**
     * Mengekspor laporan statistik putusan
     * ke dalam file teks (.txt).
     *
     * @author Earland Khansadevlin Herdian
     */
    private void exportStatistik() {
        String namaFile = "statistik_putusan.txt";

        boolean berhasil = controller.exportStatistikKeTxt(namaFile);

        if (berhasil) {
            System.out.println("Statistik berhasil diekspor ke file: " + namaFile);
        } else {
            System.out.println("Gagal mengekspor statistik.");
        }
    }

    /**
     * Menghapus data putusan berdasarkan
     * nomor perkara yang dimasukkan pengguna.
     *
     * @author Earland Khansadevlin Herdian
     */
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