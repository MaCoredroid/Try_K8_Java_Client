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
 * ServiceReference holds a reference to Service.legacy.k8s.io
 */
@ApiModel(description = "ServiceReference holds a reference to Service.legacy.k8s.io")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class AdmissionregistrationV1beta1ServiceReference {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_NAMESPACE = "namespace";
  @SerializedName(SERIALIZED_NAME_NAMESPACE)
  private String namespace;

  public static final String SERIALIZED_NAME_PATH = "path";
  @SerializedName(SERIALIZED_NAME_PATH)
  private String path;

  public static final String SERIALIZED_NAME_PORT = "port";
  @SerializedName(SERIALIZED_NAME_PORT)
  private Integer port;


  public AdmissionregistrationV1beta1ServiceReference name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * &#x60;name&#x60; is the name of the service. Required
   * @return name
  **/
  @ApiModelProperty(required = true, value = "`name` is the name of the service. Required")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public AdmissionregistrationV1beta1ServiceReference namespace(String namespace) {
    
    this.namespace = namespace;
    return this;
  }

   /**
   * &#x60;namespace&#x60; is the namespace of the service. Required
   * @return namespace
  **/
  @ApiModelProperty(required = true, value = "`namespace` is the namespace of the service. Required")

  public String getNamespace() {
    return namespace;
  }


  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }


  public AdmissionregistrationV1beta1ServiceReference path(String path) {
    
    this.path = path;
    return this;
  }

   /**
   * &#x60;path&#x60; is an optional URL path which will be sent in any request to this service.
   * @return path
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "`path` is an optional URL path which will be sent in any request to this service.")

  public String getPath() {
    return path;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public AdmissionregistrationV1beta1ServiceReference port(Integer port) {
    
    this.port = port;
    return this;
  }

   /**
   * If specified, the port on the service that hosting webhook. Default to 443 for backward compatibility. &#x60;port&#x60; should be a valid port number (1-65535, inclusive).
   * @return port
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "If specified, the port on the service that hosting webhook. Default to 443 for backward compatibility. `port` should be a valid port number (1-65535, inclusive).")

  public Integer getPort() {
    return port;
  }


  public void setPort(Integer port) {
    this.port = port;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdmissionregistrationV1beta1ServiceReference admissionregistrationV1beta1ServiceReference = (AdmissionregistrationV1beta1ServiceReference) o;
    return Objects.equals(this.name, admissionregistrationV1beta1ServiceReference.name) &&
        Objects.equals(this.namespace, admissionregistrationV1beta1ServiceReference.namespace) &&
        Objects.equals(this.path, admissionregistrationV1beta1ServiceReference.path) &&
        Objects.equals(this.port, admissionregistrationV1beta1ServiceReference.port);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, namespace, path, port);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdmissionregistrationV1beta1ServiceReference {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    namespace: ").append(toIndentedString(namespace)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
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

