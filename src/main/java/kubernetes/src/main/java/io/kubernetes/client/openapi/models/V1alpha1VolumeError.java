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
import java.time.OffsetDateTime;

/**
 * VolumeError captures an error encountered during a volume operation.
 */
@ApiModel(description = "VolumeError captures an error encountered during a volume operation.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1alpha1VolumeError {
  public static final String SERIALIZED_NAME_MESSAGE = "message";
  @SerializedName(SERIALIZED_NAME_MESSAGE)
  private String message;

  public static final String SERIALIZED_NAME_TIME = "time";
  @SerializedName(SERIALIZED_NAME_TIME)
  private OffsetDateTime time;


  public V1alpha1VolumeError message(String message) {
    
    this.message = message;
    return this;
  }

   /**
   * String detailing the error encountered during Attach or Detach operation. This string maybe logged, so it should not contain sensitive information.
   * @return message
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "String detailing the error encountered during Attach or Detach operation. This string maybe logged, so it should not contain sensitive information.")

  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }


  public V1alpha1VolumeError time(OffsetDateTime time) {
    
    this.time = time;
    return this;
  }

   /**
   * Time the error was encountered.
   * @return time
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Time the error was encountered.")

  public OffsetDateTime getTime() {
    return time;
  }


  public void setTime(OffsetDateTime time) {
    this.time = time;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1VolumeError v1alpha1VolumeError = (V1alpha1VolumeError) o;
    return Objects.equals(this.message, v1alpha1VolumeError.message) &&
        Objects.equals(this.time, v1alpha1VolumeError.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, time);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1VolumeError {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
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

