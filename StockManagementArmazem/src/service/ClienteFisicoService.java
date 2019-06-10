package service;

/**
 *
 * @author Thiago
 */

import service.raqueamento.IRankingUsuarioStrategy;
import service.validacao.ValidacaoUsuario;
import java.util.List;

import dao.ClienteDAOJDBC;
import dao.ClienteDAOJDBC;
import exception.ServiceException;
import model.tiposUsuario.ClienteFisico;
import model.Usuario;
import service.bloqueio.BloqueioUsuario;

//Revisar classe!

public class ClienteFisicoService extends UsuarioService{

	ClienteFisicoService(){
		this.usuarioDAO = new ClienteFisicoDAOJDBC();
	}
	
	@Override
	public String adicionar(Usuario usuario) {
	
		ClienteFisico clienteFisico = (ClienteFisico) usuario;
		
		try {	
			usuarioDAO.adicionar(clienteFisico);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String alterar(Usuario usuario, Usuario usuarioAlterado) {

		ClienteFisico clienteFisico = (ClienteFisico) usuario;
		
		try {	
			usuarioDAO.alterar(clienteFisico);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String remover(Usuario usuario) {
		ClienteFisico clienteFisico = (ClienteFisico) usuario;
		
		try {	
			usuarioDAO.remover(clienteFisico);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String consultar(Usuario usuario) {

		try {	
			ClienteFisico clienteFisico = (ClienteFisico) usuarioDAO.consultar(clienteFisico.getIdUsuario());
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
	public String bloquear(Usuario Usuario, BloqueioUsuario bloqueioUsuario, String causa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validacao(Usuario usuario, ValidacaoUsuario validacaoUsuario) {
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
