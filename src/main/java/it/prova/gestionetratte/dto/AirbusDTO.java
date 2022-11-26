package it.prova.gestionetratte.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.model.Airbus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirbusDTO {

	private Long id;
	
	@NotBlank(message = "{codice.notblank}")
	private String codice;
	
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	
	@NotNull(message = "{dataInizioServizio.notnull}")
	private LocalDate dataInizioServizio; 
	
	@Positive
	@NotNull(message = "{numeroPasseggeri.notnull}")
	private Integer numeroPasseggeri;
	
	@JsonIgnoreProperties(value = { "airbus" })
	private List<TrattaDTO> tratte = new ArrayList<>();
	
	public AirbusDTO() {
	}

	public AirbusDTO(Long id, String codice,
			 String descrizione,
			 LocalDate dataInizioServizio,
			 Integer numeroPasseggeri, List<TrattaDTO> tratte) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
		this.tratte = tratte;
	}
	
	public AirbusDTO(Long id, String codice,
			 String descrizione,
			 LocalDate dataInizioServizio,
			 Integer numeroPasseggeri) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
	}
	
	public AirbusDTO(String codice,
			 String descrizione,
			 LocalDate dataInizioServizio,
			 Integer numeroPasseggeri) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
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

	public LocalDate getDataInizioServizio() {
		return dataInizioServizio;
	}

	public void setDataInizioServizio(LocalDate dataInizioServizio) {
		this.dataInizioServizio = dataInizioServizio;
	}

	public Integer getNumeroPasseggeri() {
		return numeroPasseggeri;
	}

	public void setNumeroPasseggeri(Integer numeroPasseggeri) {
		this.numeroPasseggeri = numeroPasseggeri;
	}

	public List<TrattaDTO> getTratte() {
		return tratte;
	}

	public void setTratte(List<TrattaDTO> tratte) {
		this.tratte = tratte;
	}
	
	public Airbus buildAirbusModel() {
		return new Airbus(this.id, this.codice, this.descrizione, this.dataInizioServizio, this.numeroPasseggeri);
	}

	public static AirbusDTO buildAirbusDTOFromModel(Airbus airbusModel, boolean includeTratte) {
		AirbusDTO result = new AirbusDTO(airbusModel.getId(), airbusModel.getCodice(), airbusModel.getDescrizione(),
				airbusModel.getDataInizioServizio(), airbusModel.getNumeroPasseggeri());
		
		if(includeTratte)
			result.setTratte(TrattaDTO.createTrattaDTOListFromModelList(airbusModel.getTratte(), false));
		
		return result;
	}

	public static List<AirbusDTO> createAirbusDTOListFromModelList(List<Airbus> modelListInput, boolean includeTratte) {
		return modelListInput.stream().map(airbusEntity -> {
			AirbusDTO result = AirbusDTO.buildAirbusDTOFromModel(airbusEntity,includeTratte);
			if(includeTratte)
				result.setTratte(TrattaDTO.createTrattaDTOListFromModelList(airbusEntity.getTratte(), false));
			
			return result;
		}).collect(Collectors.toList());
	}
	
	
	
	
	
	
}
