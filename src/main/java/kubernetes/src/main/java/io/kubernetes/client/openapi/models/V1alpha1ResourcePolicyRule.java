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
import java.util.ArrayList;
import java.util.List;

/**
 * ResourcePolicyRule is a predicate that matches some resource requests, testing the request&#39;s verb and the target resource. A ResourcePolicyRule matches a resource request if and only if: (a) at least one member of verbs matches the request, (b) at least one member of apiGroups matches the request, (c) at least one member of resources matches the request, and (d) least one member of namespaces matches the request.
 */
@ApiModel(description = "ResourcePolicyRule is a predicate that matches some resource requests, testing the request's verb and the target resource. A ResourcePolicyRule matches a resource request if and only if: (a) at least one member of verbs matches the request, (b) at least one member of apiGroups matches the request, (c) at least one member of resources matches the request, and (d) least one member of namespaces matches the request.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-18T06:56:30.422Z[Etc/UTC]")
public class V1alpha1ResourcePolicyRule {
  public static final String SERIALIZED_NAME_API_GROUPS = "apiGroups";
  @SerializedName(SERIALIZED_NAME_API_GROUPS)
  private List<String> apiGroups = new ArrayList<>();

  public static final String SERIALIZED_NAME_CLUSTER_SCOPE = "clusterScope";
  @SerializedName(SERIALIZED_NAME_CLUSTER_SCOPE)
  private Boolean clusterScope;

  public static final String SERIALIZED_NAME_NAMESPACES = "namespaces";
  @SerializedName(SERIALIZED_NAME_NAMESPACES)
  private List<String> namespaces = null;

  public static final String SERIALIZED_NAME_RESOURCES = "resources";
  @SerializedName(SERIALIZED_NAME_RESOURCES)
  private List<String> resources = new ArrayList<>();

  public static final String SERIALIZED_NAME_VERBS = "verbs";
  @SerializedName(SERIALIZED_NAME_VERBS)
  private List<String> verbs = new ArrayList<>();


  public V1alpha1ResourcePolicyRule apiGroups(List<String> apiGroups) {
    
    this.apiGroups = apiGroups;
    return this;
  }

  public V1alpha1ResourcePolicyRule addApiGroupsItem(String apiGroupsItem) {
    this.apiGroups.add(apiGroupsItem);
    return this;
  }

   /**
   * &#x60;apiGroups&#x60; is a list of matching API groups and may not be empty. \&quot;*\&quot; matches all API groups and, if present, must be the only entry. Required.
   * @return apiGroups
  **/
  @ApiModelProperty(required = true, value = "`apiGroups` is a list of matching API groups and may not be empty. \"*\" matches all API groups and, if present, must be the only entry. Required.")

  public List<String> getApiGroups() {
    return apiGroups;
  }


  public void setApiGroups(List<String> apiGroups) {
    this.apiGroups = apiGroups;
  }


  public V1alpha1ResourcePolicyRule clusterScope(Boolean clusterScope) {
    
    this.clusterScope = clusterScope;
    return this;
  }

