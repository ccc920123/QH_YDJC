/**
 * RmJaxRpcOutAccessServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.scxd.webjczhpt;

public class RmJaxRpcOutAccessServiceLocator extends org.apache.axis.client.Service implements com.scxd.webjczhpt.RmJaxRpcOutAccessService {

    public RmJaxRpcOutAccessServiceLocator() {
    }


    public RmJaxRpcOutAccessServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RmJaxRpcOutAccessServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RmOutAccess
    private java.lang.String RmOutAccess_address = "http://10.192.12.138:9080/rmweb/services/RmOutAccess";

    public java.lang.String getRmOutAccessAddress() {
        return RmOutAccess_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RmOutAccessWSDDServiceName = "RmOutAccess";

    public java.lang.String getRmOutAccessWSDDServiceName() {
        return RmOutAccessWSDDServiceName;
    }

    public void setRmOutAccessWSDDServiceName(java.lang.String name) {
        RmOutAccessWSDDServiceName = name;
    }

    public com.scxd.webjczhpt.RmJaxRpcOutAccess getRmOutAccess() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RmOutAccess_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRmOutAccess(endpoint);
    }

    public com.scxd.webjczhpt.RmJaxRpcOutAccess getRmOutAccess(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.scxd.webjczhpt.RmOutAccessSoapBindingStub _stub = new com.scxd.webjczhpt.RmOutAccessSoapBindingStub(portAddress, this);
            _stub.setPortName(getRmOutAccessWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRmOutAccessEndpointAddress(java.lang.String address) {
        RmOutAccess_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.scxd.webjczhpt.RmJaxRpcOutAccess.class.isAssignableFrom(serviceEndpointInterface)) {
                com.scxd.webjczhpt.RmOutAccessSoapBindingStub _stub = new com.scxd.webjczhpt.RmOutAccessSoapBindingStub(new java.net.URL(RmOutAccess_address), this);
                _stub.setPortName(getRmOutAccessWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RmOutAccess".equals(inputPortName)) {
            return getRmOutAccess();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/rmweb/services/RmOutAccess", "RmJaxRpcOutAccessService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/rmweb/services/RmOutAccess", "RmOutAccess"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RmOutAccess".equals(portName)) {
            setRmOutAccessEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
