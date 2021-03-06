package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public JFXButton btnAdd;
    public JFXTextField txtSearch;

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
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        txtStudentId.setText(selectedItem.getStudentId());
        txtStudentName.setText(selectedItem.getName());
        txtEmail.setText(selectedItem.getEmail());
        txtTellNo.setText(selectedItem.getTelNo());
        txtAddress.setText(selectedItem.getAddress());
        txtNic.setText(selectedItem.getNic());

        txtStudentId.setEditable(false);
        btnAdd.setText("Update");

    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        if(StudentCrudController.deleteStudent(selectedItem.getStudentId())){
            loadAllStudent();
            new Alert(Alert.AlertType.WARNING, "Student Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something went wrong").show();
        }

    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (btnAdd.getText().equalsIgnoreCase("Add")) {
            boolean b = StudentCrudController.addStudent(new Student(
                            txtStudentId.getText(),
                            txtStudentName.getText(),
                            txtEmail.getText(),
                            txtTellNo.getText(),
                            txtAddress.getText(),
                            txtNic.getText()
                    )
            );
            if (b) {
                loadAllStudent();
                clearText();
                new Alert(Alert.AlertType.WARNING, "Student Added Successfully").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something went wrong").show();
            }
        } else {
            boolean b = StudentCrudController.updateStudent(new Student(
                            txtStudentId.getText(),
                            txtStudentName.getText(),
                            txtEmail.getText(),
                            txtTellNo.getText(),
                            txtAddress.getText(),
                            txtNic.getText()
                    )
            );
            if (b) {
                loadAllStudent();
                new Alert(Alert.AlertType.WARNING, "Student Updated Successfully").show();
                btnAdd.setText("Add");
                txtStudentId.setEditable(true);
                clearText();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something went wrong").show();
            }
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearText();
    }

    private void clearText(){
        txtStudentId.clear();
        txtStudentName.clear();
        txtEmail.clear();
        txtTellNo.clear();
        txtAddress.clear();
        txtNic.clear();
    }

    public void txtSearchKeyReleasedOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String search = "%" + txtSearch.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<Student> searchStudent = StudentCrudController.getSearchStudent(search);
            ObservableList<StudentTM> st = FXCollections.observableArrayList();

            for (Student student : searchStudent) {
                st.add(new StudentTM(
                        student.getStudentId(),
                        student.getName(),
                        student.getEmail(),
                        student.getTelNo(),
                        student.getAddress(),
                        student.getNic()
                ));
            }


            tblStudent.getItems().clear();
            tblStudent.getItems().addAll(st);
            tblStudent.refresh();
        }
    }
}
