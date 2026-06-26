package util;

import java.util.Scanner;

/**
 * Utility class untuk membaca dan memvalidasi
 * input dari pengguna melalui console.
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