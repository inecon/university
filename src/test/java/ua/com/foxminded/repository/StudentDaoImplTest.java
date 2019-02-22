package ua.com.foxminded.repository;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentDaoImplTest {
    /*List<Student> VALID_STUDENT_LIST = new ArrayList<>();
    Group VALID_GROUP = new Group(1,"Group01", "Spring math group");
    Student VALID_STUDENT = new Student(1, "student", "testSurname", "male", 25, VALID_GROUP);
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private PreparedStatement statement;
    @Mock
    private Connection connection;
    @Mock
    private ResultSet resultSet;
    @Mock
    StudentDao mockedStudentDao;

    StudentDao studentDao;

    @Before
    public void setUp() throws DaoException {
        VALID_STUDENT_LIST.add(VALID_STUDENT);
    }

    @Test
    public void getAll() throws DaoException, SQLException {
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getInt("id")).thenReturn(VALID_STUDENT.getId());
        when(resultSet.getString("name")).thenReturn(VALID_STUDENT.getName());
        when(resultSet.getString("surname")).thenReturn(VALID_STUDENT.getSurName());
        when(resultSet.getString("gender")).thenReturn(VALID_STUDENT.getGender());
        when(resultSet.getInt("age")).thenReturn(VALID_STUDENT.getAge());
        when(mockedStudentDao.getAll()).thenReturn(VALID_STUDENT_LIST);
        List<Student> actual_result = VALID_STUDENT_LIST;
        List<Student> expected_result = mockedStudentDao.getAll();
        assertEquals(actual_result, expected_result);
    }

    @Test
    public void getById() throws DaoException, SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getInt("id")).thenReturn(VALID_STUDENT.getId());
        when(resultSet.getString("name")).thenReturn(VALID_STUDENT.getName());
        when(resultSet.getString("surname")).thenReturn(VALID_STUDENT.getSurName());
        when(resultSet.getString("gender")).thenReturn(VALID_STUDENT.getGender());
        when(resultSet.getInt("age")).thenReturn(VALID_STUDENT.getAge());
        when(mockedStudentDao.getById(anyInt())).thenReturn(VALID_STUDENT);

        Student actual_result = VALID_STUDENT;
        Student expected_result = mockedStudentDao.getById(anyInt());
        assertEquals(actual_result, expected_result);
    }

    @Test
    public void shouldInvokeCreate() throws DaoException {
        mockedStudentDao.create(any(Student.class));
        verify(mockedStudentDao).create(any(Student.class));
    }

    @Test
    public void shouldInvokeUpdate() throws DaoException {
        mockedStudentDao.update(any(Student.class));
        verify(mockedStudentDao).update(any(Student.class));
    }

    @Test
    public void shouldInvokeDeleteById() throws DaoException {
        mockedStudentDao.delete(any());
        verify(mockedStudentDao).delete(any());
    }*/
}