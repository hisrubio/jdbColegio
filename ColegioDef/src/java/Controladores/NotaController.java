package Controladores;

import Entidades.Nota;
import Controladores.util.JsfUtil;
import Controladores.util.PaginationHelper;
import Entidades.Alumno;
import Entidades.Asignatura;
import Facades.NotaFacade;

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

@Named("notaController")
@SessionScoped
public class NotaController implements Serializable {

    private Nota current;
    private DataModel items = null;
    @EJB
    private Facades.NotaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Asignatura asignatura;
    private List<Nota> lista=new ArrayList();
    private Alumno alumno;

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
    public List<Nota> getLista() {
        return lista;
    }
    public void setLista(List<Nota> lista) {
        this.lista = lista;
    }
    
    
    
    public NotaController() {
    }

    public Nota getSelected() {
        if (current == null) {
            current = new Nota();
            current.setNotaPK(new Entidades.NotaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private NotaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

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
        current = (Nota) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Nota();
        current.setNotaPK(new Entidades.NotaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getNotaPK().setNifalumno(current.getAlumno().getNif());
            current.getNotaPK().setCodAsignatura(current.getAsignatura().getCodAsignatura());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NotaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Nota) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getNotaPK().setNifalumno(current.getAlumno().getNif());
            current.getNotaPK().setCodAsignatura(current.getAsignatura().getCodAsignatura());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NotaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Nota) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NotaDeleted"));
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

    public Nota getNota(Entidades.NotaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Nota.class)
    public static class NotaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotaController controller = (NotaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notaController");
            return controller.getNota(getKey(value));
        }

        Entidades.NotaPK getKey(String value) {
            Entidades.NotaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entidades.NotaPK();
            key.setNifalumno(values[0]);
            key.setCodAsignatura(values[1]);
            return key;
        }

        String getStringKey(Entidades.NotaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getNifalumno());
            sb.append(SEPARATOR);
            sb.append(value.getCodAsignatura());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Nota) {
                Nota o = (Nota) object;
                return getStringKey(o.getNotaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Nota.class.getName());
            }
        }

    }
    public void obtenerAlumnosAsignatura(){
        lista = ejbFacade.alumnosAsignatura(asignatura);
    }
    public void obtenerAsignaturaAlumnos(){
        lista = ejbFacade.asignaturaAlumno(alumno);
    }
}
