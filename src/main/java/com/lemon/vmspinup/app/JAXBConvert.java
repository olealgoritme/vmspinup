package com.lemon.vmspinup.app;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;


public class JAXBConvert {

    private JAXBContext context;

    public JAXBConvert(Class<?>[] classList) {
        try {
            context = JAXBContext.newInstance(classList);
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

    public Object xmlToObject (String xml) {
        Object object;
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            object = unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }
    public Object xmlFileToObject (File file) {
        Object object;
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            object = unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }

    public String xmlFileToString (File file) {
        String res;
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            res = unmarshaller.unmarshal(file).toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }



}



