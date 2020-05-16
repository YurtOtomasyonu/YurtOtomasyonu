package controller;

import dao.BinaDao;
import dao.OdemelerDao;
import entity.Odemeler;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import javax.inject.Named;

@Named
@SessionScoped
public class OdemelerController implements Serializable {

    private List<Odemeler> odemelerList;
    private OdemelerDao odemelerDao;
    private Odemeler odemeler;

    public void clearForm() {
        this.odemeler = new Odemeler();
    }

    public void updateForm(Odemeler odemeler) {
        this.odemeler = odemeler;
    }

    public void update() {
        this.getOdemelerDao().update(this.odemeler);
        this.clearForm();
    }

    public void deleteConfirm(Odemeler odemeler) {
        this.odemeler = odemeler;
    }

    public void delete() {

        this.getOdemelerDao().delete(this.odemeler);
        this.clearForm();
    }

    public void create() {

        this.getOdemelerDao().insert(this.odemeler);
        this.clearForm();
    }

    public List<Odemeler> getOdemelerList() {
        this.odemelerList = this.getOdemelerDao().findAll();
        return odemelerList;
    }

    public void setOdemelerList(List<Odemeler> odemelerList) {
        this.odemelerList = odemelerList;
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

    public Odemeler getOdemeler() {
        if (this.odemeler == null) {
            this.odemeler = new Odemeler();
        }
        return odemeler;
    }

    public void setOdemeler(Odemeler odemeler) {
        this.odemeler = odemeler;
    }
}