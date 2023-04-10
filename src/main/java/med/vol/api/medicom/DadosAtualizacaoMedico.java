package med.vol.api.medicom;

import jakarta.validation.constraints.NotNull;
import med.vol.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
		@NotNull Long id,
		String nome, 
		String telefone, 
		DadosEndereco endereco) {

}
