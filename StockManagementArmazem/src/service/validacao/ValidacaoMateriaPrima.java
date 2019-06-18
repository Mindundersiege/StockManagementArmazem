/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import model.Material;
import model.tiposMaterial.MateriaPrima;

/**
 * Representa uma validacao do material materiaPrima
 */
public class ValidacaoMateriaPrima extends ValidacaoMaterial{

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Material material) throws ServiceException {
        
        if ( !material.getClass().equals(MateriaPrima.class) ) 
            throw new ServiceException("Tipo de material inválido!");
        
        MateriaPrima materiaPrima = (MateriaPrima) material;
                
        for(String Assunto: materiaPrima.getAssunto()){
            boolean falhou = true;
            for(String tiposDeAssunto : materiaPrima.tiposDeAssuntos){
                if(Assunto.equals(tiposDeAssunto)) falhou = false;
            }
            if(falhou)
                throw new ServiceException("Tipo de assunto inválido!");
        }
                
        if(3 <= materiaPrima.getAutor().length() &&
           materiaPrima.getAutor().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
           !materiaPrima.getAutor().matches("[a-zA-Z[0-9][ \\t\\n\\x0B\\f\\r]]")){
            throw new 
                ServiceException("Autor inválido!");
        }
        
//        if(material.getDataDeLancamento() == null){
//         throw new ServiceException("Material criado sem data de entrada!");
//        }
        
        if(!(materiaPrima.getEdicao().length() > 0) &&
             materiaPrima.getEdicao().matches("[0-9][º]")){
            throw new ServiceException("Edição inválida!");
        }

        if(!(materiaPrima.getEditora().length() > 0) &&
             materiaPrima.getEditora().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
             materiaPrima.getEditora().substring(0, 1).matches("[0-9]") &&
             materiaPrima.getEditora().substring(0, 1).matches("[a-z]")){
            throw new ServiceException("Editora inválida!");
        }
        
        if(materiaPrima.getQuantidadeDeExemplaresEmprestados() < 0) 
            throw new ServiceException("Quantidade inválida de exemplares emprestados!");
        
        if(materiaPrima.getQuantidadeDeTotalDeExemplares() < 
                materiaPrima.getQuantidadeDeExemplaresEmprestados()) 
	    throw new ServiceException("Quantidade inválida de exemplares!");
                        
        if(!(materiaPrima.getTitulo().length() > 0) &&
             materiaPrima.getTitulo().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
             materiaPrima.getTitulo().substring(0, 1).matches("[0-9]") &&
             materiaPrima.getTitulo().substring(0, 1).matches("[a-z]")){
            throw new ServiceException("Titulo inválida!");
        }
                
        if(materiaPrima.getVolume() < 0){
            throw new ServiceException("Quantidade de volume inválida!");
        }
        
    }
        
}
