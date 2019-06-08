package model.tiposUsuario;

/**
 *
 * @author Thiago
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import model.Data;

/// CLASSES PRÓPRIAS
import model.Usuario;

/**
 * Representa um gerente
 * @see Usuario
 * @see Data
 */
public class Gerente extends Usuario implements Serializable {
	
    /// ATRIBUTOS ********************************************************************************
    
    private float salario = 0;
    private String CPF;
    private Data DataEntrada;
    private ArrayList<Operador> listaDeOperadores = new ArrayList<>();
    
    /// CONSTRUTOR *******************************************************************************
    
    public Gerente( ){
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
    
    public ArrayList<Operador> getListaDeOperadores() {
        return listaDeOperadores;
    }

    public void setListaDeOperadores(ArrayList<Operador> listaDeOperadores) {
        this.listaDeOperadores = listaDeOperadores;
    }

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";

        myObjectInString+= " " +  String.valueOf(this.salario);
        myObjectInString+= " " + this.CPF;
        myObjectInString+= " " + this.DataEntrada.toString();
        Iterator<Operador> listaDeOperadoresIterator = this.listaDeOperadores.iterator();
        while (listaDeOperadoresIterator.hasNext()) {
            Operador operador = listaDeOperadoresIterator.next();
            myObjectInString+= " " + operador.toString();
        }
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
        if (!(object instanceof Gerente)) {
            return false;
        }
        
        Gerente other = (Gerente) object;

        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Gerente[ id=" + id + " ]";
    }

}


