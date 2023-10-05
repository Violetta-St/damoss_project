package com.example.vilabfx.controllers;

import com.example.vilabfx.models.Faculty;
import com.example.vilabfx.models.HeadOf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.regex.Pattern;

public class FacultyEditDialogController {

    @FXML
    private TextField facultyNameField;
    @FXML
    private TextField facultyShortNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField  lastNameField;
    @FXML
    private TextField  patronymicField;
    @FXML
    private ChoiceBox<String> academicDegreeField;


    private Stage dialogStage;
    private Faculty faculty;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        academicDegreeField.setItems(FXCollections
                .observableArrayList(Arrays.asList("Ассистент", "Старший преподаватель", "Доцент", "Профессор")));
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param faculty
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;

        facultyNameField.setText(faculty.getFacultyName());
        facultyShortNameField.setText(faculty.getFacultyShortName());
        phoneField.setText(faculty.getPhone());
        firstNameField.setText(faculty.getFacultyHead().getFirstName());
        lastNameField.setText(faculty.getFacultyHead().getLastName());
        patronymicField.setText(faculty.getFacultyHead().getPatronymic());
        String degree;
        switch (faculty.getFacultyHead().getAcademicDegree()){
            // "Ассистент", "Старший преподаватель", "Доцент", "Профессор"
            case assistant -> degree = "Ассистент";
            case seniorTeacher -> degree = "Старший преподаватель";
            case docent -> degree = "Доцент";
            default -> degree = "Профессор";
        }
        academicDegreeField.setValue(degree);
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            faculty.setFacultyName(facultyNameField.getText());
            faculty.setFacultyShortName(facultyShortNameField.getText());
            faculty.setPhone(phoneField.getText());

            HeadOf.academicDegree degree;
            switch (academicDegreeField.getValue()){
                case "Ассистент" -> degree = HeadOf.academicDegree.assistant;
                case "Старший преподаватель" -> degree = HeadOf.academicDegree.seniorTeacher;
                case "Доцент" -> degree = HeadOf.academicDegree.docent;
                default -> degree = HeadOf.academicDegree.professor;
            }
            faculty.setFacultyHead(new Faculty.facultyHead(firstNameField.getText(), lastNameField.getText(),
                    patronymicField.getText(), degree));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (facultyNameField.getText() == null || facultyNameField.getText().length() == 0) {
            errorMessage += "No valid faculty name!\n";
        }
        if (facultyShortNameField.getText() == null || facultyShortNameField.getText().length() == 0) {
            errorMessage += "No valid faculty short name!\n";
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "No valid phone!\n";
        }
        else{
            String regexPattern = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
            boolean validEmail = Pattern.compile(regexPattern).matcher(phoneField.getText()).matches();

            if (!validEmail)
                errorMessage += "No valid email!\n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (patronymicField.getText() == null || patronymicField.getText().length() == 0) {
            errorMessage += "No valid patronymic!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
