package org.example;
//Сироткин Илья 5 группа 2 курс Лаба 5
import org.json.JSONObject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;
import java.time.LocalDate;

@XmlRootElement
public class Company {
    private String fullName;
    private String shortName;
    private LocalDate dateUpdate;
    private String address;
    private LocalDate dateFoundation;
    private int countEmployees;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private URL internetAddress;

    public Company(String _fullName, String _shortName, LocalDate _dateUpdate,
                   String _address, LocalDate _dateFoundation, int _countEmployees,
                   String _auditor, String _phone, String _email, String _branch,
                   String _activity, URL _internetAddress) {
        fullName = _fullName;
        shortName = _shortName;
        dateUpdate = _dateUpdate;
        address = _address;
        dateFoundation = _dateFoundation;
        countEmployees = _countEmployees;
        auditor = _auditor;
        phone = _phone;
       email = _email;
        branch = _branch;
        activity = _activity;
        internetAddress = _internetAddress;
    }
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("fullName", fullName);
        obj.put("shortName", shortName);
        obj.put("dateUpdate", dateUpdate);
        obj.put("address", address);
        obj.put("dateFoundation", dateFoundation);
        obj.put("countEmployees", countEmployees);
        obj.put("auditor", auditor);
        obj.put("phone", phone);
        obj.put("email", email);
        obj.put("branch", branch);
        obj.put("activity", activity);
        obj.put("internetAddress", internetAddress);

        return obj;
    }

    public Company(){}

    @XmlAttribute
    public String getFullName() {
        return fullName;
    }

    @XmlElement
    public String getShortName() {
        return shortName;
    }

    @XmlElement
    public LocalDate getDateUpdate() {
        return dateUpdate;
    }

    @XmlAttribute
    public String getAddress() {
        return address;
    }

    @XmlElement
    public LocalDate getDateFoundation() {
        return dateFoundation;
    }

    @XmlElement
    public int getCountEmployees() {
        return countEmployees;
    }

    @XmlAttribute
    public String getAuditor() {
        return auditor;
    }

    @XmlAttribute
    public String getPhone() {
        return phone;
    }

    @XmlAttribute
    public String getEmail() {
        return email;
    }

    @XmlElement
    public String getBranch() {
        return branch;
    }

    @XmlElement
    public String getActivity() {
        return activity;
    }

    @XmlAttribute
    public URL getInternetAddress() {
        return internetAddress;
    }


}