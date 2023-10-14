package fbw.auditech.model;

public class Registro {
    private Long id;
    private String descricao;
    private String categoria;
    private byte[] foto;

    public Registro(Long id, String descricao, String categoria, byte[] foto) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.foto = foto;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
