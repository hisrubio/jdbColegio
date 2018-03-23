/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Alumno;
import Entidades.Asignatura;
import Entidades.Nota;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Miguel
 */
@Stateless
public class NotaFacade extends AbstractFacade<Nota> {

    @PersistenceContext(unitName = "ColegioDefPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {
        super(Nota.class);
    }
    
     public List<Nota> alumnosAsignatura(Asignatura asignatura){
        em=getEntityManager();
        Query q;
        if(asignatura != null){
            q=em.createNamedQuery("Nota.findByCodAsignatura").setParameter("codAsignatura", asignatura.getCodAsignatura());
            return q.getResultList();
        }   
        return null;
    }
     
    public List<Nota> asignaturaAlumno(Alumno alumno){
        em=getEntityManager();
        Query q;
        if(alumno != null){
            q=em.createNamedQuery("Nota.findByNifalumno").setParameter("nifalumno", alumno.getNif());
            return q.getResultList();
        }   
        return null;
    }
    
}
