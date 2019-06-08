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
import model.tiposUsuario.ClienteFisico;
import exception.DAOException;
import java.util.ArrayList;
import model.Usuario;

/**
 * Classe controladora JPA do modelo ClienteFisico
 * @see IUsuarioDAO
 * @see Usuario
 */
public class ClienteFisicoJpaController implements Serializable, IUsuarioDAO {

    /// ATRIBUTOS ********************************************************************************
    
    private static ClienteFisicoJpaController instance;
    private EntityManager entityManager;


    /// CONSTRUTOR *******************************************************************************

    private ClienteFisicoJpaController() {
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
    
    public static ClienteFisicoJpaController getInstance(){
    	 
        if (instance == null){
            instance = new ClienteFisicoJpaController();
        }
    
        return instance;
    }
    
    public int getClienteFisicoCount() {
    
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClienteFisico> rt = cq.from(ClienteFisico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /// MÉTODOS **********************************************************************************
    
    // INSERÇÃO
    public void create(ClienteFisico clienteFisico) throws DAOException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(clienteFisico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
     }

    @Override
    public void adicionar(Usuario usuario) throws DAOException {
        create( (ClienteFisico) usuario ); 
    }

    // ALTERAÇÃO
    public void edit(ClienteFisico clienteFisico)  throws DAOException {
     
    	try {
            entityManager.getTransaction().begin();
            entityManager.merge(clienteFisico);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
         }
     }

    @Override
    public void alterar(Usuario usuarioAlterado) throws DAOException {
        edit( (ClienteFisico) usuarioAlterado ); 
    }

    // REMOÇÃO
    public void destroy(ClienteFisico clienteFisico)throws DAOException {
        try {
            entityManager.getTransaction().begin();
            clienteFisico = entityManager.find(ClienteFisico.class, clienteFisico.getId() );
            entityManager.remove(clienteFisico);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new DAOException( ex.getMessage() );
        }
    }

    @Override
    public void remover(Usuario usuario) throws DAOException {
        destroy((ClienteFisico) usuario ); 
    }
    
    // CONSULTA
    public ClienteFisico findClienteFisico(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClienteFisico.class, login);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario consultar(String login) throws DAOException {
        ClienteFisico clienteFisico = findClienteFisico(login);
        
        if( clienteFisico == null )
            throw new DAOException("Funcionario nao encontrado");
		
        return clienteFisico;
    }
    
    // CONSULTA TODOS
    private List<ClienteFisico> findClienteFisicoEntities() {
    	 return entityManager.createQuery("FROM " + ClienteFisico.class.getName() ).getResultList();
     }
    
    @Override
    public List<Usuario> consultarTodos() throws DAOException {
        
        List<ClienteFisico> clientes = findClienteFisicoEntities();
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		
        if( clientes == null )
            throw new DAOException("Funcionario nao encontrado");
		
        for( ClienteFisico i : clientes) {
            usuarios.add(i);
        }
		
        return usuarios;    
    }
    
}
