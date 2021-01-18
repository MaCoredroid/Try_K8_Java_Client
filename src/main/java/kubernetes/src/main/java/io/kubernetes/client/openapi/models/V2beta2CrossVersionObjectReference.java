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
 * CrossVersionObjectReference contains enough information to let you identify the referred resource.
 */
@ApiModel(description = "CrossVersionObjectReference contains enough information to let you identify the referred resource.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V2beta2CrossVersionObjectReference {
  public static final String SERIALIZED_NAME_API_VERSION = "apiVersion";
  @SerializedName(SERIALIZED_NAME_API_VERSION)
  private String apiVersion;

  public static final String SERIALIZED_NAME_KIND = "kind";
  @SerializedName(SERIALIZED_NAME_KIND)
  private String kind;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;


  public V2beta2CrossVersionObjectReference apiVersion(String apiVersion) {
    
    this.apiVersion = apiVersion;
    return this;
  }

   /**
   * API version of the referent
   * @return apiVersion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "API version of the referent")

  public String getApiVersion() {
    return apiVersion;
  }


  public void setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
  }


  public V2beta2CrossVersionObjectReference kind(String kind) {
    
    this.kind = kind;
    return this;
  }

   /**
   * Kind of the referent; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds\&quot;
   * @return kind
  **/
  @ApiModelProperty(required = true, value = "Kind of the referent; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds\"")

  public String getKind() {
    return kind;
  }


  public void setKind(String kind) {
    this.kind = kind;
  }


  public V2beta2CrossVersionObjectReference name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Name of the referent; More info: http://kubernetes.io/docs/user-guide/identifiers#names
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Name of the referent; More info: http://kubernetes.io/docs/user-guide/identifiers#names")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2beta2CrossVersionObjectReference v2beta2CrossVersionObjectReference = (V2beta2CrossVersionObjectReference) o;
    return Objects.equals(this.apiVersion, v2beta2CrossVersionObjectReference.apiVersion) &&
        Objects.equals(this.kind, v2beta2CrossVersionObjectReference.kind) &&
        Objects.equals(this.name, v2beta2CrossVersionObjectReference.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiVersion, kind, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2beta2CrossVersionObjectReference {\n");
    sb.append("    apiVersion: ").append(toIndentedString(apiVersion)).append("\n");
    sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

