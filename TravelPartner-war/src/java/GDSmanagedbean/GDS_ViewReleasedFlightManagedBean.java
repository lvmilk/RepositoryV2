/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDSmanagedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import sessionbean.gds.GDSLoginBean_Service;
import sessionbean.gds.GdsFlight;

/**
 *
 * @author LI HAO
 */
@Named(value = "gdsVRF")
@ViewScoped
public class GDS_ViewReleasedFlightManagedBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/GDSLoginBean/GDSLoginBean.wsdl")
    private GDSLoginBean_Service service;

    private List<GdsFlight> rlList = new ArrayList<GdsFlight>();
    private String companyName;

    @PostConstruct
    public void init() {
        companyName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("companyName");

        rlList = viewReleasedFlight(companyName);
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

    private java.util.List<sessionbean.gds.GdsFlight> viewReleasedFlight(java.lang.String companyName) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.viewReleasedFlight(companyName);
    }

    /**
     * @return the rlList
     */
    public List<GdsFlight> getRlList() {
        return rlList;
    }

    /**
     * @param rlList the rlList to set
     */
    public void setRlList(List<GdsFlight> rlList) {
        this.rlList = rlList;
    }

}
