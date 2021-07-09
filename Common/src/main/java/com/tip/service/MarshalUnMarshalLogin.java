package com.tip.service;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Response;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.transform.StringSource;

import com.ca.wsdl.IdentityContext;
import com.ca.wsdl.Login;
import com.ca.wsdl.ObjectFactory;
import com.ge.ef.ese.pocketmobile.europe.service.LoginTest;
import com.ge.ef.ese.pocketmobile.europe.service.LoginTestResponse;

public class MarshalUnMarshalLogin {

	public static void main(String[] args) {

		ObjectFactory objFactory = new ObjectFactory();
		Login login = objFactory.createLogin();
		IdentityContext id = new IdentityContext();
		id.setUserName("502352204");
		id.setPassword("Passw0rd!");
		login.setIdentityContext(id);
		login.setAppId("handheld1");
		login.setResource("/handheld1/*");
		login.setAction("NULL");

		LoginTest loginTest = new LoginTest();
		loginTest.setUserId("502352204");
		loginTest.setPassword("Passw0rd!!");

		
		try {
			System.out.println("marshal login");
			//marshal(login);
			marshal(loginTest);

			// System.out.println("marshal error login");
			// marshalError(login);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static String marshal(Login login) throws JAXBException {

		StringWriter stringWriter = new StringWriter();

		JAXBContext jaxbContext = JAXBContext.newInstance(Login.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// format the XML output
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		QName qName = new QName("com.ca.wsdl", "login");
		JAXBElement<Login> root = new JAXBElement<>(qName, Login.class, login);

		jaxbMarshaller.marshal(root, stringWriter);

		String result = stringWriter.toString();
		System.out.println(result);

		jaxbContext = JAXBContext.newInstance(Login.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		// File file = new File("C:/Users/877167/Desktop/login.wsdl");
		// root = jaxbUnmarshaller.unmarshal(new StreamSource(file),
		// Login.class);

		root = jaxbUnmarshaller.unmarshal(new StringSource(result), Login.class);
		login = root.getValue();

		System.out.println(login.getIdentityContext().getUserName());
		System.out.println(login.getIdentityContext().getPassword());

		try {
			SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());
			messageFactory.afterPropertiesSet();

			WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

			marshaller.setContextPath("com.ca.wsdl");
			marshaller.afterPropertiesSet();

			webServiceTemplate.setMarshaller(marshaller);
			webServiceTemplate.afterPropertiesSet();

			Response response = (Response) webServiceTemplate
					.marshalSendAndReceive("http://10.236.160.58:80/authazws/auth", login);

			Response msg = (Response) response;
			System.out.println(msg.toString());
			
		} catch (Exception s) {
			s.printStackTrace();
		}

		return result;
	}
	
	public static String marshal(LoginTest loginTest) throws JAXBException {

		StringWriter stringWriter = new StringWriter();

		JAXBContext jaxbContext = JAXBContext.newInstance(LoginTest.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// format the XML output
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		QName qName = new QName("com.ge.ef.ese.pocketmobile.europe.service", "loginTest");
		JAXBElement<LoginTest> root = new JAXBElement<>(qName, LoginTest.class, loginTest);

		jaxbMarshaller.marshal(root, stringWriter);

		String result = stringWriter.toString();
		System.out.println(result);

		jaxbContext = JAXBContext.newInstance(LoginTest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		// File file = new File("C:/Users/877167/Desktop/login.wsdl");
		// root = jaxbUnmarshaller.unmarshal(new StreamSource(file),
		// Login.class);

		root = jaxbUnmarshaller.unmarshal(new StringSource(result), LoginTest.class);
		loginTest = root.getValue();

		System.out.println(loginTest.getUserId());
		System.out.println(loginTest.getPassword());

		try {
			SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());
			messageFactory.afterPropertiesSet();

			WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

			marshaller.setContextPath("com.ge.ef.ese.pocketmobile.europe.service");
			marshaller.afterPropertiesSet();

			webServiceTemplate.setMarshaller(marshaller);
			webServiceTemplate.setUnmarshaller(marshaller);
			webServiceTemplate.afterPropertiesSet();

			LoginTestResponse response = (LoginTestResponse) webServiceTemplate
					.marshalSendAndReceive("https://www.tip-europe.com/UAT/PMALSCheckInServices/PocketMobileService.wsdl", loginTest);

			LoginTestResponse msg = (LoginTestResponse) response;
			System.out.println(msg.getLoginTestReturn().isAuthenticated());
			
		} catch (Exception s) {
			s.printStackTrace();
		}

		return result;
	}

	public static String marshalError(Login login) throws JAXBException {

		StringWriter stringWriter = new StringWriter();

		JAXBContext jaxbContext = JAXBContext.newInstance(Login.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// format the XML output
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(login, stringWriter);

		String result = stringWriter.toString();
		System.out.println(result);
		return result;
	}

	public static Login unmarshal(File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Login.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		JAXBElement<Login> root = jaxbUnmarshaller.unmarshal(new StreamSource(file), Login.class);
		Login login = root.getValue();

		System.out.println(login.toString());
		return login;
	}

	public static Login unmarshalError(File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Login.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		Login login = (Login) jaxbUnmarshaller.unmarshal(file);
		System.out.println(login.toString());
		return login;
	}
}
