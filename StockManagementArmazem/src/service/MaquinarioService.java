package service;

/**
 *
 * @author Thiago
 */

import service.raqueamento.IRankingMaterialStrategy;
import service.validacao.ValidacaoMaterial;
import java.util.List;

import model.Material;
import exception.ServiceException;
import model.tiposMaterial.Maquinario;
import service.bloqueio.BloqueioMaterial;

public class MaquinarioService extends MaterialService{

	MaquinarioService(){
		this.materialDAO = new MaquinarioDAOJPA();
	}
	
	@Override
	public String adicionar(Material material) {

		Maquinario maquinario = (Maquinario) material;
		
		try {	
			materialDAO.adicionar(maquinario);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String alterar(Material material, Material materialAlterado) {
		
		Maquinario maquinario = (Maquinario) material;
		
		try {	
			materialDAO.alterar(maquinario);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String remover(Material material) {
		Maquinario maquinario = (Maquinario) material;
		
		try {	
			materialDAO.remover(maquinario);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String consultar(Material material) {
		
		try {	
			Maquinario maquinario = (Maquinario) materialDAO.consultar(maquinario.getIdMaterial());
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
		

	}

	@Override
	public List<String> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultaEspecifica(List<String> params, List<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bloquear(Material material, BloqueioMaterial bloqueioMaterial, String causa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> ranquear(IRankingMaterialStrategy rankingMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validacao(Material material, ValidacaoMaterial validacaoMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

}
