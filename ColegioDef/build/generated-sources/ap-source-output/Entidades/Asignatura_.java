package Entidades;

import Entidades.Cursoasignatura;
import Entidades.Nota;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-01T11:51:59")
@StaticMetamodel(Asignatura.class)
public class Asignatura_ { 

    public static volatile SingularAttribute<Asignatura, String> codAsignatura;
    public static volatile SingularAttribute<Asignatura, Integer> horas;
    public static volatile SingularAttribute<Asignatura, String> nomAsignatura;
    public static volatile ListAttribute<Asignatura, Cursoasignatura> cursoasignaturaList;
    public static volatile ListAttribute<Asignatura, Nota> notaList;

}