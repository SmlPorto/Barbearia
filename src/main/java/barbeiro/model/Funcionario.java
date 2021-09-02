/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "usu_nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "usu_cpf", length = 100, nullable = false)
    private String cpf;
    
    @Column(name = "usu_email", length = 100, nullable = false, unique = true)
    private String email;
    
    @Column(name = "usu_senha", length = 100, nullable = true)
    private String senha;

    @Column(name = "usu_cargo", nullable = true)
    private int cargo;

    @Column(name = "funcao",length = 100, nullable = true)
    private String funcao;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getCpf() {
return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }


    @Override
    public String toString() {
        return nome;
    }
}
