//Сироткин Илья 5 группа 2 курс задача "Контакты"

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static class NameComp implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public static class TelephoneComp implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return new BigInteger(c1.getTelephone()).compareTo(new BigInteger(c2.getTelephone()));
        }
    }
    public static class OfficeTelComp implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            return new BigInteger(o1.getOfficeTelephone()).compareTo(new BigInteger(o2.getOfficeTelephone()));
        }
    }
    public static class HomeTelComp implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            return new BigInteger(o1.getHomeTelephone()).compareTo(new BigInteger(o2.getHomeTelephone()));
        }
    }
    public static class EmailComp implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact c2) {

            return o1.getEmail().compareTo(c2.getEmail());
        }
    }
    public static class WebsiteComp implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.getWebsite().compareTo(o2.getWebsite());
        }
    }
    public static class AddressComp implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.getAddress().compareTo(o2.getAddress());
        }
    }
    private static ArrayList<Contact> contacts=new ArrayList<>();
    public static void main(String[] args) {
        try(BufferedReader file=new BufferedReader(new FileReader("input.txt"));
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))){
            String inputLine=file.readLine();
            while(inputLine!=null){
                contacts.add(new Contact(inputLine));
                inputLine=file.readLine();
            }
            System.out.println("Выбери как сортировать:\n1-Имя\n" +
                    "2-Номер мобильного телефона\n" +
                    "3-Номер рабочего телефона\n" +
                    "4-Номер домашнего телефона\n" +
                    "5-Адрес электронной почты\n" +
                    "6-Вебсайт\n" +
                    "7-Физический адрес\n");
            int section=Integer.parseInt(reader.readLine());
            Comparator<Contact> comp=null;
            switch (section){
                case 1->{
                    comp=new NameComp();
                }
                case 2->{
                    comp=new TelephoneComp();
                }
                case 3->{
                    comp=new OfficeTelComp();
                }
                case 4->{
                    comp=new HomeTelComp();
                }
                case 5->{
                    comp=new EmailComp();
                }
                case 6->{
                    comp=new WebsiteComp();
                }
                case 7->{
                    comp=new AddressComp();
                }
            }
            Collections.sort(contacts,comp);
            for(Contact contact:contacts){
                System.out.println(contact);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}