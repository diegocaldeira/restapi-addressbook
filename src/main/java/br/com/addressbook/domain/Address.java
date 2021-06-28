package br.com.addressbook.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Defines address domain entity.
 * 
 * @author Diego Caldeira
 * @version 1.0 - 26 Jun 2021
 *
 */
@Data
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = -8380790488842018101L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty(message = "Please provide a street name")
    @Column(name = "streetName", length = 4000)
    private String streetName;

    @NotNull
    @NotEmpty(message = "Please provide a street number")
    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @NotNull
    @NotEmpty(message = "Please provide a neighbourhood")
    @Column(name = "neighbourhood")
    private String neighbourhood;

    @NotNull
    @NotEmpty(message = "Please provide a city")
    @Column(name = "city")
    private String city;

    @NotNull
    @NotEmpty(message = "Please provide a state")
    @Column(name = "state")
    private String state;

    @NotNull
    @NotEmpty(message = "Please provide a country")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Please provide a zipcode")
    @Column(name = "zipcode")
    private Integer zipcode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longetude")
    private Double longitude;

    @Override
    public String toString() {
        final String STRING_SEPARATOR = ", ";
        return new StringBuilder()
                .append(streetName)
                .append(STRING_SEPARATOR)
                .append(number)
                .append(STRING_SEPARATOR)
                .append(neighbourhood)
                .append(STRING_SEPARATOR)
                .append(city)
                .append(STRING_SEPARATOR)
                .append(state)
                .append(STRING_SEPARATOR)
                .append(country).toString();
    }
}



