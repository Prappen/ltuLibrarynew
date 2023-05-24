package com.example.ltulibrary;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class booksearchModel {
    private final SimpleIntegerProperty barcode_Bok;
    private final SimpleStringProperty bok_namn;
    private final SimpleIntegerProperty bok_Ar;
    private final SimpleStringProperty bok_Genre;
    private final SimpleStringProperty kategori;
    private final SimpleStringProperty bok_Forfattare;
    private final SimpleIntegerProperty hylla;
    private final SimpleIntegerProperty Antal_Kopior_Inne;
    private final SimpleStringProperty ISBN;

    public booksearchModel(int barcode_Bok, String bok_namn, int bok_Ar, String bok_Genre, String kategori, String bok_Forfattare, int hylla, int Antal_Kopior_Inne, String ISBN) {
        this.barcode_Bok = new SimpleIntegerProperty(barcode_Bok);
        this.bok_namn = new SimpleStringProperty(bok_namn);
        this.bok_Ar = new SimpleIntegerProperty(bok_Ar);
        this.bok_Genre = new SimpleStringProperty(bok_Genre);
        this.kategori = new SimpleStringProperty(kategori);
        this.bok_Forfattare = new SimpleStringProperty(bok_Forfattare);
        this.hylla = new SimpleIntegerProperty(hylla);
        this.Antal_Kopior_Inne = new SimpleIntegerProperty(Antal_Kopior_Inne);
        this.ISBN = new SimpleStringProperty(ISBN);
    }

    public int getBarcode_Bok() {
        return barcode_Bok.get();
    }

    public String getBok_namn() {
        return bok_namn.get();
    }

    public int getBok_Ar() {
        return bok_Ar.get();
    }

    public String getBok_Genre() {
        return bok_Genre.get();
    }

    public String getKategori() {
        return kategori.get();
    }

    public String getBok_Forfattare() {
        return bok_Forfattare.get();
    }

    public int getHylla() {
        return hylla.get();
    }

    public int getAntal_Kopior_Inne() {
        return Antal_Kopior_Inne.get();
    }

    public String getISBN() {
        return ISBN.get();
    }

    public void setBarcode_Bok(int barcode_Bok) {
        this.barcode_Bok.set(barcode_Bok);
    }

    public void setBok_namn(String bok_namn) {
        this.bok_namn.set(bok_namn);
    }

    public void setBok_Ar(int bok_Ar) {
        this.bok_Ar.set(bok_Ar);
    }

    public void setBok_Genre(String bok_Genre) {
        this.bok_Genre.set(bok_Genre);
    }

    public void setKategori(String kategori) {
        this.kategori.set(kategori);
    }

    public void setBok_Forfattare(String bok_Forfattare) {
        this.bok_Forfattare.set(bok_Forfattare);
    }

    public void setHylla(int hylla) {
        this.hylla.set(hylla);
    }

    public void setAntal_Kopior_Inne(int antal_Kopior_Inne) {
        this.Antal_Kopior_Inne.set(antal_Kopior_Inne);
    }

    public void setISBN(String ISBN) {
        this.ISBN.set(ISBN);
    }

    public  ObservableValue<String> kategoriProperty() {
        return kategori;
    }

    public ObservableValue<Integer> barcodeProperty() {
        return barcode_Bok.asObject();
    }

    public ObservableValue<String> nameProperty() {
        return bok_namn;
    }

    public ObservableValue<Integer> yearProperty() {
        return bok_Ar.asObject();
    }

    public ObservableValue<String> genreProperty() {
        return bok_Genre;
    }

    public ObservableValue<String> authorProperty() {
        return bok_Forfattare;
    }

    public ObservableValue<Integer> shelfProperty() {
        return hylla.asObject();
    }

    public ObservableValue<Integer> copiesProperty() {
        return Antal_Kopior_Inne.asObject();
    }

    public ObservableValue<String> isbnProperty() {
        return ISBN;
    }
}