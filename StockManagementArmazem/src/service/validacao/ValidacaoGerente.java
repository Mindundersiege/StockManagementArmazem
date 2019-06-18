/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import java.util.ArrayList;
import java.util.Map;
import model.Data;
import model.Usuario;
import model.tiposUsuario.Gerente;

/**
 * Representa uma validacao do usuario gerente
 */
public class ValidacaoGerente extends ValidacaoUsuario{
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Usuario usuario) throws ServiceException {
        if ( !usuario.getClass().equals(Gerente.class) ) 
            throw new ServiceException("Tipo de usuário inválido!");
        
        Gerente gerente = (Gerente) usuario;
        
        if(gerente.getRankingGerente() < 0 || gerente.getRankingGerente() >5) 
            throw new ServiceException("ranking de gerente é invalido!");
        
                        
        
        if(gerente.getHMapId_DataDeEmprestimoLivros() == null||
           (gerente.getHMapId_DataDeEmprestimoLivros().size() > gerente.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Data De Empréstimo inválida!");
        }
        else{
            for(Map.Entry<String, ArrayList<Data>> objeto : 
                    gerente.getHMapId_DataDeEmprestimoLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                ArrayList<Data> listaDatasEmprestimos = objeto.getValue();
                
                if(listaDatasEmprestimos == null || listaDatasEmprestimos.isEmpty())
                    throw new ServiceException("Lista ID - Data De Empréstimo inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(gerente.getHMapId_RankingLivros() == null ||
           (gerente.getHMapId_RankingLivros().size() > gerente.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Ranking de Livros inválida!");
        }
        else{
            for(Map.Entry<String, Integer> objeto : 
                    gerente.getHMapId_RankingLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                Integer RankingLivro = objeto.getValue();
                
                if(RankingLivro == null || (RankingLivro < 0 && RankingLivro > 5))
                    throw new ServiceException("Lista ID - Ranking de Livros inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(gerente.getLivrosAlugados() == null){
            throw new ServiceException("Lista ID de Livros Alugados inválida!");
        }
        
        if(gerente.getNumeroDevolucoes() < 0 && 
           gerente.getNumeroDevolucoes() > gerente.getLivrosAlugados().size())
            throw new ServiceException("Número de devoluções inválido!");
        
        if(gerente.getNumeroLivrosPendentes() < 0 && 
           gerente.getNumeroLivrosPendentes() > 
                gerente.getLivrosAlugados().size())
            throw new ServiceException("Número de livros pendentes inválido!");
        
        if(gerente.getNumeroEmprestimos() < 0 &&
           gerente.getNumeroEmprestimos() != gerente.getLivrosAlugados().size() &&
           gerente.getNumeroEmprestimos() != (gerente.getNumeroDevolucoes() + 
                gerente.getNumeroLivrosPendentes()) )
            throw new ServiceException("Número de emprestimos inválido!");
        
    }
    
}
