/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Miguel
 */
@Embeddable
public class NotaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "Nif_alumno")
    private String nifalumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "cod_asignatura")
    private String codAsignatura;

    public NotaPK() {
    }

    public NotaPK(String nifalumno, String codAsignatura) {
        this.nifalumno = nifalumno;
        this.codAsignatura = codAsignatura;
    }

    public String getNifalumno() {
        return nifalumno;
    }

    public void setNifalumno(String nifalumno) {
        this.nifalumno = nifalumno;
    }

    public String getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nifalumno != null ? nifalumno.hashCode() : 0);
        hash += (codAsignatura != null ? codAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaPK)) {
            return false;
        }
        NotaPK other = (NotaPK) object;
        if ((this.nifalumno == null && other.nifalumno != null) || (this.nifalumno != null && !this.nifalumno.equals(other.nifalumno))) {
            return false;
        }
        if ((this.codAsignatura == null && other.codAsignatura != null) || (this.codAsignatura != null && !this.codAsignatura.equals(other.codAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.NotaPK[ nifalumno=" + nifalumno + ", codAsignatura=" + codAsignatura + " ]";
    }
    
}
