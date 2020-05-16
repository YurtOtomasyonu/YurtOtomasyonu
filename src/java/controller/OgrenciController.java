package controller;

import dao.OdalarDao;
import dao.OgrenciDao;
import entity.Bina;
import entity.Odalar;
import entity.Ogrenci;
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
public class OgrenciController implements Serializable {
    
    private Ogrenci ogrenci;
    private List<Ogrenci> ogrenciList;
    private OgrenciDao ogrenciDao;
    
    private List<Odalar> odalarList;
    private OdalarDao odalarDao;
    private OdalarController odalarController;
    
   

    public OdalarController getOdalarController() {
        if(this.odalarController == null){
            this.odalarController = new OdalarController();
        }
        return odalarController;
    }

    public void setOdalarController(OdalarController odalarController) {
        this.odalarController = odalarController;
    }
    
    
    public void delete(){
        this.getOgrenciDao().remove(this.ogrenci);
        this.clearForm();
    }
     public void deleteConfirm(Ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }
    
    public void clearForm() {
        this.ogrenci = new Ogrenci();
        
    }
    
    public void updateForm(Ogrenci o){
        this.ogrenci=o;
       
    }
    
    public void update(){
        this.getOgrenciDao().edit(this.ogrenci);
        this.clearForm();
    }
    
    public void create(){
        this.getOgrenciDao().create(this.ogrenci);
        this.clearForm();
    }

    public Ogrenci getOgrenci() {
        if(this.ogrenci==null){
            this.ogrenci = new Ogrenci();
        }
        return ogrenci;
    }

    public void setOgrenci(Ogrenci Ogrenci) {
        this.ogrenci = ogrenci;
    }

    public List<Ogrenci> getOgrenciList() {
        this.ogrenciList=this.getOgrenciDao().findAll();
        return ogrenciList;
    }

    public void setOgrenciList(List<Ogrenci> ogrenciList) {
        this.ogrenciList = ogrenciList;
    }

    public OgrenciDao getOgrenciDao() {
        if(this.ogrenciDao == null){
            this.ogrenciDao = new OgrenciDao();
        }
        return ogrenciDao;
    }

    public void setOgrenciDao(OgrenciDao ogrenciDao) {
        this.ogrenciDao = ogrenciDao;
    }

    public OdalarDao getOdalarDao() {
        if(this.odalarDao == null){
            this.odalarDao = new OdalarDao();
        }
        return odalarDao;
    }

    public List<Odalar> getOdalarList() {
        this.odalarList = this.getOdalarDao().findAll();
        return odalarList;
    }

    public void setOdalarList(List<Odalar> odalarList) {
        this.odalarList = odalarList;
    }
    
}
