package service.ranqueamento;

import java.util.List;

/// CLASSES PRÓPRIAS
import model.Material;

/**
 * Interface de ranquemento de materiais 
 * @see Material
 */
public interface IRankingMaterialStrategy {

    /**
     * Ordenamento da lista de materiais
     * @param materiais Lista de materiais a serem ordenados
     * @return Lista reordenada
     */
    public List<Material> ranquear(List<Material> materiais);

}
