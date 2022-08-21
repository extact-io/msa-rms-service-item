package io.extact.msa.rms.item.persistence;

import io.extact.msa.rms.platform.fw.persistence.GenericRepository;
import io.extact.msa.rms.item.domain.RentalItem;

/**
 * レンタル品の永続化インタフェース。
 */
public interface RentalItemRepository extends GenericRepository<RentalItem> {

    /**
     * シリアル番号を指定してレンタル品を取得。
     *
     * @param serialNo シリアル番号
     * @return 該当エンティティ。該当なしはnull
     */
    RentalItem findBySerialNo(String serialNo);
}