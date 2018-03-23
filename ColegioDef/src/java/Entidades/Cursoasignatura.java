/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "cursoasignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cursoasignatura.findAll", query = "SELECT c FROM Cursoasignatura c")
    , @NamedQuery(name = "Cursoasignatura.findByCodCurso", query = "SELECT c FROM Cursoasignatura c WHERE c.cursoasignaturaPK.codCurso = :codCurso")
    , @NamedQuery(name = "Cursoasignatura.findByCodProfesor", query = "SELECT c FROM Cursoasignatura c WHERE c.nifProfesor = :codProfesor")
    , @NamedQuery(name = "Cursoasignatura.findByCodAsignatura", query = "SELECT c FROM Cursoasignatura c WHERE c.cursoasignaturaPK.codAsignatura = :codAsignatura")
    , @NamedQuery(name = "Cursoasignatura.findByAula", query = "SELECT c FROM Cursoasignatura c WHERE c.aula = :aula")})
public class Cursoasignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CursoasignaturaPK cursoasignaturaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "aula")
    private String aula;
    @JoinColumn(name = "cod_curso", referencedColumnName = "cod_curso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "cod_asignatura", referencedColumnName = "cod_asignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @JoinColumn(name = "nif_profesor", referencedColumnName = "Nif")
    @ManyToOne(optional = false)
    private Profesor nifProfesor;

    public Cursoasignatura() {
    }

    public Cursoasignatura(CursoasignaturaPK cursoasignaturaPK) {
        this.cursoasignaturaPK = cursoasignaturaPK;
    }

    public Cursoasignatura(CursoasignaturaPK cursoasignaturaPK, String aula) {
        this.cursoasignaturaPK = cursoasignaturaPK;
        this.aula = aula;
    }

    public Cursoasignatura(String codCurso, String codAsignatura) {
        this.cursoasignaturaPK = new CursoasignaturaPK(codCurso, codAsignatura);
    }

    public CursoasignaturaPK getCursoasignaturaPK() {
        return cursoasignaturaPK;
    }

    public void setCursoasignaturaPK(CursoasignaturaPK cursoasignaturaPK) {
        this.cursoasignaturaPK = cursoasignaturaPK;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Profesor getNifProfesor() {
        return nifProfesor;
    }

    public void setNifProfesor(Profesor nifProfesor) {
        this.nifProfesor = nifProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cursoasignaturaPK != null ? cursoasignaturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursoasignatura)) {
            return false;
        }
        Cursoasignatura other = (Cursoasignatura) object;
        if ((this.cursoasignaturaPK == null && other.cursoasignaturaPK != null) || (this.cursoasignaturaPK != null && !this.cursoasignaturaPK.equals(other.cursoasignaturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cursoasignatura[ cursoasignaturaPK=" + cursoasignaturaPK + " ]";
    }
    
}
