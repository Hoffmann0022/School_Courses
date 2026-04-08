package org.example.view;

import org.example.DAO.StudentDAO;
import org.example.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static org.example.view.Home.home;

public class RegisterStudent {
    static void registerStudent() {
        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student();

        System.out.println("O que deseja fazer?");
        System.out.println("1 - Cadastrar Aluno | 2 - Listar Alunos | 0 - Voltar");
        int opc = scanner.nextInt();
        scanner.nextLine();

        if (opc == 1){
            System.out.println("Cadastre um aluno.");

            try {
                System.out.println("Digite o nome do aluno: ");
                String name = scanner.nextLine();
                student.setName(name);
            } catch (Exception e) {
                System.out.println("Nome inválido!");
            }

            try{
                System.out.println("Digite o email do aluno: ");
                String email = scanner.nextLine();
                student.setEmail(email);
            } catch (Exception e) {
                System.out.println("Email inválido!");
            }

            try {
                System.out.println("Digite a data de nascimento (dd/MM/yyyy): ");
                String date = scanner.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateBirth = LocalDate.parse(date, formatter);

                student.setDateBirth(dateBirth);
            } catch (Exception e) {
                System.out.println("Data inválida! Use o formato dd/MM/yyyy");
            }

            studentDAO.create(student);
            System.out.println("Aluno cadastrado com sussesso!");
            home();
        } else if (opc == 2) {
            List<Student> students = studentDAO.read();
            System.out.println("Lista de Alunos.");

            if (students.isEmpty()) {
                System.out.println("Nenhum aluno cadastrado.");
                return;
            }

            for (Student s : students) {
                System.out.println("-----------------------------------------");
                System.out.println("Id: " + s.getId());
                System.out.println("Nome: " + s.getName());
                System.out.println("Email: " + s.getEmail());
                System.out.println("Data de nascimento: " + s.getDateBirth());
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
