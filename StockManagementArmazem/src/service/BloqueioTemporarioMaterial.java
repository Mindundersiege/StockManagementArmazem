package service;

import model.MaterialF;
import dao.IMaterialDAO;
import model.Status;

//Revisar classe!

public class BloqueioTemporarioMaterial implements IBloqueioMaterialStrategy {

	/**
	 * @see service.IBloqueioMaterialStrategy#bloquear(Model.Material, dao.IMaterialDAO, Model.String)
	 */
	public void bloquear(MaterialF material, IMaterialDAO materialDAO, String causa) {
		material.setStatus( Status.Temporario );
		material.setCausa( causa );
		materialDAO.alterar( material );
	}

}
