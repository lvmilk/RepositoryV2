/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDSmanagedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import sessionbean.gds.AirAlliances;
import sessionbean.gds.Exception_Exception;
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

    private Date flightDate = new Date();
    private Date currentDate = new Date();

    private String flightStatus;

    private Date depTime;
    private Date arrTime;

    private String depAirport;
    private String arrAirport;
    private Integer bookedSeat;
    private Integer availableSeat;
    private Integer seatQuota;
    private String companyName;

    private String username;

    private String depIATA;
    private String arrIATA;

    private String cabinName;
    private Double price;

    private Integer rowStart;
    private Integer rowEnd;

    private char columnStart;
    private char columnEnd;

    @PostConstruct
    public void init() {
        try {
            username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserId");
            AirAlliances al = new AirAlliances();
            al = retrieveAccInfo(username);
            System.out.println("************This is in GDS_FlightRelease:" + al.getName());
            companyName = al.getName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void arrangeSeat() throws IOException, DatatypeConfigurationException {
        int i, j, cRow = 0, cColumn = 0;
        for (i = rowStart; i <= rowEnd; i++) {
            cRow++;
        }

        for (j = columnStart; j <= columnEnd; j++) {
            cColumn++;
        }

        seatQuota = cRow * cColumn;

        GregorianCalendar c = new GregorianCalendar();

        c.setTime(depTime);
        XMLGregorianCalendar depTimeConvert = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        c.setTime(arrTime);
        XMLGregorianCalendar arrTimeConvert = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        publishFlight(flightNo, depTimeConvert, arrTimeConvert, depAirport, arrAirport, depIATA, arrIATA, seatQuota, companyName, cabinName, price, rowStart, rowEnd, columnStart, columnEnd);

    }

    public void releaseFlight() throws DatatypeConfigurationException, IOException {
        System.out.println("********This is in release flight");

        FacesContext.getCurrentInstance().getExternalContext().redirect("./gdsCenterChooseSeat.xhtml");
    }

    private AirAlliances retrieveAccInfo(java.lang.String gdsUserId) throws Exception_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.retrieveAccInfo(gdsUserId);
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
     * @return the depTime
     */
    public Date getDepTime() {
        return depTime;
    }

    /**
     * @param depTime the depTime to set
     */
    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    /**
     * @return the arrTime
     */
    public Date getArrTime() {
        return arrTime;
    }

    /**
     * @param arrTime the arrTime to set
     */
    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
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
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the depIATA
     */
    public String getDepIATA() {
        return depIATA;
    }

    /**
     * @param depIATA the depIATA to set
     */
    public void setDepIATA(String depIATA) {
        this.depIATA = depIATA;
    }

    /**
     * @return the arrIATA
     */
    public String getArrIATA() {
        return arrIATA;
    }

    /**
     * @param arrIATA the arrIATA to set
     */
    public void setArrIATA(String arrIATA) {
        this.arrIATA = arrIATA;
    }

    /**
     * @return the cabinName
     */
    public String getCabinName() {
        return cabinName;
    }

    /**
     * @param cabinName the cabinName to set
     */
    public void setCabinName(String cabinName) {
        this.cabinName = cabinName;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }


    private boolean publishFlight(java.lang.String flightNo, javax.xml.datatype.XMLGregorianCalendar depTime, javax.xml.datatype.XMLGregorianCalendar arrTime, java.lang.String depAirport, java.lang.String arrAirport, java.lang.String depIATA, java.lang.String arrIATA, java.lang.Integer seatQuota, java.lang.String companyName, java.lang.String cabinName, java.lang.Double price, java.lang.Integer rowStart, java.lang.Integer rowEnd, int columnStart, int columnEnd) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sessionbean.gds.GDSLoginBean port = service.getGDSLoginBeanPort();
        return port.publishFlight(flightNo, depTime, arrTime, depAirport, arrAirport, depIATA, arrIATA, seatQuota, companyName, cabinName, price, rowStart, rowEnd, columnStart, columnEnd);
    }

    /**
     * @return the rowStart
     */
    public Integer getRowStart() {
        return rowStart;
    }

    /**
     * @param rowStart the rowStart to set
     */
    public void setRowStart(Integer rowStart) {
        this.rowStart = rowStart;
    }

    /**
     * @return the rowEnd
     */
    public Integer getRowEnd() {
        return rowEnd;
    }

    /**
     * @param rowEnd the rowEnd to set
     */
    public void setRowEnd(Integer rowEnd) {
        this.rowEnd = rowEnd;
    }

    /**
     * @return the columnStart
     */
    public char getColumnStart() {
        return columnStart;
    }

    /**
     * @param columnStart the columnStart to set
     */
    public void setColumnStart(char columnStart) {
        this.columnStart = columnStart;
    }

    /**
     * @return the columnEnd
     */
    public char getColumnEnd() {
        return columnEnd;
    }

    /**
     * @param columnEnd the columnEnd to set
     */
    public void setColumnEnd(char columnEnd) {
        this.columnEnd = columnEnd;
    }

}
