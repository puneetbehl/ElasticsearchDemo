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
