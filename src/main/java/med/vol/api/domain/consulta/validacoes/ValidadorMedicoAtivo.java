package med.vol.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.medico.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{

	@Autowired
	private MedicoRepository medicoRepository;

	public void validar(DadosAgendamentoConsulta dados) {

		if (dados.idMedico() == null) {
			return;
		}

		var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());

		if (!medicoEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
		}
	}
}
