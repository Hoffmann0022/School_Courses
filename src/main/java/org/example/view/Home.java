package org.example.view;

import java.util.Scanner;

import static org.example.view.RegisterCourse.registerCourse;
import static org.example.view.RegisterRegistration.registerRegistration;
import static org.example.view.RegisterStudent.registerStudent;

public class Home {
    public static void home(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seja bem-vindo(a)!");
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Cadastrar/Listar Alunos | 2 - Cadastrar/Listar Cursos | 3 - Cadastrar/Listar Matrículas | 0 - Sair");
        int opc = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (opc){
                case 1:
                    registerStudent();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    registerRegistration();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    System.exit(0);
                default:
                    System.out.println("Número inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas números!");
        }

    }
}
