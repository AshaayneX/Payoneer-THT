package com.payoneer.data;

import com.payoneer.model.ListResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * retrofit Api interface for payoneer URL
 */
public interface PayoneerApi {
    /** static url */
    String BASE_URL  = "https://raw.githubusercontent.com/";

    /** making GET request to retrieve list data */
    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call<ListResult> getPaymentMethods();
 }
