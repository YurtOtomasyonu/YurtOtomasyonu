package controller;

import java.io.Serializable;
import javax.inject.Named;
import dao.UsersDAO;


@Named
@javax.enterprise.context.SessionScoped

public class UsersControler implements Serializable {

   // private static final long serialVersionUID = 1094801825228386363L;

    private String msg;
    private String uname;
    private String password;
    private String userTuru;
    
    

    public String create() {
        UsersDAO.insert(this.uname, this.password, this.userTuru);
        this.uname = null;
        this.password = null;
        
        return "login";
    }

    public UsersControler() {
       // UsersControler u = new UsersControler();
         // this.clist =new ArrayList();
       // UsersDAO = new UsersDAO();
        this.uname = "";
        this.password = "";
       
    }

    public UsersControler(String msg, String uname, String password, String userTuru) {
        this.msg = msg;
        this.uname = uname;
        this.password = password;
        this.userTuru = userTuru;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserTuru() {
        return userTuru;
    }

    public void setUserTuru(String userTuru) {
        this.userTuru = userTuru;
    }

 
}
