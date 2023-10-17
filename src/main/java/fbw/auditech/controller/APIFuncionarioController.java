package fbw.auditech.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fbw.auditech.model.Funcionario;
import fbw.auditech.service.IFuncionarioService;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class APIFuncionarioController {
 
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IFuncionarioService funcionarioServico;
    
    @CrossOrigin
    @GetMapping
    
    public ResponseEntity <Object> consultaFuncionarios(){
        logger.info(">>>>>> apicontroller consulta todos os funcionarios");
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioServico.consultaRegistroFuncionarios());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity <Object> consultaPorId(Long id){
        logger.info(">>>>>> apicontroller consulta funcionario por id");
        Optional<Funcionario> funcionario = funcionarioServico.consultarPorId(id);
        if(funcionario.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(funcionario.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado com o ID: " + id);
        }
       
    } 
    @CrossOrigin
    @PostMapping("/cadastro")
    public ResponseEntity <Object> cadastrarFuncionario(@RequestBody Funcionario f){
        logger.info(">>>>>> apicontroller cadastrar funcionario");
        Optional<Funcionario> funcionario = funcionarioServico.cadastrar(f);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario.get());
    }
    
    @CrossOrigin
    @PostMapping("/atualizar")
    public ResponseEntity <Object> atualizar(Long id, Funcionario funcionario){
        logger.info(">>>>>> apicontroller atualizar funcionario");
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioServico.atualizar(id, funcionario));
    }
    
    @CrossOrigin("excluir/{id}")
    @DeleteMapping
    public ResponseEntity <Object> excluir(Long id){
        logger.info(">>>>>> apicontroller excluir funcionario");
        funcionarioServico.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario excluido com sucesso");
    }
}