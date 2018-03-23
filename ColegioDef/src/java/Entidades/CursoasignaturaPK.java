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
public class CursoasignaturaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_curso")
    private String codCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "cod_asignatura")
    private String codAsignatura;

    public CursoasignaturaPK() {
    }

    public CursoasignaturaPK(String codCurso, String codAsignatura) {
        this.codCurso = codCurso;
        this.codAsignatura = codAsignatura;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
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
        hash += (codCurso != null ? codCurso.hashCode() : 0);
        hash += (codAsignatura != null ? codAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoasignaturaPK)) {
            return false;
        }
        CursoasignaturaPK other = (CursoasignaturaPK) object;
        if ((this.codCurso == null && other.codCurso != null) || (this.codCurso != null && !this.codCurso.equals(other.codCurso))) {
            return false;
        }
        if ((this.codAsignatura == null && other.codAsignatura != null) || (this.codAsignatura != null && !this.codAsignatura.equals(other.codAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CursoasignaturaPK[ codCurso=" + codCurso + ", codAsignatura=" + codAsignatura + " ]";
    }
    
}
