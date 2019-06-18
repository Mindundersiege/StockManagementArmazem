package service;

import service.ranqueamento.IRankingUsuarioStrategy;
import java.util.List;

import dao.OperadorJpaController;
import exception.DAOException;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import model.tiposUsuario.Operador;
import model.Usuario;
import service.validacao.ValidacaoOperador;

//Revisar classe!

/**
 * Classe que representa as funcionalidades desempenhadas pelo Operador
 * @see Operador
 * @see Usuario
 */
public class OperadorService extends UsuarioService{

    /// ATRIBUTOS ********************************************************************************
    
    OperadorService(){
        this.usuarioDAO = OperadorJpaController.getInstance();
        this.validacaoUsuario = new ValidacaoOperador();
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    public String adicionar(Usuario usuario) throws ServiceException{

        int cont = 3;
        while (cont > 0){
            --cont;
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                Operador operador = (Operador) usuario;
                usuarioDAO.adicionar(operador);
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
                    
                    Operador operadorAlterado = (Operador) usuarioAlterado;
                    usuarioDAO.alterar(operadorAlterado);
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
            
            if (!usuario.getClass().equals(Operador.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            Operador operador = (Operador) usuario;
            
                try {
                    validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
                    throw new ServiceException("Usuario não existente!");
                
                } catch(ServiceException ex){
                    if(ex.getMessage().equals("Usuario existente!")){
                        while (cont > 0){
                            cont--;
                            try {                                
                                usuarioDAO.remover(operador);
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
            
            if (!usuario.getClass().equals(Operador.class)) 
                throw new ServiceException("Tipo de usuário inválido!");
            
            try {
                validacaoUsuario.validacao(usuario, usuarioDAO.consultarTodos(), true );
            } catch(ServiceException ex){
                if(ex.getMessage().equals("Usuario existente!") ){
                    Operador operadorResultado = (Operador) usuarioDAO.consultar(usuario.getLogin());
                    if(operadorResultado != null)
                       return operadorResultado.toString();
                    else
                        mensagem = "Operacao invalida!";
                }
                else
                    mensagem = "Operador não existe!";
            }
	    throw new ServiceException(mensagem);
	
        }

	@Override
	public List<String> consultarTodos() throws ServiceException {
            
            List<Operador> operadors = new ArrayList<>();
            List<Usuario> usuarios = usuarioDAO.consultarTodos();
            
            if(usuarios.isEmpty()) 
                throw new ServiceException("Não há operadors!");
            
            for(Usuario usuario: usuarios){
                operadors.add((Operador) usuario);
            }
            
            List<String> retornos = new ArrayList<>();
            
            for(Operador operador: operadors){
                retornos.add(operador.toString());
            }
            
            return retornos;
            
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) throws ServiceException {
            boolean entrou = false;
            List<String> operadorsDados = this.consultarTodos();
            List<String> operadorsResultado = new ArrayList<>();
            List<String> teste = new ArrayList<>();
            
            
            for (Iterator<String> itParam = params.iterator(), itKey = keys.iterator(); 
                    itParam.hasNext() && itKey.hasNext();) {
                String param = itParam.next();
                String key = itKey.next();
                for(String operador: operadorsDados){
                    String atributos[] = operador.split(".");
                    for(String atributoValor : atributos){
                        if(atributoValor.matches(param)){
                            if(atributoValor.matches(key)){
                                teste.add(operador);
                                break;
                            }
                        }
                    }    
                }
                
                if(!entrou && operadorsResultado.isEmpty()){
                    entrou = true;
                    operadorsResultado.addAll(teste);
                }
                else 
                    operadorsResultado.retainAll(teste);
               
                teste.clear();
            }
            
            return operadorsResultado;
            
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
