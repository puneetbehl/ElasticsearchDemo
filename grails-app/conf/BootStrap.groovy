import esdemo.Post

class BootStrap {

    def init = { servletContext ->

        5.times {
            new Post(subject: "Post$it").save(flush: true)
        }

    }
    def destroy = {
    }
}
