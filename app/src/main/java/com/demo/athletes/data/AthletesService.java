package com.demo.athletes.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;



public interface AthletesService {

    @GET
    Observable<AthletesResponse> fetchAthletes(@Url String url);
}
