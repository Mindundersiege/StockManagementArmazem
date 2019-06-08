package service;

import service.validacao.ValidacaoUsuario;
import exception.ServiceException;
import model.UsuarioF;

public class ValidacaoQuantidadeDeMovimentacoes implements ValidacaoUsuario{

	@Override
	public String validacao(UsuarioF usuario, String param, String key) throws ServiceException {
		if( !param.equals("quantidadeDeMovimentacoes"))
			throw new ServiceException("Parametro invalido!");
		
		if( Integer.parseInt(key) < 0 ) 
			throw new ServiceException("Quantidade de movimenta��es invalida!");
		
		return "OK";
	}

}
