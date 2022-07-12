package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import view.TM.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Hasitha Lakshan
 * Project :JDBCExam
 * Date :7/12/2022
 * Time :9:44 AM
 */

public class StudentFormController {

    public JFXTextField txtStudentName;
    public TableView<StudentTM> tblStudent;
    public JFXTextField txtNic;
    public JFXTextField txtEmail;
    public JFXTextField txtTellNo;
    public JFXTextField txtStudentId;
    public JFXTextField txtAddress;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllStudent();

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("nic"));
    }

    public void loadAllStudent() throws SQLException, ClassNotFoundException {
        tblStudent.getItems().clear();
        ArrayList<Student> allStudent = StudentCrudController.getAllStudent();


        for (Student student : allStudent) {
            tblStudent.getItems().add(new StudentTM(
                    student.getStudentId(),
                    student.getName(),
                    student.getEmail(),
                    student.getTelNo(),
                    student.getAddress(),
                    student.getNic()
            ));
        }
    }


    public void menuUpdateOnAction(ActionEvent actionEvent) {

    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnAdd(ActionEvent actionEvent) {

    }

    public void btnClear(ActionEvent actionEvent) {

    }
}
