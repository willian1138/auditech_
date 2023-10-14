package fbw.auditech.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import fbw.auditech.model.Registro;
import fbw.auditech.model.IPacienteRepository;
import fbw.auditech.model.Foto;
import fbw.auditech.model.Paciente;

@Service
public class PacienteService implements IPacienteService {

	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	IPacienteRepository repositoryP;
	@Autowired
	FotoService fotoService;

	@Override
	public List<Registro> consultaRegistroPacientes() {

		Registro c = null;
		List < Registro > lista = new ArrayList < Registro > ();
		List < Paciente > listaP = repositoryP.findAll();
		List < Foto > listaF = fotoService.getAll();
		for (Paciente p : listaP) {
			for (Foto i : listaF) {
				if (p.getId().equals(i.getId())) {
					c = new Registro(p.getId(), p.getNome(), p.getCpf(),
						 i.getArquivo());
					lista.add(c);
				}
			}
		}
		return lista;
	}
	@Override
	public List<Paciente> consultaPorNome() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'consultaPorDescricao'");
	}
	@Override
	public Optional<Paciente> cadastrar(Paciente paciente) {
		// TODO Auto-generated method stub
		logger.info(">>>servico cadastrar paciente iniciado");
		return Optional.ofNullable(repositoryP.save(paciente));
		//throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
	}
	@Override
	public Optional<Paciente> consultarPorId(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'consultarPorId'");
	}
	@Override
	public Optional<Paciente> atualizar(Long id, Paciente pacienteAtualizado) {
		Optional<Paciente> pacienteExistente = repositoryP.findById(id);
		
		if(pacienteExistente.isPresent()){
			Paciente paciente = pacienteExistente.get();

			paciente.setNome(pacienteAtualizado.getNome());
			paciente.setIdade(pacienteAtualizado.getIdade());
			paciente.setCpf(pacienteAtualizado.getCpf());
			paciente.setEmail(pacienteAtualizado.getEmail());
			paciente.setEndereco(pacienteAtualizado.getEndereco());
			return Optional.ofNullable(repositoryP.save(paciente));
		} else{
			return Optional.empty();
		}
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void excluir(Long id) throws ResourceNotFoundException {
		Optional<Paciente> pacienteExistente = repositoryP.findById(id);
		if(pacienteExistente.isPresent()){
			repositoryP.deleteById(id);
		}else {
			throw new ResourceNotFoundException();

		}

		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'excluir'");
	}
    
}