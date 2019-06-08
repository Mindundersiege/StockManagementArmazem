package service;

import service.validacao.ValidacaoUsuario;
import exception.ServiceException;
import model.UsuarioF;

public class ValidacaoSenha implements ValidacaoUsuario {

	public String validacao(UsuarioF usuario, String param, String key) throws ServiceException{

		if(!param.equals("senha"))
			throw new ServiceException("Parametro invalido!");
		
		if( !usuario.getSenha().equals(key) ) 
			throw new ServiceException("Senha invalida!");
		
		return "OK";
		
	}	
}
