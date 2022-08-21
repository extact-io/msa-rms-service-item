package io.extact.msa.rms.item.persistence.file;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.extact.msa.rms.platform.fw.persistence.GenericRepository.ApiType;
import io.extact.msa.rms.platform.fw.persistence.file.AbstractFileRepository;
import io.extact.msa.rms.platform.fw.persistence.file.io.FileAccessor;
import io.extact.msa.rms.platform.fw.persistence.file.producer.EntityArrayConverter;
import io.extact.msa.rms.platform.core.extension.EnabledIfRuntimeConfig;
import io.extact.msa.rms.item.domain.RentalItem;
import io.extact.msa.rms.item.persistence.RentalItemRepository;

@ApplicationScoped
@EnabledIfRuntimeConfig(propertyName = ApiType.PROP_NAME, value = ApiType.FILE)
public class RentalItemFileRepository extends AbstractFileRepository<RentalItem> implements RentalItemRepository {

    @Inject
    public RentalItemFileRepository(FileAccessor fileAccessor, EntityArrayConverter<RentalItem> converter) {
        super(fileAccessor, converter);
    }

    @Override
    public RentalItem findBySerialNo(String serialNo) {
        return this.load().stream()
                .filter(attributes -> attributes[1].equals(serialNo))
                .map(this.getConverter()::toEntity)
                .findFirst()
                .orElse(null);
    }
}
