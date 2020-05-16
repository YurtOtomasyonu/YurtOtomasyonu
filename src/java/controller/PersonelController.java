package controller;

import dao.PersonelDao;
import dao.PersonelturDao;
import entity.Personel;
import entity.Personeltur;
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
public class PersonelController implements Serializable {
    
    private Personel personel;
    private List<Personel> personelList;
    private PersonelDao personelDao;
    
    private List<Personeltur> personelturList;
    private PersonelturDao personelturDao;
    
     public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String str = value.toString();
        if(str.length()<3){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 8 hane giriniz"));
        }
        if(str.length()>20){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 20 hane girilebilir"));
        }
    }
    
    public void delete(){
        this.getPersonelDao().remove(this.personel);
        this.clearForm();
    }
    public void deleteConfirm(Personel personel) {
        this.personel = personel;
    }
    
    public void clearForm() {
        this.personel = new Personel();
        
    }
    
    public void updateForm(Personel p){
        this.personel=p;
        
    }
    
    public void update(){
        this.getPersonelDao().edit(this.personel);
        this.clearForm();
    }
    
    public void create(){
        this.getPersonelDao().create(this.personel);
        this.clearForm();
    }

    public Personel getPersonel() {
        if(this.personel==null){
            this.personel = new Personel();
        }
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public List<Personel> getPersonelList() {
        this.personelList=this.getPersonelDao().findAll();
        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }

    public PersonelDao getPersonelDao() {
        if(this.personelDao == null){
            this.personelDao = new PersonelDao();
        }
        return personelDao;
    }

    public void setPersonelDao(PersonelDao personelDao) {
        this.personelDao = personelDao;
    }

    public PersonelturDao getPersonelturDao() {
        if(this.personelturDao == null){
            this.personelturDao = new PersonelturDao();
        }
        return personelturDao;
    }

    public List<Personeltur> getPersonelturList() {
        this.personelturList = this.getPersonelturDao().findAll();
        return personelturList;
    }

    public void setPersonelturList(List<Personeltur> personelturList) {
        this.personelturList = personelturList;
    }
    
}