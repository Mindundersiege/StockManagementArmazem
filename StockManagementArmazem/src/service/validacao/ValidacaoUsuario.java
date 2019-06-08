package service.validacao;

// CLASSES PRÓPRIAS
import model.Usuario;
import exception.ServiceException;

/**
 * Validação dos campos da classe usuario
 * @see Material
 * @see ServiceException
 */
public class ValidacaoUsuario {

    /**
     * Valida os campos de um usuario
     * @param usuario  Usuário cujos campos serão validados
     * @return  String contendo ok caso bem sucedido. Caso contrário, lança-se uma excessão
     * @throws ServiceException 
     */
    public String validacao(Usuario usuario) throws ServiceException{
    
        usuario.getNome();
    
        return "OK";
    }

}
