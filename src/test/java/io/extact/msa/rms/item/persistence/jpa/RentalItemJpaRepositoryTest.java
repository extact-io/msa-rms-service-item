package io.extact.msa.rms.item.persistence.jpa;

import static io.extact.msa.rms.test.assertj.ToStringAssert.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.extact.msa.rms.test.junit5.JpaTransactionalExtension;
import io.extact.msa.rms.test.junit5.TransactionalForTest;
import io.extact.msa.rms.test.utils.TestUtils;
import io.extact.msa.rms.item.domain.RentalItem;
import io.extact.msa.rms.item.persistence.AbstractRentalItemRepositoryTest;
import io.extact.msa.rms.item.persistence.RentalItemRepository;

@ExtendWith(JpaTransactionalExtension.class)
class RentalItemJpaRepositoryTest extends AbstractRentalItemRepositoryTest {

    private RentalItemRepository repository;

    @Override
    protected RentalItemRepository repository() {
        return repository;
    }

    @BeforeEach
    void setup(EntityManager em) {
        repository = new RentalItemJpaRepository();
        TestUtils.setFieldValue(repository, "em", em);
    }

    @Test
    @TransactionalForTest
    void testAdd() {
        var addEntity = RentalItem.ofTransient("A0005", "レンタル品5号");
        repository.add(addEntity);

        addEntity.setId(5);
        var expect = addEntity;
        var actual = repository.get(5);
        assertThatToString(actual).isEqualTo(expect);
    }
}
