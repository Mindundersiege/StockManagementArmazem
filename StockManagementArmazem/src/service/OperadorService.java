package service;

import service.validacao.ValidacaoUsuario;
import service.ranqueamento.IRankingUsuarioStrategy;
import java.util.List;

import model.UsuarioF;

public class OperadorService extends UsuarioService {

	@Override
	public String adicionar(UsuarioF usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alterar(UsuarioF usuario, UsuarioF usuarioAlterado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remover(UsuarioF usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String consultar(UsuarioF usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bloquear(UsuarioF Usuario, BloqueioUsuario bloqueioUsuario, String causa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validacao(UsuarioF usuario, ValidacaoUsuario validacaoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String verNotificacao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String entrarLoginSenha(String login, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

}
