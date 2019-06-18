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
import model.tiposUsuario.ClienteJuridico;

/**
 * Representa uma validacao do usuario clienteJuridico
 */
public class ValidacaoClienteJuridico extends ValidacaoUsuario{
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Usuario usuario) throws ServiceException {
        if ( !usuario.getClass().equals(ClienteJuridico.class) ) 
            throw new ServiceException("Tipo de usuário inválido!");
        
        ClienteJuridico clienteJuridico = (ClienteJuridico) usuario;
        
        if(clienteJuridico.getRankingClienteJuridico() < 0 || clienteJuridico.getRankingClienteJuridico() >5) 
            throw new ServiceException("ranking de clienteJuridico é invalido!");
        
                        
        
        if(clienteJuridico.getHMapId_DataDeEmprestimoLivros() == null||
           (clienteJuridico.getHMapId_DataDeEmprestimoLivros().size() > clienteJuridico.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Data De Empréstimo inválida!");
        }
        else{
            for(Map.Entry<String, ArrayList<Data>> objeto : 
                    clienteJuridico.getHMapId_DataDeEmprestimoLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                ArrayList<Data> listaDatasEmprestimos = objeto.getValue();
                
                if(listaDatasEmprestimos == null || listaDatasEmprestimos.isEmpty())
                    throw new ServiceException("Lista ID - Data De Empréstimo inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(clienteJuridico.getHMapId_RankingLivros() == null ||
           (clienteJuridico.getHMapId_RankingLivros().size() > clienteJuridico.getLivrosAlugados().size()) ){
            throw new ServiceException("Lista ID - Ranking de Livros inválida!");
        }
        else{
            for(Map.Entry<String, Integer> objeto : 
                    clienteJuridico.getHMapId_RankingLivros().entrySet()) {
                
                String idLivro = objeto.getKey();
                Integer RankingLivro = objeto.getValue();
                
                if(RankingLivro == null || (RankingLivro < 0 && RankingLivro > 5))
                    throw new ServiceException("Lista ID - Ranking de Livros inválida!"
                            + "ID do livro: " + idLivro);
            }
        }
        
        
        if(clienteJuridico.getLivrosAlugados() == null){
            throw new ServiceException("Lista ID de Livros Alugados inválida!");
        }
        
        if(clienteJuridico.getNumeroDevolucoes() < 0 && 
           clienteJuridico.getNumeroDevolucoes() > clienteJuridico.getLivrosAlugados().size())
            throw new ServiceException("Número de devoluções inválido!");
        
        if(clienteJuridico.getNumeroLivrosPendentes() < 0 && 
           clienteJuridico.getNumeroLivrosPendentes() > 
                clienteJuridico.getLivrosAlugados().size())
            throw new ServiceException("Número de livros pendentes inválido!");
        
        if(clienteJuridico.getNumeroEmprestimos() < 0 &&
           clienteJuridico.getNumeroEmprestimos() != clienteJuridico.getLivrosAlugados().size() &&
           clienteJuridico.getNumeroEmprestimos() != (clienteJuridico.getNumeroDevolucoes() + 
                clienteJuridico.getNumeroLivrosPendentes()) )
            throw new ServiceException("Número de emprestimos inválido!");
        
    }
    
}
