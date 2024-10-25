package com.example.budget_buddy;

import com.example.budget_buddy.service.LoginResponse;
import com.example.budget_buddy.dto.LoginUserDto;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

public class LoginApplication extends Application {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BudgetBuddy - Login");

        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 0, 1);

        TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        
        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 3);

        
        loginButton.setOnAction(e -> {
            String email = emailTextField.getText();
            String password = passwordField.getText();
            handleLogin(email, password);
        });

        
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin(String email, String password) {
        String url = "http://localhost:8005/auth/login";
        LoginUserDto loginUser = new LoginUserDto(email, password);

        try {
            LoginResponse response = restTemplate.postForObject(url, loginUser, LoginResponse.class);
            if (response != null) {
                System.out.println("Login successful! Token: " + response.getToken());
                
            }
        } catch (Exception ex) {
            System.out.println("Login failed: " + ex.getMessage());
            
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
