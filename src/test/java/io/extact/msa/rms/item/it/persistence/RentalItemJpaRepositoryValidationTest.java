package io.extact.msa.rms.item.it.persistence;

import io.helidon.microprofile.tests.junit5.AddConfig;
import io.helidon.microprofile.tests.junit5.HelidonTest;

@HelidonTest(resetPerTest = true)
@AddConfig(key = "persistence.apiType", value = "jpa")
class RentalItemJpaRepositoryValidationTest extends AbstractRentalItemRepositoryValidationTest {
}
