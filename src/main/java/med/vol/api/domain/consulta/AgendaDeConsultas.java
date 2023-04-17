package med.vol.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsultas;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.MedicoRepository;
import med.vol.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidadorAgendamentoDeConsultas> validadores;

	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

		validaDadosAgendamento(dadosAgendamentoConsulta);
		
		validadores.forEach(v -> v.validar(dadosAgendamentoConsulta));

		var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsulta.idPaciente());
		var medico = escolherMedico(dadosAgendamentoConsulta);
		if(medico == null) {
			throw new ValidacaoException("Não existe médico disponível nessa data");
		}
		var consulta = new Consulta(null, medico, paciente, dadosAgendamentoConsulta.data());
		consultaRepository.save(consulta);
		
		return new DadosDetalhamentoConsulta(consulta);
	}

	private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
		if (dadosAgendamentoConsulta.idMedico() != null) {
			return medicoRepository.getReferenceById(dadosAgendamentoConsulta.idMedico());
		}

		if (dadosAgendamentoConsulta.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");
		}

		return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamentoConsulta.especialidade(),
				dadosAgendamentoConsulta.data());
	}

	private void validaDadosAgendamento(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
		if (!pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe!");
		}

		if (dadosAgendamentoConsulta.idMedico() != null
				&& !medicoRepository.existsById(dadosAgendamentoConsulta.idMedico())) {
			throw new ValidacaoException("Id do medico informado não existe!");
		}
	}

}
