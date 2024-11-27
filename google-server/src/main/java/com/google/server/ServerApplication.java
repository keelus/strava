package com.google.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UsuarioRepositorio repositorio) {
		return args -> {
			Usuario usuario = new Usuario();
			usuario.setEmail("hola@gmail.com");
			usuario.setContrasenya("1234");

			repositorio.guardar(usuario);
			Usuario usuarioGuardado = repositorio.buscarPorId(usuario.getId()).orElseThrow(NoSuchElementException::new);
		};
	}
}

@RestController
class HolaController {

	@RequestMapping("/")
	public String ping() {
		return "Pong";
	}
}