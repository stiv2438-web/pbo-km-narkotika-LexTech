package model;

/**
 * Class induk yang menyimpan data umum entitas hukum.
 * Saat ini class ini menyimpan nomor perkara yang akan
 * diwariskan ke class Putusan.
 */
public class EntitasHukum {

    private String nomorPerkara;

    /**
     * Constructor kosong.
     */
    public EntitasHukum() {
    }

    /**
     * Constructor dengan parameter nomor perkara.
     *
     * @param nomorPerkara nomor perkara putusan
     */
    public EntitasHukum(String nomorPerkara) {
        this.nomorPerkara = nomorPerkara;
    }

    /**
     * Mengambil nomor perkara.
     *
     * @return nomor perkara
     */
    public String getNomorPerkara() {
        return nomorPerkara;
    }

    /**
     * Mengubah nomor perkara.
     *
     * @param nomorPerkara nomor perkara baru
     */
    public void setNomorPerkara(String nomorPerkara) {
        this.nomorPerkara = nomorPerkara;
    }
}