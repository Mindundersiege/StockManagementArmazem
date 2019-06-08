package model.tiposUsuario;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/// CLASSES PRÓPRIAS
import model.Data;
import model.Usuario;

/**
 * Representa um cliente físico
 */
@Entity
@Table(name = "ClienteFisico")
public class ClienteFisico  extends Usuario implements Serializable {
 
    /// ATRIBUTOS ********************************************************************************
    
    @Column(unique = true, nullable = false)
    private String cpf;
    
    @Column(unique = true, nullable = false)
    private String rg;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Data dataNascimento;
    
    /// CONSTRUTOR *******************************************************************************

    public ClienteFisico(){
        super();
    }
    
    /// GETTERS E SETTERS ************************************************************************
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRg() {
        return rg;
    }
    
    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected String ImplementYourToString() {		
        String myObjectInString = "";

        myObjectInString+= " " + this.getCpf();
        myObjectInString+= " " + this.getRg();
        myObjectInString+= " " + this.getDataNascimento();

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
        if (!(object instanceof ClienteFisico)) {
            return false;
        }
        ClienteFisico other = (ClienteFisico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ClienteFisico[ id=" + id + " ]";
    }

}
