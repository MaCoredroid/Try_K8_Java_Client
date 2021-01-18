

# V1EventSeries

EventSeries contain information on series of events, i.e. thing that was/is happening continuously for some time.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**count** | **Integer** | Number of occurrences in this series up to the last heartbeat time |  [optional]
**lastObservedTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time of the last occurrence observed |  [optional]
**state** | **String** | State of this Series: Ongoing or Finished Deprecated. Planned removal for 1.18 |  [optional]


