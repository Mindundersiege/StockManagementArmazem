package service;

import model.MaterialF;
import dao.IMaterialDAO;

public interface IBloqueioMaterialStrategy {

	public abstract void bloquear(MaterialF material, IMaterialDAO materialDAO, String causa);

}
