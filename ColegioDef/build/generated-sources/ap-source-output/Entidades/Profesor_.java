package Entidades;

import Entidades.Cursoasignatura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-01T11:51:59")
@StaticMetamodel(Profesor.class)
public class Profesor_ { 

    public static volatile SingularAttribute<Profesor, String> apeProfesor;
    public static volatile SingularAttribute<Profesor, String> foto;
    public static volatile SingularAttribute<Profesor, String> nif;
    public static volatile ListAttribute<Profesor, Cursoasignatura> cursoasignaturaList;
    public static volatile SingularAttribute<Profesor, String> nomProfesor;

}