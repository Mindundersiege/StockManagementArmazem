package service;

import service.validacao.ValidacaoUsuario;
import exception.ServiceException;
import model.Funcionario;
import model.UsuarioF;

public class ValidacaoSalario implements ValidacaoUsuario{

	@Override
	public String validacao(UsuarioF usuario, String param, String key) throws ServiceException {
		if( !usuario.getClass().equals(Funcionario.class) ) 
			throw new ServiceException("Tipo de usuario invalido!");
		
		if( !param.equals("salario") )
			throw new ServiceException("Parametro invalido!");
			
		if( !(Integer.parseInt(key) >= 0)) 
	            throw new ServiceException("Quantidade inv�lida de salario!");
	     
		return "OK";
	}
	

}