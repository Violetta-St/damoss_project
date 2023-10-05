package com.example.vilabfx;

import java.io.IOException;

import com.example.vilabfx.controllers.DepartmentEditDialogController;
import com.example.vilabfx.controllers.FacultyController;
import com.example.vilabfx.controllers.FacultyEditDialogController;
import com.example.vilabfx.models.Department;
import com.example.vilabfx.models.Faculty;
import com.example.vilabfx.models.HeadOf;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private final ObservableList<Faculty> facultyData = FXCollections.observableArrayList();
    private final ObservableList<Department> departmentData = FXCollections.observableArrayList();

    public MainApp() {
        facultyData.add(new Faculty("Полное название факультета", "ПНФ", "+78005556732",
                new Faculty.facultyHead("Имя", "Фамилия", "Отчество", HeadOf.academicDegree.professor)));
        facultyData.add(new Faculty( "Full Faculty Name", "FFN", "+78475556732",
                new Faculty.facultyHead("Firstname", "Lastname", "Patronymic", HeadOf.academicDegree.docent)));
        facultyData.add(new Faculty( "Компьютерных Технологий и Информационной Безопасности", "КТиИБ", "+78535556732",
                new Faculty.facultyHead("Евгений", "Тищенко", "Николаевич", HeadOf.academicDegree.professor)));
    }
    public ObservableList<Faculty> getFacultyData() {return facultyData;}
    public ObservableList<Department> getDepartmentData(){return departmentData;}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UniversityApp");

        initRootLayout();

        showFacultyOverview();
    }

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showFacultyOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/FacultyOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);

            FacultyController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showDepartmentOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/DepartmentOverview.fxml"));
            AnchorPane blogOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(blogOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  boolean showEditDialog(Faculty faculty){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/FacultyEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Faculty");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            FacultyEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFaculty(faculty);
            dialogStage.showAndWait();
            return controller.isOkClicked();

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
