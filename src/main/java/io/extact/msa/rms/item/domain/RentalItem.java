package io.extact.msa.rms.item.domain;

import static jakarta.persistence.AccessType.*;

import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.extact.msa.rms.platform.fw.domain.IdProperty;
import io.extact.msa.rms.platform.fw.domain.Transformable;
import io.extact.msa.rms.platform.fw.domain.constraint.ItemName;
import io.extact.msa.rms.platform.fw.domain.constraint.RmsId;
import io.extact.msa.rms.platform.fw.domain.constraint.SerialNo;
import io.extact.msa.rms.platform.fw.domain.constraint.ValidationGroups.Update;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Access(FIELD)
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter @Setter
public class RentalItem implements Transformable, IdProperty {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RmsId(groups = Update.class)
    private Integer id;

    /** シリアル番号 */
    @SerialNo
    private String serialNo;

    /** 品名 */
    @ItemName
    private String itemName;

    public static RentalItem ofTransient(String serialNo, String itemName) {
        return of(null, serialNo, itemName);
    }

    // ----------------------------------------------------- override methods

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
