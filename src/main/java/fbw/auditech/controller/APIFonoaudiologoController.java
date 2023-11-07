package fbw.auditech.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fbw.auditech.model.Fonoaudiologo;
import fbw.auditech.service.IFonoaudiologoService;

@RestController
@RequestMapping("/api/v1/fonoaudiologos")
public class APIFonoaudiologoController {
    @Autowired
    IFonoaudiologoService fonoaudiologoServico;

    @CrossOrigin
    @GetMapping

    public ResponseEntity<Object> consultaFonoaudiologos() {
        return ResponseEntity.status(HttpStatus.OK).body(fonoaudiologoServico.consultaRegistroFonoaudiologos());
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3000/"})
    @GetMapping("/{id}")
    public ResponseEntity <Object> consultaPorId(@PathVariable String id){
        Optional<Fonoaudiologo> fonoaudiologo = fonoaudiologoServico.consultarPorId(id);

        if(fonoaudiologo.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(fonoaudiologo.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fonoaudiologo não encontrado com o ID: " + id);
        }
    }
    @CrossOrigin
    @PostMapping("/cadastro")
    public ResponseEntity <Object> cadastrarFonoaudiologo(@RequestBody Fonoaudiologo f){
        Optional<Fonoaudiologo> fonoaudiologo = fonoaudiologoServico.cadastrar(f);

        return ResponseEntity.status(HttpStatus.CREATED).body(fonoaudiologo.get());
    }
    @CrossOrigin
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarFonoaudiologo(@PathVariable Long id, @RequestBody Fonoaudiologo fonoaudiologoAtualizado) {
        Optional<Fonoaudiologo> fonoaudiologo = fonoaudiologoServico.atualizar(id, fonoaudiologoAtualizado);
        if (fonoaudiologo.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(fonoaudiologo.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fonoaudiologo não encontrado com o ID: " + id);
        }
    }

    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirFonoaudiologo(@PathVariable Long id) {
        try {
            fonoaudiologoServico.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body("Fonoaudiologo excluído com sucesso");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fonoaudiologo não encontrado com o ID: " + id);
        }
    }

}
