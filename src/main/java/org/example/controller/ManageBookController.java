package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.model.Book;
import org.example.model.BookLocation;
import org.example.model.Adm;
import org.example.util.AdmHolder;

public class ManageBookController {

    @FXML
    private TextField auhtorField;

    @FXML
    private Button buttonConfirm;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField dispField;

    @FXML
    private TextField hallField;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField publishField;

    @FXML
    private AnchorPane sceneManageBook;

    @FXML
    private TextField sectionField;

    @FXML
    private TextField shelfField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField totalField;

    @FXML
    private TextField yearField;

    @FXML
    void confirmAction(ActionEvent event) {
        try {
            Adm adm = AdmHolder.getInstance().getAdm();

            String isbn = isbnField.getText();
            String title = titleField.getText();
            String author = auhtorField.getText();
            String publishingCompany = publishField.getText();
            int yearPublication = Integer.parseInt(yearField.getText());
            String category = categoryField.getText();
            String shelf = shelfField.getText();
            String hall = hallField.getText();
            String section = sectionField.getText();
            BookLocation location = new BookLocation(shelf, hall, section);
            int quantity = Integer.parseInt(totalField.getText());

            Book book = new Book(isbn, title, author, publishingCompany, yearPublication, category, location, quantity);
            adm.updateBook(book);

            AlertMessageController alertMessageController = new AlertMessageController();
            alertMessageController.setAlert("Edição concluida com sucesso!");
        } catch (Exception e) {
            AlertMessageController alertMessageController = new AlertMessageController();
            alertMessageController.setAlert("Não foi possível concluir edição.");
            //throw new RuntimeException(e);
        }
    }

}
