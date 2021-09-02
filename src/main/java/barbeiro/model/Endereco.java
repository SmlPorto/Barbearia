/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "end_cidade", length = 45, nullable = true)
    private String cidade;
    
    @Column(name = "end_estado", length = 45, nullable = true)
    private String estado;
    
    @Column(name = "end_codigo_postal", length = 11, nullable = true)
    private int codigoPostal;
    
    @Column(name = "end_rua", length = 45, nullable = true)
    private String rua;
    
    @Column(name = "end_numero", length = 6, nullable = true)
    private int numero;
    
    @Column(name = "end_bairro", length = 45, nullable = true)
    private String bairro;
    
    @Column(name = "end_complemento", length = 45, nullable = true)
    private String complemento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return  cidade + ", estado=" + estado + ", codigoPostal=" + codigoPostal + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", complemento=" + complemento + '}';
    }
    
    
}
