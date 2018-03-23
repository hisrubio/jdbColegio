/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")
    , @NamedQuery(name = "Curso.findAllPorOrden", query = "SELECT c FROM Curso c order by c.nomCurso")
    , @NamedQuery(name = "Curso.findByCodCurso", query = "SELECT c FROM Curso c WHERE c.codCurso = :codCurso")
    , @NamedQuery(name = "Curso.findByNomCurso", query = "SELECT c FROM Curso c WHERE c.nomCurso = :nomCurso")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_curso")
    private String codCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_curso")
    private String nomCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Cursoasignatura> cursoasignaturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCurso")
    private List<Alumno> alumnoList;

    public Curso() {
    }

    public Curso(String codCurso) {
        this.codCurso = codCurso;
    }

    public Curso(String codCurso, String nomCurso) {
        this.codCurso = codCurso;
        this.nomCurso = nomCurso;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    @XmlTransient
    public List<Cursoasignatura> getCursoasignaturaList() {
        return cursoasignaturaList;
    }

    public void setCursoasignaturaList(List<Cursoasignatura> cursoasignaturaList) {
        this.cursoasignaturaList = cursoasignaturaList;
    }

    @XmlTransient
    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCurso != null ? codCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.codCurso == null && other.codCurso != null) || (this.codCurso != null && !this.codCurso.equals(other.codCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Curso[ codCurso=" + codCurso + " ]";
    }
    
}
