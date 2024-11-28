package com.google.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

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

	@GetMapping("/")
	public UsuarioHttp ping() {
		UsuarioHttp usuario = new UsuarioHttp(usuarioRepositorio.getById(1).get());
		return usuario;
	}

	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody UsuarioHttp credenciales) {
		Optional<Usuario> usuarioObtenido = usuarioRepositorio.getByEmail(credenciales.getEmail());

		if(usuarioObtenido.isPresent()) {
			if(Crypto.EsHashIdentico(usuarioObtenido.get().getContrasenya(), credenciales.getContrasenya())) {
				return ResponseEntity.status(200).body("Ok");
			}
		}

		return ResponseEntity.status(400).body("Credenciales incorrectas.");
	}
}