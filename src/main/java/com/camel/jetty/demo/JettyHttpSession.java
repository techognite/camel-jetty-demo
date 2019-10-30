package com.camel.jetty.demo;

import javax.servlet.http.HttpSession;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMessage;
import org.apache.camel.impl.DefaultCamelContext;

public class JettyHttpSession {
	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("jetty:http://localhost:8180/session?sessionSupport=true").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						HttpSession session = exchange.getIn(HttpMessage.class).getRequest().getSession();
						Message out = exchange.getOut();
						out.setBody(session.getId());
					}
				});
			}
		});
		context.start();
	}

}
