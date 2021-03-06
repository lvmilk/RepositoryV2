/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDSmanagedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import sessionbean.gds.AirAlliances;
import sessionbean.gds.Exception_Exception;
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
    
    private String companyName;

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

    public void gdsLogin() throws IOException {
        Boolean validity;
        String bkSystem = "ARS";
//        HttpSession session = SessionUtil.getSession();
//        session.setAttribute("username", username);
//        session.setAttribute("stfType", stfType);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserId", username);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("StaffType", stfType);
        validity = login(username, password);
        System.out.println(validity);
        if (validity) {
            System.out.println("~~~~~~~GDS_Login: Account exists");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserId", username);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("StaffType", stfType);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bkSystem", bkSystem);

            FacesContext.getCurrentInstance().getExternalContext().redirect("gdsWorkspace.xhtml");

  
            AirAlliances al = new AirAlliances();
            try {
                al = retrieveAccInfo(username);
            } catch (Exception_Exception ex) {
                Logger.getLogger(GDS_LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("************This is in GDS_FlightRelease:" + al.getName());
            companyName=al.getName();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("companyName", companyName);

        } else {
            System.out.println("Username or password incorrect");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Username or password incorrect"));
        }
    }

    private boolean login(java.lang.String gdsUserId, java.lang.String gdsPwd) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.

        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.login(gdsUserId, gdsPwd);
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private AirAlliances retrieveAccInfo(java.lang.String gdsUserId) throws Exception_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.retrieveAccInfo(gdsUserId);
    }

}
