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
import model.tiposUsuario.ClienteFisico;

/**
 * Representa uma validacao do usuario clienteFisico
 */
public class ValidacaoClienteFisico extends ValidacaoUsuario{
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Usuario usuario) throws ServiceException {
        if ( !usuario.getClass().equals(ClienteFisico.class) ) 
            throw new ServiceException("Tipo de usuário inválido!");
        
        ClienteFisico clienteFisico = (ClienteFisico) usuario;
        
        if(clienteFisico.getRankingClienteFisico() < 0 || clienteFisico.getRankingClienteFisico() >5) 
            throw new ServiceException("ranking de clienteFisico é invalido!");
        
                        
        
        if(clienteFisico.getHMapId_DataDeEmprestimoLivros() == null||
           (clienteFisico.getHMapId_DataDeEmprestimoLivros().size() > clienteFisico.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Data De Empréstimo inválida!");
        }
        else{
            for(Map.Entry<String, ArrayList<Data>> objeto : 
                    clienteFisico.getHMapId_DataDeEmprestimoLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                ArrayList<Data> listaDatasEmprestimos = objeto.getValue();
                
                if(listaDatasEmprestimos == null || listaDatasEmprestimos.isEmpty())
                    throw new ServiceException("Lista ID - Data De Empréstimo inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(clienteFisico.getHMapId_RankingLivros() == null ||
           (clienteFisico.getHMapId_RankingLivros().size() > clienteFisico.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Ranking de Livros inválida!");
        }
        else{
            for(Map.Entry<String, Integer> objeto : 
                    clienteFisico.getHMapId_RankingLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                Integer RankingLivro = objeto.getValue();
                
                if(RankingLivro == null || (RankingLivro < 0 && RankingLivro > 5))
                    throw new ServiceException("Lista ID - Ranking de Livros inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(clienteFisico.getLivrosAlugados() == null){
            throw new ServiceException("Lista ID de Livros Alugados inválida!");
        }
        
        if(clienteFisico.getNumeroDevolucoes() < 0 && 
           clienteFisico.getNumeroDevolucoes() > clienteFisico.getLivrosAlugados().size())
            throw new ServiceException("Número de devoluções inválido!");
        
        if(clienteFisico.getNumeroLivrosPendentes() < 0 && 
           clienteFisico.getNumeroLivrosPendentes() > 
                clienteFisico.getLivrosAlugados().size())
            throw new ServiceException("Número de livros pendentes inválido!");
        
        if(clienteFisico.getNumeroEmprestimos() < 0 &&
           clienteFisico.getNumeroEmprestimos() != clienteFisico.getLivrosAlugados().size() &&
           clienteFisico.getNumeroEmprestimos() != (clienteFisico.getNumeroDevolucoes() + 
                clienteFisico.getNumeroLivrosPendentes()) )
            throw new ServiceException("Número de emprestimos inválido!");
        
    }
    
}
