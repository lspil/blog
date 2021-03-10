package com.example.wiremockfortesting.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "httpbin", url = "${base.url.httpbin}")
public interface HttpBinProxy {

  @GetMapping("/anything")
  String getAnything();
}
