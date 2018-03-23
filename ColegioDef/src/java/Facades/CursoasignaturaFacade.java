/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Curso;
import Entidades.Cursoasignatura;
import Entidades.Profesor;
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
public class CursoasignaturaFacade extends AbstractFacade<Cursoasignatura> {

    @PersistenceContext(unitName = "ColegioDefPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoasignaturaFacade() {
        super(Cursoasignatura.class);
    }
    
    
    public List<Cursoasignatura> AsignaturasProfesor(Profesor profesor){
        em=getEntityManager();
        Query q;
        if(profesor != null){
            q=em.createNamedQuery("Cursoasignatura.findByCodProfesor").setParameter("codProfesor", profesor);
            return q.getResultList();
        }   
        return null;
    }
    
}
