package com.example.postgresdemo.model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class ModelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "Имя пользователя не может быть пустым")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    private String username;
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 1, message = "Пароль должен содержать минимум 1 символ")
    private String password;
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    public ModelUser() {
    }

    public ModelUser(long id, String username, String password, boolean active, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
