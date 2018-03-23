package Entidades;

import Entidades.Curso;
import Entidades.Nota;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-01T11:51:59")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, String> apeAlumno;
    public static volatile SingularAttribute<Alumno, String> foto;
    public static volatile SingularAttribute<Alumno, String> nomAlumno;
    public static volatile SingularAttribute<Alumno, Curso> codCurso;
    public static volatile SingularAttribute<Alumno, String> nif;
    public static volatile ListAttribute<Alumno, Nota> notaList;

}