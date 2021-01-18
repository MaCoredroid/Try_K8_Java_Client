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
import io.kubernetes.client.openapi.models.V1alpha1FlowSchemaCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FlowSchemaStatus represents the current state of a FlowSchema.
 */
@ApiModel(description = "FlowSchemaStatus represents the current state of a FlowSchema.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V1alpha1FlowSchemaStatus {
  public static final String SERIALIZED_NAME_CONDITIONS = "conditions";
  @SerializedName(SERIALIZED_NAME_CONDITIONS)
  private List<V1alpha1FlowSchemaCondition> conditions = null;


  public V1alpha1FlowSchemaStatus conditions(List<V1alpha1FlowSchemaCondition> conditions) {
    
    this.conditions = conditions;
    return this;
  }

  public V1alpha1FlowSchemaStatus addConditionsItem(V1alpha1FlowSchemaCondition conditionsItem) {
    if (this.conditions == null) {
      this.conditions = new ArrayList<>();
    }
    this.conditions.add(conditionsItem);
    return this;
  }

   /**
   * &#x60;conditions&#x60; is a list of the current states of FlowSchema.
   * @return conditions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "`conditions` is a list of the current states of FlowSchema.")

  public List<V1alpha1FlowSchemaCondition> getConditions() {
    return conditions;
  }


  public void setConditions(List<V1alpha1FlowSchemaCondition> conditions) {
    this.conditions = conditions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1FlowSchemaStatus v1alpha1FlowSchemaStatus = (V1alpha1FlowSchemaStatus) o;
    return Objects.equals(this.conditions, v1alpha1FlowSchemaStatus.conditions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conditions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1FlowSchemaStatus {\n");
    sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n");
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

