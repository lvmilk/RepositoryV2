/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonInfraManagedBean;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LI HAO
 */
@Named(value = "gdsSslManagedBean")
@RequestScoped
public class gdsSslManagedBean {
    public void startSsl(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/TravelPartner-war/login.xhtml");
    }

    public void endSsl(ActionEvent event) throws IOException {
//        String serverName = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
//        String serverPort = "8080";
//        System.out.println(serverName);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
//        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("UserId");
//        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).removeAttribute("StaffType");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/TravelPartner-war/GDSpages/gdsLogin.xhtml");
    }
}
