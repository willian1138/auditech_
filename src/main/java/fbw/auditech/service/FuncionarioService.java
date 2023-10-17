package fbw.auditech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import fbw.auditech.model.Funcionario;
import fbw.auditech.model.IFuncionarioRepository;
import fbw.auditech.model.Registro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class FuncionarioService implements IFuncionarioService {
    
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired 
    IFuncionarioRepository repositoryF;


    @Override
    public Optional<Funcionario> cadastrar(Funcionario funcionario) {
        logger.info(">>>servico cadastrar funcionario iniciado");
  
        return Optional.ofNullable(repositoryF.save(funcionario));
         
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }
    @Override
    public Optional<Funcionario> consultarPorId(Long id) throws ResourceNotFoundException {
        logger.info(">>>servico consultar funcionario por id iniciado");
        Optional<Funcionario> funcionario = repositoryF.findById(id);
        if(funcionario.isPresent())
        {
            return funcionario;}
        else{
            throw new ResourceNotFoundException("Funcionario não encontrado");
        }
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'consultarPorId'");
    }
    @Override
    public Optional<Funcionario> atualizar(Long id, Funcionario funcionario) {
        Optional<Funcionario> funcionarioAtual = repositoryF.findById(id);
        if(funcionarioAtual.isPresent()){
            funcionarioAtual.get().setNome(funcionario.getNome());
            funcionarioAtual.get().setCpf(funcionario.getCpf());
            funcionarioAtual.get().setEmail(funcionario.getEmail());
            funcionarioAtual.get().setEndereco(funcionario.getEndereco());
            return Optional.ofNullable(repositoryF.save(funcionarioAtual.get()));
        } else{
            return Optional.empty();
        }
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
    @Override
    public void excluir(Long id) {
        logger.info(">>>servico excluir funcionario por id iniciado");
        Optional<Funcionario> funcionario = repositoryF.findById(id);
        if(funcionario.isPresent()){
            repositoryF.deleteById(id);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

   

}
	@Override
	public List<Registro> consultaRegistroFuncionarios() {
        Registro r = null;
        List<Registro> lista = new ArrayList <>();
        List<Funcionario> listaFuncionarios = repositoryF.findAll();
        for(Funcionario f : listaFuncionarios){
            r = new Registro();
            r.setId(f.getId());
            r.setDescricao(f.getNome());
            r.setFuncao(f.getFuncao());
            lista.add(r);
        }
        return lista;
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'consultaRegistroFuncionarios'");
	}
}
