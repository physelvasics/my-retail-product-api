package com.myretail.productapi.controller;

import com.datastax.driver.core.Session;

import com.myretail.productapi.domain.HealthCheckResponse;
import com.myretail.productapi.domain.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * This health check controller serves system's integration status information. This information will used by
 * the consumers.
 *
 * @author Selvaraj Karuppusamy
 */
@RestController
@RequestMapping("/")
public class HealthCheckController {

    @Autowired
    private CassandraClusterFactoryBean cluster;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product.endpoint}")
    private String endpoint;

    /**
     * This method will run health check on cassandra and product service.
     *
     * @return List<HealthCheckResponse> contains health check status of each integration item.
     */
    @GetMapping(value = "/health-check", produces = "application/json")
    List<HealthCheckResponse> getHealthCheck() {
        List<HealthCheckResponse> responseList = new ArrayList<>();

        //Cassandra Health check
        HealthCheckResponse cassandraHealthCheckResponse = new
                HealthCheckResponse();
        cassandraHealthCheckResponse.setName("Cassandra Connection");

        try (Session session = cluster.getObject().connect()) {
            cassandraHealthCheckResponse.setStatus("Success");
        } catch (Exception e) {
            cassandraHealthCheckResponse.setStatus("Failure");
            cassandraHealthCheckResponse.setDetails(e.getMessage());
        } finally {
            responseList.add(cassandraHealthCheckResponse);
        }

        // Product Api Health check
        HealthCheckResponse apiHealthCheckResponse = new
                HealthCheckResponse();
        apiHealthCheckResponse.setName("Product Endpoint");

        try {
            //Using the provided end point as health check endpoint of given service
            ResponseEntity<ProductResponse> responseEntity = restTemplate.exchange(String.format(endpoint, 13860429), HttpMethod.GET, null, ProductResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                apiHealthCheckResponse.setStatus("Success");
            } else {
                apiHealthCheckResponse.setStatus("Failure");
                apiHealthCheckResponse.setDetails("Status Code: "+responseEntity.getStatusCode().toString());
            }

        } catch (Exception e) {
            apiHealthCheckResponse.setStatus("Failure");
            apiHealthCheckResponse.setDetails(e.getMessage());
        } finally {
            responseList.add(apiHealthCheckResponse);
        }

        //TODO: Need to make consolidated status which represent overall system health.

        return responseList;
    }

}
