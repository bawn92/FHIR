/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model.util.test;

import java.io.StringReader;

import javax.xml.stream.XMLStreamReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ibm.watson.health.fhir.model.util.XMLSupport;

public class XMLSupportTest {
    @Test
    public void testParseDiv() throws Exception {
        String div = "<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative</b></p></div>";
        XMLStreamReader reader = XMLSupport.createXMLStreamReader(new StringReader(div));
        reader.next();
        Assert.assertEquals(XMLSupport.parseDiv(reader), div);
        
        div = "<h:div xmlns:h=\"http://www.w3.org/1999/xhtml\"><h:p><h:b>Generated Narrative</h:b></h:p></h:div>";
        reader = XMLSupport.createXMLStreamReader(new StringReader(div));
        reader.next();
        Assert.assertEquals(XMLSupport.parseDiv(reader), div);
        
        div = "<div xmlns=\"http://www.w3.org/1999/xhtml\"><div><p>Anything</p></div></div>";
        reader = XMLSupport.createXMLStreamReader(new StringReader(div));
        reader.next();
        Assert.assertEquals(XMLSupport.parseDiv(reader), div);
    }
}
