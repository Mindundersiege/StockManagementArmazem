package service;

import model.UsuarioF;
import dao.IUsuarioDAO;

public interface IBloqueioUsuarioStrategy {

	public abstract void bloquear(UsuarioF usuario, IUsuarioDAO usuarioDAO, String causa);

}
