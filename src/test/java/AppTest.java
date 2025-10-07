import junit.framework.TestCase;
import org.example.entities.Student;
import org.example.services.StudentService;
import org.junit.Test;

import java.util.List;

public class AppTest {

    StudentService studentService = new StudentService();

    @Test
    public void testGetStudentTest() {
        Student student = studentService.getById(1);
        System.out.println(student.getName());
    }

    @Test
    public void testGetByCollegeCriteria() {
        List<Student> students = studentService.getStudentsByCollegeCriteria("SIES");
        System.out.println(students.size());
    }

    @Test
    public void testGetByPagination() {
        List<Student> students = studentService.getStudentWithPagination(2, 3);
        System.out.println(students.size());
    }

    @Test
    public void testUpdateStudents() {
        Student student = new Student();
        student.setName("Kunjal Padwal");
        student.setCollege("Dhahanukar");
        student.setFatherName("Uday Padwal");
        student.setPhone("1234567890");
        student.setActive(true);
        student.setAbout("CA");

        Student student1 = studentService.updateStudent(5, student);
        System.out.println(student1);
    }
}
