package com.example.test.beans.dao;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Repository
@Slf4j
public class Map {

    public static Float[] geoCoding(String location) {

        if (location == null)
            return null;
        Geocoder geocoder = new Geocoder();
// setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)
// setLanguate : 인코딩 설정
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();
        GeocodeResponse geocoderResponse;
        try {
            geocoderResponse = geocoder.geocode(geocoderRequest);
            if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
                log.info("드렁옴---------------------");
                GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();
                LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();
                Float[] coords = new Float[2];
                coords[0] = latitudeLongitude.getLat().floatValue();
                coords[1] = latitudeLongitude.getLng().floatValue();
                return coords;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
