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
 * AllowedFlexVolume represents a single Flexvolume that is allowed to be used.
 */
@ApiModel(description = "AllowedFlexVolume represents a single Flexvolume that is allowed to be used.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1beta1AllowedFlexVolume {
  public static final String SERIALIZED_NAME_DRIVER = "driver";
  @SerializedName(SERIALIZED_NAME_DRIVER)
  private String driver;


  public V1beta1AllowedFlexVolume driver(String driver) {
    
    this.driver = driver;
    return this;
  }

   /**
   * driver is the name of the Flexvolume driver.
   * @return driver
  **/
  @ApiModelProperty(required = true, value = "driver is the name of the Flexvolume driver.")

  public String getDriver() {
    return driver;
  }


  public void setDriver(String driver) {
    this.driver = driver;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1beta1AllowedFlexVolume v1beta1AllowedFlexVolume = (V1beta1AllowedFlexVolume) o;
    return Objects.equals(this.driver, v1beta1AllowedFlexVolume.driver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(driver);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1beta1AllowedFlexVolume {\n");
    sb.append("    driver: ").append(toIndentedString(driver)).append("\n");
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

