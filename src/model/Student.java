package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Hasitha Lakshan
 * Project :JDBCExam
 * Date :7/12/2022
 * Time :10:07 AM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    String studentId;
    String name;
    String email;
    String telNo;
    String address;
    String nic;
}
