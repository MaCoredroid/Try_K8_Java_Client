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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * WebhookThrottleConfig holds the configuration for throttling events
 */
@ApiModel(description = "WebhookThrottleConfig holds the configuration for throttling events")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1alpha1WebhookThrottleConfig {
  public static final String SERIALIZED_NAME_BURST = "burst";
  @SerializedName(SERIALIZED_NAME_BURST)
  private Long burst;

  public static final String SERIALIZED_NAME_QPS = "qps";
  @SerializedName(SERIALIZED_NAME_QPS)
  private Long qps;


  public V1alpha1WebhookThrottleConfig burst(Long burst) {
    
    this.burst = burst;
    return this;
  }

   /**
   * ThrottleBurst is the maximum number of events sent at the same moment default 15 QPS
   * @return burst
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "ThrottleBurst is the maximum number of events sent at the same moment default 15 QPS")

  public Long getBurst() {
    return burst;
  }


  public void setBurst(Long burst) {
    this.burst = burst;
  }


  public V1alpha1WebhookThrottleConfig qps(Long qps) {
    
    this.qps = qps;
    return this;
  }

   /**
   * ThrottleQPS maximum number of batches per second default 10 QPS
   * @return qps
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "ThrottleQPS maximum number of batches per second default 10 QPS")

  public Long getQps() {
    return qps;
  }


  public void setQps(Long qps) {
    this.qps = qps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1WebhookThrottleConfig v1alpha1WebhookThrottleConfig = (V1alpha1WebhookThrottleConfig) o;
    return Objects.equals(this.burst, v1alpha1WebhookThrottleConfig.burst) &&
        Objects.equals(this.qps, v1alpha1WebhookThrottleConfig.qps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(burst, qps);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1WebhookThrottleConfig {\n");
    sb.append("    burst: ").append(toIndentedString(burst)).append("\n");
    sb.append("    qps: ").append(toIndentedString(qps)).append("\n");
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

