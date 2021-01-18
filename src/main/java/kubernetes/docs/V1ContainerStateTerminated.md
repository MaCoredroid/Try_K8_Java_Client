

# V1ContainerStateTerminated

ContainerStateTerminated is a terminated state of a container.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**containerID** | **String** | Container&#39;s ID in the format &#39;docker://&lt;container_id&gt;&#39; |  [optional]
**exitCode** | **Integer** | Exit status from the last termination of the container | 
**finishedAt** | [**OffsetDateTime**](OffsetDateTime.md) | Time at which the container last terminated |  [optional]
**message** | **String** | Message regarding the last termination of the container |  [optional]
**reason** | **String** | (brief) reason from the last termination of the container |  [optional]
**signal** | **Integer** | Signal from the last termination of the container |  [optional]
**startedAt** | [**OffsetDateTime**](OffsetDateTime.md) | Time at which previous execution of the container started |  [optional]



