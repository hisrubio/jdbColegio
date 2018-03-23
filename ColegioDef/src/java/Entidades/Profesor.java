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
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p")
    , @NamedQuery(name = "Profesor.findByNif", query = "SELECT p FROM Profesor p WHERE p.nif = :nif")
    , @NamedQuery(name = "Profesor.findByNomProfesor", query = "SELECT p FROM Profesor p WHERE p.nomProfesor = :nomProfesor")
    , @NamedQuery(name = "Profesor.findByApeProfesor", query = "SELECT p FROM Profesor p WHERE p.apeProfesor = :apeProfesor")
    , @NamedQuery(name = "Profesor.findByFoto", query = "SELECT p FROM Profesor p WHERE p.foto = :foto")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "Nif")
    private String nif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_profesor")
    private String nomProfesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ape_profesor")
    private String apeProfesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "foto")
    private String foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nifProfesor")
    private List<Cursoasignatura> cursoasignaturaList;

    public Profesor() {
    }

    public Profesor(String nif) {
        this.nif = nif;
    }

    public Profesor(String nif, String nomProfesor, String apeProfesor, String foto) {
        this.nif = nif;
        this.nomProfesor = nomProfesor;
        this.apeProfesor = apeProfesor;
        this.foto = foto;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNomProfesor() {
        return nomProfesor;
    }

    public void setNomProfesor(String nomProfesor) {
        this.nomProfesor = nomProfesor;
    }

    public String getApeProfesor() {
        return apeProfesor;
    }

    public void setApeProfesor(String apeProfesor) {
        this.apeProfesor = apeProfesor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @XmlTransient
    public List<Cursoasignatura> getCursoasignaturaList() {
        return cursoasignaturaList;
    }

    public void setCursoasignaturaList(List<Cursoasignatura> cursoasignaturaList) {
        this.cursoasignaturaList = cursoasignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Profesor[ nif=" + nif + " ]";
    }
    
}
