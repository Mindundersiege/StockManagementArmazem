/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tiposUsuario;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/// CLASSES PRÓPRIAS
import model.Usuario;

/**
 * Representa um cliente juridico
 */
@Entity
@Table(name = "ClienteJuridico")
public class ClienteJuridico extends Usuario implements Serializable {
    
    /// ATRIBUTOS ********************************************************************************
    
    @Column(unique = true, nullable = false)
    private String cpnj;
    
    @Column(unique = true, nullable = false)
    private String razaoSocial;
    
    @Column(unique = true, nullable = false)
    private String inscricaoEstadual;
    
    /// CONSTRUTOR *******************************************************************************

    ClienteJuridico(){
        super();
    }
    
    /// GETTERS E SETTERS ************************************************************************
    
    public String getCpnj() {
        return cpnj;
    }

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteJuridico)) {
            return false;
        }
        ClienteJuridico other = (ClienteJuridico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ClienteJuridico[ id=" + id + " ]";
    }

    @Override
    protected String ImplementYourToString() {
        
        String myObjectInString = "";

        myObjectInString+= " " + this.cpnj;
        myObjectInString+= " " + this.razaoSocial;
        myObjectInString+= " " + this.inscricaoEstadual;

        return myObjectInString;    
    }
    
}
