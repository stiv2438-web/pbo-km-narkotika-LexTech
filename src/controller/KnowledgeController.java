package controller;

import model.KnowledgeRepository;
import model.Putusan;
import model.StatistikPutusan;

import java.util.ArrayList;
import java.io.FileWriter;

/**
 * Class KnowledgeController berfungsi sebagai penghubung antara View dan Model.
 * Controller mengatur proses tambah data, pencarian, filter, statistik,
 * penghapusan data, sorting, dan ekspor laporan.
 */
public class KnowledgeController {

    private KnowledgeRepository repo = new KnowledgeRepository();

    public boolean tambahPutusan(String[] data) {
        try {
            String nomor = data[0];
            String pengadilan;
            String nama;
            String jenis;
            int vonis;
            double denda;
            String peran;

            if (data.length >= 7) {
                pengadilan = data[1];
                nama = data[2];
                jenis = data[3];
                vonis = Integer.parseInt(data[4]);
                denda = Double.parseDouble(data[5]);
                peran = data[6];
            } else if (data.length >= 5) {
                pengadilan = data[1];
                nama = data[2];
                jenis = data[3];
                vonis = Integer.parseInt(data[4]);
                denda = 500000000;
                peran = tentukanPeranDefault(vonis);
            } else {
                pengadilan = "PN Surabaya";
                nama = data[1];
                jenis = data[2];
                vonis = Integer.parseInt(data[3]);
                denda = 500000000;
                peran = tentukanPeranDefault(vonis);
            }

            Putusan p = new Putusan(nomor, nama, jenis, vonis);
            p.setPengadilan(pengadilan);
            p.setVonisDenda(denda);
            p.setPeranTerdakwa(peran);

            repo.simpan(p);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String tentukanPeranDefault(int vonis) {
        if (vonis >= 40) {
            return "Bandar";
        } else if (vonis >= 25) {
            return "Kurir";
        } else {
            return "Pengguna";
        }
    }

    public ArrayList<Putusan> getSemuaPutusan() {
        return repo.getDaftarSemua();
    }

    public Putusan cariByNomor(String nomor) {
        return repo.cariByNomor(nomor);
    }

    public ArrayList<Putusan> cariByNama(String nama) {

        return repo.cariByNama(nama);
    }

    public ArrayList<Putusan> filterByJenis(String jenis) {

        return repo.filterByJenis(jenis);
    }

    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        return repo.filterByPengadilan(pengadilan);
    }
    public ArrayList<Putusan> filterByRentangVonis(int min, int max) {
        return repo.filterByRentangVonis(min, max);
    }

    public ArrayList<Putusan> sortByVonis() {
        return repo.sortByVonis();
    }

    public boolean hapusPutusan(String nomor) {

        return repo.hapus(nomor);
    }

    public int totalData() {

        return repo.getTotalData();
    }

    public StatistikPutusan getStatistik() {

        return new StatistikPutusan(repo.getDaftarSemua());
    }


    /**
     * Mengekspor laporan statistik putusan ke file TXT.
     *
     * @param namaFile nama file tujuan
     * @return true jika export berhasil, false jika gagal
     */
    public boolean exportStatistikKeTxt(String namaFile) {
        try (FileWriter writer = new FileWriter(namaFile)) {
            StatistikPutusan statistik = getStatistik();

            writer.write("=== LAPORAN STATISTIK PUTUSAN ===\n");
            writer.write(statistik.getLaporanText());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void muatDataAwal() {

        if (repo.getTotalData() > 0) {
            return;
        }

        String[][] dataAwal = {
                {"001/Pid.Sus/2024/PN Sby", "Andi Pratama", "Sabu", "24"},
                {"002/Pid.Sus/2024/PN Sby", "Budi Santoso", "Ganja", "18"},
                {"003/Pid.Sus/2024/PN Sby", "Rudi Hartono", "Sabu", "36"},
                {"004/Pid.Sus/2024/PN Sby", "Deni Saputra", "Ekstasi", "20"},
                {"005/Pid.Sus/2024/PN Sby", "Agus Wijaya", "Sabu", "48"},
                {"006/Pid.Sus/2024/PN Sby", "Fajar Nugroho", "Ganja", "15"},
                {"007/Pid.Sus/2024/PN Sby", "Hendra Kusuma", "Sabu", "30"},
                {"008/Pid.Sus/2024/PN Sby", "Rizky Maulana", "Ekstasi", "22"},
                {"009/Pid.Sus/2024/PN Sby", "Yusuf Ibrahim", "Sabu", "42"},
                {"010/Pid.Sus/2024/PN Sby", "Teguh Prasetyo", "Ganja", "16"},

                {"011/Pid.Sus/2024/PN Sby", "Arif Setiawan", "Sabu", "28"},
                {"012/Pid.Sus/2024/PN Sby", "Bayu Ramadhan", "Ekstasi", "25"},
                {"013/Pid.Sus/2024/PN Sby", "Candra Permana", "Sabu", "40"},
                {"014/Pid.Sus/2024/PN Sby", "Dimas Kurniawan", "Ganja", "19"},
                {"015/Pid.Sus/2024/PN Sby", "Eko Firmansyah", "Sabu", "34"},
                {"016/Pid.Sus/2024/PN Sby", "Farhan Akbar", "Ekstasi", "27"},
                {"017/Pid.Sus/2024/PN Sby", "Gilang Ramadhan", "Sabu", "50"},
                {"018/Pid.Sus/2024/PN Sby", "Hari Wibowo", "Ganja", "14"},
                {"019/Pid.Sus/2024/PN Sby", "Indra Gunawan", "Sabu", "44"},
                {"020/Pid.Sus/2024/PN Sby", "Joko Susilo", "Ekstasi", "23"},

                {"021/Pid.Sus/2024/PN Sby", "Kurniawan Adi", "Sabu", "38"},
                {"022/Pid.Sus/2024/PN Sby", "Lukman Hakim", "Ganja", "17"},
                {"023/Pid.Sus/2024/PN Sby", "Maman Suparman", "Sabu", "46"},
                {"024/Pid.Sus/2024/PN Sby", "Nanda Saputra", "Ekstasi", "29"},
                {"025/Pid.Sus/2024/PN Sby", "Oscar Wijaya", "Sabu", "32"},
                {"026/Pid.Sus/2024/PN Sby", "Pandu Pratama", "Ganja", "21"},
                {"027/Pid.Sus/2024/PN Sby", "Qomarudin", "Sabu", "52"},
                {"028/Pid.Sus/2024/PN Sby", "Rangga Saputra", "Ekstasi", "26"},
                {"029/Pid.Sus/2024/PN Sby", "Surya Nugraha", "Sabu", "35"},
                {"030/Pid.Sus/2024/PN Sby", "Taufik Hidayat", "Ganja", "18"},

                {"031/Pid.Sus/2024/PN Sby", "Umar Faruq", "Sabu", "41"},
                {"032/Pid.Sus/2024/PN Sby", "Vicky Prasetyo", "Ekstasi", "24"},
                {"033/Pid.Sus/2024/PN Sby", "Wahyu Setiawan", "Sabu", "37"},
                {"034/Pid.Sus/2024/PN Sby", "Yoga Firmansyah", "Ganja", "20"},
                {"035/Pid.Sus/2024/PN Sby", "Zainal Arifin", "Sabu", "49"},
                {"036/Pid.Sus/2024/PN Sby", "Asep Suhendar", "Ekstasi", "31"},
                {"037/Pid.Sus/2024/PN Sby", "Bambang Irawan", "Sabu", "45"},
                {"038/Pid.Sus/2024/PN Sby", "Cecep Hidayat", "Ganja", "16"},
                {"039/Pid.Sus/2024/PN Sby", "Dadang Koswara", "Sabu", "39"},
                {"040/Pid.Sus/2024/PN Sby", "Edi Saputra", "Ekstasi", "28"},

                {"041/Pid.Sus/2024/PN Sby", "Fikri Maulana", "Sabu", "33"},
                {"042/Pid.Sus/2024/PN Sby", "Galih Prakoso", "Ganja", "22"},
                {"043/Pid.Sus/2024/PN Sby", "Hasan Basri", "Sabu", "54"},
                {"044/Pid.Sus/2024/PN Sby", "Iqbal Ramadhan", "Ekstasi", "30"},
                {"045/Pid.Sus/2024/PN Sby", "Jamaludin", "Sabu", "43"},
                {"046/Pid.Sus/2024/PN Sby", "Kamaludin", "Ganja", "19"},
                {"047/Pid.Sus/2024/PN Sby", "Latif Hidayat", "Sabu", "47"},
                {"048/Pid.Sus/2024/PN Sby", "Miftah Farid", "Ekstasi", "27"},
                {"049/Pid.Sus/2024/PN Sby", "Nugraha Putra", "Sabu", "36"},
                {"050/Pid.Sus/2024/PN Sby", "Oki Pratama", "Ganja", "18"}
        };

        String[] daftarPengadilan = {
                "PN Surabaya",
                "PN Malang",
                "PN Sidoarjo",
                "PN Gresik",
                "PN Mojokerto"
        };

        for (int i = 0; i < dataAwal.length; i++) {
            String[] data = dataAwal[i];
            int vonis = Integer.parseInt(data[3]);

            double denda = 300000000 + ((i % 5) * 100000000);

            String[] dataLengkap = {
                    data[0],
                    daftarPengadilan[i % daftarPengadilan.length],
                    data[1],
                    data[2],
                    data[3],
                    String.valueOf(denda),
                    tentukanPeranDefault(vonis)
            };

            tambahPutusan(dataLengkap);
        }
    }
// Commit 1 oleh Ebby// Commit 2 oleh Ebby// Commit 3 oleh Ebby// Commit 4 oleh Ebby// Commit 5 oleh Ebby// Commit 6 oleh Ebby// Commit 7 oleh Ebby