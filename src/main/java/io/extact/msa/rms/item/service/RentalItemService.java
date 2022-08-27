package io.extact.msa.rms.item.service;

import java.util.function.Consumer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

import io.extact.msa.rms.platform.fw.exception.BusinessFlowException;
import io.extact.msa.rms.platform.fw.exception.BusinessFlowException.CauseType;
import io.extact.msa.rms.platform.fw.persistence.GenericRepository;
import io.extact.msa.rms.platform.fw.service.GenericService;
import io.extact.msa.rms.item.domain.RentalItem;
import io.extact.msa.rms.item.persistence.RentalItemRepository;

@Transactional(TxType.REQUIRED)
@ApplicationScoped
public class RentalItemService implements GenericService<RentalItem> {

    private RentalItemRepository repository;

    @Inject
    public RentalItemService(RentalItemRepository rentalItemRepository) {
        this.repository = rentalItemRepository;
    }

    public RentalItem findBySerialNo(String serialNo) {
        return repository.findBySerialNo(serialNo);
    }

    @Override
    public Consumer<RentalItem> getDuplicateChecker() {
        return (targetItem) -> {
            var foundItem = findBySerialNo(targetItem.getSerialNo());
            if (foundItem != null && (targetItem.getId() == null || !foundItem.isSameId(targetItem))) {
                throw new BusinessFlowException("The serialNo is already registered.", CauseType.DUPRICATE);
            }
        };
    }

    @Override
    public GenericRepository<RentalItem> getRepository() {
        return this.repository;
    }
}
