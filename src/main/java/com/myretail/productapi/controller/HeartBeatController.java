package com.myretail.productapi.controller;


import com.myretail.productapi.domain.HeartBeatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This heartbeat controller serves system's metadata and it will be used by deployment services.
 *
 * @author Selvaraj Karuppusamy
 */
@RestController
@RequestMapping("/")
public class HeartBeatController {

    @Value("${application.name}")
    String name;

    @Value("${application.version}")
    String version;

    /**
     * This method returns system's metadata like application name and version.
     *
     * @return HeartBeatResponse
     */
    @GetMapping(value = "/heartbeat", produces = "application/json")
    HeartBeatResponse getHeartBeat() {
        HeartBeatResponse response = new HeartBeatResponse(name, version);
        return response;
    }

}
