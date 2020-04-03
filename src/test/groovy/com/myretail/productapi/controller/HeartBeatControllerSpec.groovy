package com.myretail.productapi.controller

import com.myretail.productapi.domain.HeartBeatResponse
import spock.lang.Specification

class HeartBeatControllerSpec extends Specification{

    def "Successful heartbeat"(){
        given:
        HeartBeatController controller = new HeartBeatController(name:"app name", version: "version")

        when:
        HeartBeatResponse heartBeatResponse = controller.getHeartBeat()

        then:
        heartBeatResponse != null
        heartBeatResponse.appName == "app name"
        heartBeatResponse.version == "version"
    }
}
