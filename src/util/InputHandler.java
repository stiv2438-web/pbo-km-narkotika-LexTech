package util;

import java.util.Scanner;

/**
 * Class InputHandler digunakan untuk memvalidasi input pengguna
 * agar program tidak crash ketika input tidak sesuai.
 */
public class InputHandler {

    public static int validasiInt(String pesan, Scanner sc) {
        while (true) {
            try {
                System.out.print(pesan);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static String validasiString(String pesan, Scanner sc) {
        while (true) {
            System.out.print(pesan);
            String input = sc.nextLine();

            if (!input.trim().isEmpty()) {
                return input;
            }

            System.out.println("Input tidak boleh kosong!");
        }
    }
}