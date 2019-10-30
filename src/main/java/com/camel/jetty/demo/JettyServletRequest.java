package com.camel.jetty.demo;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class JettyServletRequest {
	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("jetty:http://localhost:8180/user/agent").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						HttpServletRequest req = exchange.getIn().getBody(HttpServletRequest.class); 
						Message out = exchange.getOut();
						out.setBody(req.getHeader("User-Agent"));
					}
				});
			}
		});
		context.start();
	}
}
