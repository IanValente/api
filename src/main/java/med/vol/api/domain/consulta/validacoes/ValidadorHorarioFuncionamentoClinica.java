package med.vol.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas{

	public void validar(DadosAgendamentoConsulta dados) {
		var dataConsulta = dados.data();

		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

		var foraHorarioClinica = dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;

		if (domingo || foraHorarioClinica) {
			throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica!");
		}
	}

}
