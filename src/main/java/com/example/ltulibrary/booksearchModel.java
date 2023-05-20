package com.example.ltulibrary;

public class booksearchModel {
    int barcode_Bok;
    String bok_namn;
    int bok_Ar;
    String bok_Genre, kategori, bok_Forfattare;
    int hylla, Antal_Kopior_Inne;
    String ISBN;


    public booksearchModel(int barcode_Bok, String bok_namn, int bok_Ar, String bok_Genre, String kategori, String bok_Forfattare, int hylla, int Antal_Kopior_Inne, String ISBN) {
        this.barcode_Bok = barcode_Bok;
        this.bok_namn = bok_namn;
        this.bok_Ar = bok_Ar;
        this.bok_Genre = bok_Genre;
        this.kategori = kategori;
        this.bok_Forfattare = bok_Forfattare;
        this.hylla = hylla;
        this.Antal_Kopior_Inne = Antal_Kopior_Inne;
        this.ISBN = ISBN;
    }

    public int getBarcode_Bok() {
        return barcode_Bok;
    }

    public String getBok_namn() {
        return bok_namn;
    }

    public int getBok_Ar() {
        return bok_Ar;
    }

    public String getBok_Genre() {
        return bok_Genre;
    }

    public String getKategori() {
        return kategori;
    }

    public String getBok_Forfattare() {
        return bok_Forfattare;
    }

    public int getHylla() {
        return hylla;
    }

    public int getAntal_Kopior_Inne() {
        return Antal_Kopior_Inne;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setBarcode_Bok(int barcode_Bok) {
        this.barcode_Bok = barcode_Bok;
    }

    public void setBok_namn(String bok_namn) {
        this.bok_namn = bok_namn;
    }

    public void setBok_Ar(int bok_Ar) {
        this.bok_Ar = bok_Ar;
    }

    public void setBok_Genre(String bok_Genre) {
        this.bok_Genre = bok_Genre;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setBok_Forfattare(String bok_Forfattare) {
        this.bok_Forfattare = bok_Forfattare;
    }

    public void setHylla(int hylla) {
        this.hylla = hylla;
    }

    public void setAntal_Kopior_Inne(int antal_Kopior_Inne) {
        Antal_Kopior_Inne = antal_Kopior_Inne;
    }

    public void setISBN(String ISBN) {this.ISBN = ISBN;}

}