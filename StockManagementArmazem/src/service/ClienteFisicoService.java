package service;

import service.ranqueamento.IRankingUsuarioStrategy;
import java.util.List;

import dao.ClienteFisicoJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.tiposUsuario.ClienteFisico;
import model.Usuario;
import service.validacao.ValidacaoClienteFisico;

//Revisar classe!

/**
 * Classe que representa as funcionalidades desempenhadas pelo Cliente Fisico
 * @see ClienteFisico
 * @see Usuario
 */
public class ClienteFisicoService extends UsuarioService{

    /// ATRIBUTOS ********************************************************************************
    
    ClienteFisicoService(){
        this.usuarioDAO = ClienteFisicoJpaController.getInstance();
        this.validacaoUsuario = new ValidacaoClienteFisico();
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    public String adicionar(Usuario usuario) throws ServiceException{

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                ClienteFisico clienteFisico = (ClienteFisico) usuario;
                usuarioDAO.adicionar(clienteFisico);
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
                    
                    ClienteFisico clienteFisicoAlterado = (ClienteFisico) usuarioAlterado;
                    usuarioDAO.alterar(clienteFisicoAlterado);
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
            
            if (!usuario.getClass().equals(ClienteFisico.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            ClienteFisico clienteFisico = (ClienteFisico) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!")){
                        while (cont > 0){
                            cont--;
                            try {                                
                                usuarioDAO.remover(clienteFisico);
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
            
            if (!usuario.getClass().equals(ClienteFisico.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
            } catch(ServiceException ex){
                if(ex.getMessage().equals("Usuario existente!") ){
                    ClienteFisico clienteFisicoResultado = (ClienteFisico) usuarioDAO.consultar(usuario.getLogin());
                    if(clienteFisicoResultado != null)
                       return clienteFisicoResultado.toString();
                    else
                        mensagem = "Operacao invalida!";
                }
                else
                    mensagem = "ClienteFisico não existe!";
            }
	    throw new ServiceException(mensagem);
	
        }

	@Override
	public List<String> consultarTodos() throws ServiceException {
            
            List<ClienteFisico> clienteFisicos = new ArrayList<>();
            List<Usuario> usuarios = usuarioDAO.consultarTodos();
            
            if(usuarios.isEmpty()) 
                throw new ServiceException("Não há clienteFisicos!");
            
            for(Usuario usuario: usuarios){
                clienteFisicos.add((ClienteFisico) usuario);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(ClienteFisico clienteFisico: clienteFisicos){
                retornos.add(clienteFisico.toString());
            }
            
            return retornos;
            
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
            boolean entrou = false;
            List<String> clienteFisicosDados = this.consultarTodos();
            List<String> clienteFisicosResultado = new ArrayList<>();
            List<String> teste = new ArrayList<>();
            
            
            for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                    itParam.hasNext() && itKey.hasNext();) {
                String param = itParam.next();
                String key = itKey.next();
                for(String clienteFisico: clienteFisicosDados){
                    String atributos[] = clienteFisico.split(".");
                    for(String atributoValor : atributos){
                        if(atributoValor.matches(param)){
                            if(atributoValor.matches(key)){
                                teste.add(clienteFisico);
                                break;
                            }
                        }
                    }    
                }
                
                if(!entrou && clienteFisicosResultado.isEmpty()){
                    entrou = true;
                    clienteFisicosResultado.addAll(teste);
                }
                else 
                    clienteFisicosResultado.retainAll(teste);
               
                teste.clear();
            }
            
            return clienteFisicosResultado;
            
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
