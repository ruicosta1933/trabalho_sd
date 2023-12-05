package com.example.view;

import com.example.User;
import com.example.JSON.SaveJsonToFile;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Scanner;

public class Register {

    // Método para registrar um novo usuário
    public static void register() {
        Scanner input = new Scanner(System.in);

        try {
            // Solicitar informações do usuário
            System.out.println("Enter your name: ");
            String name = input.nextLine();

            System.out.println("Enter your email: ");
            String email = input.nextLine();

            System.out.println("Enter your password: ");
            String password = input.nextLine();

            System.out.println("Repeat your password: ");
            String password2 = input.nextLine();

            // Verificar se as senhas coincidem
            if (!password.equals(password2)) {
                System.out.println("Passwords don't match!");
                register();  // Chamada recursiva para registrar novamente se as senhas não coincidirem
                return;
            }

            System.out.println("Enter your profile: ");
            String profile = input.nextLine();

            // Criar um objeto User com as informações fornecidas
            User user = new User(name, email, password, profile);
            System.out.println("AQUIIII");
            // Carregar a lista de usuários existentes a partir de um arquivo JSON
            List<User> users = SaveJsonToFile.loadObjects("users.json", new TypeToken<List<User>>() {
            }.getType());
            System.out.println("AQUIIII");
            // Verificar se o usuário já existe com base no nome
            for (User u : users) {
                if (u.getName().equals(user.getName())) {
                    System.out.println("User already exists!");
                    register();  // Chamada recursiva para registrar novamente se o usuário já existir
                    return;
                }
            }

            // Adicionar o novo usuário à lista
            users.add(user);

            // Salvar a lista atualizada de usuários de volta ao arquivo JSON

            SaveJsonToFile.saveJsonToFile(users, "users.json");

            System.out.println("User registered successfully!");
            //set json lenient to true

            // Voltar ao menu inicial
            startMenu.startMenu();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
