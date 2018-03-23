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
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a")
    , @NamedQuery(name = "Asignatura.findByCodAsignatura", query = "SELECT a FROM Asignatura a WHERE a.codAsignatura = :codAsignatura")
    , @NamedQuery(name = "Asignatura.findByNomAsignatura", query = "SELECT a FROM Asignatura a WHERE a.nomAsignatura = :nomAsignatura")
    , @NamedQuery(name = "Asignatura.findByHoras", query = "SELECT a FROM Asignatura a WHERE a.horas = :horas")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "cod_asignatura")
    private String codAsignatura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_asignatura")
    private String nomAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horas")
    private int horas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<Cursoasignatura> cursoasignaturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<Nota> notaList;

    public Asignatura() {
    }

    public Asignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public Asignatura(String codAsignatura, String nomAsignatura, int horas) {
        this.codAsignatura = codAsignatura;
        this.nomAsignatura = nomAsignatura;
        this.horas = horas;
    }

    public String getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public String getNomAsignatura() {
        return nomAsignatura;
    }

    public void setNomAsignatura(String nomAsignatura) {
        this.nomAsignatura = nomAsignatura;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @XmlTransient
    public List<Cursoasignatura> getCursoasignaturaList() {
        return cursoasignaturaList;
    }

    public void setCursoasignaturaList(List<Cursoasignatura> cursoasignaturaList) {
        this.cursoasignaturaList = cursoasignaturaList;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAsignatura != null ? codAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.codAsignatura == null && other.codAsignatura != null) || (this.codAsignatura != null && !this.codAsignatura.equals(other.codAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Asignatura[ codAsignatura=" + codAsignatura + " ]";
    }
    
}
