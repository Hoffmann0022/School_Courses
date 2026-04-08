package org.example.view;

import org.example.DAO.CourseDAO;
import org.example.DAO.RegistrationDAO;
import org.example.DAO.StudentDAO;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.Student;

import java.util.List;
import java.util.Scanner;

import static org.example.view.Home.home;

public class RegisterRegistration {
    static void registerRegistration() {
        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        RegistrationDAO registrationDAO = new RegistrationDAO();
        Registration registration = new Registration();

        System.out.println("O que deseja fazer?");
        System.out.println("1 - Fazer Matrícula | 2 - Listar Matrículas | 0 - Voltar");
        int opc = scanner.nextInt();
        scanner.nextLine();

        if (opc == 1){
            System.out.println("Faça uma matrícula:");


            System.out.println("Digite o email do aluno: ");
            String email = scanner.nextLine();
            List<Student> students = studentDAO.searchToEmail(email);

            if (students.isEmpty()) {
                System.out.println("Aluno não encontrado!");
                return;
            }

            for (Student s : students){
                System.out.println(s.getId() + " - " + s.getName() + " (" + s.getEmail() + ")");
            }
            System.out.print("Digite o ID do aluno que deseja matricular: ");
            Long id_student = Long.parseLong(scanner.nextLine());

            Student student = students.stream()
                    .filter(s -> s.getId().equals(id_student))
                    .findFirst()
                    .orElse(null);

            if (student == null) {
                System.out.println("Aluno inválido!");
                return;
            }

            System.out.println("Digite o nome do curso: ");
            String name = scanner.nextLine();
            List<Course> courses = courseDAO.searchToName(name);

            if (courses.isEmpty()) {
                System.out.println("Curso não encontrado!");
                return;
            }

            for (Course c : courses){
                System.out.println(c.getId() + " - " + c.getName());
            }
            System.out.print("Digite o ID do curso que deseja matricular: ");
            Long id_course = Long.parseLong(scanner.nextLine());

            Course course = courses.stream()
                    .filter(c -> c.getId().equals(id_course))
                    .findFirst()
                    .orElse(null);

            if (course == null) {
                System.out.println("Curso inválido!");
                return;
            }


            registration.setStudent(student);
            registration.setCourse(course);
            registrationDAO.create(registration);

            System.out.println("Matrícula feita com sussesso!");
            home();
        } else if (opc == 2) {
            List<Registration> registrations = registrationDAO.read();
            System.out.println("Lista de Matrículas:");

            if (registrations.isEmpty()) {
                System.out.println("Nenhuma matrícula feita.");
                home();
            }

            for (Registration r : registrations) {
                System.out.println("-----------------------------------------");
                System.out.println("Id: " + r.getId());
                System.out.println("Aluno: " + r.getStudent().getName());
                System.out.println("Curso: " + r.getCourse().getName());
                System.out.println("-----------------------------------------");
            }
            home();
        } else if (opc == 0) {
            home();
        } else {
            System.out.println("Número inválido!");
        }
    }
}
