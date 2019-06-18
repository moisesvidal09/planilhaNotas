package br.pucgoias.trabalho04.negocio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.pucgoias.trabalho04.entidade.Nota;
import br.pucgoias.trabalho04.persistencia.NotaDAO;
import br.pucgoias.trabalho04.util.Trabalho04Exception;


/**
 * Classe que define as operacoes da camada de persistencia de Nota
 * @author Moises
 *
 */
@Service
@Transactional
public class NotaSeriviceImpl implements NotaService {

	private NotaDAO notaDAO;

	public NotaDAO getNotaDAO() {
		return notaDAO;
	}

	@Autowired
	public void setNotaDAO(NotaDAO notaDAO) {
		this.notaDAO = notaDAO;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Nota incluir(Nota nota) throws Trabalho04Exception {
		return getNotaDAO().incluir(nota);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Nota alterar(Nota nota) throws Trabalho04Exception {

		return getNotaDAO().alterar(nota);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void excluir(Integer id) throws Trabalho04Exception {

		getNotaDAO().excluir(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Nota consultar(Integer id) throws Trabalho04Exception {
		Nota nota = getNotaDAO().consultar(id);
		return nota;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Nota> listar() throws Trabalho04Exception {
		return getNotaDAO().listar();
	}

}
