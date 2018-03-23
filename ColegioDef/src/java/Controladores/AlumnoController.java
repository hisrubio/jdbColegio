package Controladores;

import Entidades.Alumno;
import Controladores.util.JsfUtil;
import Controladores.util.PaginationHelper;
import Entidades.Curso;
import Entidades.Cursoasignatura;
import Facades.AlumnoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("alumnoController")
@SessionScoped
public class AlumnoController implements Serializable {

    private Alumno current;
    private DataModel items = null;
    @EJB
    private Facades.AlumnoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Curso curso;
    private List<Alumno> lista=new ArrayList();
    private List<Cursoasignatura> lista2=new ArrayList();

    public List<Cursoasignatura> getLista2() {
        return lista2;
    }
    public void setLista2(List<Cursoasignatura> lista2) {
        this.lista2 = lista2;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public List<Alumno> getLista() {
        return lista;
    }
    public void setLista(List<Alumno> lista) {
        this.lista = lista;
    }
    
    
    
    
    
    
    
    public AlumnoController() {
    }

    public Alumno getSelected() {
        if (current == null) {
            current = new Alumno();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AlumnoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(getItemsAvailableSelectMany().length) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Alumno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Alumno();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlumnoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Alumno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlumnoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Alumno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlumnoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
    //desplegable de alumnos
    public SelectItem[] getItemsAvailableSelectOneAlumnos() {
        return JsfUtil.getSelectItemsAlumnos(ejbFacade.findAll(), true);
    }

    public Alumno getAlumno(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Alumno.class, value="AlumnosConverter")
    public static class AlumnoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlumnoController controller = (AlumnoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alumnoController");
            return controller.getAlumno(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Alumno) {
                Alumno o = (Alumno) object;
                return getStringKey(o.getNif());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Alumno.class.getName());
            }
        }

    }
    
    public void obtener(){
        obtenerAlumnosCursos();
        obtenerAsignaturaProfesorCursos();
    }
    public void obtenerAlumnosCursos(){
        lista = ejbFacade.alumnosCurso(curso);
    }
    public void obtenerAsignaturaProfesorCursos(){
        lista2 = ejbFacade.AsignaturasProfesoresCursos(curso);
    }
}
