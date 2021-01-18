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
 * ServerAddressByClientCIDR helps the client to determine the server address that they should use, depending on the clientCIDR that they match.
 */
@ApiModel(description = "ServerAddressByClientCIDR helps the client to determine the server address that they should use, depending on the clientCIDR that they match.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V1ServerAddressByClientCIDR {
  public static final String SERIALIZED_NAME_CLIENT_C_I_D_R = "clientCIDR";
  @SerializedName(SERIALIZED_NAME_CLIENT_C_I_D_R)
  private String clientCIDR;

  public static final String SERIALIZED_NAME_SERVER_ADDRESS = "serverAddress";
  @SerializedName(SERIALIZED_NAME_SERVER_ADDRESS)
  private String serverAddress;


  public V1ServerAddressByClientCIDR clientCIDR(String clientCIDR) {
    
    this.clientCIDR = clientCIDR;
    return this;
  }

   /**
   * The CIDR with which clients can match their IP to figure out the server address that they should use.
   * @return clientCIDR
  **/
  @ApiModelProperty(required = true, value = "The CIDR with which clients can match their IP to figure out the server address that they should use.")

  public String getClientCIDR() {
    return clientCIDR;
  }


  public void setClientCIDR(String clientCIDR) {
    this.clientCIDR = clientCIDR;
  }


  public V1ServerAddressByClientCIDR serverAddress(String serverAddress) {
    
    this.serverAddress = serverAddress;
    return this;
  }

   /**
   * Address of this server, suitable for a client that matches the above CIDR. This can be a hostname, hostname:port, IP or IP:port.
   * @return serverAddress
  **/
  @ApiModelProperty(required = true, value = "Address of this server, suitable for a client that matches the above CIDR. This can be a hostname, hostname:port, IP or IP:port.")

  public String getServerAddress() {
    return serverAddress;
  }


  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1ServerAddressByClientCIDR v1ServerAddressByClientCIDR = (V1ServerAddressByClientCIDR) o;
    return Objects.equals(this.clientCIDR, v1ServerAddressByClientCIDR.clientCIDR) &&
        Objects.equals(this.serverAddress, v1ServerAddressByClientCIDR.serverAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientCIDR, serverAddress);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1ServerAddressByClientCIDR {\n");
    sb.append("    clientCIDR: ").append(toIndentedString(clientCIDR)).append("\n");
    sb.append("    serverAddress: ").append(toIndentedString(serverAddress)).append("\n");
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

