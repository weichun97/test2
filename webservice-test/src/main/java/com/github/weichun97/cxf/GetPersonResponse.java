
package com.github.weichun97.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getPersonResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getPersonResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="returnPerson" type="{http://cxf.tg.com/}person" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPersonResponse", propOrder = {
    "returnPerson"
})
public class GetPersonResponse {

    protected Person returnPerson;

    /**
     * 获取returnPerson属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getReturnPerson() {
        return returnPerson;
    }

    /**
     * 设置returnPerson属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setReturnPerson(Person value) {
        this.returnPerson = value;
    }

}
