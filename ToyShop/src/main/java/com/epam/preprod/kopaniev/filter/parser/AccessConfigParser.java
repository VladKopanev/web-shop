package com.epam.preprod.kopaniev.filter.parser;

import com.epam.preprod.kopaniev.bean.Role;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Влад on 28.11.2015.
 */
public class AccessConfigParser {

    public Map<String, List<Role>> parseAccessConfig(String pathToAccessConfig) {
        Document doc;
        Map<String, List<Role>> accessMap = null;
        try {
            doc = getDocument(pathToAccessConfig);
            Element root = doc.getRootElement();
            Namespace ns = root.getNamespace();
            List<Element> constraints = root.getChildren("constraint", ns);

            accessMap = new HashMap<>();
            for (Element constraint : constraints) {
                String urlPattern = constraint.getChildText("url-pattern", ns);
                List<Element> rolesElem = constraint.getChildren("role", ns);
                List<Role> roles = rolesElem.stream()
                        .map(e -> Role.valueOf(e.getText().toUpperCase()))
                        .collect(Collectors.toList());
                accessMap.put(urlPattern, roles);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return accessMap;
    }

    private Document getDocument(String file)
            throws ParserConfigurationException, SAXException, IOException {
        //creating DOM Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.parse(new File(file));
        DOMBuilder domBuilder = new DOMBuilder();
        return domBuilder.build(doc);
    }
}
