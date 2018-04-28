package com.uiappcenter.NoSmokingPeople.retrofit;

import com.uiappcenter.NoSmokingPeople.Model.ErrorMsgData;
import com.uiappcenter.NoSmokingPeople.Model.ErrorMsgResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 성민 on 2018-03-09.
 */

public interface NetworkService {

    // ?먮윭硫붿꽭吏
    @POST("/errormsg")
    Call<ErrorMsgResult> getErrorMsgResult(@Body ErrorMsgData data);
}
