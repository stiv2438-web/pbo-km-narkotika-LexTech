package model;

import java.util.ArrayList;

/**
 * Class untuk menghitung statistik data putusan.
 * Statistik meliputi jumlah data, rata-rata vonis,
 * rata-rata denda, jenis narkotika terbanyak,
 * dan distribusi peran terdakwa.
 */
public class StatistikPutusan {

    private int totalPutusan;
    private double rataRataVonis;
    private double rataRataDenda;
    private String jenisNarkotikaTerbanyak;
    private String distribusiPeran;

    /**
     * Constructor yang langsung menghitung statistik
     * berdasarkan daftar putusan.
     *
     * @param daftar daftar seluruh putusan
     */
    public StatistikPutusan(ArrayList<Putusan> daftar) {
        hitungSemua(daftar);
    }

    /**
     * Menghitung seluruh statistik dari daftar putusan.
     *
     * @param daftar daftar putusan
     */
    private void hitungSemua(ArrayList<Putusan> daftar) {
        totalPutusan = daftar.size();

        if (daftar.isEmpty()) {
            rataRataVonis = 0;
            rataRataDenda = 0;
            jenisNarkotikaTerbanyak = "-";
            distribusiPeran = "-";
            return;
        }

        int totalVonis = 0;
        double totalDenda = 0;

        int jumlahSabu = 0;
        int jumlahGanja = 0;
        int jumlahEkstasi = 0;
        int jumlahLainnya = 0;

        int jumlahBandar = 0;
        int jumlahKurir = 0;
        int jumlahPengguna = 0;
        int jumlahPenyimpan = 0;
        int jumlahPeranLainnya = 0;

        for (Putusan p : daftar) {
            totalVonis += p.getVonisHukuman();
            totalDenda += p.getVonisDenda();

            String jenis = p.getJenisNarkotika().toLowerCase();

            if (jenis.contains("sabu")) {
                jumlahSabu++;
            } else if (jenis.contains("ganja")) {
                jumlahGanja++;
            } else if (jenis.contains("ekstasi")) {
                jumlahEkstasi++;
            } else {
                jumlahLainnya++;
            }

            String peran = p.getPeranTerdakwa().toLowerCase();

            if (peran.contains("bandar")) {
                jumlahBandar++;
            } else if (peran.contains("kurir")) {
                jumlahKurir++;
            } else if (peran.contains("pengguna")) {
                jumlahPengguna++;
            } else if (peran.contains("penyimpan")) {
                jumlahPenyimpan++;
            } else {
                jumlahPeranLainnya++;
            }
        }

        rataRataVonis = (double) totalVonis / daftar.size();
        rataRataDenda = totalDenda / daftar.size();

        jenisNarkotikaTerbanyak = "Sabu";
        int max = jumlahSabu;

        if (jumlahGanja > max) {
            max = jumlahGanja;
            jenisNarkotikaTerbanyak = "Ganja";
        }

        if (jumlahEkstasi > max) {
            max = jumlahEkstasi;
            jenisNarkotikaTerbanyak = "Ekstasi";
        }

        if (jumlahLainnya > max) {
            jenisNarkotikaTerbanyak = "Lainnya";
        }

        distribusiPeran =
                "Bandar: " + jumlahBandar
                        + ", Kurir: " + jumlahKurir
                        + ", Pengguna: " + jumlahPengguna
                        + ", Penyimpan: " + jumlahPenyimpan
                        + ", Lainnya: " + jumlahPeranLainnya;
    }

    /**
     * Menampilkan laporan statistik ke console.
     */
    public void tampilkanLaporan() {
        System.out.println("Total Putusan              : " + totalPutusan);
        System.out.println("Rata-rata Vonis            : " + rataRataVonis + " bulan");
        System.out.println("Rata-rata Denda            : Rp" + rataRataDenda);
        System.out.println("Jenis Narkotika Terbanyak  : " + jenisNarkotikaTerbanyak);
        System.out.println("Distribusi Peran           : " + distribusiPeran);
    }

    /**
     * Menghasilkan laporan statistik dalam bentuk teks.
     *
     * @return laporan statistik
     */
    public String getLaporanText() {
        return "Total Putusan              : " + totalPutusan + "\n"
                + "Rata-rata Vonis            : " + rataRataVonis + " bulan\n"
                + "Rata-rata Denda            : Rp" + rataRataDenda + "\n"
                + "Jenis Narkotika Terbanyak  : " + jenisNarkotikaTerbanyak + "\n"
                + "Distribusi Peran           : " + distribusiPeran + "\n";
    }
}