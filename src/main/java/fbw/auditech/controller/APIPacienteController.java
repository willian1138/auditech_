package fbw.auditech.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;//nao esta reconhecendo esse import
// usado para tratar a exception do delete

import fbw.auditech.model.Foto;
import fbw.auditech.model.Paciente;
import fbw.auditech.service.IPacienteService;
import fbw.auditech.service.FotoService;

@RestController
@RequestMapping("api/v1/pacientes")
public class APIPacienteController {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	IPacienteService pacienteServico;
	/*
	 * consulta catalogo retorna um arquivo json de pacientes
	 */
	@CrossOrigin
	@GetMapping
	public ResponseEntity <Object> consultaTodos(){
		logger.info(">>>>>> apicontroller consulta todos");
		return ResponseEntity.status(HttpStatus.OK).body(pacienteServico.consultaRegistroPacientes());
	}

	@CrossOrigin
	@PostMapping("/cadastro")
	public ResponseEntity<Object> cadastrarPaciente(@RequestBody Paciente p){
		logger.info(">>>>>>apicontroller cadastrar paciente iniciado");
		Optional<Paciente> paciente = pacienteServico.cadastrar(p);

		return ResponseEntity.status(HttpStatus.CREATED).body(paciente.get());
	}

	@Autowired
	FotoService fotoService;

	@CrossOrigin
	@PostMapping("/imadb")
	public ResponseEntity<String> upload( @RequestParam("id") long id, @RequestParam ("file") MultipartFile file) {
{ 
 logger.info(">>>>>> api upload iniciada...");
try { 
 	logger.info(">>>>>> api manipula file upload chamou servico salvar");
 	
	//long codPaciente = Long.parseLong(id);
	long codPaciente = id;
	Optional<Foto> p = fotoService.salvar(codPaciente ,file);
 	
	if (p.isPresent()) { 
 		return ResponseEntity.ok().body("Imagem enviada com sucesso");
 	} else { 
 		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id invalido não localizado");
 	} 

} catch (FileNotFoundException e) { 
 	logger.info(">>>>>> api manipula file upload arquivo não encontrado");
 	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo nao encontrado");

} catch (FileUploadException e) { 
 	logger.info(">>>>>> api manipula file upload erro no upload");
 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao enviar o arquivo");

} catch (IOException e) { 
 	logger.info(">>>>>> api manipula file upload erro de i/o => " + e.getMessage());
 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha erro de I/O");

} catch (NumberFormatException e) { 
 	logger.info(">>>>>> api manipula file upload erro de i/o => " + e.getMessage());
 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id invalido");
} 
}

}


@CrossOrigin
@PutMapping("/atualizar/{id}")
public ResponseEntity<Object> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado){
	logger.info(">>>>>>apicontroller atualizar paciente iniciado");
	Optional<Paciente> paciente = pacienteServico.atualizar(id, pacienteAtualizado);
	
	if (paciente.isPresent()) {
        return ResponseEntity.status(HttpStatus.OK).body(paciente.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado com o ID: " + id);
    }
}

@CrossOrigin
@DeleteMapping("/excluir/{id}")
public ResponseEntity<Object> excluirPaciente(@PathVariable Long id) {
	logger.info(">>>>>>apicontroller excluir paciente iniciado");
    try {
        pacienteServico.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (ResourceNotFoundException e) {//pesquisando para tratar esse essa expection
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado com o ID: " + id);
    }
}

//testato no insomnia nao está localizando o path vou tentar trocar longa para UUID e vamos ver tambem excluirei esse tal slug
//vamos ver 

}