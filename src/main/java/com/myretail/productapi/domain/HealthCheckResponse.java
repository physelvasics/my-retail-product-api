package com.myretail.productapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This domain objects represents health check response which includes name and status of a system
 * for which the health check is performed.
 *
 * @author Selvaraj Karuppusamy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthCheckResponse {

    /**
     * Represents name of the service.
     */
    private String name;

    /**
     * Represents status of the service.
     */
    private String status;

    /**
     * Contains additional details about the system having failure.
     * This will be null in case of target system is healthy.
     */
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
