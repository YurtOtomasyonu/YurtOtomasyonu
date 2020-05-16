package controller;

import dao.BinaDao;
import entity.Bina;
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
public class BinaController implements Serializable {

    private List<Bina> binaList;
    private BinaDao binaDao;
    private Bina bina;
    
   
    
    public void clearForm() {
        this.bina = new Bina();
    }

    public void updateForm(Bina bina) {
        this.bina = bina;
    }

    public void update() {
        this.getBinaDao().update(this.bina);
        this.clearForm();
    }

    public void deleteConfirm(Bina bina) {
        this.bina = bina;
    }

    public void delete() {
        
        this.getBinaDao().delete(this.bina);
        this.clearForm();
    }

    public void create() {

        this.getBinaDao().insert(this.bina);
        this.clearForm();
    }

    public List<Bina> getBinaList() {
        this.binaList = this.getBinaDao().findAll();
        return binaList;
    }

    public void setBinaList(List<Bina> binaList) {
        this.binaList = binaList;
    }

    public BinaDao getBinaDao() {
        if (this.binaDao == null) {
            this.binaDao = new BinaDao();
        }
        return binaDao;
    }

    public void setBinaDao(BinaDao binaDao) {
        this.binaDao = binaDao;
    }

    public Bina getBina() {
        if (this.bina == null) {
            this.bina = new Bina();
        }
        return bina;
    }

    public void setBina(Bina bina) {
        this.bina = bina;
    }
}
