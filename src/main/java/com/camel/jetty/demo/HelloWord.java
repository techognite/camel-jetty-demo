package com.camel.jetty.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWord {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("jetty:http://localhost:8180/helloword").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Message out = exchange.getOut();
						out.setBody("Hello World!");
					}
				});
			}
		});
		context.start();
	}
}