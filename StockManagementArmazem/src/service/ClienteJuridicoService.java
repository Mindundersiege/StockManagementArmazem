package service;

import service.ranqueamento.IRankingUsuarioStrategy;
import java.util.List;

import dao.ClienteJuridicoJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.tiposUsuario.ClienteJuridico;
import model.Usuario;
import service.validacao.ValidacaoClienteJuridico;

//Revisar classe!

/**
 * Classe que representa as funcionalidades desempenhadas pelo Cliente Juridico
 * @see ClienteJuridico
 * @see Usuario
 */
public class ClienteJuridicoService extends UsuarioService{

    /// ATRIBUTOS ********************************************************************************
    
    ClienteJuridicoService(){
        this.usuarioDAO = ClienteJuridicoJpaController.getInstance();
        this.validacaoUsuario = new ValidacaoClienteJuridico();
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    public String adicionar(Usuario usuario) throws ServiceException{

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                ClienteJuridico clienteJuridico = (ClienteJuridico) usuario;
                usuarioDAO.adicionar(clienteJuridico);
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
                    
                    ClienteJuridico clienteJuridicoAlterado = (ClienteJuridico) usuarioAlterado;
                    usuarioDAO.alterar(clienteJuridicoAlterado);
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
            
            if (!usuario.getClass().equals(ClienteJuridico.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            ClienteJuridico clienteJuridico = (ClienteJuridico) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!")){
                        while (cont > 0){
                            cont--;
                            try {                                
                                usuarioDAO.remover(clienteJuridico);
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
            
            if (!usuario.getClass().equals(ClienteJuridico.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
            } catch(ServiceException ex){
                if(ex.getMessage().equals("Usuario existente!") ){
                    ClienteJuridico clienteJuridicoResultado = (ClienteJuridico) usuarioDAO.consultar(usuario.getLogin());
                    if(clienteJuridicoResultado != null)
                       return clienteJuridicoResultado.toString();
                    else
                        mensagem = "Operacao invalida!";
                }
                else
                    mensagem = "ClienteJuridico não existe!";
            }
	    throw new ServiceException(mensagem);
	
        }

	@Override
	public List<String> consultarTodos() throws ServiceException {
            
            List<ClienteJuridico> clienteJuridicos = new ArrayList<>();
            List<Usuario> usuarios = usuarioDAO.consultarTodos();
            
            if(usuarios.isEmpty()) 
                throw new ServiceException("Não há clienteJuridicos!");
            
            for(Usuario usuario: usuarios){
                clienteJuridicos.add((ClienteJuridico) usuario);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(ClienteJuridico clienteJuridico: clienteJuridicos){
                retornos.add(clienteJuridico.toString());
            }
            
            return retornos;
            
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
            boolean entrou = false;
            List<String> clienteJuridicosDados = this.consultarTodos();
            List<String> clienteJuridicosResultado = new ArrayList<>();
            List<String> teste = new ArrayList<>();
            
            
            for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                    itParam.hasNext() && itKey.hasNext();) {
                String param = itParam.next();
                String key = itKey.next();
                for(String clienteJuridico: clienteJuridicosDados){
                    String atributos[] = clienteJuridico.split(".");
                    for(String atributoValor : atributos){
                        if(atributoValor.matches(param)){
                            if(atributoValor.matches(key)){
                                teste.add(clienteJuridico);
                                break;
                            }
                        }
                    }    
                }
                
                if(!entrou && clienteJuridicosResultado.isEmpty()){
                    entrou = true;
                    clienteJuridicosResultado.addAll(teste);
                }
                else 
                    clienteJuridicosResultado.retainAll(teste);
               
                teste.clear();
            }
            
            return clienteJuridicosResultado;
            
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
