package ru.job4j.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.model.Metadata;
import ru.job4j.repository.car.parts.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MetadataServiceImplTest {

    @Autowired
    private MetadataService service;
    @Autowired
    private CarModelRepository carRepo;
    @Autowired
    private ManufactureRepository manufactureRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private BodyRepository bodyRepo;
    @Autowired
    private FuelRepository fuelRepo;
    @Autowired
    private TransmissionRepository transmissionRepo;

    @Test
    public void getMetadata() {
        Metadata found = service.getMetadata();
        assertThat(found.getCarmodels()).hasSize(25);
        assertThat(found.getManufactures()).hasSize(9);
        assertThat(found.getCategories()).hasSize(9);
        assertThat(found.getBodies()).hasSize(7);
        assertThat(found.getFuels()).hasSize(6);
        assertThat(found.getTransmissions()).hasSize(4);
    }

    @TestConfiguration
    static class MetadataServiceImplTestContextConfiguration {

        @Autowired
        private CarModelRepository carModelDao;
        @Autowired
        private ManufactureRepository manufactureDao;
        @Autowired
        private CategoryRepository categoryDao;
        @Autowired
        private BodyRepository bodyDao;
        @Autowired
        private FuelRepository fuelDao;
        @Autowired
        private TransmissionRepository transmissionDao;

        @Bean
        public MetadataService service() {
            return new MetadataServiceImpl();
        }
    }
}