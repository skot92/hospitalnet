package hu.unideb.hospitalnet.core.dao;

import java.util.List;

import hu.unideb.hospitalnet.core.entity.MedicalRecord;
import hu.unideb.hospitalnet.core.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface MedicalRecordDao extends JpaRepository<MedicalRecord, Long>{
	List<MedicalRecord> findByPatientId(Long patient);
}
