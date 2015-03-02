package javazoom.jl.xmlparser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	String [] filename;
	String [] URL;
	NodeList nList = null;
	public void parse() {

		try {

			File fXmlFile = new File("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			nList = doc.getElementsByTagName("station");

			filename = new String[nList.getLength()];
			URL = new String[nList.getLength()];

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					filename[temp] = getTagValue("folderpath", eElement);
					URL[temp] = getTagValue("url", eElement);	 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

	public String getFileName(int i) {
		// TODO Auto-generated method stub
		return filename[i];
	}

	public String getURL(int i) {
		// TODO Auto-generated method stub
		return URL[i];
	}

	public int getStationCount() {
		// TODO Auto-generated method stub
		return nList.getLength();
	}
}
