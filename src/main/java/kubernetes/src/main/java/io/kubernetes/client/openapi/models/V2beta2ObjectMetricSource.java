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
import io.kubernetes.client.openapi.models.V2beta2CrossVersionObjectReference;
import io.kubernetes.client.openapi.models.V2beta2MetricIdentifier;
import io.kubernetes.client.openapi.models.V2beta2MetricTarget;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ObjectMetricSource indicates how to scale on a metric describing a kubernetes object (for example, hits-per-second on an Ingress object).
 */
@ApiModel(description = "ObjectMetricSource indicates how to scale on a metric describing a kubernetes object (for example, hits-per-second on an Ingress object).")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V2beta2ObjectMetricSource {
  public static final String SERIALIZED_NAME_DESCRIBED_OBJECT = "describedObject";
  @SerializedName(SERIALIZED_NAME_DESCRIBED_OBJECT)
  private V2beta2CrossVersionObjectReference describedObject;

  public static final String SERIALIZED_NAME_METRIC = "metric";
  @SerializedName(SERIALIZED_NAME_METRIC)
  private V2beta2MetricIdentifier metric;

  public static final String SERIALIZED_NAME_TARGET = "target";
  @SerializedName(SERIALIZED_NAME_TARGET)
  private V2beta2MetricTarget target;


  public V2beta2ObjectMetricSource describedObject(V2beta2CrossVersionObjectReference describedObject) {
    
    this.describedObject = describedObject;
    return this;
  }

   /**
   * Get describedObject
   * @return describedObject
  **/
  @ApiModelProperty(required = true, value = "")

  public V2beta2CrossVersionObjectReference getDescribedObject() {
    return describedObject;
  }


  public void setDescribedObject(V2beta2CrossVersionObjectReference describedObject) {
    this.describedObject = describedObject;
  }


  public V2beta2ObjectMetricSource metric(V2beta2MetricIdentifier metric) {
    
    this.metric = metric;
    return this;
  }

   /**
   * Get metric
   * @return metric
  **/
  @ApiModelProperty(required = true, value = "")

  public V2beta2MetricIdentifier getMetric() {
    return metric;
  }


  public void setMetric(V2beta2MetricIdentifier metric) {
    this.metric = metric;
  }


  public V2beta2ObjectMetricSource target(V2beta2MetricTarget target) {
    
    this.target = target;
    return this;
  }

   /**
   * Get target
   * @return target
  **/
  @ApiModelProperty(required = true, value = "")

  public V2beta2MetricTarget getTarget() {
    return target;
  }


  public void setTarget(V2beta2MetricTarget target) {
    this.target = target;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2beta2ObjectMetricSource v2beta2ObjectMetricSource = (V2beta2ObjectMetricSource) o;
    return Objects.equals(this.describedObject, v2beta2ObjectMetricSource.describedObject) &&
        Objects.equals(this.metric, v2beta2ObjectMetricSource.metric) &&
        Objects.equals(this.target, v2beta2ObjectMetricSource.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(describedObject, metric, target);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2beta2ObjectMetricSource {\n");
    sb.append("    describedObject: ").append(toIndentedString(describedObject)).append("\n");
    sb.append("    metric: ").append(toIndentedString(metric)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
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

