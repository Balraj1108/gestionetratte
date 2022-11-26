package it.prova.gestionetratte.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.model.StatoTratta;
import it.prova.gestionetratte.model.Tratta;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrattaDTO {

	private Long id;
	
	@NotBlank(message = "{codice.notblank}")
	private String codice;
	
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	
	@NotNull(message = "{data.notnull}")
	private LocalDate data;
	
	@NotNull(message = "{oraDecollo.notnull}")
	private LocalTime oraDecollo;
	
	@NotNull(message = "{oraAtterraggio.notnull}")
	private LocalTime oraAtterraggio;
	
	@NotNull(message = "{stato.notnull}")
	private StatoTratta stato;
	
	@JsonIgnoreProperties(value = { "tratte" })
	@NotNull(message = "{airbus.notnull}")
	private AirbusDTO airbusDTO;
	
	public TrattaDTO() {
	}

	public TrattaDTO(Long id,  String codice,
			 String descrizione,
			 LocalDate data,
			 LocalTime oraDecollo,
			 LocalTime oraAtterraggio,
			 StatoTratta stato,
			 AirbusDTO airbusDTO) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraDecollo = oraDecollo;
		this.oraAtterraggio = oraAtterraggio;
		this.stato = stato;
		this.airbusDTO = airbusDTO;
	}
	
	public TrattaDTO(Long id,  String codice,
			 String descrizione,
			 LocalDate data,
			 LocalTime oraDecollo,
			 LocalTime oraAtterraggio,
			 StatoTratta stato
			 ) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraDecollo = oraDecollo;
		this.oraAtterraggio = oraAtterraggio;
		this.stato = stato;
	}
	
	public TrattaDTO(String codice,
			 String descrizione
			 
			 ) {
		super();
		
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOraDecollo() {
		return oraDecollo;
	}

	public void setOraDecollo(LocalTime oraDecollo) {
		this.oraDecollo = oraDecollo;
	}

	public LocalTime getOraAtterraggio() {
		return oraAtterraggio;
	}

	public void setOraAtterraggio(LocalTime oraAtterraggio) {
		this.oraAtterraggio = oraAtterraggio;
	}

	public StatoTratta getStato() {
		return stato;
	}

	public void setStato(StatoTratta stato) {
		this.stato = stato;
	}

	public AirbusDTO getAirbusDTO() {
		return airbusDTO;
	}

	public void setAirbusDTO(AirbusDTO airbusDTO) {
		this.airbusDTO = airbusDTO;
	}
	
	public Tratta buildTrattaModel() {
		Tratta result = new Tratta(this.id, this.codice, this.descrizione, this.data, this.oraDecollo, this.oraAtterraggio, this.stato);
		
		if (this.airbusDTO != null)
			result.setAirbus(this.airbusDTO.buildAirbusModel());

		return result;
	}

	public static TrattaDTO buildTrattaDTOFromModel(Tratta trattaModel, boolean includeAirbuss) {
		TrattaDTO result = new TrattaDTO(trattaModel.getId(), trattaModel.getCodice(), trattaModel.getDescrizione(),
				trattaModel.getData(), trattaModel.getOraDecollo(), trattaModel.getOraAtterraggio(), trattaModel.getStato());

		if (includeAirbuss)
			result.setAirbusDTO(AirbusDTO.buildAirbusDTOFromModel(trattaModel.getAirbus(), false));

		return result;
	}

	public static List<TrattaDTO> createTrattaDTOListFromModelList(List<Tratta> modelListInput, boolean includeAirbuss) {
		return modelListInput.stream().map(trattaEntity -> {
			return TrattaDTO.buildTrattaDTOFromModel(trattaEntity, includeAirbuss);
		}).collect(Collectors.toList());
	}

	public static Set<TrattaDTO> createTrattaDTOSetFromModelSet(Set<Tratta> modelListInput, boolean includeAirbus) {
		return modelListInput.stream().map(trattaEntity -> {
			return TrattaDTO.buildTrattaDTOFromModel(trattaEntity, includeAirbus);
		}).collect(Collectors.toSet());
	}
	
	
	
	
}
