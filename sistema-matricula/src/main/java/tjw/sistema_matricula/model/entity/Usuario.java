package tjw.sistema_matricula.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tjw.sistema_matricula.model.entity.register.RegisterCreate;
import tjw.sistema_matricula.model.enums.Role;

import java.util.Collection;
import java.util.List;

@Entity
public class Usuario extends RegisterCreate implements UserDetails{

    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;

    public Long getId() {return id;}
    public void setLogin(String login) {this.login = login;}
    public String getLogin() {return login;}
    public void setSenha(String senha) {this.senha = senha;}
    public String getSenha() {return senha;}
    public void setRole(Role role) {this.role = role;}
    public Role getRole() {return role;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }
}
