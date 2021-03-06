package Controladores.util;

import Entidades.Alumno;
import Entidades.Asignatura;
import Entidades.Curso;
import Entidades.Cursoasignatura;
import Entidades.Nota;
import Entidades.Profesor;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }
    
    //desplegable alumnos
    public static SelectItem[] getSelectItemsAlumnos(List<Alumno> entities, boolean selectOne) {
        int size = selectOne ? entities.size() : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;

        for (Alumno x : entities) {
            items[i++] = new SelectItem(x, x.getNomAlumno());
        }
        return items;
    }
    
    //desplegable asignaturas
    public static SelectItem[] getSelectItemsAsignaturas(List<Asignatura> entities, boolean selectOne) {
        int size = selectOne ? entities.size() : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;

        for (Asignatura x : entities) {
            items[i++] = new SelectItem(x, x.getNomAsignatura());
        }
        return items;
    }
    
    //desplegable cursoasignatura
    public static SelectItem[] getSelectItemsCursoAsignaturas(List<Cursoasignatura> entities, boolean selectOne) {
        int size = selectOne ? entities.size() : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;

        for (Cursoasignatura x : entities) {
            items[i++] = new SelectItem(x, x.getAula());
        }
        return items;
    }
    
    //desplegable profesor
    public static SelectItem[] getSelectItemsProfesor(List<Profesor> entities, boolean selectOne) {
        int size = selectOne ? entities.size() : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;

        for (Profesor x : entities) {
            items[i++] = new SelectItem(x, x.getNomProfesor());
        }
        return items;
    }
    
        //desplegable profesor
    public static SelectItem[] getSelectItemsNota(List<Nota> entities, boolean selectOne) {
        int size = selectOne ? entities.size() : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;

        for (Nota x : entities) {
            items[i++] = new SelectItem(x);
        }
        return items;
    }
    
    //desplegable cursos en el edit de alumnos
    public static SelectItem[] getSelectItemsCursos(List<Curso> entities, boolean selectOne) {
        //int size = selectOne ? entities.size() + 1 : entities.size();
        int size = selectOne ? entities.size() : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        /*if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }*/
        for (Curso x : entities) {
            items[i++] = new SelectItem(x, x.getNomCurso());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

}
