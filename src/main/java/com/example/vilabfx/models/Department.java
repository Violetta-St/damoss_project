package com.example.vilabfx.models;

public class Department {
    private Faculty faculty;
    private String departmentName;
    private String departmentShortName;
    private String phone;
    private departmentHead departmentHead;
    public static class departmentHead extends HeadOf{

        public departmentHead(String firstName, String lastName, String patronymic, HeadOf.academicDegree academicDegree) {
            super(firstName, lastName, patronymic, academicDegree);
        }
    }

    public Faculty getFaculty() {return faculty;}

    public void setFaculty(Faculty faculty) {this.faculty = faculty;}

    public String getDepartmentName() {return departmentName;}

    public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}

    public String getDepartmentShortName() {return departmentShortName;}

    public void setDepartmentShortName(String departmentShortName) {this.departmentShortName = departmentShortName;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public Department.departmentHead getDepartmentHead() {return departmentHead;}

    public void setDepartmentHead(Department.departmentHead departmentHead) {this.departmentHead = departmentHead;}
}
