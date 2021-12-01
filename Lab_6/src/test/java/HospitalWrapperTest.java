import database.HospitalWrapper;
import hospital.Doctor;
import hospital.Hospital;
import hospital.Patient;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.testng.Assert.*;

public class HospitalWrapperTest {
    Calendar calendar = Calendar.getInstance();

    {
        calendar.set(1999, Calendar.DECEMBER, 11);
    }

    Patient patient1 = new Patient.Builder().setName("Patientname1").setSurname("Patientsurname1")
            .setBirthday(calendar).setId(11).setPhoneNumber("+11111111111").setDoctorId(21)
            .setMedicalCardId(111).createPatient();
    Patient patient2 = new Patient.Builder().setName("Patientname2").setSurname("Patientsurname2")
            .setBirthday(calendar).setId(12).setPhoneNumber("+11111111112").setDoctorId(21)
            .setMedicalCardId(112).createPatient();
    Patient patient3 = new Patient.Builder().setName("Patientname3").setSurname("Patientsurname2")
            .setBirthday(calendar).setId(13).setPhoneNumber("+11111111113").setDoctorId(22)
            .setMedicalCardId(121).createPatient();
    Patient patient4 = new Patient.Builder().setName("Patientname4").setSurname("Patientsurname4")
            .setBirthday(calendar).setId(14).setPhoneNumber("+11111111114").setDoctorId(23)
            .setMedicalCardId(131).createPatient();

    Doctor doctor1 = new Doctor.Builder().setName("Doctorname1").setSurname("Doctorsurname1")
            .setBirthday(calendar).setId(21).setPhoneNumber("+11111111121")
            .setPatients(Arrays.asList(patient1, patient2)).setCabinetNumber(1).setHospitalNumber(1)
            .setSalary(12345).createDoctor();
    Doctor doctor2 = new Doctor.Builder().setName("Doctorname2").setSurname("Doctorsurname2")
            .setBirthday(calendar).setId(22).setPhoneNumber("+11111111122")
            .setPatients(List.of(patient3)).setCabinetNumber(2).setHospitalNumber(1)
            .setSalary(23456).createDoctor();
    Doctor doctor3 = new Doctor.Builder().setName("Doctorname3").setSurname("Doctorsurname3")
            .setBirthday(calendar).setId(23).setPhoneNumber("+11111111123")
            .setPatients(List.of(patient4)).setCabinetNumber(1).setHospitalNumber(2)
            .setSalary(34567).createDoctor();

    Hospital hospital1 = new Hospital.Builder().setAddress("Address1").setNumber(1)
            .setDoctors(Arrays.asList(doctor1, doctor2)).createHospital();
    Hospital hospital2 = new Hospital.Builder().setAddress("Address2").setNumber(2)
            .setDoctors(List.of(doctor3)).createHospital();

    HospitalWrapper hospitalWrapper = new HospitalWrapper("jdbc:h2:mem:test", "username", "password");

    {
        hospitalWrapper.addHospital(hospital1);
        hospitalWrapper.addHospital(hospital2);
    }

    @Test
    public void testGetPatients() {
        List<Patient> actual = hospitalWrapper.getPatients();
        actual.sort(Patient::compareTo);
        List<Patient> expected = Arrays.asList(patient1, patient2, patient3, patient4);
        expected.sort(Patient::compareTo);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "DoctorProvider")
    public Object[][] dpDoctor() {
        return new Object[][]{{doctor1}, {doctor2}, {doctor3}};
    }

    @Test(dataProvider = "DoctorProvider")
    public void testGetPatientsByDoctor(Doctor doctor) {
        List<Patient> patients = hospitalWrapper.getPatientsByDoctor(doctor.getId());
        assertEquals(patients, doctor.getPatients());
    }

    @DataProvider(name = "PatientProvider")
    public Object[][] dpPatient() {
        return new Object[][]{{patient1}, {patient2}, {patient3}, {patient4}};
    }

    @Test(dataProvider = "PatientProvider")
    public void testGetPatientsByName(Patient patient) {
        List<Patient> patients = hospitalWrapper.getPatientsByName(patient.getName());
        if (!patients.contains(patient))
            fail();
    }

    @Test(dataProvider = "PatientProvider")
    public void testGetPatientsBySurname(Patient patient) {
        List<Patient> patients = hospitalWrapper.getPatientsBySurname(patient.getSurname());
        if (!patients.contains(patient))
            fail();
    }

    @Test(dataProvider = "PatientProvider")
    public void testGetPatientsById(Patient patient) {
        Patient result = hospitalWrapper.getPatientsById(patient.getId());
        assertEquals(result, patient);
    }

    @Test(dataProvider = "PatientProvider")
    public void testGetPatientsByMedicalCardId(Patient patient) {
        Patient result = hospitalWrapper.getPatientsByMedicalCardId(patient.getMedicalCardId());
        assertEquals(result, patient);
    }

    @Test
    public void testGetPatientsMedicalCardID() {
        List<Integer> actual = hospitalWrapper.getPatientsMedicalCardId();
        actual.sort(Integer::compareTo);
        List<Integer> expected = Arrays.asList(patient1.getMedicalCardId(), patient2.getMedicalCardId(),
                patient3.getMedicalCardId(), patient4.getMedicalCardId());
        expected.sort(Integer::compareTo);
        assertEquals(actual, expected);
    }

    @Test
    public void testGetDoctors() {
        List<Doctor> actual = hospitalWrapper.getDoctors();
        actual.sort(Doctor::compareTo);
        List<Doctor> expected = Arrays.asList(doctor1, doctor2, doctor3);
        expected.sort(Doctor::compareTo);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "DoctorProvider")
    public void testGetDoctorsByHospital(Doctor doctor) {
        List<Doctor> doctors = hospitalWrapper.getDoctorsByHospital(doctor.getHospitalNumber());
        if (!doctors.contains(doctor))
            fail();
    }

