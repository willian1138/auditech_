package fbw.auditech.service;

import java.util.List;
import java.util.Optional;
//import java.util.UUID;

import fbw.auditech.model.Paciente;
import fbw.auditech.model.Registro;

public interface IPacienteService {
    public List<Registro> consultaRegistroPacientes();
    public List<Paciente> consultaPorNome();
    public Optional<Paciente> cadastrar(Paciente paciente);
    public Optional<Paciente> consultarPorId(String id);
    public Optional<Paciente> atualizar(Long id, Paciente paciente);
    public void excluir(Long id);
}
