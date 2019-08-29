package mihnea.licenta.server.entity;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface SensorsDataRepository extends CrudRepository<SensorsData, Integer> {
    /* metoda auto generata de SpringBoot pe baza semnaturii
     * returneaza toate inregistrarile de SensorsData de dupa un anumit moment de timp
     * (acest moment de timp e dat de Localdate date)
     */
    List<SensorsData> findAllByDateAfter(LocalDate date);
}
