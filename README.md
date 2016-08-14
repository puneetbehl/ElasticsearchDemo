# Elasticsearch Plugin Demo Application

This is a demo application for Grails Elasticsearch plugin. Following is the domain class in the application:


```groovy

package esdemo

class Post {

    String subject
    String body

    static searchable = {
        all = [analyzer: 'repl_analyzer']
        subject analyzer: 'test_analyzer'
        body analyzer: 'test_analyzer'
    }

}


```

Please note that in order to run this application in development you need to set-up and run Elasticsearch. Otherwise, update following in `Config.groovy`

```groovy

elasticSearch.client.mode = 'local'

```

