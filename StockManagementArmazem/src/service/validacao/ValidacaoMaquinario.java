/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import model.Material;
import model.tiposMaterial.Maquinario;

/**
 * Representa uma validacao do material maquinario
 */
public class ValidacaoMaquinario extends ValidacaoMaterial{

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Material material) throws ServiceException {
        
        if ( !material.getClass().equals(Maquinario.class) ) 
            throw new ServiceException("Tipo de material inválido!");
        
        Maquinario maquinario = (Maquinario) material;
                
        for(String Assunto: maquinario.getAssunto()){
            boolean falhou = true;
            for(String tiposDeAssunto : maquinario.tiposDeAssuntos){
                if(Assunto.equals(tiposDeAssunto)) falhou = false;
            }
            if(falhou)
                throw new ServiceException("Tipo de assunto inválido!");
        }
                
        if(3 <= maquinario.getAutor().length() &&
           maquinario.getAutor().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
           !maquinario.getAutor().matches("[a-zA-Z[0-9][ \\t\\n\\x0B\\f\\r]]")){
            throw new 
                ServiceException("Autor inválido!");
        }
        
//        if(material.getDataDeLancamento() == null){
//         throw new ServiceException("Material criado sem data de entrada!");
//        }
        
        if(!(maquinario.getEdicao().length() > 0) &&
             maquinario.getEdicao().matches("[0-9][º]")){
            throw new ServiceException("Edição inválida!");
        }

        if(!(maquinario.getEditora().length() > 0) &&
             maquinario.getEditora().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
             maquinario.getEditora().substring(0, 1).matches("[0-9]") &&
             maquinario.getEditora().substring(0, 1).matches("[a-z]")){
            throw new ServiceException("Editora inválida!");
        }
        
        if(maquinario.getQuantidadeDeExemplaresEmprestados() < 0) 
            throw new ServiceException("Quantidade inválida de exemplares emprestados!");
        
        if(maquinario.getQuantidadeDeTotalDeExemplares() < 
                maquinario.getQuantidadeDeExemplaresEmprestados()) 
	    throw new ServiceException("Quantidade inválida de exemplares!");
                        
        if(!(maquinario.getTitulo().length() > 0) &&
             maquinario.getTitulo().substring(0, 1).matches("[ \\t\\n\\x0B\\f\\r]") &&
             maquinario.getTitulo().substring(0, 1).matches("[0-9]") &&
             maquinario.getTitulo().substring(0, 1).matches("[a-z]")){
            throw new ServiceException("Titulo inválida!");
        }
                
        if(maquinario.getVolume() < 0){
            throw new ServiceException("Quantidade de volume inválida!");
        }
        
    }
        
}
