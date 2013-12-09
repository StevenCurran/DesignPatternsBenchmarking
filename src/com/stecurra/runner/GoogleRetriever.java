package com.stecurra.runner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Lists;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

public class GoogleRetriever implements CalendarMethod {

	private GoogleRetriever() {

		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();

			// initialize the data store factory
			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

			// authorization
			Credential credential = authorize();

			// set up global Calendar instance
			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static final String APPLICATION_NAME = "";

	/** Directory to store user credentials. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");

	/**
	 * Global instance of the {@link DataStoreFactory}. The best practice is to
	 * make it a single globally shared instance across your application.
	 */
	private static FileDataStoreFactory dataStoreFactory;

	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static com.google.api.services.calendar.Calendar client;

	static final java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();

	/** Authorizes the installed application to access user's protected data. */
	private static Credential authorize() throws Exception {
		// load client secrets

		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		
		InputStream strem = new FileInputStream(s + "\\client_secret.json");
		Reader reader = new InputStreamReader(strem);

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);

		if (clientSecrets.getDetails().getClientId().startsWith("Enter") || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
			System.out.println("Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar " + "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
			System.exit(1);
		}
		// set up authorization code flow

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
				dataStoreFactory).build();
		// authorize

		AuthorizationCodeInstalledApp authorizationCodeInstalledApp = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver());

		Credential authorize = authorizationCodeInstalledApp.authorize("user");

		return authorize;
	}

	@Override
	public List<SimpleEvent> retrieveEvents() {
		Date maxOfaWeek = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(maxOfaWeek);
		cal.add(java.util.Calendar.DATE, 7);
		maxOfaWeek.setTime(cal.getTime().getTime());

		Events feed = null;

		try {
			feed = client.events().list(System.getProperty("google.calendar.name")).setTimeMax(new DateTime(maxOfaWeek)).setTimeMin(new DateTime(new Date())).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<SimpleEvent> events = new ArrayList<>();

		for (Event event : feed.getItems()) {

			try {
				SimpleEvent se = new SimpleEvent();
				se.setEventDesc(event.getDescription());

				se.setEventEndDate(new Date(event.getEnd().getDate().getValue()));

				se.setEventStartTime(new Date(event.getStart().getDate().getValue()));
				se.setEventName(event.getSummary());
				events.add(se);

			} catch (Exception e) {
				System.err.println("Problem with: " + event.getSummary());
				System.err.println("at location: " + event.getLocation());
			}

		}

		return events;
	}

	private static void showCalendars() throws IOException {
		CalendarList feed = client.calendarList().list().execute();

		List<CalendarListEntry> items = feed.getItems();

		for (CalendarListEntry calendarListEntry : items) {
			System.out.println(calendarListEntry.getSummary());
		}

	}

	public static GoogleRetriever getInstance() {
		return new GoogleRetriever();
	}

}
