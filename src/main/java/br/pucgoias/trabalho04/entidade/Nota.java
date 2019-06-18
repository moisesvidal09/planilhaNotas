package br.pucgoias.trabalho04.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Classe que representa os dados persistentes de nota
 * @author Moises
 *
 */
@Entity
@Table(name = "NOTA")
public class Nota implements Serializable {

	private static final long serialVersionUID = 6487849002108370775L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NOMEALUNO")
	private String nomeAluno;

	@Column(name = "MATRICULA")
	private int matricula;

	@Column(name = "DATAAVALIACAO")
	private Date dataAvaliacao;

	@Column(name = "NOTAALUNO")
	private double notaAluno;

	@Column(name = "SITUACAO")
	private String situacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
