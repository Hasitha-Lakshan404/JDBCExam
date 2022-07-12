package controller;

import model.Student;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Hasitha Lakshan
 * Project :JDBCExam
 * Date :7/12/2022
 * Time :10:08 AM
 */

public class StudentCrudController {
    public static ArrayList<Student> getAllStudent() throws SQLException, ClassNotFoundException {
        ResultSet resultset = CrudUtil.execute("SELECT * FROM Student");

        ArrayList<Student> stList=new ArrayList<>();

        while(resultset.next()){
            stList.add(new Student(
                    resultset.getString(1),
                    resultset.getString(2),
                    resultset.getString(3),
                    resultset.getString(4),
                    resultset.getString(5),
                    resultset.getString(6)
            ));
        }

        return stList;

    }

    public static boolean addStudent(Student student) throws SQLException, ClassNotFoundException {


        if (CrudUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)",student.getStudentId(),student.getName(),student.getEmail(),student.getTelNo(),student.getAddress(),student.getNic())){
            return true;
        }
        return false;
    }

    public static boolean updateStudent(Student student) throws SQLException, ClassNotFoundException{

        if(CrudUtil.execute("UPDATE Student SET studentId = ?,studentName = ?,email = ?, contact = ?, address = ?,nic = ? WHERE StudentId = ? ",student.getStudentId(),student.getName(),student.getEmail(),student.getTelNo(),student.getAddress(),student.getNic(),student.getStudentId())){

            return true;
        }
        return false;
    }


    public static boolean deleteStudent(String id)throws SQLException, ClassNotFoundException{
        return CrudUtil.execute("DELETE FROM Student WHERE studentId = ?",id);
    }

    public static ArrayList<Student> getSearchStudent(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM student where studentId LIKE ? OR studentName LIKE ? OR email LIKE ? OR contact LIKE ? OR address LIKE ? OR nic LIKE ?", enteredText, enteredText, enteredText, enteredText,enteredText,enteredText);
        ArrayList<Student> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Student(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)

            ));
        }
        return list;


    }
}
