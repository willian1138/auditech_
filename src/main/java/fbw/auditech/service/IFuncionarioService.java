package fbw.auditech.service;

import fbw.auditech.model.Funcionario;
import fbw.auditech.model.Registro;

import java.util.List;
import java.util.Optional;

public interface IFuncionarioService {
    public List<Registro> consultaRegistroFuncionarios();
    public Optional<Funcionario> cadastrar(Funcionario funcionario);
    public Optional<Funcionario> consultarPorId(String id);
    public Optional<Funcionario> atualizar(Long id, Funcionario funcionario);
    public void excluir(Long id);   
}
