package service.validacao;

/// CLASSES PRÓPRIAS
import model.Material;
import exception.ServiceException;
import model.Data;

/**
 * Validação dos campos da classe material
 * @see Material
 * @see ServiceException
 */
public class ValidacaoMaterial {
    
    /**
     * Valida os campos de um material
     * @param material  Material cujos campos serão validados
     * @return  String contendo ok caso bem sucedido. Caso contrário, lança-se uma excessão
     * @throws ServiceException 
     */
    public String validacao( Material material ) throws ServiceException {
        
        int quantidade = material.getQuantidade();
        String causa = material.getCausa();
        double valorUnitario = material.getValorUnitario();
        Data dataEntrada = material.getDataEntrada();
        
        if( quantidade < 0)
            throw new ServiceException("Quantidade de material nao pode ser negativa");
        if( valorUnitario < 0)
            throw new ServiceException("Valor unitario de um material nao pode ser negativa");
        
        return "OK";
    }

}
