/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Cursoasignatura;
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
public class AlumnoFacade extends AbstractFacade<Alumno> {

    @PersistenceContext(unitName = "ColegioDefPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }
    
    
    public List<Alumno> alumnosCurso(Curso curso){
        em=getEntityManager();
        Query q;
        if(curso != null){
            q=em.createNamedQuery("Alumno.findByCurso").setParameter("elCurso", curso);
            return q.getResultList();
        }   
        return null;
    }
    
    public List<Cursoasignatura> AsignaturasProfesoresCursos(Curso curso){
        em=getEntityManager();
        Query q;
        if(curso != null){
            q=em.createNamedQuery("Cursoasignatura.findByCodCurso").setParameter("codCurso", curso.getCodCurso());
            return q.getResultList();
        }   
        return null;
    }
}
