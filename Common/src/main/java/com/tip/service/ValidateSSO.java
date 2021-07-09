package com.tip.service;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import com.ca.wsdl.LoginResponse;

public class ValidateSSO {

	public static void main(String[] args) {
		ValidateSSO validateSSO = new ValidateSSO();
		validateSSO.login();
	}

	public void login() {

		LoginResponse response = new LoginResponse();

		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "http://10.236.160.58:80/authazws/auth";
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
			printSOAPResponse(soapResponse);

			// We can use this soap response further to call the proc etc.

			soapConnection.close();
		} catch (Exception e) {
			System.err.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}

	}

	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = "http://10.236.160.58:80/";

		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("login", serverURI);

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("Login", "login");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("userName");
		soapBodyElem1.addTextNode("502352204");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password");
		soapBodyElem2.addTextNode("Passw0rd!");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "Login");

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message = ");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nResponse SOAP Message = ");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
	}

}
