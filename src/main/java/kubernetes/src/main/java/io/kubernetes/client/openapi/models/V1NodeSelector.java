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
import io.kubernetes.client.openapi.models.V1NodeSelectorTerm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A node selector represents the union of the results of one or more label queries over a set of nodes; that is, it represents the OR of the selectors represented by the node selector terms.
 */
@ApiModel(description = "A node selector represents the union of the results of one or more label queries over a set of nodes; that is, it represents the OR of the selectors represented by the node selector terms.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1NodeSelector {
  public static final String SERIALIZED_NAME_NODE_SELECTOR_TERMS = "nodeSelectorTerms";
  @SerializedName(SERIALIZED_NAME_NODE_SELECTOR_TERMS)
  private List<V1NodeSelectorTerm> nodeSelectorTerms = new ArrayList<>();


  public V1NodeSelector nodeSelectorTerms(List<V1NodeSelectorTerm> nodeSelectorTerms) {
    
    this.nodeSelectorTerms = nodeSelectorTerms;
    return this;
  }

  public V1NodeSelector addNodeSelectorTermsItem(V1NodeSelectorTerm nodeSelectorTermsItem) {
    this.nodeSelectorTerms.add(nodeSelectorTermsItem);
    return this;
  }

   /**
   * Required. A list of node selector terms. The terms are ORed.
   * @return nodeSelectorTerms
  **/
  @ApiModelProperty(required = true, value = "Required. A list of node selector terms. The terms are ORed.")

  public List<V1NodeSelectorTerm> getNodeSelectorTerms() {
    return nodeSelectorTerms;
  }


  public void setNodeSelectorTerms(List<V1NodeSelectorTerm> nodeSelectorTerms) {
    this.nodeSelectorTerms = nodeSelectorTerms;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1NodeSelector v1NodeSelector = (V1NodeSelector) o;
    return Objects.equals(this.nodeSelectorTerms, v1NodeSelector.nodeSelectorTerms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeSelectorTerms);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1NodeSelector {\n");
    sb.append("    nodeSelectorTerms: ").append(toIndentedString(nodeSelectorTerms)).append("\n");
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

