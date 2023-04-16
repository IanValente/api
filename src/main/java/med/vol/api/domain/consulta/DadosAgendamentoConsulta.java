package med.vol.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.medico.Especialidade;

public record DadosAgendamentoConsulta(Long idMedico,

		@NotNull Long idPaciente,

		@NotNull @Future LocalDateTime data,

		Especialidade especialidade) {
}
