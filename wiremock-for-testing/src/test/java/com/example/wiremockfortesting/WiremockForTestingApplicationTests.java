package com.example.wiremockfortesting;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WiremockForTestingApplicationTests {

  private static WireMockServer wireMockServer;

  @Autowired
  private MockMvc mockMvc;

  @BeforeAll
  static void init() {
    wireMockServer = new WireMockServer(
        new WireMockConfiguration().port(7070)
    );
    wireMockServer.start();
    WireMock.configureFor("localhost", 7070);
  }

  @Test
  void testCallHttpBinAnything() throws Exception {
    stubFor(WireMock.get(urlMatching("/anything"))
        .willReturn(aResponse()
            .withStatus(OK.value())));

    mockMvc.perform(get("/demo"))
        .andExpect(status().isOk());

    verify(getRequestedFor(urlPathEqualTo("/anything")));
  }

}