    @Test(dataProvider = "DoctorProvider")
    public void testGetDoctorsByName(Doctor doctor) {
        List<Doctor> doctors = hospitalWrapper.getDoctorsByName(doctor.getName());
        if (!doctors.contains(doctor))
            fail();
    }

    @Test(dataProvider = "DoctorProvider")
    public void testGetDoctorsBySurname(Doctor doctor) {
        List<Doctor> doctors = hospitalWrapper.getDoctorsBySurname(doctor.getSurname());
        if (!doctors.contains(doctor))
            fail();
    }

    @Test(dataProvider = "DoctorProvider")
    public void testGetDoctorsById(Doctor doctor) {
        Doctor result = hospitalWrapper.getDoctorsById(doctor.getId());
        assertEquals(result, doctor);
    }

    @Test
    public void testGetHospitals() {
        List<Hospital> actual = hospitalWrapper.getHospitals();
        actual.sort(Hospital::compareTo);
        List<Hospital> expected = Arrays.asList(hospital1, hospital2);
        expected.sort(Hospital::compareTo);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "HospitalProvider")
    public Object[][] dpHospital() {
        return new Object[][]{{hospital1}, {hospital2}};
    }

    @Test(dataProvider = "HospitalProvider")
    public void testGetHospitalByNumber(Hospital hospital) {
        Hospital result = hospitalWrapper.getHospitalByNumber(hospital.getNumber());
        assertEquals(result, hospital);
    }

    @Test(dataProvider = "HospitalProvider")
    public void testGetHospitalByAddress(Hospital hospital) {
        Hospital result = hospitalWrapper.getHospitalByAddress(hospital.getAddress());
        assertEquals(result, hospital);
    }

    @Test
    public void testUpdatePatient() {
        patient1.setName("Newname");
        hospitalWrapper.updatePatient(patient1);
        List<Patient> result = hospitalWrapper.getPatientsByName(patient1.getName());
        if (!result.contains(patient1))
            fail();
    }

    @Test
    public void testUpdateDoctor() {
        doctor1.setSurname("Newsurname");
        hospitalWrapper.updateDoctor(doctor1);
        List<Doctor> result = hospitalWrapper.getDoctorsBySurname(doctor1.getSurname());
        if (!result.contains(doctor1))
            fail();
    }

    @Test
    public void testUpdateHospital() {
        hospital1.setAddress("New address");
        hospitalWrapper.updateHospital(hospital1);
        Hospital result = hospitalWrapper.getHospitalByAddress(hospital1.getAddress());
        assertEquals(result, hospital1);
    }

    @Test
    public void testErasePatient() {
        Patient patient = new Patient.Builder().setName("Patientname5").setSurname("Patientsurname5")
                .setBirthday(calendar).setId(15).setPhoneNumber("+11111111115").setDoctorId(21)
                .setMedicalCardId(110).createPatient();

        hospitalWrapper.addPatient(patient);
        List<Patient> patients = hospitalWrapper.getPatients();

        if (!patients.contains(patient))
            fail();

        hospitalWrapper.erasePatient(patient);
        patients = hospitalWrapper.getPatients();

        if (patients.contains(patient))
            fail();
    }

    @Test
    public void testEraseDoctor() {
        Patient patient = new Patient.Builder().setName("Patientname5").setSurname("Patientsurname5")
                .setBirthday(calendar).setId(15).setPhoneNumber("+11111111115").setDoctorId(25)
                .setMedicalCardId(110).createPatient();

        Doctor doctor = new Doctor.Builder().setName("Doctorname5").setSurname("Doctorsurname5")
                .setBirthday(calendar).setId(25).setPhoneNumber("+11111111125")
                .setPatients(List.of(patient)).setCabinetNumber(5).setHospitalNumber(1)
                .setSalary(12345).createDoctor();

        hospitalWrapper.addDoctor(doctor);
        List<Doctor> doctors = hospitalWrapper.getDoctors();

        if (!doctors.contains(doctor))
            fail();

        hospitalWrapper.eraseDoctor(doctor);
        doctors = hospitalWrapper.getDoctors();

        if (doctors.contains(doctor))
            fail();

        hospitalWrapper.erasePatient(patient);
    }

    @Test
    public void testEraseHospital() {
        Patient patient = new Patient.Builder().setName("Patientname5").setSurname("Patientsurname5")
                .setBirthday(calendar).setId(15).setPhoneNumber("+11111111115").setDoctorId(25)
                .setMedicalCardId(110).createPatient();

        Doctor doctor = new Doctor.Builder().setName("Doctorname5").setSurname("Doctorsurname5")
                .setBirthday(calendar).setId(25).setPhoneNumber("+11111111125")
                .setPatients(List.of(patient)).setCabinetNumber(5).setHospitalNumber(3)
                .setSalary(12345).createDoctor();

        Hospital hospital = new Hospital.Builder().setAddress("Address3").setNumber(3)
                .setDoctors(List.of(doctor)).createHospital();

        if (!hospitalWrapper.addHospital(hospital))
            fail();
        List<Hospital> hospitals = hospitalWrapper.getHospitals();

        if (!hospitals.contains(hospital))
            fail();

        hospitalWrapper.eraseHospital(hospital);
        hospitals = hospitalWrapper.getHospitals();

        if (hospitals.contains(hospital))
            fail();

        hospitalWrapper.eraseDoctor(doctor);
        hospitalWrapper.erasePatient(patient);
    }
}
