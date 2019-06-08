package service;

import service.validacao.ValidacaoMaterial;
import exception.ServiceException;
import model.MaterialF;

public class ValidacaoAlteracaoEspacoTotal implements ValidacaoMaterial {

	/// MÉTODOS

	public String validacao(MaterialF material, String param, String key) throws ServiceException {

		if(!param.equals("quantidadeEspacoTotal"))
			throw new ServiceException("Parametro invalido!");
			
		if( Integer.parseInt(key) < material.getQuantidadeMateriasTotal() ) 
	            throw new ServiceException("Quantidade inv�lida de espaco!");
	     
		return "OK";
	}

}
