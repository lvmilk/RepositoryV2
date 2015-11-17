/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDSmanagedbean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.xml.ws.WebServiceRef;
import sessionbean.gds.AirAlliances;
import sessionbean.gds.GDSLoginBean_Service;

/**
 *
 * @author LI HAO
 */
@Named(value = "gdsFR")
@ViewScoped
public class GDS_FlightReleaseManagedBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/GDSLoginBean/GDSLoginBean.wsdl")
    private GDSLoginBean_Service service;

    private String flightNo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date flightDate;

    private String flightStatus;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date actualDepTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date actualArrTime;

    private String depAirport;
    private String arrAirport;
    private Integer bookedSeat;
    private Integer availableSeat;
    private Integer seatQuota;

    private AirAlliances retrieveAccInfo(java.lang.String gdsUserId, java.lang.String gdsPwd) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.retrieveAccInfo(gdsUserId, gdsPwd);
    }

    /**
     * @return the flightNo
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * @param flightNo the flightNo to set
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    /**
     * @return the flightDate
     */
    public Date getFlightDate() {
        return flightDate;
    }

    /**
     * @param flightDate the flightDate to set
     */
    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    /**
     * @return the flightStatus
     */
    public String getFlightStatus() {
        return flightStatus;
    }

    /**
     * @param flightStatus the flightStatus to set
     */
    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    /**
     * @return the actualDepTime
     */
    public Date getActualDepTime() {
        return actualDepTime;
    }

    /**
     * @param actualDepTime the actualDepTime to set
     */
    public void setActualDepTime(Date actualDepTime) {
        this.actualDepTime = actualDepTime;
    }

    /**
     * @return the actualArrTime
     */
    public Date getActualArrTime() {
        return actualArrTime;
    }

    /**
     * @param actualArrTime the actualArrTime to set
     */
    public void setActualArrTime(Date actualArrTime) {
        this.actualArrTime = actualArrTime;
    }

    /**
     * @return the depAirport
     */
    public String getDepAirport() {
        return depAirport;
    }

    /**
     * @param depAirport the depAirport to set
     */
    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    /**
     * @return the arrAirport
     */
    public String getArrAirport() {
        return arrAirport;
    }

    /**
     * @param arrAirport the arrAirport to set
     */
    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }

    /**
     * @return the bookedSeat
     */
    public Integer getBookedSeat() {
        return bookedSeat;
    }

    /**
     * @param bookedSeat the bookedSeat to set
     */
    public void setBookedSeat(Integer bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    /**
     * @return the availableSeat
     */
    public Integer getAvailableSeat() {
        return availableSeat;
    }

    /**
     * @param availableSeat the availableSeat to set
     */
    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    /**
     * @return the seatQuota
     */
    public Integer getSeatQuota() {
        return seatQuota;
    }

    /**
     * @param seatQuota the seatQuota to set
     */
    public void setSeatQuota(Integer seatQuota) {
        this.seatQuota = seatQuota;
    }

}
