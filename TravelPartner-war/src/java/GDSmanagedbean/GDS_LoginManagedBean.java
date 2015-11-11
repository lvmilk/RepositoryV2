/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDSmanagedbean;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import sessionbean.gds.GDSLoginBean_Service;

/**
 *
 * @author LI HAO
 */
@Named(value = "gdslogin")
@ViewScoped
public class GDS_LoginManagedBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/GDSLoginBean/GDSLoginBean.wsdl")
    private GDSLoginBean_Service service;
    private String username;
    private String password;
    private String stfType = "alliance";

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the stfType
     */
    public String getStfType() {
        return stfType;
    }

    /**
     * @param stfType the stfType to set
     */
    public void setStfType(String stfType) {
        this.stfType = stfType;
    }

    public void gdsLogin()throws IOException  {
        Boolean validity;
//        HttpSession session = SessionUtil.getSession();
//        session.setAttribute("username", username);
//        session.setAttribute("stfType", stfType);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserId", username);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("StaffType", stfType);
        validity = login(username, password);
        System.out.println(validity);
        if (validity) {
            System.out.println("~~~~~~~GDS_Login: Account exists");
            FacesContext.getCurrentInstance().getExternalContext().redirect("gdsWorkspace.xhtml");
        }
    }

    private boolean login(java.lang.String gdsUserId, java.lang.String gdsPwd) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.

        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.login(gdsUserId, gdsPwd);
    }

}
