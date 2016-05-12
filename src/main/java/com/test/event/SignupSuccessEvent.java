package com.test.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.test.model.Person;



@SuppressWarnings("serial")
public class SignupSuccessEvent extends ApplicationEvent {
	private final Locale locale;
	private final Person person;

	public SignupSuccessEvent(final Locale locale, final Person person) {
		super(person);
		this.locale = locale;
		this.person = person;
	}

	public Locale getLocale() {
		return locale;
	}

	public Person getPerson() {
		return person;
	}

}
