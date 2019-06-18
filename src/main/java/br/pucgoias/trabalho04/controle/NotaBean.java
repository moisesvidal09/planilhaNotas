package br.pucgoias.trabalho04.controle;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Classe que representa uma Nota Bean, ou seja, ela ira guardar dados do formulario
 * @author Moises
 *
 */
@Component
public class NotaBean {

	private Integer id;
	private String nomeAluno;
	private Integer matricula;
	private Date dataAvaliacao;
	private double notaAluno;
	private String situacao;


	/**
	 * @return the idNota
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param idNota the idNota to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public double getNotaAluno() {
		return notaAluno;
	}

	public void setNotaAluno(double notaAluno) {
		this.notaAluno = notaAluno;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
