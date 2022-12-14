package io.extact.msa.rms.item.persistence.file;

import java.io.IOException;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import io.extact.msa.rms.item.domain.RentalItem;
import io.extact.msa.rms.platform.fw.persistence.file.io.FileAccessor;
import io.extact.msa.rms.platform.fw.persistence.file.producer.EntityArrayConverter;
import io.extact.msa.rms.platform.fw.persistence.file.producer.FileOpenPathDeriver;
import io.extact.msa.rms.platform.fw.persistence.file.producer.FileRepositoryProducers;

@Dependent
public class RentalItemFileRepositoryProducers implements FileRepositoryProducers<RentalItem> {

    // ファイルパスが定義されている設定ファイルキー(csv.%s.fileName.%s)の2個目の%sの値
    private static final String FILE_NAME_TYPE_CONFIG_KEY = "rentalitem";
    private FileOpenPathDeriver pathDeriver;

    @Inject
    public RentalItemFileRepositoryProducers(FileOpenPathDeriver pathDeriver) {
        this.pathDeriver = pathDeriver;
    }

    @Produces
    public FileAccessor creteFileAccessor() throws IOException {
        return new FileAccessor(pathDeriver.derive(FILE_NAME_TYPE_CONFIG_KEY));
    }

    @Produces
    public EntityArrayConverter<RentalItem> createRentalItemConverter() {
        return RentalItemArrayConverter.INSTANCE;
    }
}
