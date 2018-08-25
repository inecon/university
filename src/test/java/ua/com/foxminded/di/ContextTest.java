package ua.com.foxminded.di;

import org.junit.Before;
import org.junit.Test;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Initialization;
import ua.com.foxminded.domain.Student;

public class ContextTest {
    public Student VALID_STUDENT1 = new Student(1, "Petro", "Kolhozin", "male", 19);
    public Group VALID_GROUP1 = new Group(1,"Group01", "Spring math group");

    ConnectionFactory VALID_DAO_FACTORY = new ConnectionFactory();
    Context contextForTest;
    @Before
    public void setUp() throws Exception {

        Initialization init = new Initialization();
        init.initializationUniversity();
    }
    @Test
    public void add() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        contextForTest.registerBean("VALID_STUDENT1", VALID_STUDENT1);
        contextForTest.registerBean("VALID_GROUP1", VALID_GROUP1);
        contextForTest.registerBean("VALID_DAO_FACTORY" ,VALID_DAO_FACTORY);

        contextForTest.registerBean(Group.class, Context.Scope.PROTOTYPE);

        contextForTest.registerBean(Student.class, Context.Scope.SINGLTON);

        Student student = (Student) contextForTest.getBeanWithScope(Student.class);

        System.out.println(student);
    }


}