package com.ramblerag.xslt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

public final class TransformService {
	
	private static TransformService instance;

	private TransformService() {
	}

	public static TransformService getInstance() {
		if (null == instance) {
			instance = new TransformService();
		}
		return instance;
	}

	public StreamResult transform(String xslFilePath, InputStream xmlInput, StreamResult xslResult) throws FileNotFoundException, TransformerException {
		
		// 1. Instantiate a TransformerFactory.
		TransformerFactory tFactory = TransformerFactory.newInstance();

		// 2. Use the TransformerFactory to process the stylesheet Source and
		// generate a Transformer.
		InputStream xslStream = this.getClass().getClassLoader().getResourceAsStream(xslFilePath);
		Transformer transformer = tFactory.newTransformer(new StreamSource(xslStream));

		// 3. Use the Transformer to transform an XML Source and send the
		// output to a Result object.
		transformer.transform(new StreamSource(xmlInput), xslResult);
		return xslResult;
	}

}
