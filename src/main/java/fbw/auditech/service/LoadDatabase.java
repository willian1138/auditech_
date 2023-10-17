
package fbw.auditech.service;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import fbw.auditech.model.Foto;
import fbw.auditech.model.Funcionario;
import fbw.auditech.model.IFotoRepository;
import fbw.auditech.model.IFuncionarioRepository;
import fbw.auditech.model.Paciente;
import fbw.auditech.model.IPacienteRepository;
@Configuration
public class LoadDatabase {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	IFotoRepository fotoRepository;
	@Bean
	CommandLineRunner initDatabase(IPacienteRepository repository, IFuncionarioRepository repositoryF) {
		return args -> {
            Paciente paciente1 = new Paciente("Lince", 20, "10291827682", "felino@email.com",
            "Rua Laranjeiras", "123");
            Paciente paciente2 = new Paciente("Clarice", 30, "11212827682", "clarice@email.com",
            "Rua Laranjeiras", "456");
            repository.saveAll(Arrays.asList(paciente1, paciente2));
            logger.info(">>>>> loaddatabase -> 2 pacientes cadastrados no db.");

            // Upload - obtém a foto do caminho, atribui ao objeto foto e salva no banco de dados do servidor

            Path path = Paths.get("c:\\Users\\psilv\\Desktop\\fotopaciente\\produto1.jpg");
            InputStream file = Files.newInputStream(path);
            byte[] arquivo1 = file.readAllBytes();
            Foto foto = new Foto();
            foto.setId(1L); // associa o id do paciente ao id da foto
            foto.setNome("paciente1.jpg");
            foto.setCaminho("fotos/" + foto.getNome());
            foto.setArquivo(arquivo1);
            logger.info(">>>>> loaddatabase -> upload de arquivo foto realizado => " + arquivo1.length);
            fotoRepository.save(foto);

            path = Paths.get("c:\\Users\\psilv\\Desktop\\fotopaciente\\produto2.jpg");
            file = Files.newInputStream(path);
            byte[] arquivo2 = file.readAllBytes();
            foto = new Foto();
            foto.setId(2L); // associa o id do paciente ao id da foto
            foto.setNome("paciente2.jpg");
            foto.setCaminho("fotos/" + foto.getNome());
            foto.setArquivo(arquivo2);
            logger.info(">>>>> loaddatabase -> upload de arquivo foto realizado => " + arquivo2.length);
            fotoRepository.save(foto);

            Funcionario funcionario1 = new Funcionario("Willian", "10291827222", "wllian@email.com",
            "Rua acai", "comsono");
            Funcionario funcionario2 = new Funcionario( "Funcione", "11222827682", "funcione@email.com",
            "Rua ladeira", "semsono");
            repositoryF.save(funcionario1);
            repositoryF.save(funcionario2);
            logger.info(">>>>> loaddatabase -> 2 funcionarios cadastrados no db.");
        };
    }
}
