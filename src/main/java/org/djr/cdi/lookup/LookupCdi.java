package org.djr.cdi.lookup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

@ApplicationScoped
public class LookupCdi {
    /**
     * Method to help get CDI managed beans
     * @param name name of the bean you are looking for; should be annotated with @Named("BeanName")
     * @param clazz the class type you are looking for BeanName.class
     * @param <T> generics
     * @return instance of BeanName.class
     * @throws Exception if something goes horribly wrong I suppose it could get thrown
     */
    @SuppressWarnings("unchecked")
    public <T> T getBeanByNameOfClass(String name, Class<T> clazz)
            throws Exception {
        BeanManager bm = CDI.current().getBeanManager();
        Bean<T> bean = (Bean<T>) bm.getBeans(name).iterator().next();
        CreationalContext<T> ctx = bm.createCreationalContext(bean);
        return (T) bm.getReference(bean, clazz, ctx);
    }
}
