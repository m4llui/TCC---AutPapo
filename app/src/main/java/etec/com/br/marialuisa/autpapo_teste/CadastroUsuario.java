package etec.com.br.marialuisa.autpapo_teste;

public class CadastroUsuario {
    //classe Usuario
    private int codUsuario;
    private String email;
    private String senha;

    public CadastroUsuario(int codUsuario, String email, String senha) {
        this.codUsuario = codUsuario;
        this.email = email;
        this.senha = senha;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
