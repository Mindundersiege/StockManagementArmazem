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
import model.Usuario;
import model.tiposUsuario.ClienteJuridico;
import service.bloqueio.BloqueioUsuario;

//Revisar classe!

public class ClienteJuridicoService extends UsuarioService{

	ClienteJuridicoService(){
		this.usuarioDAO = new ClienteJuridicoDAOJDBC();
	}
	
	@Override
	public String adicionar(Usuario usuario) {
	
		ClienteJuridico clienteJuridico = (ClienteJuridico) usuario;
		
		try {	
			usuarioDAO.adicionar(clienteJuridico);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String alterar(Usuario usuario, Usuario usuarioAlterado) {

		ClienteJuridico clienteJuridico = (ClienteJuridico) usuario;
		
		try {	
			usuarioDAO.alterar(clienteJuridico);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String remover(Usuario usuario) {
		ClienteJuridico clienteJuridico = (ClienteJuridico) usuario;
		
		try {	
			usuarioDAO.remover(clienteJuridico);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String consultar(Usuario usuario) {

		try {	
			ClienteJuridico clienteJuridico = (ClienteJuridico) usuarioDAO.consultar(clienteJuridico.getIdUsuario());
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