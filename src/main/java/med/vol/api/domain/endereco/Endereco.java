package med.vol.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	public Endereco(DadosEndereco dados) {
		this.logradouro = dados.logradouro();
		this.bairro = dados.bairro();
		this.cep = dados.cep();
		this.uf = dados.uf();
		this.cidade = dados.cidade();
		this.numero = dados.numero();
		this.complemento = dados.complemento();
	}

	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;

	public void atualizarInformacoes(DadosEndereco endereco) {
		if (endereco.logradouro() != null) {
			this.logradouro = endereco.logradouro();
		}
		if (endereco.bairro() != null) {
			this.bairro = endereco.bairro();
		}
		if (endereco.cep() != null) {
			this.cep = endereco.cep();
		}
		if (endereco.uf() != null) {
			this.uf = endereco.uf();
		}
		if (endereco.cidade() != null) {
			this.cidade = endereco.cidade();
		}
		if (endereco.numero() != null) {
			this.numero = endereco.numero();
		}
		if (endereco.complemento() != null) {
			this.complemento = endereco.complemento();
		}
	}

}
