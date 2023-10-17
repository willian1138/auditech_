package fbw.auditech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import java.util.UUID;
 
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // manipula - lazy loaded properties
@Entity
@Data
public class Paciente {

 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
   	private String cpf; 
   	private String dataDeNascimento;
	private String numeroDeTelefone;
	private String endereco;
	private String email;
	private String senha;
    
    
	public Paciente(String nome, String cpf, String dataDeNascimento, String numeroDeTelefone, String endereco, String email, String senha){
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.numeroDeTelefone = numeroDeTelefone;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		
	}
	public Paciente(){}
		


	


}