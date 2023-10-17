package fbw.auditech.model;

import lombok.Data;

//import java.util.UUID;
@Data
public class Registro {
    private Long id;
    private String descricao;
    private String categoria;
    private String funcao;
   ;

    public Registro(Long id, String descricao, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        ;
    }
    public Registro(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
