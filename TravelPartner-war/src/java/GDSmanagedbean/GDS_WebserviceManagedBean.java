/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDSmanagedbean;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import sessionbean.gds.GDSLoginBean_Service;

/**
 *
 * @author LI HAO
 */

@Named(value = "gdsWs")
@ViewScoped
public class GDS_WebserviceManagedBean implements Serializable{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/GDSLoginBean/GDSLoginBean.wsdl")
    private GDSLoginBean_Service service;

    private boolean publishFlight(java.lang.String flightNo, javax.xml.datatype.XMLGregorianCalendar flightDate, javax.xml.datatype.XMLGregorianCalendar depTime, javax.xml.datatype.XMLGregorianCalendar arrTime, java.lang.String depAirport, java.lang.String arrAirport, java.lang.String depIATA, java.lang.String arrIATA, java.lang.Integer seatQuota) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.publishFlight(flightNo, flightDate, depTime, arrTime, depAirport, arrAirport, depIATA, arrIATA, seatQuota);
    }
    
}
