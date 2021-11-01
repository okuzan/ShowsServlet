package org.training.model.dto;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String username;
    private BigDecimal balance;
    private boolean enabled = true;
    private ROLE role;

    private ROLE parseRole(String roleStr) {
        return User.ROLE.valueOf(roleStr.replaceFirst("^ROLE_", ""));
    }

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

    public User(Long id, boolean enabled,
                String password, String username,
                String email, String name,
                BigDecimal balance, String roleStr) {
        this.id = id;
        this.enabled = enabled;
        this.password = password;
        this.username = username;
        this.balance = balance;
        this.name = name;
        this.email = email;
        this.role = parseRole(roleStr);
    }

    public User(boolean enabled,
                String password, String username,
                String email, String name,
                BigDecimal balance, String roleStr) {
        this.enabled = enabled;
        this.password = password;
        this.username = username;
        this.balance = balance;
        this.name = name;
        this.email = email;
        this.role = parseRole(roleStr);
    }

    public User(String name, String username, String email, String password) {
        this.password = password;
        this.username = username;
        this.name = name;
        this.email = email;
    }


    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
