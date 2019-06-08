package service.ranqueamento;

import java.util.List;

/// CLASSES PRÓPRIAS
import model.Usuario;

/**
 * Interface de ranquemento de usuários 
 * @see Usuario
 */
public interface IRankingUsuarioStrategy {
    
    /**
     * Ordenamento da lista de usuarios
     * @param usuarios  Lista de usuarios a serem ordenados
     * @return Lista reordenada
     */
    public List<Usuario> ranquear(List<Usuario> usuarios);

}
