package fbw.auditech.service;

import java.util.List;
import java.util.Optional;

import fbw.auditech.model.Fonoaudiologo;
import fbw.auditech.model.Registro;

public interface IFonoaudiologoService {
    public Optional<Fonoaudiologo> cadastrar(Fonoaudiologo fonoaudiologo);
    public Optional<Fonoaudiologo> consultarPorId(String id);
    //public Optional<Fonoaudiologo> consultarPorCpf(String cpf);
    public List<Registro> consultaRegistroFonoaudiologos();
    public Optional<Fonoaudiologo> atualizar(Long id, Fonoaudiologo fonoaudiologo);
    public void excluir(Long id);
    //public Optional<Fonoaudiologo> login(String cpf, String senha);
   // public void excluirPorCpf(String cpf);
}
