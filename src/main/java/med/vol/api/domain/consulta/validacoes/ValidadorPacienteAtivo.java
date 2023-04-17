package med.vol.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas{

	@Autowired
	private PacienteRepository pacienteRepository;

	public void validar(DadosAgendamentoConsulta dados) {

		if (dados.idPaciente() == null) {
			return;
		}

		var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

		if (!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
		}
	}
}