   /**
   * &#x60;clusterScope&#x60; indicates whether to match requests that do not specify a namespace (which happens either because the resource is not namespaced or the request targets all namespaces). If this field is omitted or false then the &#x60;namespaces&#x60; field must contain a non-empty list.
   * @return clusterScope
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "`clusterScope` indicates whether to match requests that do not specify a namespace (which happens either because the resource is not namespaced or the request targets all namespaces). If this field is omitted or false then the `namespaces` field must contain a non-empty list.")

  public Boolean getClusterScope() {
    return clusterScope;
  }


  public void setClusterScope(Boolean clusterScope) {
    this.clusterScope = clusterScope;
  }


  public V1alpha1ResourcePolicyRule namespaces(List<String> namespaces) {
    
    this.namespaces = namespaces;
    return this;
  }

  public V1alpha1ResourcePolicyRule addNamespacesItem(String namespacesItem) {
    if (this.namespaces == null) {
      this.namespaces = new ArrayList<>();
    }
    this.namespaces.add(namespacesItem);
    return this;
  }

   /**
   * &#x60;namespaces&#x60; is a list of target namespaces that restricts matches.  A request that specifies a target namespace matches only if either (a) this list contains that target namespace or (b) this list contains \&quot;*\&quot;.  Note that \&quot;*\&quot; matches any specified namespace but does not match a request that _does not specify_ a namespace (see the &#x60;clusterScope&#x60; field for that). This list may be empty, but only if &#x60;clusterScope&#x60; is true.
   * @return namespaces
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "`namespaces` is a list of target namespaces that restricts matches.  A request that specifies a target namespace matches only if either (a) this list contains that target namespace or (b) this list contains \"*\".  Note that \"*\" matches any specified namespace but does not match a request that _does not specify_ a namespace (see the `clusterScope` field for that). This list may be empty, but only if `clusterScope` is true.")

  public List<String> getNamespaces() {
    return namespaces;
  }


  public void setNamespaces(List<String> namespaces) {
    this.namespaces = namespaces;
  }


  public V1alpha1ResourcePolicyRule resources(List<String> resources) {
    
    this.resources = resources;
    return this;
  }

  public V1alpha1ResourcePolicyRule addResourcesItem(String resourcesItem) {
    this.resources.add(resourcesItem);
    return this;
  }

   /**
   * &#x60;resources&#x60; is a list of matching resources (i.e., lowercase and plural) with, if desired, subresource.  For example, [ \&quot;services\&quot;, \&quot;nodes/status\&quot; ].  This list may not be empty. \&quot;*\&quot; matches all resources and, if present, must be the only entry. Required.
   * @return resources
  **/
  @ApiModelProperty(required = true, value = "`resources` is a list of matching resources (i.e., lowercase and plural) with, if desired, subresource.  For example, [ \"services\", \"nodes/status\" ].  This list may not be empty. \"*\" matches all resources and, if present, must be the only entry. Required.")

  public List<String> getResources() {
    return resources;
  }


  public void setResources(List<String> resources) {
    this.resources = resources;
  }


  public V1alpha1ResourcePolicyRule verbs(List<String> verbs) {
    
    this.verbs = verbs;
    return this;
  }

  public V1alpha1ResourcePolicyRule addVerbsItem(String verbsItem) {
    this.verbs.add(verbsItem);
    return this;
  }

   /**
   * &#x60;verbs&#x60; is a list of matching verbs and may not be empty. \&quot;*\&quot; matches all verbs and, if present, must be the only entry. Required.
   * @return verbs
  **/
  @ApiModelProperty(required = true, value = "`verbs` is a list of matching verbs and may not be empty. \"*\" matches all verbs and, if present, must be the only entry. Required.")

  public List<String> getVerbs() {
    return verbs;
  }


  public void setVerbs(List<String> verbs) {
    this.verbs = verbs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V1alpha1ResourcePolicyRule v1alpha1ResourcePolicyRule = (V1alpha1ResourcePolicyRule) o;
    return Objects.equals(this.apiGroups, v1alpha1ResourcePolicyRule.apiGroups) &&
        Objects.equals(this.clusterScope, v1alpha1ResourcePolicyRule.clusterScope) &&
        Objects.equals(this.namespaces, v1alpha1ResourcePolicyRule.namespaces) &&
        Objects.equals(this.resources, v1alpha1ResourcePolicyRule.resources) &&
        Objects.equals(this.verbs, v1alpha1ResourcePolicyRule.verbs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiGroups, clusterScope, namespaces, resources, verbs);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V1alpha1ResourcePolicyRule {\n");
    sb.append("    apiGroups: ").append(toIndentedString(apiGroups)).append("\n");
    sb.append("    clusterScope: ").append(toIndentedString(clusterScope)).append("\n");
    sb.append("    namespaces: ").append(toIndentedString(namespaces)).append("\n");
    sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
    sb.append("    verbs: ").append(toIndentedString(verbs)).append("\n");
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

