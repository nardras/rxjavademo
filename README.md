# rxjavademo
Simple demonstration of using rxjava for orchestrating calls to backend services

# usage

- run mvn to retrieve all necessary dependencies
- simply start org.dreisiebner.rxjavademo.Application to have a webserver running on port 8080
- point your browser to
    - ```yourhost:8080/sync/recommendations?user=user_AT``` for getting recommendatinos synchronously for user\_AT
    - ```yourhost:8080/rx/recommendations?user=user_AT``` for getting recommendations asynchronously for user_AT


# code

to compare sync and async implementation of the orchestrating rest endpoint, have a look at

- ```rx.dreisiebner.rxjavademo.resources.ObservableRecommendationResource``` for async implementation
- ```rx.dreisiebner.rxjavademo.resources.RecommendationResource``` for sync implementation