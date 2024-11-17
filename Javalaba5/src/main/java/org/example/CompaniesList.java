package org.example;
//Сироткин Илья 5 группа 2 курс Лаба 5
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class CompaniesList {
    private ArrayList<Company> companies;

    @XmlElementWrapper(name = "companies")
    @XmlElement(name = "company")

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public CompaniesList(){}
    public CompaniesList(ArrayList<Company> _companies) { companies = _companies; }

}
