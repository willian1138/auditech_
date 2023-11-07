package fbw.auditech.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import fbw.auditech.model.IFonoaudiologoRepository;
import fbw.auditech.model.Registro;
import fbw.auditech.model.Fonoaudiologo;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FonoaudiologoService implements IFonoaudiologoService {
    
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IFonoaudiologoRepository repositoryF;
    
    public Optional<Fonoaudiologo> cadastrar(Fonoaudiologo fonoaudiologo) {
        logger.info("Serviço de cadastro de fonoaudiologo iniciado");
        return Optional.ofNullable(repositoryF.save(fonoaudiologo));
    }

    public Optional<Fonoaudiologo> atualizar(Long id, Fonoaudiologo fonoaudiologo) {
        logger.info("Serviço de atualização de fonoaudiologo iniciado");
        Optional<Fonoaudiologo> fonoaudiologoAtual = repositoryF.findById(id);
        if (fonoaudiologoAtual.isPresent()) {
            Fonoaudiologo fonoaudiologoExistente = fonoaudiologoAtual.get();
            fonoaudiologoExistente.setNome(fonoaudiologo.getNome());
            fonoaudiologoExistente.setCpf(fonoaudiologo.getCpf());
            fonoaudiologoExistente.setEmail(fonoaudiologo.getEmail());
            fonoaudiologoExistente.setEndereco(fonoaudiologo.getEndereco());
            fonoaudiologoExistente.setSenha(fonoaudiologo.getSenha());
            return Optional.ofNullable(repositoryF.save(fonoaudiologoExistente));
        } else {
            return Optional.empty();
        }
    }
    
    public void excluir(Long id) {
        logger.info("Serviço de exclusão de fonoaudiologo iniciado");
        Optional<Fonoaudiologo> fonoaudiologo = repositoryF.findById(id);
        if (fonoaudiologo.isPresent()) {
            repositoryF.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Fonoaudiologo não encontrado para o ID '" + id + "'");
        }
    }

    @Override
    public List<Registro> consultaRegistroFonoaudiologos() {
        Registro r = null;
        List<Registro> lista = new ArrayList<>();
        List<Fonoaudiologo> listaFonoaudiologos = repositoryF.findAll();
        for (Fonoaudiologo f : listaFonoaudiologos) {
            r = new Registro();
            r.setId(Long.parseLong(f.getCpf()));
            r.setNome(f.getNome());
            r.setFuncao("Fonoaudiologo");
            lista.add(r);
        }
        return lista;
    }

    @Override
    public Optional<Fonoaudiologo> consultarPorId(String id) {
        logger.info("Serviço de consulta de fonoaudiologo por ID iniciado");
        Optional<Fonoaudiologo> fonoaudiologo = repositoryF.findById(Long.parseLong(id));
        if (fonoaudiologo.isPresent()) {
            return fonoaudiologo;
        } else {
            throw new ResourceNotFoundException("Fonoaudiologo não encontrado para o ID '" + id + "'");
        }
    }
}
