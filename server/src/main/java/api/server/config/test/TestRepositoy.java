package api.server.config.test;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class TestRepositoy extends QuerydslRepositorySupport {
    public TestRepositoy(Class<?> domainClass) {
        super(domainClass);
    }

}
