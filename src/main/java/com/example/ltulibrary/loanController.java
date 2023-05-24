package com.example.ltulibrary;

import java.util.List;

public class loanController {


    public loanController(List<booksearchModel> addedBook) {
        this.addedBook = addedBook;

    }
    private List<booksearchModel> addedBook;

    public List<booksearchModel> getAddedBook() {
        return addedBook;}
    }