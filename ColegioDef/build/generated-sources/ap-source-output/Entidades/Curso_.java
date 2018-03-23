package Entidades;

import Entidades.Alumno;
import Entidades.Cursoasignatura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-01T11:51:59")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, String> codCurso;
    public static volatile ListAttribute<Curso, Alumno> alumnoList;
    public static volatile ListAttribute<Curso, Cursoasignatura> cursoasignaturaList;
    public static volatile SingularAttribute<Curso, String> nomCurso;

}