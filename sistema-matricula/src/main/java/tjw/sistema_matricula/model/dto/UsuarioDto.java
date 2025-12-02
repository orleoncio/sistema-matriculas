package tjw.sistema_matricula.model.dto;

import tjw.sistema_matricula.model.enums.Role;

public class UsuarioDto {
    private String login;
    private String senha;
    private String confirmSenha;
    private String role;

    public void setLogin(String login){this.login = login;}
    public String getLogin(){return login;}
    public void setSenha(String senha){this.senha = senha;}
    public String getSenha(){return senha;}
    public void setConfirmSenha(String confirmSenha){this.confirmSenha = confirmSenha;}
    public String getConfirmSenha(){return confirmSenha;}
    public void setRole(String role){this.role = role;}
    public String getRole(){return role;}
}
