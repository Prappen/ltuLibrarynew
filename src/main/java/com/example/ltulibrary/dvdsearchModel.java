package com.example.ltulibrary;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class dvdsearchModel {
    private final SimpleIntegerProperty barcode_DVD;
    private final SimpleStringProperty dvd_Namn;
    private final SimpleIntegerProperty dvd_Ar;
    private final SimpleStringProperty dvd_Genre;
    private final SimpleStringProperty dvd_Regissor;
    private final SimpleIntegerProperty aldersgrans;
    private final SimpleIntegerProperty hylla;
    private final SimpleIntegerProperty Antal_Kopior_Inne;

    public dvdsearchModel(int barcode_DVD, String dvd_Namn, int dvd_Ar, String dvd_Genre, String dvd_Regissor, int aldersgrans, int hylla, int antal_Kopior_Inne) {
        this.barcode_DVD = new SimpleIntegerProperty(barcode_DVD);
        this.dvd_Namn = new SimpleStringProperty(dvd_Namn);
        this.dvd_Ar = new SimpleIntegerProperty(dvd_Ar);
        this.dvd_Genre = new SimpleStringProperty(dvd_Genre);
        this.dvd_Regissor = new SimpleStringProperty(dvd_Regissor);
        this.aldersgrans = new SimpleIntegerProperty(aldersgrans);
        this.hylla = new SimpleIntegerProperty(hylla);
        this.Antal_Kopior_Inne = new SimpleIntegerProperty(antal_Kopior_Inne);
    }

    public int getBarcode_DVD() {
        return barcode_DVD.get();
    }

    public String getDvd_Namn() {
        return dvd_Namn.get();
    }

    public int getDvd_Ar() {
        return dvd_Ar.get();
    }

    public String getDvd_Genre() {
        return dvd_Genre.get();
    }

    public String getDvd_Regissor() {
        return dvd_Regissor.get();
    }

    public int getAldersgrans() {
        return aldersgrans.get();
    }

    public int getHylla() {
        return hylla.get();
    }

    public int getAntal_Kopior_Inne() {
        return Antal_Kopior_Inne.get();
    }

    public void setBarcode_DVD(int barcode_DVD) {
        this.barcode_DVD.set(barcode_DVD);
    }

    public void setDvd_Namn(String dvd_Namn) {
        this.dvd_Namn.set(dvd_Namn);
    }

    public void setDvd_Ar(int dvd_Ar) {
        this.dvd_Ar.set(dvd_Ar);
    }

    public void setDvd_Genre(String dvd_Genre) {
        this.dvd_Genre.set(dvd_Genre);
    }

    public void setDvd_Regissor(String dvd_Regissor) {
        this.dvd_Regissor.set(dvd_Regissor);
    }

    public void setAldersgrans(int aldersgrans) {
        this.aldersgrans.set(aldersgrans);
    }

    public void setHylla(int hylla) {
        this.hylla.set(hylla);
    }

    public void setAntal_Kopior_Inne(int antal_Kopior_Inne) {
        this.Antal_Kopior_Inne.set(antal_Kopior_Inne);
    }

    public ObservableValue<Integer> barcodeProperty() {
        return barcode_DVD.asObject();
    }

    public ObservableValue<String> nameProperty() {
        return dvd_Namn;
    }

    public ObservableValue<Integer> yearProperty() {
        return dvd_Ar.asObject();
    }

    public ObservableValue<String> genreProperty() {
        return dvd_Genre;
    }

    public ObservableValue<String> directorProperty() {
        return dvd_Regissor;
    }

    public ObservableValue<Integer> ageRatingProperty() {
        return aldersgrans.asObject();
    }

    public ObservableValue<Integer> shelfProperty() {
        return hylla.asObject();
    }

    public ObservableValue<Integer> copiesProperty() {
        return Antal_Kopior_Inne.asObject();
    }
}
