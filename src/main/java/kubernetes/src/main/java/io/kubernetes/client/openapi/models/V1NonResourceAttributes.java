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
 * NonResourceAttributes includes the authorization attributes available for non-resource requests to the Authorizer interface
 */
@ApiModel(description = "NonResourceAttributes includes the authorization attributes available for non-resource requests to the Authorizer interface")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1NonResourceAttributes {
  public static final String SERIALIZED_NAME_PATH = "path";
  @SerializedName(SERIALIZED_NAME_PATH)
  private String path;

  public static final String SERIALIZED_NAME_VERB = "verb";
  @SerializedName(SERIALIZED_NAME_VERB)
  private String verb;


  public V1NonResourceAttributes path(String path) {
    
    this.path = path;
    return this;
  }

   /**
   * Path is the URL path of the request
   * @return path
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Path is the URL path of the request")

  public String getPath() {
    return path;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public V1NonResourceAttributes verb(String verb) {
    
    this.verb = verb;
    return this;
  }

   /**
   * Verb is the standard HTTP verb
   * @return verb
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Verb is the standard HTTP verb")

  public String getVerb() {
    return verb;
  }


  public void setVerb(String verb) {
    this.verb = verb;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1NonResourceAttributes v1NonResourceAttributes = (V1NonResourceAttributes) o;
    return Objects.equals(this.path, v1NonResourceAttributes.path) &&
        Objects.equals(this.verb, v1NonResourceAttributes.verb);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, verb);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1NonResourceAttributes {\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    verb: ").append(toIndentedString(verb)).append("\n");
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

