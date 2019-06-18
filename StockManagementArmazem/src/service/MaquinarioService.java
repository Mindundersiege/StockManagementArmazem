package service;

import java.util.List;

import service.ranqueamento.IRankingMaterialStrategy;
import dao.MaquinarioJpaController;
import exception.DAOException;
import exception.ServiceException;
import model.Material;
import model.tiposMaterial.Maquinario;
import service.validacao.ValidacaoMaquinario;

public class MaquinarioService extends MaterialService{

    /// CONSTRUTOR *******************************************************************************

    MaquinarioService(){
        this.materialDAO = MaquinarioJpaController.getInstance();
        this.validacaoMaterial = new ValidacaoMaquinario();
    }

    @Override
    public String adicionar(Material material) {

        Maquinario maquinario = (Maquinario) material;

        try {	
                materialDAO.adicionar(maquinario);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String alterar(Material material, Material materialAlterado) {

        Maquinario maquinario = (Maquinario) material;

        try {	
                materialDAO.alterar(maquinario);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String remover(Material material) {
        Maquinario maquinario = (Maquinario) material;

        try {	
                materialDAO.remover(maquinario);
        }catch( DAOException serExc ) {
                return serExc.getMessage();
        }

        return "OK";
    }

    @Override
    public String consultar(Material material) {
//
//            try {	
//                    Maquinario maquinario = (Maquinario) materialDAO.consultar(maquinario.getId());
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
