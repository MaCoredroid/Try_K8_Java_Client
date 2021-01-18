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
 * SecretReference represents a Secret Reference. It has enough information to retrieve secret in any namespace
 */
@ApiModel(description = "SecretReference represents a Secret Reference. It has enough information to retrieve secret in any namespace")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T03:08:54.156Z[Etc/UTC]")
public class V1SecretReference {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_NAMESPACE = "namespace";
  @SerializedName(SERIALIZED_NAME_NAMESPACE)
  private String namespace;


  public V1SecretReference name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Name is unique within a namespace to reference a secret resource.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Name is unique within a namespace to reference a secret resource.")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public V1SecretReference namespace(String namespace) {
    
    this.namespace = namespace;
    return this;
  }

   /**
   * Namespace defines the space within which the secret name must be unique.
   * @return namespace
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Namespace defines the space within which the secret name must be unique.")

  public String getNamespace() {
    return namespace;
  }


  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1SecretReference v1SecretReference = (V1SecretReference) o;
    return Objects.equals(this.name, v1SecretReference.name) &&
        Objects.equals(this.namespace, v1SecretReference.namespace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, namespace);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1SecretReference {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    namespace: ").append(toIndentedString(namespace)).append("\n");
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

