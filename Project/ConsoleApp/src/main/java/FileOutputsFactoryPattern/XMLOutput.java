package FileOutputsFactoryPattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLOutput extends FileOutput {
    public XMLOutput(ArrayList<Double> array) {
        result = array;
    }

    @XmlRootElement(name = "results")
    private static class XMLSerialization {

        @XmlElement(name = "result")
        private List<Double> results = new ArrayList<>();

        void getResults(ArrayList<Double> array) {
            results = array;
        }


    }

    @Override
    public void fileFlushing() throws JAXBException, IOException {
        XMLSerialization serialization = new XMLSerialization();
        serialization.getResults(result);
        StringWriter writer = new StringWriter();

        JAXBContext context = null;
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File("output/output.xml")))) {
            context = JAXBContext.newInstance(XMLSerialization.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(serialization, writer);
        fileWriter.write(writer.toString());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
