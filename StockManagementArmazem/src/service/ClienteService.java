package service;

import service.validacao.ValidacaoUsuario;
import service.ranqueamento.IRankingUsuarioStrategy;
import java.util.List;

import dao.ClienteDAOJDBC;
import dao.ClienteDAOJDBC;
import exception.ServiceException;
import model.ClienteFisicoF;
import model.UsuarioF;

//Revisar classe!

public class ClienteService extends UsuarioService{

	ClienteService(){
		this.usuarioDAO = new ClienteDAOJDBC();
	}
	
	@Override
	public String adicionar(UsuarioF usuario) {
	
		ClienteFisicoF cliente = (ClienteFisicoF) usuario;
		
		try {	
			usuarioDAO.adicionar(cliente);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String alterar(UsuarioF usuario, UsuarioF usuarioAlterado) {

		ClienteFisicoF cliente = (ClienteFisicoF) usuario;
		
		try {	
			usuarioDAO.alterar(cliente);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String remover(UsuarioF usuario) {
		ClienteFisicoF cliente = (ClienteFisicoF) usuario;
		
		try {	
			usuarioDAO.remover(cliente);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String consultar(UsuarioF usuario) {

		try {	
			ClienteFisicoF cliente = (ClienteFisicoF) usuarioDAO.consultar(cliente.getIdUsuario());
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
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
