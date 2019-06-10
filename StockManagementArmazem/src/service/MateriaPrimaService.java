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
import model.tiposMaterial.MateriaPrima;
import service.bloqueio.BloqueioMaterial;

public class MateriaPrimaService extends MaterialService{

	MateriaPrimaService(){
		this.materialDAO = new MateriaPrimaDAOJPA();
	}
	
	@Override
	public String adicionar(Material material) {

		MateriaPrima materiaPrima = (MateriaPrima) material;
		
		try {	
			materialDAO.adicionar(materiaPrima);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String alterar(Material material, Material materialAlterado) {
		
		MateriaPrima materiaPrima = (MateriaPrima) material;
		
		try {	
			materialDAO.alterar(materiaPrima);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String remover(Material material) {
		MateriaPrima materiaPrima = (MateriaPrima) material;
		
		try {	
			materialDAO.remover(materiaPrima);
		}catch( ServiceException serExc ) {
			return serExc.getMessage();
		}
		
		return "OK";
	}

	@Override
	public String consultar(Material material) {
		
		try {	
			MateriaPrima materiaPrima = (MateriaPrima) materialDAO.consultar(materiaPrima.getIdMaterial());
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
