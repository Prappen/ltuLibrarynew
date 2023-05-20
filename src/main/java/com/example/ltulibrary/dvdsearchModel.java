package com.example.ltulibrary;

public class dvdsearchModel {
    int barcode_DVD;
    String dvd_Namn;
    String dvd_Genre;
    String Dvd_regigör;
    int aldersgrans,hylla,Antal_Kopior_Inne;
    public dvdsearchModel(int barcode_DVD, String dvd_Namn, String dvd_Genre, String dvd_regigör, int aldersgrans, int hylla, int antal_Kopior_Inne) {
        this.barcode_DVD = barcode_DVD;
        this.dvd_Namn = dvd_Namn;
        this.dvd_Genre = dvd_Genre;
        Dvd_regigör = dvd_regigör;
        this.aldersgrans = aldersgrans;
        this.hylla = hylla;
        Antal_Kopior_Inne = antal_Kopior_Inne;
    }

    public int getBarcode_DVD() {
        return barcode_DVD;
    }

    public String getDvd_Namn() {
        return dvd_Namn;
    }

    public String getDvd_Genre() {
        return dvd_Genre;
    }

    public String getDvd_regigör() {
        return Dvd_regigör;
    }

    public int getAldersgrans() {
        return aldersgrans;
    }

    public int getHylla() {
        return hylla;
    }

    public int getAntal_Kopior_Inne() {
        return Antal_Kopior_Inne;
    }



    public void setBarcode_DVD(int barcode_DVD) {
        this.barcode_DVD = barcode_DVD;
    }

    public void setDvd_Namn(String dvd_Namn) {
        this.dvd_Namn = dvd_Namn;
    }

    public void setDvd_Genre(String dvd_Genre) {
        this.dvd_Genre = dvd_Genre;
    }

    public void setDvd_regigör(String dvd_regigör) {
        Dvd_regigör = dvd_regigör;
    }

    public void setAldersgrans(int aldersgrans) {
        this.aldersgrans = aldersgrans;
    }

    public void setHylla(int hylla) {
        this.hylla = hylla;
    }

    public void setAntal_Kopior_Inne(int antal_Kopior_Inne) {
        Antal_Kopior_Inne = antal_Kopior_Inne;
    }




}
