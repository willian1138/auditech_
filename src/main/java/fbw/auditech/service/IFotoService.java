package fbw.auditech.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import fbw.auditech.model.Foto;


public interface IFotoService {
	public Optional<Foto> salvar(long id, MultipartFile arquivo) throws IOException;
    public List < Foto > getAll();
    public byte[] getFoto(String nomeArquivo);
    public byte[] getFotoById(Long id);
}