package app;

import view.ConsoleView;

/**
 * Entry point aplikasi LexTechKMS.
 * Class ini menjalankan aplikasi dan
 * menginisialisasi komponen utama sistem.
 */
public class Main {

    public static void main(String[] args) {

        ConsoleView view = new ConsoleView();
        view.mulai();

    }
}