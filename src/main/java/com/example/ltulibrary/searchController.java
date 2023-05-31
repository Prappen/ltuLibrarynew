package com.example.ltulibrary;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class searchController implements Initializable {

    private List<booksearchModel> cartBooks = new ArrayList<>();
    private List<dvdsearchModel> cartDVDs = new ArrayList<>();
    @FXML
    private TableView<dvdsearchModel> dvdTableView;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdBarcodeColumn;

    @FXML
    private TableColumn<dvdsearchModel, String> dvdNameColumn;

    @FXML
    private TableColumn<dvdsearchModel, String> dvdGenreColumn;

    @FXML
    private TableColumn<dvdsearchModel, String> dvdDirectorColumn;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdAgeRatingColumn;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdShelfColumn;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdCopiesColumn;

    @FXML
    private TableView<booksearchModel> bookTableView;

    @FXML
    private TableColumn<booksearchModel, Integer> bookBarcodeColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookNameColumn;

    @FXML
    private TableColumn<booksearchModel, Integer> bookYearColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookGenreColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookCategoryColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookAuthorColumn;

    @FXML
    private TableColumn<booksearchModel, Integer> bookShelfColumn;

    @FXML
    private TableColumn<booksearchModel, Integer> bookCopiesColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookISBNColumn;

    @FXML
    private TextField keywordsField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnection db = new databaseConnection();

        try {
            db.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String dvdViewQuery = "SELECT barcode_DVD, dvd_Namn, dvd_Ar, dvd_Genre, dvd_Regissor, aldersgrans, Hylla, Antal_Kopior_Inne FROM DVD";

        try {
            PreparedStatement dvdStatement = db.conn.prepareStatement(dvdViewQuery);
            ResultSet dvdResultSet = dvdStatement.executeQuery();

            while (dvdResultSet.next()) {
                int barcode = dvdResultSet.getInt("barcode_DVD");
                String name = dvdResultSet.getString("dvd_Namn");
                int year = dvdResultSet.getInt("dvd_Ar");
                String genre = dvdResultSet.getString("dvd_Genre");
                String director = dvdResultSet.getString("dvd_Regissor");
                int ageRating = dvdResultSet.getInt("aldersgrans");
                int shelf = dvdResultSet.getInt("Hylla");
                int copies = dvdResultSet.getInt("Antal_Kopior_Inne");

                if (dvdTableView != null) {
                    dvdsearchModel dvd = new dvdsearchModel(barcode, name, year, genre, director, ageRating, shelf, copies);
                    dvdTableView.getItems().add(dvd);
                } else {
                    System.out.println("dvdTableView is null. Unable to add DVD to the table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String bookViewQuery = "SELECT barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, Hylla, Antal_Kopior_Inne, ISBN FROM Bok";

        try {
            PreparedStatement bookStatement = db.conn.prepareStatement(bookViewQuery);
            ResultSet bookResultSet = bookStatement.executeQuery();

            while (bookResultSet.next()) {
                int barcode = bookResultSet.getInt("barcode_Bok");
                String name = bookResultSet.getString("bok_Namn");
                int year = bookResultSet.getInt("bok_Ar");
                String genre = bookResultSet.getString("bok_Genre");
                String category = bookResultSet.getString("kategori");
                String author = bookResultSet.getString("bok_Forfattare");
                int shelf = bookResultSet.getInt("Hylla");
                int copies = bookResultSet.getInt("Antal_Kopior_Inne");
                String isbn = bookResultSet.getString("ISBN");

                if (bookTableView != null) {
                    booksearchModel book = new booksearchModel(barcode, name, year, genre, category, author, shelf, copies, isbn);
                    bookTableView.getItems().add(book);
                } else {
                    System.out.println("bookTableView is null. Unable to add book to the table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Initialize DVD table columns
        dvdNameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        dvdGenreColumn.setCellValueFactory(data -> data.getValue().genreProperty());
        dvdDirectorColumn.setCellValueFactory(data -> data.getValue().directorProperty());
        dvdAgeRatingColumn.setCellValueFactory(data -> data.getValue().ageRatingProperty());
        dvdShelfColumn.setCellValueFactory(data -> data.getValue().shelfProperty());
        dvdCopiesColumn.setCellValueFactory(data -> data.getValue().copiesProperty());

        // Initialize Book table columns
        bookNameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        bookYearColumn.setCellValueFactory(data -> data.getValue().yearProperty());
        bookGenreColumn.setCellValueFactory(data -> data.getValue().genreProperty());
        bookCategoryColumn.setCellValueFactory(data -> data.getValue().kategoriProperty());
        bookAuthorColumn.setCellValueFactory(data -> data.getValue().authorProperty());
        bookShelfColumn.setCellValueFactory(data -> data.getValue().shelfProperty());
        bookCopiesColumn.setCellValueFactory(data -> data.getValue().copiesProperty());
        bookISBNColumn.setCellValueFactory(data -> data.getValue().isbnProperty());

        // Create filtered lists for books and DVDs
        FilteredList<booksearchModel> bookFilteredData = new FilteredList<>(bookTableView.getItems(), b -> true);
        FilteredList<dvdsearchModel> dvdFilteredData = new FilteredList<>(dvdTableView.getItems(), d -> true);

        // Bind the sorted lists to the table views
        SortedList<booksearchModel> sortedBookData = new SortedList<>(bookFilteredData);
        SortedList<dvdsearchModel> sortedDVDData = new SortedList<>(dvdFilteredData);
        sortedBookData.comparatorProperty().bind(bookTableView.comparatorProperty());
        sortedDVDData.comparatorProperty().bind(dvdTableView.comparatorProperty());
        bookTableView.setItems(sortedBookData);
        dvdTableView.setItems(sortedDVDData);

        // Listen to changes in the search keywords text field
        keywordsField.textProperty().addListener((observable, oldValue, newValue) -> {
            String keywords = newValue.toLowerCase();
            bookFilteredData.setPredicate(booksearchModel -> {
                if (keywords == null || keywords.isBlank()) {
                    return true;
                }
                return booksearchModel.getBok_namn().toLowerCase().contains(keywords)
                        || booksearchModel.getBok_Forfattare().toLowerCase().contains(keywords)
                        || booksearchModel.getBok_Genre().toLowerCase().contains(keywords)
                        || booksearchModel.getKategori().toLowerCase().contains(keywords)
                        || booksearchModel.getISBN().toLowerCase().contains(keywords);
            });
            dvdFilteredData.setPredicate(dvdsearchModel -> {
                if (keywords == null || keywords.isBlank()) {
                    return true;
                }
                return dvdsearchModel.getDvd_Namn().toLowerCase().contains(keywords)
                        || dvdsearchModel.getDvd_Regissor().toLowerCase().contains(keywords)
                        || dvdsearchModel.getDvd_Genre().toLowerCase().contains(keywords)
                        || dvdsearchModel.getDvd_Namn().toLowerCase().contains(keywords);
            });
        });
    }

    private String username;
    private String password;

    public int getKund_Id() {
        return kund_Id;
    }

    private int kund_Id;
    private String name_F;

    public String getName_F() {
        return name_F;
    }

    public String getName_E() {
        return name_E;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getKund_Typ() {
        return kund_Typ;
    }

    private String name_E;
    private String email;
    private String phone;
    private String address;
    private int age;
    private String kund_Typ;

    public void setUserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUserDetails(int kund_Id, String name_F, String name_E, String email, String phone, String address, int age, String kund_Typ) {
        this.kund_Id = kund_Id;
        this.name_F = name_F;
        this.name_E = name_E;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.kund_Typ = kund_Typ;

    }

    @FXML
    public void AddbookAction(ActionEvent actionEvent) {
        // Get the selected item from the bookTableView or dvdTableView
        booksearchModel selectedBook = bookTableView.getSelectionModel().getSelectedItem();
        dvdsearchModel selectedDVD = dvdTableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Add the selected book to the cartBooks list
            if (checkLoanLimit(kund_Typ)) {
                cartBooks.add(selectedBook);
                System.out.println("Book added");
            } else {
                System.out.println("You have reached the maximum number of loans for your customer type.");
            }
        }

        if (selectedDVD != null) {
            int ageRatingProperty = selectedDVD.getAldersgrans();
            int userAge = age;

            if (userAge >= ageRatingProperty) {
                // Check loan limit for DVD
                if (checkLoanLimit(kund_Typ)) {
                    cartDVDs.add(selectedDVD);
                    System.out.println("DVD added");
                    // Use the DVD name as needed in your application (e.g., add it to the receipt)
                } else {
                    System.out.println("You have reached the maximum number of loans for your customer type.");
                }
            } else {
                System.out.println("You are not old enough to rent this DVD.");
            }
        }
    }

    // Method to check loan limit based on the customer type
    private boolean checkLoanLimit(String kund_Typ) {
        int loanLimit = 0;

        // Set the loan limit based on the customer type
        if (kund_Typ.equalsIgnoreCase("student")) {
            loanLimit = 10;
        } else if (kund_Typ.equalsIgnoreCase("scientist") || kund_Typ.equalsIgnoreCase("teacher")) {
            loanLimit = 20;
        }

        // Retrieve the number of loans for the customer from the database
        int numLoans = getNumLoans(kund_Typ);

        // Check if the number of loans exceeds the loan limit
        return numLoans < loanLimit;
    }

    // Method to retrieve the number of loans for a customer from the database
    private int getNumLoans(String kund_Typ) {
        int numLoans = 0;

        try {
            // Establish the database connection
            databaseConnection.connect();

            // Query to retrieve the number of loans for the customer type
            String query = "SELECT COUNT(*) FROM lan JOIN kund ON lan.kund_Id = kund.kund_Id WHERE kund.kund_Typ = ?";

            // Create the prepared statement
            PreparedStatement pstmt = databaseConnection.conn.prepareStatement(query);
            pstmt.setString(1, getKund_Typ());

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Retrieve the result
            if (rs.next()) {
                numLoans = rs.getInt(1);
            }

            // Close the result set, prepared statement, and database connection
            rs.close();
            pstmt.close();
            databaseConnection.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numLoans;
    }

    public void checkout(ActionEvent actionEvent) throws SQLException, IOException {
        // Check if the borrower has reached the maximum number of items allowed


        booksearchModel selectedBook = null;
        dvdsearchModel selectedDVD = null;

        if (cartBooks != null && !cartBooks.isEmpty()) {
            selectedBook = cartBooks.get(0);
        }

        if (cartDVDs != null && !cartDVDs.isEmpty()) {
            selectedDVD = cartDVDs.get(0);
        }

        Calendar returnDate = null;
        PreparedStatement bokLanInsertStatement = null;

        try {

            String lanInsertQuery = "INSERT INTO Lan (kund_Id) VALUES (?)";
            databaseConnection db = new databaseConnection();
            PreparedStatement lanInsertStatement = db.conn.prepareStatement(lanInsertQuery, Statement.RETURN_GENERATED_KEYS);
            lanInsertStatement.setInt(1, kund_Id);
            lanInsertStatement.executeUpdate();

            int lanId;
            try (ResultSet generatedKeys = lanInsertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lanId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated lan_Id.");
                }
            }

            // Calculate the return date based on the category of the book
            returnDate = Calendar.getInstance();
            if (selectedBook.getKategori().equalsIgnoreCase("skolbok")) {
                returnDate.add(Calendar.DAY_OF_MONTH, 14);
            } else if (selectedBook.getKategori().equalsIgnoreCase("skönlitterär")) {
                returnDate.add(Calendar.MONTH, 1);
            }

            // Step 2: Insert a new row into the Bok_Lan table
            String bokLanInsertQuery = "INSERT INTO Bok_Lan (datum_Utlanad, bok_Status, barcode_Bok, lan_Id, return_Date) VALUES (?, ?, ?, ?, ?)";
            bokLanInsertStatement = db.conn.prepareStatement(bokLanInsertQuery);
            bokLanInsertStatement.setDate(1, getCurrentDate());
            bokLanInsertStatement.setString(2, "Loaned");
            bokLanInsertStatement.setInt(3, selectedBook.getBarcode_Bok());
            bokLanInsertStatement.setInt(4, lanId);
            bokLanInsertStatement.setDate(5, new Date(returnDate.getTimeInMillis()));
            bokLanInsertStatement.executeUpdate();

            if (selectedDVD != null) {
                // Calculate the return date based on the category of the DVD
                returnDate = Calendar.getInstance();
                returnDate.add(Calendar.DAY_OF_MONTH, 7);

                // Step 3: Insert a new row into the DVD_Lan table, referencing the same Lan
                String dvdLanInsertQuery = "INSERT INTO DVD_Lan (dvddatum_Utlanad, dvd_Status, barcode_DVD, lan_Id, dvdreturn_Date) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement dvdLanInsertStatement = db.conn.prepareStatement(dvdLanInsertQuery);
                dvdLanInsertStatement.setDate(1, getCurrentDate());
                dvdLanInsertStatement.setString(2, "Loaned");
                dvdLanInsertStatement.setInt(3, selectedDVD.getBarcode_DVD());
                dvdLanInsertStatement.setInt(4, lanId);
                dvdLanInsertStatement.setDate(5, new Date(returnDate.getTimeInMillis()));
                dvdLanInsertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load the Checkout.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Checkout.fxml"));
        Parent checkoutRoot = loader.load();

        // Create a new scene with the loaded FXML file
        Scene checkoutScene = new Scene(checkoutRoot);

        // Get the current stage
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(checkoutScene);
        currentStage.show();
    }



    private Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        return new Date(currentDate.getTime());
    }

    @FXML
    public void Cancelsearch(ActionEvent actionEvent) {
        // Code for canceling the search action
    }
}