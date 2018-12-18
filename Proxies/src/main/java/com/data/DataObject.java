package com.data;

public interface DataObject {
    void setIP(String ip);

    void setProtocols(String protocols);

    void setPort(int port);

    String getIP();

    String getProtocols();

    int getPort();

    String toString();

    void setResponseTime(int time);

    int getResponseTime();

    String getLocate();

    void setLocate(String locate);
}
