package it.prova.gestionetratte;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.service.TrattaService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner {

	@Autowired
	private AirbusService airbusService;

	@Autowired
	private TrattaService trattaService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Airbus airbus = airbusService.findByCodiceAndDescrizione("123x", "airbus1");

		if (airbus == null) {
			airbus = new Airbus("123x", "airbus1",
					LocalDate.parse("01-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 20);
			airbusService.inserisciNuovo(airbus);
		}

		Tratta tratta = new Tratta("dsa5", "Roma-Milano",
				LocalDate.parse("01-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				LocalTime.parse("15.00", DateTimeFormatter.ofPattern("HH.mm")),
				LocalTime.parse("18.00", DateTimeFormatter.ofPattern("HH.mm")), airbus);
		if (trattaService.findByCodiceAndDescrizione(tratta.getCodice(), tratta.getDescrizione()).isEmpty())
			trattaService.inserisciNuovo(tratta);

		// ----------------------

		Airbus airbus2 = airbusService.findByCodiceAndDescrizione("4556f", "airbus2");

		if (airbus2 == null) {
			airbus2 = new Airbus("4556f", "airbus2",
					LocalDate.parse("12-12-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")), (Integer) 50);
			airbusService.inserisciNuovo(airbus2);
		}

		Tratta tratta2 = new Tratta("223bx", "Torino-Londra",
				LocalDate.parse("12-12-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				LocalTime.parse("10.00", DateTimeFormatter.ofPattern("HH.mm")),
				LocalTime.parse("13.00", DateTimeFormatter.ofPattern("HH.mm")), airbus2);
		if (trattaService.findByCodiceAndDescrizione(tratta2.getCodice(), tratta2.getDescrizione()).isEmpty())
			trattaService.inserisciNuovo(tratta2);

	}

}
