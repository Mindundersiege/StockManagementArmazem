package model.tiposMaterial;

/**
 *
 * @author Thiago
 */

import java.io.Serializable;

/// CLASSES PRÓPRIAS
import model.Material;
import model.tiposUsuario.Operador;

/**
 * Representa um Maquinário
 * @see Material
 */
public class Maquinario extends Material implements Serializable{
	
    /// ATRIBUTOS ********************************************************************************
    
    private float peso;
    private String marca;
    private Operador operadorDaMaquina;

    /// CONSTRUTOR *******************************************************************************
    
    public Maquinario(){ /** vazio **/ }
    
    /// GETTERS E SETTERS ************************************************************************
       
   public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Operador getOperadorDaMaquina() {
        return operadorDaMaquina;
    }

    public void setOperadorDaMaquina(Operador operadorDaMaquina) {
        this.operadorDaMaquina = operadorDaMaquina;
    }
   
    /// MÉTODOS **********************************************************************************
        
    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";
        
        myObjectInString+= " " + String.valueOf(this.peso);
        myObjectInString+= " " + this.marca;
        myObjectInString+= " " + this.operadorDaMaquina.toString();

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
        if (!(object instanceof Maquinario)) {
            return false;
        }
        Maquinario other = (Maquinario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Maquinario[ id=" + id + " ]";
    }
    
}
