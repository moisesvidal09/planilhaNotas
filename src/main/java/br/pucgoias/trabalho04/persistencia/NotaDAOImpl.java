package br.pucgoias.trabalho04.persistencia;

import org.springframework.stereotype.Repository;

import br.pucgoias.trabalho04.entidade.Nota;

/**
 * Classe que define as operacoes da camada de persistencia de Pessoa
 * @author Moises
 *
 */


@Repository
public class NotaDAOImpl extends GenericoDAOImpl<Nota, Integer> implements
		NotaDAO {

}
