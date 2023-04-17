package med.vol.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.ConsultaRepository;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorPacienteSemConsultaNoDia implements ValidadorAgendamentoDeConsultas{

	@Autowired
	private ConsultaRepository consultaRepository;

	public void validar(DadosAgendamentoConsulta dados) {
		var primeiroHorario = dados.data().withHour(7);
		var ultimoHorario = dados.data().withHour(18);

		var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),
				primeiroHorario, ultimoHorario);
		if (pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoException("Paciente já possui outra consulta agendada nesse dia");
		}
	}

}
