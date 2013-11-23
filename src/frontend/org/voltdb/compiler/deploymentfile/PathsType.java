//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.08.30 at 02:28:03 PM EDT 
//


package org.voltdb.compiler.deploymentfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pathsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pathsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="voltdbroot" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" default="voltdbroot" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="snapshots" type="{}pathEntry" minOccurs="0"/>
 *         &lt;element name="exportoverflow" type="{}pathEntry" minOccurs="0"/>
 *         &lt;element name="commandlog" type="{}pathEntry" minOccurs="0"/>
 *         &lt;element name="commandlogsnapshot" type="{}pathEntry" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pathsType", propOrder = {

})
public class PathsType {

    protected PathsType.Voltdbroot voltdbroot;
    protected PathEntry snapshots;
    protected PathEntry exportoverflow;
    protected PathEntry commandlog;
    protected PathEntry commandlogsnapshot;

    /**
     * Gets the value of the voltdbroot property.
     * 
     * @return
     *     possible object is
     *     {@link PathsType.Voltdbroot }
     *     
     */
    public PathsType.Voltdbroot getVoltdbroot() {
        return voltdbroot;
    }

    /**
     * Sets the value of the voltdbroot property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathsType.Voltdbroot }
     *     
     */
    public void setVoltdbroot(PathsType.Voltdbroot value) {
        this.voltdbroot = value;
    }

    /**
     * Gets the value of the snapshots property.
     * 
     * @return
     *     possible object is
     *     {@link PathEntry }
     *     
     */
    public PathEntry getSnapshots() {
        return snapshots;
    }

    /**
     * Sets the value of the snapshots property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathEntry }
     *     
     */
    public void setSnapshots(PathEntry value) {
        this.snapshots = value;
    }

    /**
     * Gets the value of the exportoverflow property.
     * 
     * @return
     *     possible object is
     *     {@link PathEntry }
     *     
     */
    public PathEntry getExportoverflow() {
        return exportoverflow;
    }

    /**
     * Sets the value of the exportoverflow property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathEntry }
     *     
     */
    public void setExportoverflow(PathEntry value) {
        this.exportoverflow = value;
    }

    /**
     * Gets the value of the commandlog property.
     * 
     * @return
     *     possible object is
     *     {@link PathEntry }
     *     
     */
    public PathEntry getCommandlog() {
        return commandlog;
    }

    /**
     * Sets the value of the commandlog property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathEntry }
     *     
     */
    public void setCommandlog(PathEntry value) {
        this.commandlog = value;
    }

    /**
     * Gets the value of the commandlogsnapshot property.
     * 
     * @return
     *     possible object is
     *     {@link PathEntry }
     *     
     */
    public PathEntry getCommandlogsnapshot() {
        return commandlogsnapshot;
    }

    /**
     * Sets the value of the commandlogsnapshot property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathEntry }
     *     
     */
    public void setCommandlogsnapshot(PathEntry value) {
        this.commandlogsnapshot = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" default="voltdbroot" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Voltdbroot {

        @XmlAttribute
        protected String path;

        /**
         * Gets the value of the path property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPath() {
            if (path == null) {
                return "voltdbroot";
            } else {
                return path;
            }
        }

        /**
         * Sets the value of the path property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPath(String value) {
            this.path = value;
        }

    }

}
