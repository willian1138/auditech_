package fbw.auditech.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // manipula - lazy loaded properties
@Entity
@Data
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String funcao;

    public Funcionario() {
    }
    public Funcionario(String nome, String cpf, String email, String endereco, String funcao){
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.endereco = endereco;
    this.funcao = funcao;}

}
