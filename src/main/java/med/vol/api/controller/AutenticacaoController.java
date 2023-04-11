package med.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.vol.api.domain.usuario.DaDosAutenticacao;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DaDosAutenticacao dadosAutenticacao) {
		var token = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
		var authentication = authenticationManager.authenticate(token);

		return ResponseEntity.ok().build();
	}

}
