package br.com.addressbook.integrations.googlemaps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Google Maps Geolocation client for easily converts an address into geographic coordinates.
 * 
 * @author Diego Caldeira
 * @version 1.0 - 26 Jun 2021
 */
@Configuration
public class GoogleGeocodingClient {
    
    @Value("${app.integrations.googlemaps.key}")
    private String apiKey;
    
    @Bean
    public GeoApiContext getGeocodingClient () {
        return new GeoApiContext().setApiKey(apiKey); 
    }
    
    /**
     * Converts an address into geographic coordinates.
     * 
     * @param address
     * @return 
     */
    public LatLng getLatLng(String address) {
        LatLng location = null;
        try {
            GeocodingResult[] geocoding = GeocodingApi.geocode(this.getGeocodingClient(), address).await();
            if (geocoding.length > 0) {
                location = geocoding[0].geometry.location;
            }
        } catch (Exception ex) {
            Logger.getLogger(GoogleGeocodingClient.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return location;
    }
}




















