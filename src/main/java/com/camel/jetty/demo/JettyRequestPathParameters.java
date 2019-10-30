package com.camel.jetty.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class JettyRequestPathParameters {
	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {

				restConfiguration().component("jetty").host("localhost").port(8180);

				rest("/hello").get("/{id}").to("direct:hello");

				from("direct:hello").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						System.out.println(exchange.getIn().getHeader("id"));
					}
				}).end();
			}

		});
		context.start();
	}
}
