package com.corekcioglu.weatherobservations.component;

import com.corekcioglu.weatherobservations.dto.ObservationMessage;
import com.corekcioglu.weatherobservations.exception.StringFormatException;
import com.corekcioglu.weatherobservations.model.Observation.Location;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObservationMessageResolver {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static ObservationMessage resolve(String messageString) {
        StringTokenizer tokenizer = new StringTokenizer(messageString, "|");
        if (tokenizer.countTokens() != 5) {
            throw new StringFormatException();
        }

        ObservationMessage message = new ObservationMessage();
        message.setBalloonId(Long.parseLong(tokenizer.nextToken()));
        message.setTimestamp(LocalDateTime.from(DATE_TIME_FORMATTER.parse(tokenizer.nextToken())).toInstant(
            ZoneOffset.UTC));

        String location = tokenizer.nextToken();
        StringTokenizer locationTokenizer = new StringTokenizer(location, ",");
        if (locationTokenizer.countTokens() != 2) {
            throw new StringFormatException();
        }
        message.setLocation(new Location(
            Double.parseDouble(locationTokenizer.nextToken()),
            Double.parseDouble(locationTokenizer.nextToken())));

        message.setTemperature(Double.parseDouble(tokenizer.nextToken()));
        message.setObservatoryId(tokenizer.nextToken());
        return message;
    }
}
