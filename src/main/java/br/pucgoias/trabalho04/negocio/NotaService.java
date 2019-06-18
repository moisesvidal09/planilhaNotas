package br.pucgoias.trabalho04.negocio;

import java.util.List;

import br.pucgoias.trabalho04.entidade.Nota;
import br.pucgoias.trabalho04.util.Trabalho04Exception;

/**
 * Interface que define as operacoes da camada de negocio de nota
 * @author Moises
 *
 */
public interface NotaService {
	
	/**
	 * Inclui uma nota
	 * 
	 * @param nota
	 * @return
	 * @throws Trabalho04Exception
	 */
	public Nota incluir(Nota nota) throws Trabalho04Exception;
	
	/**
	 * Altera uma nota
	 * @param nota
	 * @return
	 * @throws Trabalho04Exception
	 */
	public Nota alterar(Nota nota) throws Trabalho04Exception;
	
	/**
	 * Exclui uma nota
	 * @param id
	 * @throws Trabalho04Exception
	 */
	public void excluir(Integer id) throws Trabalho04Exception;
	
	/**
	 * Consulta uma nota pelo identificadors
	 * @param id
	 * @return
	 * @throws Trabalho04Exception
	 */
	public Nota consultar(Integer id) throws Trabalho04Exception;
	
	/**
	 * Lista todas as notas cadastradas
	 * @return
	 * @throws Trabalho04Exception
	 */
	public List<Nota> listar() throws Trabalho04Exception;

}
