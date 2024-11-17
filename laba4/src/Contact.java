//Сироткин Илья 5 группа 2 курс задача "Контакты"

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Contact implements Comparable<Contact>, Iterator<String>{
    private final String name;
    private final String telephone;
    private final String officeTelephone;
    private final String homeTelephone;
    private final String email;
    private final String website;
    private final String address;
    private int currentIndex = 0;
    private ArrayList<String> data;

    ////ГЕТЕРЫ////
    public String getName(){
        return this.name;
    }
    public String getTelephone(){
        return telephone;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getOfficeTelephone(){
        return officeTelephone;
    }
    public String getHomeTelephone(){
        return homeTelephone;
    }
    public String getWebsite(){
        return website;
    }

    ///CNSTR////
    public Contact(String _name, String _telephone) {
        name = _name;
        telephone = _telephone;
        officeTelephone=null;
        homeTelephone=null;
        email=null;
        website=null;
        address=null;
        this.data = new ArrayList<>(Arrays.asList(
                name,
                telephone,
                officeTelephone,
                homeTelephone,
                email,
                website,
                address
        ));
    }

    ////CNSTR////
    public Contact(String _name, String _telephone, String _officeTelephone, String _homeTelephone, String _email, String _website, String _address) {
        name = _name;
        telephone = _telephone;
        officeTelephone =_officeTelephone;
        homeTelephone = _homeTelephone;
        email = _email;
        website = _website;
        address = _address;
        this.data = new ArrayList<>(Arrays.asList(
                name,
                telephone,
                officeTelephone,
                homeTelephone,
                email,
                website,
                address
        ));
    }

    ////CNSTR////
    public Contact(String inputString){
            String[] words =inputString.split(",");
            if(words.length==9)
                words[words.length-3]+=","+words[words.length-2]+","+words[words.length-1];
            else if(words.length==8)
                words[words.length - 2] += "," + words[words.length - 1];

        name = words[0].trim();
        telephone = words[1].trim();
        officeTelephone = words.length > 2 ? words[2].trim() : null;
        homeTelephone = words.length > 3 ? words[3].trim() : null;
        email = words.length > 4 ? words[4].trim() : null;
        website = words.length > 5 ? words[5].trim() : null;
        address = words.length > 6 ? words[6].trim() : null;

    }


    ////TO STRING////
    @Override
    public String toString() {
        return String.join(", ",
                name,
                telephone,
                officeTelephone != null ? officeTelephone: "",
                homeTelephone != null ?  homeTelephone : "",
                email != null ? email :"",
                website != null ? website:"",
                address != null ? address : ""
        );
    }

    ////COMPARE TO////
    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.getName());
    }

    ////HAS NEXT////
    @Override
    public boolean hasNext() {
        return currentIndex < data.size();
    }

    ////NEXT////
    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(currentIndex++);
    }
}
