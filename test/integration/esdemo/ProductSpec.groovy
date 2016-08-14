package esdemo

import org.grails.plugins.elasticsearch.ElasticSearchAdminService
import org.grails.plugins.elasticsearch.ElasticSearchService
import spock.lang.Specification

class ProductSpec extends Specification {

    ElasticSearchService elasticSearchService
    ElasticSearchAdminService elasticSearchAdminService

    void 'Index and un-index a domain object'() {
        given:
        def product = new Product(name: 'myTestProduct')
        product.save(failOnError: true)

        when:
        elasticSearchAdminService.refresh() // Ensure the latest operations have been exposed on the ES instance

        and:
        elasticSearchService.search('myTestProduct', [indices: Product, types: Product]).total == 1

        then:
        elasticSearchService.unindex(product)
        elasticSearchAdminService.refresh()

        and:
        elasticSearchService.search('myTestProduct', [indices: Product, types: Product]).total == 0

        cleanup:
        product.delete(flush: true)
    }


    void 'Index and un-index multiple domain objects'() {
        given:
        def product1 = new Product(name: 'myTestProduct1')
        product1.save(failOnError: true)
        def product2 = new Product(name: 'myTestProduct2')
        product2.save(failOnError: true)

        when:
        elasticSearchAdminService.refresh() // Ensure the latest operations have been exposed on the ES instance

        and:
        elasticSearchService.search('myTestProduct1', [indices: Product, types: Product]).total == 1
        elasticSearchService.search('myTestProduct2', [indices: Product, types: Product]).total == 1

        then:
        elasticSearchService.unindex(Product)
        elasticSearchAdminService.refresh()

        and:
        elasticSearchService.search('myTestProduct1', [indices: Product, types: Product]).total == 0
        elasticSearchService.search('myTestProduct2', [indices: Product, types: Product]).total == 0

        cleanup:
        product1.delete(flush: true)
        product2.delete(flush: true)
    }

}
