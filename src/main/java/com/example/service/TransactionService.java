package com.example.service;

import com.example.model.CreditCard;
import com.example.model.Response;
import org.springframework.stereotype.Service;

import javax.xml.soap.*;

/**
 * @author Brayden
 * @create 2/20/22 10:24 PM
 * @Description
 */
@Service
public class TransactionService
{
//	public Response creditTransaction(CreditCard card) {
//
//	}
	// SAAJ - SOAP Client Testing
	public static void main(String args[]) {
        /*
            The example below requests from the Web Service at:
             http://www.webservicex.net/uszip.asmx?op=GetInfoByCity


            To call other WS, change the parameters below, which are:
             - the SOAP Endpoint URL (that is, where the service is responding from)
             - the SOAP Action

            Also change the contents of the method createSoapEnvelope() in this class. It constructs
             the inner part of the SOAP envelope that is actually sent.
         */
		String soapEndpointUrl = "http://localhost:5051/transaction";
		String soapAction = "http://localhost:5051/transaction";

		callSoapWebService(soapEndpointUrl, soapAction);
	}

	private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException
	{
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String myNamespace = "tran";
		String myNamespaceURI = "transaction";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:GetInfoByCity>
                        <myNamespace:USCity>New York</myNamespace:USCity>
                    </myNamespace:GetInfoByCity>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyList = soapBody.addChildElement("CustomerRequest", myNamespace);
		SOAPElement soapBodyElem1 = soapBodyList.addChildElement("username", myNamespace);
		SOAPElement soapBodyElem2 = soapBodyList.addChildElement("creditNumber", myNamespace);
		soapBodyElem2.addTextNode("New York");
		soapBodyElem2.addTextNode("4324234");
	}

	private static Response callSoapWebService(String soapEndpointUrl, String soapAction) {
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);
			SOAPBody body = soapResponse.getSOAPBody();
			String res = body.getFirstChild().getFirstChild().getFirstChild().getNodeValue();

			// Print the SOAP Response
			System.out.println("Response SOAP Message:");
			soapResponse.writeTo(System.out);
			System.out.println();

			soapConnection.close();
			if (res.equals("true")) {
				return new Response<>("Transaction Success");
			}
			return new Response<>("Transaction Fail");
		} catch (Exception e) {
			System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
			e.printStackTrace();
		}
		return new Response<>("error");
	}

	private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");

		return soapMessage;
	}

	public Response credit()
	{
		String soapEndpointUrl = "http://localhost:5051/transaction";
		String soapAction = "http://localhost:5051/transaction";

		return callSoapWebService(soapEndpointUrl, soapAction);
	}
}
