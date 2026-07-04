package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Repository untuk menyimpan dan mengelola seluruh data putusan.
 * Class ini menggunakan ArrayList sebagai struktur data utama.
 */
public class KnowledgeRepository {

    private ArrayList<Putusan> daftarPutusan = new ArrayList<>();

    /**
     * Menyimpan data putusan ke repository.
     *
     * @param p objek Putusan yang akan disimpan
     */
    public void simpan(Putusan p) {
        daftarPutusan.add(p);
    }

    /**
     * Mengambil seluruh data putusan.
     *
     * @return daftar seluruh putusan
     */
    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }

    /**
     * Menghitung total data putusan.
     *
     * @return jumlah data putusan
     */
    public int getTotalData() {
        return daftarPutusan.size();
    }

    /**
     * Mencari putusan berdasarkan nomor perkara.
     *
     * @param nomor nomor perkara yang dicari
     * @return objek Putusan jika ditemukan, null jika tidak ditemukan
     */
    public Putusan cariByNomor(String nomor) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Mencari putusan berdasarkan nama terdakwa.
     *
     * @param nama nama terdakwa yang dicari
     * @return daftar putusan yang cocok
     */
    public ArrayList<Putusan> cariByNama(String nama) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getNamaTerdakwa().toLowerCase().contains(nama.toLowerCase())) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * Memfilter putusan berdasarkan jenis narkotika.
     *
     * @param jenis jenis narkotika
     * @return daftar putusan yang sesuai
     */
    public ArrayList<Putusan> filterByJenis(String jenis) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * Memfilter putusan berdasarkan nama pengadilan.
     *
     * @param pengadilan nama pengadilan
     * @return daftar putusan yang sesuai
     */
    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getPengadilan().toLowerCase().contains(pengadilan.toLowerCase())) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * Memfilter putusan berdasarkan rentang vonis hukuman.
     *
     * @param min batas minimum vonis
     * @param max batas maksimum vonis
     * @return daftar putusan dalam rentang vonis
     */
    public ArrayList<Putusan> filterByRentangVonis(int min, int max) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getVonisHukuman() >= min && p.getVonisHukuman() <= max) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * Mengurutkan putusan berdasarkan vonis hukuman dari kecil ke besar.
     *
     * @return daftar putusan yang sudah diurutkan
     */
    public ArrayList<Putusan> sortByVonis() {
        ArrayList<Putusan> hasil = new ArrayList<>(daftarPutusan);

        Collections.sort(hasil, new Comparator<Putusan>() {
            @Override
            public int compare(Putusan p1, Putusan p2) {
                return Integer.compare(p1.getVonisHukuman(), p2.getVonisHukuman());
            }
        });

        return hasil;
    }

    /**
     * Menghapus data putusan berdasarkan nomor perkara.
     *
     * @param nomor nomor perkara yang akan dihapus
     * @return true jika berhasil dihapus, false jika tidak ditemukan
     */
    public boolean hapus(String nomor) {
        Putusan p = cariByNomor(nomor);

        if (p != null) {
            daftarPutusan.remove(p);
            return true;
        }

        return false;
    }
}