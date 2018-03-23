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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")
    , @NamedQuery(name = "Alumno.findByCurso", query = "SELECT a FROM Alumno a WHERE a.codCurso = :elCurso")
    , @NamedQuery(name = "Alumno.findByNif", query = "SELECT a FROM Alumno a WHERE a.nif = :nif")
    , @NamedQuery(name = "Alumno.findByNomAlumno", query = "SELECT a FROM Alumno a WHERE a.nomAlumno = :nomAlumno")
    , @NamedQuery(name = "Alumno.findByApeAlumno", query = "SELECT a FROM Alumno a WHERE a.apeAlumno = :apeAlumno")
    , @NamedQuery(name = "Alumno.findByFoto", query = "SELECT a FROM Alumno a WHERE a.foto = :foto")})
public class Alumno implements Serializable {

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
    @Column(name = "nom_alumno")
    private String nomAlumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ape_alumno")
    private String apeAlumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "foto")
    private String foto;
    @JoinColumn(name = "cod_curso", referencedColumnName = "cod_curso")
    @ManyToOne(optional = false)
    private Curso codCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Nota> notaList;

    public Alumno() {
    }

    public Alumno(String nif) {
        this.nif = nif;
    }

    public Alumno(String nif, String nomAlumno, String apeAlumno, String foto) {
        this.nif = nif;
        this.nomAlumno = nomAlumno;
        this.apeAlumno = apeAlumno;
        this.foto = foto;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNomAlumno() {
        return nomAlumno;
    }

    public void setNomAlumno(String nomAlumno) {
        this.nomAlumno = nomAlumno;
    }

    public String getApeAlumno() {
        return apeAlumno;
    }

    public void setApeAlumno(String apeAlumno) {
        this.apeAlumno = apeAlumno;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Curso getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(Curso codCurso) {
        this.codCurso = codCurso;
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
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Alumno[ nif=" + nif + " ]";
    }
    
}
