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
import model.tiposUsuario.Operador;

/**
 * Representa uma validacao do usuario operador
 */
public class ValidacaoOperador extends ValidacaoUsuario{
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Usuario usuario) throws ServiceException {
        if ( !usuario.getClass().equals(Operador.class) ) 
            throw new ServiceException("Tipo de usuário inválido!");
        
        Operador operador = (Operador) usuario;
        
        if(operador.getRankingOperador() < 0 || operador.getRankingOperador() >5) 
            throw new ServiceException("ranking de operador é invalido!");
        
                        
        
        if(operador.getHMapId_DataDeEmprestimoLivros() == null||
           (operador.getHMapId_DataDeEmprestimoLivros().size() > operador.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Data De Empréstimo inválida!");
        }
        else{
            for(Map.Entry<String, ArrayList<Data>> objeto : 
                    operador.getHMapId_DataDeEmprestimoLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                ArrayList<Data> listaDatasEmprestimos = objeto.getValue();
                
                if(listaDatasEmprestimos == null || listaDatasEmprestimos.isEmpty())
                    throw new ServiceException("Lista ID - Data De Empréstimo inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(operador.getHMapId_RankingLivros() == null ||
           (operador.getHMapId_RankingLivros().size() > operador.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Ranking de Livros inválida!");
        }
        else{
            for(Map.Entry<String, Integer> objeto : 
                    operador.getHMapId_RankingLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                Integer RankingLivro = objeto.getValue();
                
                if(RankingLivro == null || (RankingLivro < 0 && RankingLivro > 5))
                    throw new ServiceException("Lista ID - Ranking de Livros inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(operador.getLivrosAlugados() == null){
            throw new ServiceException("Lista ID de Livros Alugados inválida!");
        }
        
        if(operador.getNumeroDevolucoes() < 0 && 
           operador.getNumeroDevolucoes() > operador.getLivrosAlugados().size())
            throw new ServiceException("Número de devoluções inválido!");
        
        if(operador.getNumeroLivrosPendentes() < 0 && 
           operador.getNumeroLivrosPendentes() > 
                operador.getLivrosAlugados().size())
            throw new ServiceException("Número de livros pendentes inválido!");
        
        if(operador.getNumeroEmprestimos() < 0 &&
           operador.getNumeroEmprestimos() != operador.getLivrosAlugados().size() &&
           operador.getNumeroEmprestimos() != (operador.getNumeroDevolucoes() + 
                operador.getNumeroLivrosPendentes()) )
            throw new ServiceException("Número de emprestimos inválido!");
        
    }
    
}
