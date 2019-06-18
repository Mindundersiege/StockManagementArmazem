package service;

import java.util.List;

import service.ranqueamento.IRankingMaterialStrategy;
import dao.MateriaPrimaJpaController;
import exception.DAOException;
import exception.ServiceException;
import model.Material;
import model.tiposMaterial.MateriaPrima;
import service.validacao.ValidacaoMateriaPrima;

public class MateriaPrimaService extends MaterialService{

    /// CONSTRUTOR *******************************************************************************

    MateriaPrimaService(){
        this.materialDAO = MateriaPrimaJpaController.getInstance();
        this.validacaoMaterial = new ValidacaoMateriaPrima();
    }

    @Override
    public String adicionar(Material material) {

        MateriaPrima materiaPrima = (MateriaPrima) material;

        try {	
                materialDAO.adicionar(materiaPrima);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String alterar(Material material, Material materialAlterado) {

        MateriaPrima materiaPrima = (MateriaPrima) material;

        try {	
                materialDAO.alterar(materiaPrima);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String remover(Material material) {
        MateriaPrima materiaPrima = (MateriaPrima) material;

        try {	
                materialDAO.remover(materiaPrima);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String consultar(Material material) {
//
//            try {	
//                    MateriaPrima materiaPrima = (MateriaPrima) materialDAO.consultar(materiaPrima.getId());
//            }catch( DAOException serExc ) {
//                    return serExc.getMessage();
//            }

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
    public List<String> ranquear(IRankingMaterialStrategy rankingMaterial) {
            // TODO Auto-generated method stub
            return null;
    }


    @Override
    public String validacao(Material material) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
