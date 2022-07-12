package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
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

    public void initialize(){

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
