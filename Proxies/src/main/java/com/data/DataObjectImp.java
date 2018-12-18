package com.data;

public class DataObjectImp implements DataObject {

    private String IP;
    private String protocols;
    private int port;
    private String locate;
    private int responseTime;


    public DataObjectImp(String ip, String protocols, int port, String locate){
        this.setIP(ip);
        this.setProtocols(protocols);
        this.setPort(port);
        this.setLocate(locate);
        this.setResponseTime(-1);
    }

    @Override
    public String toString() {
        return "{\"protocols\" :  \""+this.getProtocols()+
                ",   \"ip\":\""+this.getIP()+"\",     \"port\":\""+this.getPort()+
                "\",    \"locate\":\""+this.getLocate()+"\",    \"responseTime\":\"" +
                ""+this.getResponseTime()+"\"}";
    }

    @Override
    public String getIP() {
        return IP;
    }

    @Override
    public String getProtocols() {
        return protocols;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setIP(String IP) {
        this.IP = IP;
    }

    @Override
    public void setProtocols(String protocols) {
        this.protocols = protocols;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String getLocate() {
        return locate;
    }

    @Override
    public int getResponseTime() {
        return responseTime;
    }

    @Override
    public void setLocate(String locate) {
        this.locate = locate;
    }

    @Override
    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
}
