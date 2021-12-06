package DZDB;

//import java.sql.Connection;

public class viewAllStudents {
protected static int id;
protected static String fioStudent;
protected static String sex;
protected static String group;
protected static String fio_curator;
protected static int countStudents;
protected static int updateCurator;
//public viewAllStudents(){};
public viewAllStudents(int id, String fioStudent, String sex, String group, String fio_curator){
    this.id=id;
    this.fioStudent=fioStudent;
    this.sex=sex;
    this.group=group;
    this.fio_curator=fio_curator;

}


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", ФИО='" + fioStudent + '\'' +
                ", Пол='" + sex + '\'' +
                ", Группа=" + group +
                ", Куратор='" + fio_curator + '\'' +
                '}';
    }
}
