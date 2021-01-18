/*
 * Kubernetes
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: release-1.18
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.kubernetes.client.openapi.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.kubernetes.client.openapi.models.V1alpha1LimitResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * LimitedPriorityLevelConfiguration specifies how to handle requests that are subject to limits. It addresses two issues:  * How are requests for this priority level limited?  * What should be done with requests that exceed the limit?
 */
@ApiModel(description = "LimitedPriorityLevelConfiguration specifies how to handle requests that are subject to limits. It addresses two issues:  * How are requests for this priority level limited?  * What should be done with requests that exceed the limit?")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V1alpha1LimitedPriorityLevelConfiguration {
  public static final String SERIALIZED_NAME_ASSURED_CONCURRENCY_SHARES = "assuredConcurrencyShares";
  @SerializedName(SERIALIZED_NAME_ASSURED_CONCURRENCY_SHARES)
  private Integer assuredConcurrencyShares;

  public static final String SERIALIZED_NAME_LIMIT_RESPONSE = "limitResponse";
  @SerializedName(SERIALIZED_NAME_LIMIT_RESPONSE)
  private V1alpha1LimitResponse limitResponse;


  public V1alpha1LimitedPriorityLevelConfiguration assuredConcurrencyShares(Integer assuredConcurrencyShares) {
    
    this.assuredConcurrencyShares = assuredConcurrencyShares;
    return this;
  }

   /**
   * &#x60;assuredConcurrencyShares&#x60; (ACS) configures the execution limit, which is a limit on the number of requests of this priority level that may be exeucting at a given time.  ACS must be a positive number. The server&#39;s concurrency limit (SCL) is divided among the concurrency-controlled priority levels in proportion to their assured concurrency shares. This produces the assured concurrency value (ACV) --- the number of requests that may be executing at a time --- for each such priority level:              ACV(l) &#x3D; ceil( SCL * ACS(l) / ( sum[priority levels k] ACS(k) ) )  bigger numbers of ACS mean more reserved concurrent requests (at the expense of every other PL). This field has a default value of 30.
   * @return assuredConcurrencyShares
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "`assuredConcurrencyShares` (ACS) configures the execution limit, which is a limit on the number of requests of this priority level that may be exeucting at a given time.  ACS must be a positive number. The server's concurrency limit (SCL) is divided among the concurrency-controlled priority levels in proportion to their assured concurrency shares. This produces the assured concurrency value (ACV) --- the number of requests that may be executing at a time --- for each such priority level:              ACV(l) = ceil( SCL * ACS(l) / ( sum[priority levels k] ACS(k) ) )  bigger numbers of ACS mean more reserved concurrent requests (at the expense of every other PL). This field has a default value of 30.")

  public Integer getAssuredConcurrencyShares() {
    return assuredConcurrencyShares;
  }


  public void setAssuredConcurrencyShares(Integer assuredConcurrencyShares) {
    this.assuredConcurrencyShares = assuredConcurrencyShares;
  }


  public V1alpha1LimitedPriorityLevelConfiguration limitResponse(V1alpha1LimitResponse limitResponse) {
    
    this.limitResponse = limitResponse;
    return this;
  }

   /**
   * Get limitResponse
   * @return limitResponse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1alpha1LimitResponse getLimitResponse() {
    return limitResponse;
  }


  public void setLimitResponse(V1alpha1LimitResponse limitResponse) {
    this.limitResponse = limitResponse;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1LimitedPriorityLevelConfiguration v1alpha1LimitedPriorityLevelConfiguration = (V1alpha1LimitedPriorityLevelConfiguration) o;
    return Objects.equals(this.assuredConcurrencyShares, v1alpha1LimitedPriorityLevelConfiguration.assuredConcurrencyShares) &&
        Objects.equals(this.limitResponse, v1alpha1LimitedPriorityLevelConfiguration.limitResponse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(assuredConcurrencyShares, limitResponse);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1LimitedPriorityLevelConfiguration {\n");
    sb.append("    assuredConcurrencyShares: ").append(toIndentedString(assuredConcurrencyShares)).append("\n");
    sb.append("    limitResponse: ").append(toIndentedString(limitResponse)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

