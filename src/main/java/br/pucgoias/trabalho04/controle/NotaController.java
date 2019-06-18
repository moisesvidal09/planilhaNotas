package br.pucgoias.trabalho04.controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.pucgoias.trabalho04.entidade.Nota;
import br.pucgoias.trabalho04.negocio.NotaService;
import br.pucgoias.trabalho04.util.Trabalho04Exception;

/**
 * Classe que controla as notas
 * 
 * @author Moises
 *
 */
@ManagedBean(name = "notaController")
@RequestScoped
@Controller
public class NotaController {

	@Autowired
	private NotaBean notaBean;

	@Autowired
	private List<NotaBean> listaNotaBean;

	@Autowired
	private NotaService notaService;

	@SuppressWarnings("unchecked")
	public NotaController() {
		notaBean = new NotaBean();
	}

	public boolean validarDados(NotaBean notaBean) {

		int count = 0;
		String regex = "^[\\p{L}\\s\\'.-]+$";

		if (!notaBean.getNomeAluno().matches(regex)) {
			FacesMessage message = new FacesMessage("No campo nome deve ter apenas letras");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getNomeAluno() == "") {
			FacesMessage message = new FacesMessage("Preencha o campo nome");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getMatricula().toString() == "") {
			FacesMessage message = new FacesMessage("Preencha o campo matricula");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getMatricula().toString().matches("/[^0-9]+/g")) {
			FacesMessage message = new FacesMessage("A matricula não pode conter letras");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getNotaAluno() < 0) {
			FacesMessage message = new FacesMessage("A nota do aluno deve ser maior que 0(zero)");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getNotaAluno() > 10) {
			FacesMessage message = new FacesMessage("A nota do aluno deve ser menor que 10(dez)");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getDataAvaliacao().toString() == "") {
			FacesMessage message = new FacesMessage("Preenche o campo data da avaliação");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getSituacao().toString().matches(
				"/^(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|^(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$/g")) {
			FacesMessage message = new FacesMessage("Data inválida");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}
		if (notaBean.getSituacao().toString() == "") {
			FacesMessage message = new FacesMessage("Preenche o campo situação");
			this.getFacesContext().addMessage("formulario", message);
			count++;
		}

		if (count == 0)
			return true;
		else
			return false;
	}

