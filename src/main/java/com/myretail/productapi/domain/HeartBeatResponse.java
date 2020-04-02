package com.myretail.productapi.domain;

/**
 * This domain objects represents heart beat response of product api.
 *
 * @author Selvaraj Karuppusamy
 */
public class HeartBeatResponse {

    /**
     * Represents application name(ex: my-retail)
     */
    private String appName;

    /**
     * Represents current application version(ex: 1.0.0).
     */
    private String version;

    public HeartBeatResponse() {
    }

    public HeartBeatResponse(String appName, String version) {
        this.appName = appName;
        this.version = version;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
