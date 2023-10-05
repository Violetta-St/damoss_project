package com.example.vilabfx.models;

import javafx.beans.property.*;

public class Faculty {
    private StringProperty  facultyName;
    private StringProperty facultyShortName;
    private StringProperty  phone;
    private ObjectProperty<facultyHead>  facultyHead;
    public static class facultyHead extends HeadOf{

        public facultyHead(String firstName, String lastName, String patronymic, HeadOf.academicDegree academicDegree) {
            super(firstName, lastName, patronymic, academicDegree);
        }
    }


    public String getFacultyName() {return facultyName.get();}

    public void setFacultyName(String facultyName) {this.facultyName.set(facultyName);}

    public StringProperty facultyNameProperty() {return facultyName;}

    public String getFacultyShortName() {return facultyShortName.get();}

    public void setFacultyShortName(String facultyShortName) {this.facultyShortName.set(facultyShortName);}
    public StringProperty facultyShortNameProperty() {return facultyShortName;}

    public String getPhone() {return phone.get();}

    public void setPhone(String phone) {this.phone.set(phone);}
    public StringProperty phoneProperty() {return phone;}
    public Faculty.facultyHead getFacultyHead() {return facultyHead.get();}

    public void setFacultyHead(Faculty.facultyHead facultyHead) {this.facultyHead.set(facultyHead);}
    public ObjectProperty<Faculty.facultyHead> facultyHeadProperty() {return facultyHead;}

    public Faculty(String facultyName, String facultyShortName, String phone, Faculty.facultyHead facultyHead) {
        this.facultyName = new SimpleStringProperty(facultyName);
        this.facultyShortName = new SimpleStringProperty(facultyShortName);
        this.phone = new SimpleStringProperty(phone) ;
        this.facultyHead = new SimpleObjectProperty<>(facultyHead);
    }
}
