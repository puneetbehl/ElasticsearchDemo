package esdemo

class Post {

    String name
    String description

    static constraints = {
        description nullable: true
    }

    static searchable  = {
        name boost: 2
    }

}
