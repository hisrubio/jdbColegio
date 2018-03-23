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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n")
    , @NamedQuery(name = "Nota.findByNifalumno", query = "SELECT n FROM Nota n WHERE n.notaPK.nifalumno = :nifalumno")
    , @NamedQuery(name = "Nota.findByCodAsignatura", query = "SELECT n FROM Nota n WHERE n.notaPK.codAsignatura = :codAsignatura")
    , @NamedQuery(name = "Nota.findByCalificacion", query = "SELECT n FROM Nota n WHERE n.calificacion = :calificacion")})
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotaPK notaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calificacion")
    private int calificacion;
    @JoinColumn(name = "Nif_alumno", referencedColumnName = "Nif", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "cod_asignatura", referencedColumnName = "cod_asignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;

    public Nota() {
    }

    public Nota(NotaPK notaPK) {
        this.notaPK = notaPK;
    }

    public Nota(NotaPK notaPK, int calificacion) {
        this.notaPK = notaPK;
        this.calificacion = calificacion;
    }

    public Nota(String nifalumno, String codAsignatura) {
        this.notaPK = new NotaPK(nifalumno, codAsignatura);
    }

    public NotaPK getNotaPK() {
        return notaPK;
    }

    public void setNotaPK(NotaPK notaPK) {
        this.notaPK = notaPK;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notaPK != null ? notaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nota)) {
            return false;
        }
        Nota other = (Nota) object;
        if ((this.notaPK == null && other.notaPK != null) || (this.notaPK != null && !this.notaPK.equals(other.notaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Nota[ notaPK=" + notaPK + " ]";
    }
    
}
