package com.google.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario user1 = new Usuario("user1@gmail.com", "password1");
		usuarioRepositorio.save(user1);
	}

}

@RestController
class HolaController {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@RequestMapping("/")
	public String ping() {
		return "Pong";
	}

	@RequestMapping("/crear")
	public String crear() {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setEmail("adios@gmail.com");
		nuevoUsuario.setContrasenya("1234");
		// How to access interface UsuarioRepositorio
		usuarioRepositorio.save(nuevoUsuario);
		return "Usuario creado?";
	}

	@RequestMapping("/listar")
	public String listar() {
		String cuerpo = "<ul>";
		for (Usuario usuario : usuarioRepositorio.findAll()) {
			cuerpo += "<li>" + usuario.toString() + "</li>";
		}
		cuerpo += "<ul>";
		return cuerpo;
	}
}