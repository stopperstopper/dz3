package DZDB;
import java.sql.*;


import static DZDB.viewAllStudents.*;


//Drop TABLE student; DROP TABLE gruppa; DROP TABLE curator;

public class Main {
        private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/dz3";
        private static final String USER = "admin";
        private static final String PASSWORD = "admin";

        public static void main(String[] args) throws  SQLException {
            try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
                Statement statement = connection.createStatement();


                //создание таблицы curator
                statement.execute("CREATE TABLE IF NOT EXISTS Curator" +
                        "(id int auto_increment primary key," +
                        " fio_curator varchar(50));");

                //создание таблицы gruppa
                statement.execute("CREATE TABLE IF NOT EXISTS Gruppa (id int auto_increment primary key, " +
                        "name varchar(50), " +
                        "id_curator int, " +
                        "FOREIGN KEY(id_curator) REFERENCES Curator(id))");

                //создание таблицы student
                statement.execute("CREATE TABLE IF NOT EXISTS Student(id int auto_increment primary key, " +
                        "fio varchar(50), " +
                        "id_group int, " +
                        "sex varchar(1), " +
                        "FOREIGN KEY(id_group) REFERENCES Gruppa(id))");
                // заполняем таблицы данными
                statement.executeUpdate("INSERT curator(fio_curator) VALUES ('Иванов Иван Иванович'),('Петров Петр Петрович'),('Сидоров Сидор Сидорович'), ('Васильев Василий Васильевич')");
                statement.executeUpdate("INSERT gruppa(name,id_curator) VALUES ('Группа1','1'),('Группа2','2'), ('Группа3','3')");
                statement.executeUpdate("INSERT student(fio, id_group, sex) VALUES ('Студент1','1','m'),('Студент2','1','m'), ('Студент3','2','m')," +
                        "('Студентка1','3','f'), ('Студентка2','1','f'),('Студентка3','2','f'),('Студент4','3','m'),('Студент5','1','m'),('Студент6','2','m'),('Студент7','3','m')," +
                        "('Студент8','1','m'),('Студентка4','3','f'),('Студент9','3','m'),('Студент10','3','m'),('Студент11','2','m')");

                //вывод всех студентов
                ResultSet resSet = statement.executeQuery("SELECT student.id, student.fio,student.sex, gruppa.name, curator.fio_curator FROM student JOIN gruppa ON gruppa.id=student.id_group JOIN curator ON curator.id=gruppa.id_curator;");

                while (resSet.next()) {
                    id = resSet.getInt(1);
                    fioStudent = resSet.getString(2);
                    sex = resSet.getString(3);
                    group = resSet.getString(4);
                    fio_curator = resSet.getString(5);

                 //   viewAllStudents printStudents = new viewAllStudents(id, fioStudent, sex, group, fio_curator);

                    System.out.println("Id=" + id + " " + "ФИО- " + fioStudent + " " + "Пол- " + sex + " " + "Группа- " + group + " " + "Куратор- " + fio_curator);
                }

                //вывод кол-ва студентов
                ResultSet count = statement.executeQuery("SELECT COUNT(*) as COUNT FROM student;");
                count.next();
                countStudents = count.getInt(1);
                System.out.println();
                System.out.println("Всего студентов " + countStudents);
                System.out.println();

                //Вывод списка студенток
                resSet = statement.executeQuery("SELECT * from student WHERE sex='f';");

                while (resSet.next()) {
                    fioStudent = resSet.getString("fio");
                    sex = resSet.getString("sex");

                    System.out.println("ФИО- " + fioStudent + " " + "Пол- " + sex);
                }
                System.out.println();

                // обновить куратора
                updateCurator = statement.executeUpdate("UPDATE gruppa SET id_curator=4 WHERE id_curator=3");

                //список групп
                resSet = statement.executeQuery("SELECT gruppa.name, curator.fio_curator FROM gruppa JOIN curator ON curator.id=gruppa.id_curator;");

                while (resSet.next()) {
                    group = resSet.getString("name");
                    fio_curator = resSet.getString("fio_curator");
                    System.out.println("ФИО- " + fio_curator + " " + "Куратор группы- " + group);
                }
                System.out.println();

                //список студентов из Группа3
                    resSet = statement.executeQuery("SELECT student.fio, gruppa.name from student JOIN gruppa on gruppa.id=student.id_group WHERE gruppa.name='Группа3';");

                    while (resSet.next()) {
                        group = resSet.getString("name");
                        fioStudent = resSet.getString("fio");

                        System.out.println("ФИО- " + fioStudent + " " + "Группа №- " + group);


                    }
                    System.out.println();

                }


            }
        }









