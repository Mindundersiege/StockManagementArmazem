package service;

import service.raqueamento.IRankingMaterialStrategy;
import service.validacao.ValidacaoMaterial;
import model.Material;
import dao.IMaterialDAO;
import java.util.List;
import service.bloqueio.BloqueioMaterial;

public abstract class MaterialService {

	/// ATRIBUTOS

	protected IMaterialDAO materialDAO;

	/// MÉTODOS

	public abstract String adicionar(Material material);

	public abstract String alterar(Material material, Material materialAlterado);

	public abstract String remover(Material material);

	public abstract String consultar(Material material);

	public abstract List<String> consultarTodos();

	public abstract List<String> consultaEspecifica(List<String> params, List<String> keys);

	public abstract String bloquear(Material material, BloqueioMaterial bloqueioMaterial, String causa);

	public abstract List<String> ranquear(IRankingMaterialStrategy rankingMaterial);

	public abstract String validacao(Material material, ValidacaoMaterial validacaoMaterial);

}
