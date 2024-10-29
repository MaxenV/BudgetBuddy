package com.example.budget_buddy;

import com.example.budget_buddy.service.LoginResponse;
import com.example.budget_buddy.dto.LoginUserDto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.web.client.RestTemplate;

public class LoginApplication extends JFrame {

    private RestTemplate restTemplate = new RestTemplate();

    public LoginApplication() {
        setTitle("BudgetBuddy - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        
        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(emailLabel, constraints);

        JTextField emailTextField = new JTextField(15);
        constraints.gridx = 1;
        panel.add(emailTextField, constraints);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(15);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        // Login button
        JButton loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                String password = new String(passwordField.getPassword());
                handleLogin(email, password);
            }
        });

        add(panel);
    }

    private void handleLogin(String email, String password) {
        String url = "http://localhost:8005/auth/login";
        LoginUserDto loginUser = new LoginUserDto(email, password);
    
        try {
            LoginResponse response = restTemplate.postForObject(url, loginUser, LoginResponse.class);
            if (response != null) {
                JOptionPane.showMessageDialog(this, "Login successful! Token: " + response.getToken());
                this.dispose(); // Zamknij okno logowania
                MainPanel mainPanel = new MainPanel(); // Otwórz panel główny
                mainPanel.setVisible(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Login failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginApplication loginFrame = new LoginApplication();
            loginFrame.setVisible(true);
        });
    }
}
