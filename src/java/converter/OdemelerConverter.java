package converter;

import dao.OdemelerDao;
import entity.Odemeler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "odemelerConverter")
public class OdemelerConverter implements Converter {

    private OdemelerDao odemelerDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getOdemelerDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Odemeler bn = (Odemeler) value;
        return bn.getOdeme_id().toString();//bn==null ? null : 
    }

    public OdemelerDao getOdemelerDao() {
        if (this.odemelerDao == null) {
            this.odemelerDao = new OdemelerDao();
        }
        return odemelerDao;
    }

    public void setOdemelerDao(OdemelerDao odemelerDao) {
        this.odemelerDao = odemelerDao;
    }
}
