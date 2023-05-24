package com.example.ltulibrary;

import java.sql.Date;

public class Receipt {
    private String customerName;
    private String loanedBook;
    private String loanedDVD;
    private Date dueDate;

    public Receipt(String customerName, String loanedBook, String loanedDVD, Date dueDate) {
        this.customerName = customerName;
        this.loanedBook = loanedBook;
        this.loanedDVD = loanedDVD;
        this.dueDate = dueDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getLoanedBook() {
        return loanedBook;
    }

    public String getLoanedDVD() {
        return loanedDVD;
    }

    public Date getDueDate() {
        return dueDate;
    }
}