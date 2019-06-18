package br.pucgoias.trabalho04.persistencia;

import java.io.Serializable;
import java.util.List;

import br.pucgoias.trabalho04.util.Trabalho04Exception;

/**
 * Interface que define as operacoes da camada de persistencia generica
 * @author Moises
 *
 */
public interface GenericoDAO<T, ID extends Serializable> {
	
	/**
	 * Retorna a classe a ser persistida
	 * @return
	 */
	public Class<T> getObjectClass();
	
	/**
	 * Inclui um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws Trabalho04Exception
	 */
	public T incluir(T object) throws Trabalho04Exception;
	
	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws Trabalho04Exception
	 */
	public T alterar(T object) throws Trabalho04Exception;
	
	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws Trabalho04Exception
	 */
	public T consultar(Integer id) throws Trabalho04Exception;
	
	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws Trabalho04Exception
	 */
	public void excluir(Integer id) throws Trabalho04Exception;
	
	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws Trabalho04Exception
	 */
	public List<T> listar() throws Trabalho04Exception;
}
