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
import io.kubernetes.client.openapi.models.V1PersistentVolumeSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * VolumeAttachmentSource represents a volume that should be attached. Right now only PersistenVolumes can be attached via external attacher, in future we may allow also inline volumes in pods. Exactly one member can be set.
 */
@ApiModel(description = "VolumeAttachmentSource represents a volume that should be attached. Right now only PersistenVolumes can be attached via external attacher, in future we may allow also inline volumes in pods. Exactly one member can be set.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T05:41:11.202Z[Etc/UTC]")
public class V1VolumeAttachmentSource {
  public static final String SERIALIZED_NAME_INLINE_VOLUME_SPEC = "inlineVolumeSpec";
  @SerializedName(SERIALIZED_NAME_INLINE_VOLUME_SPEC)
  private V1PersistentVolumeSpec inlineVolumeSpec;

  public static final String SERIALIZED_NAME_PERSISTENT_VOLUME_NAME = "persistentVolumeName";
  @SerializedName(SERIALIZED_NAME_PERSISTENT_VOLUME_NAME)
  private String persistentVolumeName;


  public V1VolumeAttachmentSource inlineVolumeSpec(V1PersistentVolumeSpec inlineVolumeSpec) {
    
    this.inlineVolumeSpec = inlineVolumeSpec;
    return this;
  }

   /**
   * Get inlineVolumeSpec
   * @return inlineVolumeSpec
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public V1PersistentVolumeSpec getInlineVolumeSpec() {
    return inlineVolumeSpec;
  }


  public void setInlineVolumeSpec(V1PersistentVolumeSpec inlineVolumeSpec) {
    this.inlineVolumeSpec = inlineVolumeSpec;
  }


  public V1VolumeAttachmentSource persistentVolumeName(String persistentVolumeName) {
    
    this.persistentVolumeName = persistentVolumeName;
    return this;
  }

   /**
   * Name of the persistent volume to attach.
   * @return persistentVolumeName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Name of the persistent volume to attach.")

  public String getPersistentVolumeName() {
    return persistentVolumeName;
  }


  public void setPersistentVolumeName(String persistentVolumeName) {
    this.persistentVolumeName = persistentVolumeName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1VolumeAttachmentSource v1VolumeAttachmentSource = (V1VolumeAttachmentSource) o;
    return Objects.equals(this.inlineVolumeSpec, v1VolumeAttachmentSource.inlineVolumeSpec) &&
        Objects.equals(this.persistentVolumeName, v1VolumeAttachmentSource.persistentVolumeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inlineVolumeSpec, persistentVolumeName);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1VolumeAttachmentSource {\n");
    sb.append("    inlineVolumeSpec: ").append(toIndentedString(inlineVolumeSpec)).append("\n");
    sb.append("    persistentVolumeName: ").append(toIndentedString(persistentVolumeName)).append("\n");
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

