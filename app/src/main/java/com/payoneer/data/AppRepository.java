package com.payoneer.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.payoneer.data.PayoneerApi;
import com.payoneer.model.ApplicableNetwork;
import com.payoneer.model.ListResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Singleton class containing all data retrieving methods required by the app
 */
@Singleton
public class AppRepository {
    /** class tag*/
    private static final String TAG = "APP_REPO";

    /** instance of retrofit api for payoneer Uri */
    private final PayoneerApi payoneerApi;

    /** holds response data*/
    @Getter
    private MutableLiveData<List<ApplicableNetwork>> paymentOptionLiveData ;


    @Inject
    public AppRepository(PayoneerApi payoneerApi) {
        this.payoneerApi = payoneerApi;
        paymentOptionLiveData = new MutableLiveData<>();
    };

    /** making the API call and posting response and logging error if available */
    public void callPaymentApi(){
        Call<ListResult> call = payoneerApi.getPaymentMethods();
        call.enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(Call<ListResult> call, Response<ListResult> response) {
                paymentOptionLiveData.postValue(response.body().getNetworks().getApplicable());
            }

            @Override
            public void onFailure(Call<ListResult> call, Throwable t) {
                Log.e(TAG,t.getMessage());
                paymentOptionLiveData.postValue(null);
            }
        });
    }
}
