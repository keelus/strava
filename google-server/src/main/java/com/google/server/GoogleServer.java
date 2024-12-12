package com.google.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SpringBootApplication
public class GoogleServer implements CommandLineRunner {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(GoogleServer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario1 = new Usuario("usuario1@gmail.com", "1234");
		usuarioRepositorio.save(usuario1);

		Usuario usuario2 = new Usuario("usuario2@gmail.com", "1234");
		usuarioRepositorio.save(usuario2);
	}
}

@RestController
class GoogleController {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody UsuarioHttp credenciales) {
		Optional<Usuario> usuarioObtenido = usuarioRepositorio.getByEmail(credenciales.getEmail());

		if(usuarioObtenido.isPresent()) {
			if(Crypto.EsHashIdentico(usuarioObtenido.get().getContrasenya(), credenciales.getContrasenya())) {
				return ResponseEntity.status(200).body("Ok");
			}
		}

		return ResponseEntity.status(401).body("Las credenciales introducidas no son correctas, o el email no existe.");
	}
}