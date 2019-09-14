package com.lemon.vmspinup.app;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;


public class JAXBConvert {

    private JAXBContext context;

    public JAXBConvert(Class[] classList) {
        try {
            context = JAXBContextFactory.createContext(classList, null);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String objectToXML (Object object) {
        StringWriter sw;
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            sw = new StringWriter();
            marshaller.marshal(object, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return sw.toString();
    }

    public Object xmlFileToObject (File file) {
        Object object;
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            object = unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }
}



