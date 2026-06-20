package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class KnowledgeRepository digunakan untuk menyimpan dan mengelola
 * kumpulan data Putusan menggunakan ArrayList.
 */
public class KnowledgeRepository {

    private ArrayList<Putusan> daftarPutusan = new ArrayList<>();

    public void simpan(Putusan p) {
        daftarPutusan.add(p);
    }

    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }

    public int getTotalData() {
        return daftarPutusan.size();
    }

    public Putusan cariByNomor(String nomor) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Putusan> cariByNama(String nama) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getNamaTerdakwa().toLowerCase().contains(nama.toLowerCase())) {
                hasil.add(p);
            }
        }

        return hasil;
    }
    public ArrayList<Putusan> filterByJenis(String jenis) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getPengadilan().toLowerCase().contains(pengadilan.toLowerCase())) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    public ArrayList<Putusan> filterByRentangVonis(int min, int max) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getVonisHukuman() >= min && p.getVonisHukuman() <= max) {
                hasil.add(p);
            }
        }

        return hasil;
    }

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

    public boolean hapus(String nomor) {
        Putusan p = cariByNomor(nomor);

        if (p != null) {
            daftarPutusan.remove(p);
            return true;
        }

        return false;
    }
}