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
import io.kubernetes.client.openapi.models.V1ContainerStateRunning;
import io.kubernetes.client.openapi.models.V1ContainerStateTerminated;
import io.kubernetes.client.openapi.models.V1ContainerStateWaiting;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ContainerState holds a possible state of container. Only one of its members may be specified. If none of them is specified, the default one is ContainerStateWaiting.
 */
@ApiModel(description = "ContainerState holds a possible state of container. Only one of its members may be specified. If none of them is specified, the default one is ContainerStateWaiting.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V1ContainerState {
  public static final String SERIALIZED_NAME_RUNNING = "running";
  @SerializedName(SERIALIZED_NAME_RUNNING)
  private V1ContainerStateRunning running;

  public static final String SERIALIZED_NAME_TERMINATED = "terminated";
  @SerializedName(SERIALIZED_NAME_TERMINATED)
  private V1ContainerStateTerminated terminated;

  public static final String SERIALIZED_NAME_WAITING = "waiting";
  @SerializedName(SERIALIZED_NAME_WAITING)
  private V1ContainerStateWaiting waiting;


  public V1ContainerState running(V1ContainerStateRunning running) {
    
    this.running = running;
    return this;
  }

   /**
   * Get running
   * @return running
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1ContainerStateRunning getRunning() {
    return running;
  }


  public void setRunning(V1ContainerStateRunning running) {
    this.running = running;
  }


  public V1ContainerState terminated(V1ContainerStateTerminated terminated) {
    
    this.terminated = terminated;
    return this;
  }

   /**
   * Get terminated
   * @return terminated
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1ContainerStateTerminated getTerminated() {
    return terminated;
  }


  public void setTerminated(V1ContainerStateTerminated terminated) {
    this.terminated = terminated;
  }


  public V1ContainerState waiting(V1ContainerStateWaiting waiting) {
    
    this.waiting = waiting;
    return this;
  }

   /**
   * Get waiting
   * @return waiting
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1ContainerStateWaiting getWaiting() {
    return waiting;
  }


  public void setWaiting(V1ContainerStateWaiting waiting) {
    this.waiting = waiting;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1ContainerState v1ContainerState = (V1ContainerState) o;
    return Objects.equals(this.running, v1ContainerState.running) &&
        Objects.equals(this.terminated, v1ContainerState.terminated) &&
        Objects.equals(this.waiting, v1ContainerState.waiting);
  }

  @Override
  public int hashCode() {
    return Objects.hash(running, terminated, waiting);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1ContainerState {\n");
    sb.append("    running: ").append(toIndentedString(running)).append("\n");
    sb.append("    terminated: ").append(toIndentedString(terminated)).append("\n");
    sb.append("    waiting: ").append(toIndentedString(waiting)).append("\n");
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

