package br.pucgoias.trabalho04.util;

/**
 * Classe que encapsula as excecoes da aplicacao Nota
 * @author Moises
 *
 */
public class Trabalho04Exception extends Exception {
	
	private static final long serialVersionUID = 1189188521388183949L;
	private Exception ex;
	private String msg;

	public Trabalho04Exception(Exception e){
		ex = e;
		msg = e.getMessage();
	}

	public Trabalho04Exception(Exception e, String mensagem){
		e.printStackTrace();
		ex = e;
		msg = mensagem;
	}

	public Exception getEx() {
		return ex;
	}

	public String getMsg() {
		return msg;
	}
	
}
