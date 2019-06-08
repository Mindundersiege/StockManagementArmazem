/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/// CLASSES PRÓPRIAS
import model.tiposUsuario.ClienteJuridico;
import exception.DAOException;
import java.util.ArrayList;
import model.Usuario;

/**
 * Classe controladora JPA do modelo ClienteJuridico
 * @see IUsuarioDAO
 * @see Usuario
 */
public class ClienteJuridicoJpaController implements Serializable, IUsuarioDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static ClienteJuridicoJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private ClienteJuridicoJpaController() {
        this.entityManager = getEntityManager();
    }

    /// GETTERS **********************************************************************************
        
    public EntityManager getEntityManager() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");

        if (entityManager == null) {
          entityManager = factory.createEntityManager();
        }

         return entityManager;        
        
    }
    
    public static ClienteJuridicoJpaController getInstance(){
    	 
        if (instance == null){
            instance = new ClienteJuridicoJpaController();
        }
    
        return instance;
    }
    
    public int getClienteJuridicoCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClienteJuridico> rt = cq.from(ClienteJuridico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(ClienteJuridico clienteJuridico) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(clienteJuridico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
     }

    @Override
    public void adicionar(Usuario usuario) throws DAOException {
        create( (ClienteJuridico) usuario ); 
    }

    // ALTERAÇÃO
    public void edit(ClienteJuridico clienteJuridico)  throws DAOException {
     
    	try {
            entityManager.getTransaction().begin();
            entityManager.merge(clienteJuridico);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
         }
     }

    @Override
    public void alterar(Usuario usuarioAlterado) throws DAOException {
        edit( (ClienteJuridico) usuarioAlterado ); 
    }

    // REMOÇÃO
    public void destroy(ClienteJuridico clienteJuridico)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            clienteJuridico = entityManager.find(ClienteJuridico.class, clienteJuridico.getId() );
            entityManager.remove(clienteJuridico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Usuario usuario) throws DAOException {
        destroy((ClienteJuridico) usuario ); 
    }
    
    // CONSULTA
    public ClienteJuridico findClienteJuridico(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClienteJuridico.class, login);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario consultar(String login) throws DAOException {
        ClienteJuridico clienteJuridico = findClienteJuridico(login);
        
        if( clienteJuridico == null )
            throw new DAOException("Funcionario nao encontrado");
		
        return clienteJuridico;
    }
    
    // CONSULTA TODOS
    private List<ClienteJuridico> findClienteJuridicoEntities() {
    	 return entityManager.createQuery("FROM " + ClienteJuridico.class.getName() ).getResultList();
     }
    
    @Override
    public List<Usuario> consultarTodos() throws DAOException {
        
        List<ClienteJuridico> clientes = findClienteJuridicoEntities();
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		
        if( clientes == null )
            throw new DAOException("Funcionario nao encontrado");
		
        for( ClienteJuridico i : clientes) {
            usuarios.add(i);
        }
		
        return usuarios;    
    }
    
}
