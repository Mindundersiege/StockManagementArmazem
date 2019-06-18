package service;

import service.ranqueamento.IRankingUsuarioStrategy;
import java.util.List;

import dao.GerenteJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.tiposUsuario.Gerente;
import model.Usuario;
import service.validacao.ValidacaoGerente;

//Revisar classe!

/**
 * Classe que representa as funcionalidades desempenhadas pelo Gerente
 * @see Gerente
 * @see Usuario
 */
public class GerenteService extends UsuarioService{

    /// ATRIBUTOS ********************************************************************************
    
    GerenteService(){
        this.usuarioDAO = GerenteJpaController.getInstance();
        this.validacaoUsuario = new ValidacaoGerente();
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    public String adicionar(Usuario usuario) throws ServiceException{

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                Gerente gerente = (Gerente) usuario;
                usuarioDAO.adicionar(gerente);
                break;
            } catch (DAOException ex) {
                if( cont == 0)
                    throw new ServiceException( "Operação não concluida!" );
            }
        }
        
        return "OK";
    }

	@Override
	public String alterar(Usuario usuario, Usuario usuarioAlterado) throws ServiceException {

            int cont = 3;
            while (cont > 0){
                --cont;
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), false );  /// Verifica se existe e se houve problemna na passagem de informacao
                    validacaoUsuario.validacao(usuarioAlterado, usuarioDAO.consultarTodos(), false );   /// Verifica se existe e se houve problemna na passagem de informacao                   
                    
                    //validar os atributos segundo as regras de negocio
                    
                    Gerente gerenteAlterado = (Gerente) usuarioAlterado;
                    usuarioDAO.alterar(gerenteAlterado);
                    break;
                } catch (DAOException ex) {
                    if( cont == 0)
                        throw new ServiceException( "Operação não concluida!" );
                }
            }
        
        return "OK";
	}

	@Override
	public String remover(Usuario usuario) throws ServiceException{

            int cont = 3;
            
            if (!usuario.getClass().equals(Gerente.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            Gerente gerente = (Gerente) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!")){
                        while (cont > 0){
                            cont--;
                            try {                                
                                usuarioDAO.remover(gerente);
                                break;
                            } catch (DAOException ex1) {
                                if(cont == 0)
                                    throw new ServiceException( "Operação não concluida!" );
                            }
                        }
                    }
                    else{
                        throw new ServiceException(ex.getMessage());
                    }                
                }

            return "OK";
	}

	@Override
	public String consultar(Usuario usuario) throws ServiceException{

            String mensagem = "";
            
            if (!usuario.getClass().equals(Gerente.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
            } catch(ServiceException ex){
                if(ex.getMessage().equals("Usuario existente!") ){
                    Gerente gerenteResultado = (Gerente) usuarioDAO.consultar(usuario.getLogin());
                    if(gerenteResultado != null)
                       return gerenteResultado.toString();
                    else
                        mensagem = "Operacao invalida!";
                }
                else
                    mensagem = "Gerente não existe!";
            }
	    throw new ServiceException(mensagem);
	
        }

	@Override
	public List<String> consultarTodos() throws ServiceException {
            
            List<Gerente> gerentes = new ArrayList<>();
            List<Usuario> usuarios = usuarioDAO.consultarTodos();
            
            if(usuarios.isEmpty()) 
                throw new ServiceException("Não há gerentes!");
            
            for(Usuario usuario: usuarios){
                gerentes.add((Gerente) usuario);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(Gerente gerente: gerentes){
                retornos.add(gerente.toString());
            }
            
            return retornos;
            
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
            boolean entrou = false;
            List<String> gerentesDados = this.consultarTodos();
            List<String> gerentesResultado = new ArrayList<>();
            List<String> teste = new ArrayList<>();
            
            
            for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                    itParam.hasNext() && itKey.hasNext();) {
                String param = itParam.next();
                String key = itKey.next();
                for(String gerente: gerentesDados){
                    String atributos[] = gerente.split(".");
                    for(String atributoValor : atributos){
                        if(atributoValor.matches(param)){
                            if(atributoValor.matches(key)){
                                teste.add(gerente);
                                break;
                            }
                        }
                    }    
                }
                
                if(!entrou && gerentesResultado.isEmpty()){
                    entrou = true;
                    gerentesResultado.addAll(teste);
                }
                else 
                    gerentesResultado.retainAll(teste);
               
                teste.clear();
            }
            
            return gerentesResultado;
            
	}
        
    @Override
    public List<String> ranquear(IRankingUsuarioStrategy rankingUsuario) {
            // TODO Auto-generated method stub
            return null;
    }

    @Override
    public String verNotificacao(Usuario usuario) throws ServiceException {
        return usuario.getNoficacao();
    }

    @Override
    public void notificar(String notificacao, Usuario usuario) throws ServiceException {
        usuario.notificar(notificacao);
    }
   
}
