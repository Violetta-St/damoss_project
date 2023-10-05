package com.example.vilabfx.controllers;

import com.example.vilabfx.MainApp;
import com.example.vilabfx.models.Faculty;
import com.example.vilabfx.models.HeadOf;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FacultyController {
    @FXML
    private TableView<Faculty> facultyTable;
    @FXML
    private TableColumn<Faculty, String> facultyNameColumn;
    @FXML
    private TableColumn<Faculty, String> facultyHeadColumn;
    @FXML
    private Label facultyNameLabel;
    @FXML
    private Label facultyShortNameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label headOfFacultyLabel;
    private MainApp mainApp;

    public FacultyController() {}
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        facultyNameColumn.setCellValueFactory(cellData -> cellData.getValue().facultyShortNameProperty());
        facultyHeadColumn.setCellValueFactory(cellData -> cellData.getValue().facultyHeadProperty().asString());

        showFacultyDetails(null);

        facultyTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue)-> showFacultyDetails(newValue));
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        facultyTable.setItems(mainApp.getFacultyData());
    }
    private void showFacultyDetails(Faculty faculty){
        if (faculty != null){
            facultyNameLabel.setText(faculty.getFacultyName());
            facultyShortNameLabel.setText(faculty.getFacultyShortName());
            phoneLabel.setText(faculty.getPhone());
            headOfFacultyLabel.setText(faculty.getFacultyHead().toString());
        }
        else{
            facultyNameLabel.setText("");
            facultyShortNameLabel.setText("");
            phoneLabel.setText("");
            headOfFacultyLabel.setText("");
        }

    }

    @FXML
    private void handleDeleteFaculty() {
        int selectedIndex = facultyTable.getSelectionModel().getSelectedIndex();
        try{
            facultyTable.getItems().remove(selectedIndex);
        }
        catch (IndexOutOfBoundsException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Author Selected");
            alert.setContentText("Please select an author in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewFaculty() {
        Faculty tempAuthor = new Faculty("", "", "",
                new Faculty.facultyHead("", "", "", HeadOf.academicDegree.professor));
        boolean okClicked = mainApp.showEditDialog(tempAuthor);
        if (okClicked) {
            mainApp.getFacultyData().add(tempAuthor);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditFaculty() {
        Faculty selectedAuthor = facultyTable.getSelectionModel().getSelectedItem();
        if (selectedAuthor != null) {
            boolean okClicked = mainApp.showEditDialog(selectedAuthor);
            if (okClicked) {
                showFacultyDetails(selectedAuthor);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
