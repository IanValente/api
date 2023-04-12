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
import med.vol.api.domain.usuario.Usuario;
import med.vol.api.infra.security.DadosTokenJWT;
import med.vol.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DaDosAutenticacao dadosAutenticacao) {
		var autenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(),
				dadosAutenticacao.senha());
		var authentication = authenticationManager.authenticate(autenticationToken);

		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}

}