	/**
	 * Metodo que constroi uma entidade nota apartir do FormularioBean e passar a
	 * entidade para a camada service
	 * 
	 * @return String, ou seja, uma pagina ou de sucesso ou de erro.
	 */
	public String incluir() {

		try {
			Nota nota = new Nota();

			// construção da entidade com um formularioBean
			nota.setNomeAluno(notaBean.getNomeAluno());
			nota.setMatricula(notaBean.getMatricula());
			nota.setNotaAluno(notaBean.getNotaAluno());
			nota.setDataAvaliacao(notaBean.getDataAvaliacao());
			nota.setSituacao(notaBean.getSituacao());

			// validações
			if (validarDados(notaBean)) {
				// tentar incluir
				getNotaService().incluir(nota);
				return "sucesso";
			}
		} catch (Exception e) {
			String msg = "Inclusao nao realizada. Movito: " + e.getMessage();
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
		return "";
	}

	/**
	 * Metodo que lista todas as notas
	 * 
	 * @return String
	 */
	public String listar() {

		try {
			List<Nota> listaNota = getNotaService().listar();

			// validação para encontrar registro
			if (listaNota == null || listaNota.size() == 0) {
				FacesMessage message = new FacesMessage("Nenhum registro encontrado");
				this.getFacesContext().addMessage("formilario", message);
			}

			listaNotaBean = new ArrayList<NotaBean>();

			// construindo Beans para preencher formulario
			for (Nota nota : listaNota) {
				NotaBean notaBean = new NotaBean();
				notaBean.setId(nota.getId());
				notaBean.setNomeAluno(nota.getNomeAluno());
				notaBean.setMatricula(nota.getMatricula());
				notaBean.setDataAvaliacao(nota.getDataAvaliacao());
				notaBean.setNotaAluno(nota.getNotaAluno());
				notaBean.setSituacao(nota.getSituacao());
				listaNotaBean.add(notaBean);
			}
			return "listar";
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "Listagem nao realizada. Movito: "
					+ ((e instanceof Trabalho04Exception ? ((Trabalho04Exception) e).getEx().getMessage() : ""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}

	}

	/**
	 * Metodo encarregado de consultar uma nota para preencher formularioBean de
	 * editar nota
	 * 
	 * @return String
	 */
	public String consultar() {

		try {
			String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			Nota nota = getNotaService().consultar(Integer.parseInt(id));

			// validação para encontrar registro
			if (nota == null) {
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			// construindo Beans para preencher formulario
			notaBean.setId(nota.getId());
			notaBean.setNomeAluno(nota.getNomeAluno());
			notaBean.setMatricula(nota.getMatricula());
			notaBean.setNotaAluno(nota.getNotaAluno());
			notaBean.setSituacao(nota.getSituacao());
			notaBean.setDataAvaliacao(nota.getDataAvaliacao());

			return "editar";

		} catch (Exception e) {
			String msg = "Consulta nao realizada. Movito: " + e.getMessage();
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}

	}

	/**
	 * Metodo encarregado de criar uma instancia de NotaBean
	 * 
	 * @return String
	 */
	public String criar() {

		try {
			notaBean = new NotaBean();

			return "criar";
		} catch (Exception e) {
			String msg = "Criacao nao realizada. Movito: " + e.getMessage();
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Metodo encarregado de excluir uma nota
	 * 
	 * @return String
	 */
	public String excluir() {

		try {

			HtmlInputHidden id = (HtmlInputHidden) this.getFacesContext().getViewRoot().findComponent("formulario:id");
			Nota nota = getNotaService().consultar((Integer) id.getValue());

			// validação para encontrar registro
			if (nota == null) {
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			// Mandando a entidade para a camada service
			getNotaService().excluir(nota.getId());

			return "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "Exclusao nao realizada. Movito: "
					+ ((e instanceof Trabalho04Exception ? ((Trabalho04Exception) e).getEx().getMessage() : ""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
	}

	/**
	 * Metodo encarregado de alterar uma nota
	 * 
	 * @return String
	 */
	public String alterar() {

		try {

			Nota nota = getNotaService().consultar(notaBean.getId());

			// validação para encontrar registro
			if (nota == null) {
				FacesMessage message = new FacesMessage("Nenhum registro encontrado.");
				this.getFacesContext().addMessage("formulario", message);
				return "listar";
			}

			// criando uma entidade com os dados do Bean de formulario
			nota.setId(notaBean.getId());
			nota.setNomeAluno(notaBean.getNomeAluno());
			nota.setMatricula(notaBean.getMatricula());
			nota.setNotaAluno(notaBean.getNotaAluno());
			nota.setDataAvaliacao(notaBean.getDataAvaliacao());
			nota.setSituacao(notaBean.getSituacao());

			// chamando metodo para validar todos os dados
			if (validarDados(notaBean)) {
				// Manda a entidade para a camada service
				getNotaService().alterar(nota);
				return "sucesso";
			}
		} catch (Exception e) {
			String msg = "Alteracao nao realizada. Movito: "
					+ ((e instanceof Trabalho04Exception ? ((Trabalho04Exception) e).getEx().getMessage() : ""));
			FacesMessage message = new FacesMessage(msg);
			this.getFacesContext().addMessage("formulario", message);
			return "falha";
		}
		return "";
	}

	public NotaService getNotaService() {
		return notaService;
	}

	public void setNotaService(NotaService notaService) {
		this.notaService = notaService;
	}

	private FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	private Object getSession(String variavel) {
		return this.getFacesContext().getExternalContext().getSessionMap().get(variavel);
	}

	private void setSession(String variavel, Object objeto) {
		this.getFacesContext().getExternalContext().getSessionMap().put(variavel, objeto);
	}

	public NotaBean getNotaBean() {
		return notaBean;
	}

	public void setNotaBean(NotaBean notaBean) {
		this.notaBean = notaBean;
	}

	public List<NotaBean> getListaNotaBean() {
		return listaNotaBean;
	}

	public void setListaNotaBean(List<NotaBean> listaNotaBean) {
		this.listaNotaBean = listaNotaBean;
	}

}
