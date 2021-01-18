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
import io.kubernetes.client.openapi.models.V1CSINodeDriver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSINodeSpec holds information about the specification of all CSI drivers installed on a node
 */
@ApiModel(description = "CSINodeSpec holds information about the specification of all CSI drivers installed on a node")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V1CSINodeSpec {
  public static final String SERIALIZED_NAME_DRIVERS = "drivers";
  @SerializedName(SERIALIZED_NAME_DRIVERS)
  private List<V1CSINodeDriver> drivers = new ArrayList<>();


  public V1CSINodeSpec drivers(List<V1CSINodeDriver> drivers) {
    
    this.drivers = drivers;
    return this;
  }

  public V1CSINodeSpec addDriversItem(V1CSINodeDriver driversItem) {
    this.drivers.add(driversItem);
    return this;
  }

   /**
   * drivers is a list of information of all CSI Drivers existing on a node. If all drivers in the list are uninstalled, this can become empty.
   * @return drivers
  **/
  @ApiModelProperty(required = true, value = "drivers is a list of information of all CSI Drivers existing on a node. If all drivers in the list are uninstalled, this can become empty.")

  public List<V1CSINodeDriver> getDrivers() {
    return drivers;
  }


  public void setDrivers(List<V1CSINodeDriver> drivers) {
    this.drivers = drivers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1CSINodeSpec v1CSINodeSpec = (V1CSINodeSpec) o;
    return Objects.equals(this.drivers, v1CSINodeSpec.drivers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(drivers);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1CSINodeSpec {\n");
    sb.append("    drivers: ").append(toIndentedString(drivers)).append("\n");
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

