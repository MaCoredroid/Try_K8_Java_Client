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
import io.kubernetes.client.openapi.models.V1alpha1LimitedPriorityLevelConfiguration;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * PriorityLevelConfigurationSpec specifies the configuration of a priority level.
 */
@ApiModel(description = "PriorityLevelConfigurationSpec specifies the configuration of a priority level.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1alpha1PriorityLevelConfigurationSpec {
  public static final String SERIALIZED_NAME_LIMITED = "limited";
  @SerializedName(SERIALIZED_NAME_LIMITED)
  private V1alpha1LimitedPriorityLevelConfiguration limited;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;


  public V1alpha1PriorityLevelConfigurationSpec limited(V1alpha1LimitedPriorityLevelConfiguration limited) {
    
    this.limited = limited;
    return this;
  }

   /**
   * Get limited
   * @return limited
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1alpha1LimitedPriorityLevelConfiguration getLimited() {
    return limited;
  }


  public void setLimited(V1alpha1LimitedPriorityLevelConfiguration limited) {
    this.limited = limited;
  }


  public V1alpha1PriorityLevelConfigurationSpec type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * &#x60;type&#x60; indicates whether this priority level is subject to limitation on request execution.  A value of &#x60;\&quot;Exempt\&quot;&#x60; means that requests of this priority level are not subject to a limit (and thus are never queued) and do not detract from the capacity made available to other priority levels.  A value of &#x60;\&quot;Limited\&quot;&#x60; means that (a) requests of this priority level _are_ subject to limits and (b) some of the server&#39;s limited capacity is made available exclusively to this priority level. Required.
   * @return type
  **/
  @ApiModelProperty(required = true, value = "`type` indicates whether this priority level is subject to limitation on request execution.  A value of `\"Exempt\"` means that requests of this priority level are not subject to a limit (and thus are never queued) and do not detract from the capacity made available to other priority levels.  A value of `\"Limited\"` means that (a) requests of this priority level _are_ subject to limits and (b) some of the server's limited capacity is made available exclusively to this priority level. Required.")

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1PriorityLevelConfigurationSpec v1alpha1PriorityLevelConfigurationSpec = (V1alpha1PriorityLevelConfigurationSpec) o;
    return Objects.equals(this.limited, v1alpha1PriorityLevelConfigurationSpec.limited) &&
        Objects.equals(this.type, v1alpha1PriorityLevelConfigurationSpec.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(limited, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1PriorityLevelConfigurationSpec {\n");
    sb.append("    limited: ").append(toIndentedString(limited)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

