package model.tiposUsuario;

/**
 *
 * @author Thiago
 */

import java.io.Serializable;
import model.Data;

/// CLASSES PRÓPRIAS
import model.Usuario;
import model.tiposMaterial.Maquinario;

/**
 * Representa um operador
 * @see Usuario
 * @see Data
 */
public class Operador extends Usuario implements Serializable {
	
    /// ATRIBUTOS ********************************************************************************
    
    private float salario = 0;
    private String CPF;
    private Data DataEntrada;
    private Maquinario maquinaOperada;
    
    /// CONSTRUTOR *******************************************************************************
    
    public Operador( ){
        super();
    }
    
    /// GETTERS E SETTERS ************************************************************************
       
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
    	this.salario = salario;
    }
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Data getDataEntrada() {
        return DataEntrada;
    }

    public void setDataEntrada(Data DataEntrada) {
        this.DataEntrada = DataEntrada;
    }

    public Maquinario getMaquinaOperada() {
        return maquinaOperada;
    }

    public void setMaquinaOperada(Maquinario maquinaOperada) {
        this.maquinaOperada = maquinaOperada;
    }
    
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";

        myObjectInString+= " " + String.valueOf(this.salario);
        myObjectInString+= " " + this.CPF;
        myObjectInString+= " " + this.DataEntrada.toString();
        myObjectInString+= " " + this.maquinaOperada.toString();
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
        if (!(object instanceof Operador)) {
            return false;
        }
        
        Operador other = (Operador) object;

        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Operador[ id=" + id + " ]";
    }

}
