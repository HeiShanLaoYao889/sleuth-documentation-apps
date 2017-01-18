package io.spring.cloud.sleuth.docs.service3;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired Tracer tracer;

	@RequestMapping("/bar")
	public String service3MethodInController() throws InterruptedException {
		Thread.sleep(300);
		log.info("Hello from service3");
		log.info("Service3: Baggage for [foo] is [" + tracer.getCurrentSpan().getBaggageItem("foo") + "]");
		return "Hello from service3";
	}

	public static void main(String... args) {
		new SpringApplication(Application.class).run(args);
	}
}
