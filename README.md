# Elasticsearch Plugin Demo Application

This is a demo application for Grails Elasticsearch plugin. Following is the domain class in the application:


```groovy

package esdemo

class Post {

    String name
    String description

    static constraints = {
        description nullable: true
    }

    static searchable  = true

}

```

Please note that in order to run this application in development you need to set-up and run Elasticsearch. Otherwise, update following in `Config.groovy`

```groovy

elasticSearch.client.mode = 'local'

```

