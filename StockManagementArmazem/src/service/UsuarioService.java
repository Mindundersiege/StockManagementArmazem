package service;

import service.validacao.ValidacaoUsuario;
import service.ranqueamento.IRankingUsuarioStrategy;
import model.UsuarioF;
import dao.IUsuarioDAO;

import java.util.List;

public abstract class UsuarioService {

	/// ATRIBUTOS

	protected IUsuarioDAO usuarioDAO;
	
	/// METODOS

	public abstract String adicionar(UsuarioF usuario);

	public abstract String alterar(UsuarioF usuario, UsuarioF usuarioAlterado);

	public abstract String remover(UsuarioF usuario);

	public abstract String consultar(UsuarioF usuario);

	public abstract List<String> consultarTodos();

	public abstract List<String> consultaEspecifica(List<String> params, List<String> keys);

	public abstract String bloquear(UsuarioF Usuario, BloqueioUsuario bloqueioUsuario, String causa);

	public abstract List<String> ranquear(IRankingUsuarioStrategy rankingUsuario);

	public abstract String validacao(UsuarioF usuario, ValidacaoUsuario validacaoUsuario);

	public abstract String verNotificacao();

	public abstract String entrarLoginSenha(String login, String senha);

}
