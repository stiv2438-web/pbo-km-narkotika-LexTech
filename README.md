# LexTechKMS

Knowledge Management System Putusan Pengadilan Narkotika berbasis Java OOP dengan arsitektur MVC.

## Deskripsi Proyek

Project ini dibuat untuk memenuhi Tugas Besar Pemrograman Berorientasi Objek. Sistem ini digunakan untuk mengelola data putusan pengadilan narkotika, meliputi tambah data, tampilkan data, pencarian, filter, statistik, hapus data, sorting, dan ekspor laporan statistik.

## Arsitektur

Project menggunakan pola MVC:

- Model: Putusan, KnowledgeRepository, StatistikPutusan
- View: ConsoleView
- Controller: KnowledgeController
- Util: InputHandler
- App: Main

## Fitur

- Tambah data putusan
- Tampilkan semua putusan
- Cari berdasarkan nomor perkara
- Cari berdasarkan nama terdakwa
- Filter berdasarkan jenis narkotika
- Filter berdasarkan pengadilan
- Filter berdasarkan rentang vonis
- Statistik putusan
- Hapus data putusan
- Sorting berdasarkan vonis
- Export statistik ke file TXT

## Konsep OOP yang Digunakan

- Encapsulation
- Constructor
- Method overloading
- Static field dan static method
- Inheritance
- Interface
- Method overriding
- ArrayList
- Exception handling

## Cara Menjalankan

1. Buka project menggunakan IntelliJ IDEA.
2. Pastikan JDK 11 atau lebih baru sudah terpasang.
3. Jalankan file:

```text
src/app/Main.java

## Anggota Kelompok

Kelompok: LexTech

- Stiven Cahyono - 202510370110100 - Model Developer
- Earland Khansadevlin Herdian - 202510370110098 - View Developer 
- Ebby Regista Sari Hatuina - 202510370110068 - Controller Engineer 