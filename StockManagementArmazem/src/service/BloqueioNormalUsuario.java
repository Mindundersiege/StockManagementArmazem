package service;

import model.StatusSM;
import model.UsuarioF;
import dao.IUsuarioDAO;
import exception.DAOException;
import exception.ServiceException;

public class BloqueioNormalUsuario extends BloqueioUsuario {


	/**
	 * @throws ServiceException 
	 * @see service.BloqueioUsuario#bloquear(Model.Usuario, dao.IUsuarioDAO, Model.String)
	 */
	public void bloquear(UsuarioF usuario, IUsuarioDAO usuarioDAO, String causa) throws DAOException {
		if(usuario.getStatus().equals(StatusSM.Permanente)) throw new ServiceException("ERRO: Usuarios bloqueados permanentemente não podem ter seu status alterado!");		
		
		else{		
			usuario.setStatus( StatusSM.bloqueado );
			usuario.setCausa( causa );
			usuarioDAO.alterar( usuario );
		}
	}

}
