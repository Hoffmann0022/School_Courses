package org.example.view;

import org.example.DAO.CourseDAO;
import org.example.model.Course;

import java.util.List;
import java.util.Scanner;

import static org.example.view.Home.home;

public class RegisterCourse {
    static void registerCourse() {
        Scanner scanner = new Scanner(System.in);
        CourseDAO courseDAO = new CourseDAO();
        Course course = new Course();

        System.out.println("O que deseja fazer?");
        System.out.println("1 - Cadastrar Curso | 2 - Listar Cursos | 0 - Voltar");
        int opc = scanner.nextInt();
        scanner.nextLine();

        if (opc == 1){
            System.out.println("Cadastre um curso:");

            try {
                System.out.println("Digite o nome do curso: ");
                String name = scanner.nextLine();
                course.setName(name);
            } catch (Exception e) {
                System.out.println("Nome inválido!");
            }

            try{
                System.out.println("Digite a descrição do curso: ");
                String description = scanner.nextLine();
                course.setDescription(description);
            } catch (Exception e) {
                System.out.println("Descrição inválida!");
            }

            try {
                System.out.println("Digite a carga horária do curso:");
                int workload = scanner.nextInt();
                course.setWorkload(workload);
            } catch (Exception e) {
                System.out.println("Carga horária inválida!");
            }

            courseDAO.create(course);
            System.out.println("Curso cadastrado com sussesso!");
            home();
        } else if (opc == 2) {
            List<Course> courses = courseDAO.read();
            System.out.println("Lista de Cursos:");

            if (courses.isEmpty()) {
                System.out.println("Nenhum curso cadastrado.");
                home();
            }

            for (Course c : courses) {
                System.out.println("-----------------------------------------");
                System.out.println("Id: " + c.getId());
                System.out.println("Nome: " + c.getName());
                System.out.println("Descrição: " + c.getDescription());
                System.out.println("Carga horária: " + c.getWorkload() + "h");
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
