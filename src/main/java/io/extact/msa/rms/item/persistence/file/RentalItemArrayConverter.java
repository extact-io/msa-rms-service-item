package io.extact.msa.rms.item.persistence.file;

import io.extact.msa.rms.platform.fw.exception.RmsSystemException;
import io.extact.msa.rms.platform.fw.persistence.file.producer.EntityArrayConverter;
import io.extact.msa.rms.item.domain.RentalItem;

public class RentalItemArrayConverter implements EntityArrayConverter<RentalItem> {

    public static final RentalItemArrayConverter INSTANCE = new RentalItemArrayConverter();

    public RentalItem toEntity(String[] attributes) throws RmsSystemException {

        var id = Integer.parseInt(attributes[0]);
        var serialNo = attributes[1];
        var itemName = attributes[2];

        return RentalItem.of(id, serialNo, itemName);
    }

    public String[] toArray(RentalItem rentalItem) {

        var itemAttributes = new String[3];

        itemAttributes[0] = String.valueOf(rentalItem.getId());
        itemAttributes[1] = rentalItem.getSerialNo();
        itemAttributes[2] = rentalItem.getItemName();

        return itemAttributes;
    }
}
