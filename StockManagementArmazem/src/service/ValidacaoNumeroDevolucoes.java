package service;

import service.validacao.ValidacaoUsuario;
import exception.ServiceException;
import model.ClienteFisicoF;
import model.UsuarioF;

public class ValidacaoNumeroDevolucoes implements ValidacaoUsuario{

	@Override
	public String validacao(UsuarioF usuario, String param, String key) throws ServiceException {
		if ( !usuario.getClass().equals(ClienteFisicoF.class) ) 
			throw new ServiceException("Tipo de usuario invalido!");
		
		ClienteFisicoF cliente = (ClienteFisicoF) usuario;
		
		if(!param.equals("numeroDevolucoes"))
			throw new ServiceException("Parametro invalido!");
			
		if(Integer.parseInt(key) < cliente.getLivrosAlugados().size()) 
	            throw new ServiceException("Valor de numero de devolu��es invalido!");
	     
		return "OK";
	}

}
