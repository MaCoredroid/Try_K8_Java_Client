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
 * EventSource contains information for an event.
 */
@ApiModel(description = "EventSource contains information for an event.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1EventSource {
  public static final String SERIALIZED_NAME_COMPONENT = "component";
  @SerializedName(SERIALIZED_NAME_COMPONENT)
  private String component;

  public static final String SERIALIZED_NAME_HOST = "host";
  @SerializedName(SERIALIZED_NAME_HOST)
  private String host;


  public V1EventSource component(String component) {
    
    this.component = component;
    return this;
  }

   /**
   * Component from which the event is generated.
   * @return component
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Component from which the event is generated.")

  public String getComponent() {
    return component;
  }


  public void setComponent(String component) {
    this.component = component;
  }


  public V1EventSource host(String host) {
    
    this.host = host;
    return this;
  }

   /**
   * Node name on which the event is generated.
   * @return host
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Node name on which the event is generated.")

  public String getHost() {
    return host;
  }


  public void setHost(String host) {
    this.host = host;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1EventSource v1EventSource = (V1EventSource) o;
    return Objects.equals(this.component, v1EventSource.component) &&
        Objects.equals(this.host, v1EventSource.host);
  }

  @Override
  public int hashCode() {
    return Objects.hash(component, host);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1EventSource {\n");
    sb.append("    component: ").append(toIndentedString(component)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
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

