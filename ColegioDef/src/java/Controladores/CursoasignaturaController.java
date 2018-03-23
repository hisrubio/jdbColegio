package Controladores;

import Entidades.Cursoasignatura;
import Controladores.util.JsfUtil;
import Controladores.util.PaginationHelper;
import Entidades.Curso;
import Entidades.Profesor;
import Facades.CursoasignaturaFacade;

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

@Named("cursoasignaturaController")
@SessionScoped
public class CursoasignaturaController implements Serializable {

    private Cursoasignatura current;
    private DataModel items = null;
    @EJB
    private Facades.CursoasignaturaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Profesor profesor;
    private List<Cursoasignatura> lista=new ArrayList();

    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    public List<Cursoasignatura> getLista() {
        return lista;
    }
    public void setLista(List<Cursoasignatura> lista) {
        this.lista = lista;
    }
    
    
    
    public CursoasignaturaController() {
    }

    public Cursoasignatura getSelected() {
        if (current == null) {
            current = new Cursoasignatura();
            current.setCursoasignaturaPK(new Entidades.CursoasignaturaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private CursoasignaturaFacade getFacade() {
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
        current = (Cursoasignatura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Cursoasignatura();
        current.setCursoasignaturaPK(new Entidades.CursoasignaturaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getCursoasignaturaPK().setCodCurso(current.getCurso().getCodCurso());
            current.getCursoasignaturaPK().setCodAsignatura(current.getAsignatura().getCodAsignatura());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CursoasignaturaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Cursoasignatura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getCursoasignaturaPK().setCodCurso(current.getCurso().getCodCurso());
            current.getCursoasignaturaPK().setCodAsignatura(current.getAsignatura().getCodAsignatura());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CursoasignaturaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Cursoasignatura) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CursoasignaturaDeleted"));
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

    public Cursoasignatura getCursoasignatura(Entidades.CursoasignaturaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Cursoasignatura.class)
    public static class CursoasignaturaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CursoasignaturaController controller = (CursoasignaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cursoasignaturaController");
            return controller.getCursoasignatura(getKey(value));
        }

        Entidades.CursoasignaturaPK getKey(String value) {
            Entidades.CursoasignaturaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entidades.CursoasignaturaPK();
            key.setCodCurso(values[0]);
            key.setCodAsignatura(values[1]);
            return key;
        }

        String getStringKey(Entidades.CursoasignaturaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCodCurso());
            sb.append(SEPARATOR);
            sb.append(value.getCodAsignatura());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cursoasignatura) {
                Cursoasignatura o = (Cursoasignatura) object;
                return getStringKey(o.getCursoasignaturaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cursoasignatura.class.getName());
            }
        }

    }

    public void obtenerAsignaturasProfesor(){
        lista = ejbFacade.AsignaturasProfesor(profesor);
    } 
}
