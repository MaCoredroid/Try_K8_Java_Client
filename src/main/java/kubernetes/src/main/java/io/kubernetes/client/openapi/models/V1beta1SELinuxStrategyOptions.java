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
import io.kubernetes.client.openapi.models.V1SELinuxOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * SELinuxStrategyOptions defines the strategy type and any options used to create the strategy.
 */
@ApiModel(description = "SELinuxStrategyOptions defines the strategy type and any options used to create the strategy.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V1beta1SELinuxStrategyOptions {
  public static final String SERIALIZED_NAME_RULE = "rule";
  @SerializedName(SERIALIZED_NAME_RULE)
  private String rule;

  public static final String SERIALIZED_NAME_SE_LINUX_OPTIONS = "seLinuxOptions";
  @SerializedName(SERIALIZED_NAME_SE_LINUX_OPTIONS)
  private V1SELinuxOptions seLinuxOptions;


  public V1beta1SELinuxStrategyOptions rule(String rule) {
    
    this.rule = rule;
    return this;
  }

   /**
   * rule is the strategy that will dictate the allowable labels that may be set.
   * @return rule
  **/
  @ApiModelProperty(required = true, value = "rule is the strategy that will dictate the allowable labels that may be set.")

  public String getRule() {
    return rule;
  }


  public void setRule(String rule) {
    this.rule = rule;
  }


  public V1beta1SELinuxStrategyOptions seLinuxOptions(V1SELinuxOptions seLinuxOptions) {
    
    this.seLinuxOptions = seLinuxOptions;
    return this;
  }

   /**
   * Get seLinuxOptions
   * @return seLinuxOptions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1SELinuxOptions getSeLinuxOptions() {
    return seLinuxOptions;
  }


  public void setSeLinuxOptions(V1SELinuxOptions seLinuxOptions) {
    this.seLinuxOptions = seLinuxOptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1beta1SELinuxStrategyOptions v1beta1SELinuxStrategyOptions = (V1beta1SELinuxStrategyOptions) o;
    return Objects.equals(this.rule, v1beta1SELinuxStrategyOptions.rule) &&
        Objects.equals(this.seLinuxOptions, v1beta1SELinuxStrategyOptions.seLinuxOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rule, seLinuxOptions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1beta1SELinuxStrategyOptions {\n");
    sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
    sb.append("    seLinuxOptions: ").append(toIndentedString(seLinuxOptions)).append("\n");
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

