package service;

import service.validacao.ValidacaoMaterial;
import service.ranqueamento.IRankingMaterialStrategy;
import model.MaterialF;
import dao.IMaterialDAO;
import java.util.List;

public abstract class MaterialService {

	/// ATRIBUTOS

	protected IMaterialDAO materialDAO;

	/// MÉTODOS

	public abstract String adicionar(MaterialF material);

	public abstract String alterar(MaterialF material, MaterialF materialAlterado);

	public abstract String remover(MaterialF material);

	public abstract String consultar(MaterialF material);

	public abstract List<String> consultarTodos();

	public abstract List<String> consultaEspecifica(List<String> params, List<String> keys);

	public abstract String bloquear(MaterialF material, BloqueioMaterial bloqueioMaterial, String causa);

	public abstract List<String> ranquear(IRankingMaterialStrategy rankingMaterial);

	public abstract String validacao(MaterialF material, ValidacaoMaterial validacaoMaterial);

}
