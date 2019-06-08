package model.tiposMaterial;

/**
 *
 * @author Thiago
 */

import java.io.Serializable;

/// CLASSES PRÓPRIAS
import model.Material;
import model.tiposUsuario.ClienteJuridico;

/**
 * Representa uma Matéria Prima
 * @see Material
 */
public class MateriaPrima extends Material implements Serializable{
	
    /// ATRIBUTOS ********************************************************************************
    
    private float peso;
    private String tipoDeMateriaPrima;
    private ClienteJuridico fornecedor;
    
    /// CONSTRUTOR *******************************************************************************
    
    public MateriaPrima(){ /** vazio **/ }
    
    /// GETTERS E SETTERS ************************************************************************
       
 
   
    /// MÉTODOS **********************************************************************************
        
    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";
        
        myObjectInString+= " " + String.valueOf(this.peso);
        myObjectInString+= " " + this.tipoDeMateriaPrima;
        myObjectInString+= " " + this.fornecedor.toString();

        return myObjectInString;
    }
	
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPrima)) {
            return false;
        }
        MateriaPrima other = (MateriaPrima) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MateriaPrima[ id=" + id + " ]";
    }
    
}
