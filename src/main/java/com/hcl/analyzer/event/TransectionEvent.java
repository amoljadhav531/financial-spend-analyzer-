package com.hcl.analyzer.event;

import org.springframework.context.ApplicationEvent;


public class TransectionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4436338162621811139L;
	
	private Object message;

	public TransectionEvent(Object source) {
		super(source);
		this.message = source;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

}
