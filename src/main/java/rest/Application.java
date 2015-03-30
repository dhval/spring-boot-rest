package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dhval on 3/30/15.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    //Utilities methods.

    public static Timestamp toTime(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }
}
