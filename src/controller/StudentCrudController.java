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
        return CrudUtil.execute("DELETE FROM Student WHERE id = ?",id);
    }
}
