package com.myretail.productapi.controller

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import com.myretail.productapi.domain.HealthCheckResponse
import com.myretail.productapi.domain.ProductResponse
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class HealthCheckControllerSpec extends Specification {

    private CassandraClusterFactoryBean cassandraClusterFactoryBean = Mock(CassandraClusterFactoryBean)
    private RestTemplate restTemplate = Mock(RestTemplate)
    private String endpoint = "sample/url"
    private String host = "test.host"

    private HealthCheckController healthCheckController = new HealthCheckController(
            cluster: cassandraClusterFactoryBean,
            restTemplate: restTemplate,
            endpoint: endpoint,
            host: host
    )

    def "Successful health check"() {

        given:
        Session session = Mock(Session)
        ResponseEntity<ProductResponse> productResponseResponseEntity = Mock(ResponseEntity)

        when:
        List<HealthCheckResponse> responses = healthCheckController.getHealthCheck()

        then:
        2 * cassandraClusterFactoryBean.getObject() >> Mock(Cluster)
        1 * cassandraClusterFactoryBean.getObject().connect() >> session
        1 * session.close()

        1 * restTemplate.exchange(String.format(host+endpoint, 13860429), HttpMethod.GET, null, ProductResponse.class) >> productResponseResponseEntity
        1 * productResponseResponseEntity.statusCode >> HttpStatus.OK
        0 * _

        responses.size() == 2
        responses.get(0).name == "Cassandra Connection"
        responses.get(0).status == "Success"

        responses.get(1).name == "Product Endpoint"
        responses.get(1).status == "Success"
    }

    def "Rest client failed"() {

        given:
        Session session = Mock(Session)
        ResponseEntity<ProductResponse> productResponseResponseEntity = Mock(ResponseEntity)

        when:
        List<HealthCheckResponse> responses = healthCheckController.getHealthCheck()

        then:
        2 * cassandraClusterFactoryBean.getObject() >> Mock(Cluster)
        1 * cassandraClusterFactoryBean.getObject().connect() >> session
        1 * session.close()

        1 * restTemplate.exchange(String.format(host+endpoint, 13860429), HttpMethod.GET, null, ProductResponse.class) >> productResponseResponseEntity
        2 * productResponseResponseEntity.statusCode >> HttpStatus.NOT_FOUND
        0 * _

        responses.size() == 2
        responses.get(0).name == "Cassandra Connection"
        responses.get(0).status == "Success"

        responses.get(1).name == "Product Endpoint"
        responses.get(1).status == "Failure"
    }

    def "Exception in rest client"() {

        given:
        Session session = Mock(Session)

        when:
        List<HealthCheckResponse> responses = healthCheckController.getHealthCheck()

        then:
        2 * cassandraClusterFactoryBean.getObject() >> Mock(Cluster)
        1 * cassandraClusterFactoryBean.getObject().connect() >> session
        1 * session.close()

        1 * restTemplate.exchange(String.format(host+endpoint, 13860429), HttpMethod.GET, null, ProductResponse.class) >> { throw new Exception("some exception") }
        0 * _

        responses.size() == 2
        responses.get(0).name == "Cassandra Connection"
        responses.get(0).status == "Success"

        responses.get(1).name == "Product Endpoint"
        responses.get(1).status == "Failure"
    }


    def "Cassandra connection failure"() {

        given:
        ResponseEntity<ProductResponse> productResponseResponseEntity = Mock(ResponseEntity)

        when:
        List<HealthCheckResponse> responses = healthCheckController.getHealthCheck()

        then:
        2 * cassandraClusterFactoryBean.getObject() >> Mock(Cluster)
        1 * cassandraClusterFactoryBean.getObject().connect() >> { throw new Exception("some exception") }

        1 * restTemplate.exchange(String.format(host+endpoint, 13860429), HttpMethod.GET, null, ProductResponse.class) >> productResponseResponseEntity
        1 * productResponseResponseEntity.statusCode >> HttpStatus.OK
        0 * _

        responses.size() == 2
        responses.get(0).name == "Cassandra Connection"
        responses.get(0).status == "Failure"

        responses.get(1).name == "Product Endpoint"
        responses.get(1).status == "Success"
    }
}
