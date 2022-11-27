package it.prova.gestionetratte.web.api.exception;

public class RimozioneTrattaNonAnnullataException extends RuntimeException{

	private static final long serialVersionUID = 1L;


	public RimozioneTrattaNonAnnullataException(String message) {
		super(message);
	}
}
