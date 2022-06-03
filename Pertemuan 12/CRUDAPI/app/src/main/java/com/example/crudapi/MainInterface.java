package com.example.crudapi;

import com.example.crudapi.ListUsers.ListUserResponse;
import com.example.crudapi.ListUsersResource.ListUserResourceResponse;
import com.example.crudapi.LoginSuccessful.BodyLogin;
import com.example.crudapi.LoginSuccessful.LoginResponse;
import com.example.crudapi.LoginUnsuccessful.BodyLoginUnsuccessful;
import com.example.crudapi.LoginUnsuccessful.LoginUnsuccessfulResponse;
import com.example.crudapi.Patch.PatchResponse;
import com.example.crudapi.Post.PostResponse;
import com.example.crudapi.Put.PutResponse;
import com.example.crudapi.RegisterSuccessful.BodyRegister;
import com.example.crudapi.RegisterSuccessful.RegisterResponse;
import com.example.crudapi.RegisterUnsuccessful.BodyRegisterUnsuccessful;
import com.example.crudapi.RegisterUnsuccessful.RegisterUnsuccessfulResponse;
import com.example.crudapi.SingleUsers.SingleResponse;
import com.example.crudapi.SingleUsersResource.SingleResourceResponse;
//import com.example.crudapi.SingleUsersNotFound.SingleNotFoundResponse;
//import com.example.crudapi.Login.BodyLogin;
//import com.example.crudapi.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface MainInterface {

    // TODO Memasukkan Endpoint
    @GET("api/users?page=2")
    Call<ListUserResponse> getList();

    @GET("/api/users/2")
    Call<SingleResponse> getSingle();

    @GET("/api/users?delay=3")
    Call<ListUserResponse> getDelayed();

    @GET("/api/users/23")
    Call<SingleResponse> getSingleNotFound();

    @GET("/api/unknown/2")
    Call<SingleResourceResponse> getSingleResource();

    @GET("/api/unknown/23")
    Call<SingleResourceResponse> getSingleResourceNotFound();

    @GET("/api/unknown")
    Call<ListUserResourceResponse> getListResource();

    @FormUrlEncoded
    @POST("/api/users")
    Call<PostResponse> createPost(@Field("name") String mName,
                                  @Field("job") String mJob);

    @FormUrlEncoded
    @PUT("/api/users/2")
    Call<PutResponse> updatePut(@Field("name") String mName,
                                @Field("job") String mJob);

    @FormUrlEncoded
    @PATCH("/api/users/2")
    Call<PatchResponse> updatePatch(@Field("name") String mName,
                                    @Field("job") String mJob);

    @DELETE("/api/users/2")
    Call<SingleResponse> deleteList();

    @POST("api/login")
    Call<LoginResponse> postLogin(@Body BodyLogin bodyLogin);

    @POST("api/login")
    Call<LoginUnsuccessfulResponse> postUnsuccessLogin(@Body BodyLoginUnsuccessful bodyLoginUnsuccessful);

    @POST("api/register")
    Call<RegisterResponse> postRegister(@Body BodyRegister bodyRegister);

    @POST("api/register")
    Call<RegisterUnsuccessfulResponse> postRegisterUnsuccessful(@Body BodyRegisterUnsuccessful bodyRegisterUnsuccessful);

}
