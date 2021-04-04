package org.djr.cdi.lookup;

import org.apiguardian.api.API;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

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
    @API(status = API.Status.MAINTAINED, since = "2018-09-03")
    public static <T> T getBeanByNameOfClass(String name, Class<T> clazz) {
        BeanManager bm = CDI.current().getBeanManager();
        Bean<T> bean = (Bean<T>) bm.getBeans(name).iterator().next();
        CreationalContext<T> ctx = bm.createCreationalContext(bean);
        return (T) bm.getReference(bean, clazz, ctx);
    }
}
