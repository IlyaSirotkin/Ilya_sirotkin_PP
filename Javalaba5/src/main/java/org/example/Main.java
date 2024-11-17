package org.example;
//Сироткин Илья 5 группа 2 курс Лаба 5
import org.json.JSONArray;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class Main {
    public static ArrayList<Company> companies = new ArrayList<>();
    private static ArrayList<Company> selectedCompanies = new ArrayList<>();
    private static String logsData = new String();
    private static File XMLoutput = new File("output.xml");
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static String getCurrentDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(new Date());
    }

    public static Company findByShortName(String shortName) {
        for (Company company : companies) {
            if (company.getShortName().toLowerCase().equals(shortName.toLowerCase()))
                return company;
        }
        return null;
    }

    public static ArrayList<Company> findByBranch(String branch) {
        ArrayList<Company> foundedCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getBranch().toLowerCase().equals(branch.toLowerCase())) {
                foundedCompanies.add(company);
            }
        }
        return foundedCompanies;
    }

    public static ArrayList<Company> findByActivity(String activityName) {
        ArrayList<Company> foundedCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getActivity().toLowerCase().equals(activityName.toLowerCase())) {
                foundedCompanies.add(company);
            }
        }
        return foundedCompanies;
    }

    public static ArrayList<Company> findByEmployees(Integer number1, Integer number2) {
        ArrayList<Company> foundedCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getCountEmployees() >= number1 && company.getCountEmployees() <= number2) {
                foundedCompanies.add(company);
            }
        }
        return foundedCompanies;
    }

    public static ArrayList<Company> findByFoundation(LocalDate date1, LocalDate date2) {
        ArrayList<Company> foundedCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getDateFoundation().isAfter(date1) && company.getDateFoundation().isBefore(date2))
                foundedCompanies.add(company);
        }
        return foundedCompanies;
    }

    public static void main(String[] args) {
        String startTime = getCurrentDate();
        try (BufferedReader CSVreader = new BufferedReader(new FileReader("input.csv"));
             BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
             FileWriter jsonOutput = new FileWriter("output.json");
             FileWriter logsOutput = new FileWriter("logfile.txt", true)) {
            String line=CSVreader.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                for(String attribute: attributes){
                    System.out.print(attribute+", ");
                }
                System.out.println();
                companies.add(new Company(attributes[0], attributes[1], LocalDate.parse(attributes[2], dateTimeFormatter),
                        attributes[3], LocalDate.parse(attributes[4], dateTimeFormatter),
                        Integer.parseInt(!attributes[5].isEmpty() ? attributes[5] : "0"), attributes[6], attributes[7],
                        attributes[8], attributes[9], attributes[10], new URL(attributes[11])));

                line=CSVreader.readLine();
            }

                System.out.println("Выберите:\n" +
                        "1. Найти компанию по краткому наименованию.\n" +
                        "2. Выбрать компании по отрасли.\n" +
                        "3. Выбрать компании по виду деятельности.\n" +
                        "4. Выбрать компании по дате основания в определенном промежутке (с и по). \n" +
                        "5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).");
            int option;
            option = Integer.parseInt(cin.readLine().trim());
            logsData += (String.valueOf(option) + " ");
            while (option < 1 || option > 5) {
                System.out.println("Введите опцию снова, число должно быть в диапозоне 1-5");
                option = Integer.parseInt(cin.readLine().trim());
            }
            switch (option) {
                case 1 -> {
                    System.out.println("Введите короткое наименование компании:");
                    line = cin.readLine();
                    while (!Pattern.matches("[a-zA-Z]+|\\W+", line)) {
                        System.out.println("Введите в правильном формате еще раз");
                        line = cin.readLine().trim();
                    }
                    selectedCompanies.add(findByShortName(line));
                    logsData += (line + " ");
                }
                case 2 -> {
                    System.out.println("Введите отрасль компании:");
                    line = cin.readLine();
                    while (!Pattern.matches("[a-zA-Z]+|\\W+", line)) {
                        System.out.println("Введите в правильном формате еще раз");
                        line = cin.readLine().trim();
                    }
                    selectedCompanies.addAll(findByBranch(line));
                    logsData += (line + " ");
                }
                case 3 -> {
                    System.out.println("Введите вид деятельности:");
                    line = cin.readLine();
                    while (!Pattern.matches("[a-zA-Z]+|\\W+", line)) {
                        System.out.println("Введите в правильном формате еще раз");
                        line = cin.readLine().trim();
                    }
                    selectedCompanies.addAll(findByActivity(line));
                    logsData += (line + " ");
                }
                case 4 -> {

                    System.out.println("Введите даты в формате YYYY-MM-DD YYYY-MM-DD:");
                    String read = cin.readLine().trim();
                    while (!Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}-\\d{2}-\\d{2}", read)) {
                        System.out.println("Введите в правильном формате еще раз");
                        read = cin.readLine().trim();
                    }
                    String[] dates = read.split(" ");
                    logsData += (read + " ");
                    selectedCompanies.addAll(findByFoundation(LocalDate.parse(dates[0], dateTimeFormatter), LocalDate.parse(dates[1], dateTimeFormatter)));

                }
                case 5 -> {

                    System.out.println("Введите число сотрудников (от и до) через пробел");
                    String read = cin.readLine().trim();
                    while (!Pattern.matches("\\d+ \\d+", read)) {
                        System.out.println("Введите в правильном формате еще раз");
                        read = cin.readLine().trim();
                    }
                    String[] numbers = read.split(" ");
                    logsData += (read + " ");
                    selectedCompanies.addAll(findByEmployees(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));

                }
            }

            ////JSON WRITE/////
            JSONArray jsArray = new JSONArray();
            for (Company company : selectedCompanies) {
                jsArray.put(company.toJSONObject());
            }
            jsonOutput.write(jsArray.toString(4));

            ////XML WRITE////
            CompaniesList companyList = new CompaniesList(selectedCompanies);
            JAXBContext context = JAXBContext.newInstance(CompaniesList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(companyList, XMLoutput);

            ////LOGS WRITE////
            logsOutput.write(startTime + " ");
            logsOutput.write(logsData.toString()+"\n");

            System.out.println("Данные выведены в JSON, XML файлы");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}