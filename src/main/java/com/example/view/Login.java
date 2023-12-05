package com.example.view;

import com.example.JSON.SaveJsonToFile;
import com.example.StartPage;
import com.example.User;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Scanner;

public class Login {

    // Método para realizar o login
    public static void login() {
        System.out.println("Login");

        Scanner input = new Scanner(System.in);

        try {
            // Solicitar nome de usuário e senha
            System.out.println("Username: ");
            String username = input.nextLine();
            System.out.println("Password: ");
            String password = input.nextLine();

            // Carregar a lista de usuários a partir do arquivo JSON
            List<User> users = SaveJsonToFile.loadObjects("users.json", new TypeToken<List<User>>() {}.getType());

            // Verificar se o usuário existe e a senha está correta
            for (User u : users) {
                if (u.getName().equals(username) && u.getPassword().equals(password)) {
                    System.out.println("User exists!");
                    // Iniciar a página inicial passando o usuário autenticado
                    StartPage.startPage(u);
                    return;
                } else {
                    // Se o usuário não for encontrado, solicitar login novamente
                    System.out.println("User doesn't exist!");
                    login();
                    return;
                }
            }

        } catch (Exception e) {
            // Tratar exceções, como problemas ao carregar o arquivo JSON
            System.out.println("Error: " + e);
        }
    }
}
