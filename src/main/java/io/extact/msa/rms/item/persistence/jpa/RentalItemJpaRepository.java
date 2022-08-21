package io.extact.msa.rms.item.persistence.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import io.extact.msa.rms.platform.fw.persistence.GenericRepository.ApiType;
import io.extact.msa.rms.platform.fw.persistence.jpa.JpaCrudRepository;
import io.extact.msa.rms.platform.core.extension.EnabledIfRuntimeConfig;
import io.extact.msa.rms.item.domain.RentalItem;
import io.extact.msa.rms.item.persistence.RentalItemRepository;

@ApplicationScoped
@EnabledIfRuntimeConfig(propertyName = ApiType.PROP_NAME, value = ApiType.JPA)
public class RentalItemJpaRepository implements RentalItemRepository, JpaCrudRepository<RentalItem> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public RentalItem findBySerialNo(String serialNo) {
        var jpql = "select r from RentalItem r where r.serialNo = ?1";
        try {
            return em.createQuery(jpql, RentalItem.class)
                        .setParameter(1, serialNo)
                        .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public EntityManager getEntityManage() {
        return this.em;
    }

    @Override
    public Class<RentalItem> getTargetClass() {
        return RentalItem.class;
    }
}
