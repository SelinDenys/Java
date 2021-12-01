import hospital.Doctor;
import hospital.Hospital;
import hospital.Patient;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestMethods {
    Calendar calendar1 = Calendar.getInstance();

    {
        calendar1.set(1999, Calendar.OCTOBER, 9);
    }

    Calendar calendar2 = Calendar.getInstance();

    {
        calendar1.set(1988, Calendar.SEPTEMBER, 8);
    }

    Calendar calendar3 = Calendar.getInstance();

    {
        calendar1.set(1977, Calendar.AUGUST, 7);
    }

    Patient patient1 = new Patient.Builder().setName("Patient1Name").setSurname("Patient1Surname")
            .setBirthday(calendar1).setId(211).setPhoneNumber("+1111111111")
            .setMedicalCardId(3).createPatient();
    Patient patient2 = new Patient.Builder().setName("Patient2Name").setSurname("Patient2Surname")
            .setBirthday(calendar2).setId(221).setPhoneNumber("+1111111111")
            .setMedicalCardId(2).createPatient();
    Patient patient3 = new Patient.Builder().setName("Patient3Name").setSurname("Patient3Surname")
            .setBirthday(calendar3).setId(231).setPhoneNumber("+1111111111")
            .setMedicalCardId(1).createPatient();

    Doctor doctor1 = new Doctor.Builder().setName("Doctor1Name").setSurname("Doctor1Surname")
            .setBirthday(calendar1).setId(1).setPhoneNumber("+1111111111")
            .setPatients(Arrays.asList(patient1, patient2, patient3)).setCabinetNumber(1)
            .setSalary(12345).createDoctor();
    Doctor doctor2 = new Doctor.Builder().setName("Doctor2Name").setSurname("Doctor2Surname")
            .setBirthday(calendar1).setId(2).setPhoneNumber("+1111111111")
            .setPatients(Arrays.asList(patient1, patient3)).setCabinetNumber(2)
            .setSalary(12345).createDoctor();

    Hospital hospital1 = new Hospital.Builder().setAddress("address").setNumber(1)
            .setDoctors(Arrays.asList(doctor1, doctor2)).createHospital();

    @Test
    public void testSortPatientsByNameAndSurname() {
        Doctor doctor = new Doctor.Builder().setName("Name").setSurname("Surname")
                .setBirthday(calendar1).setId(1).setPhoneNumber("+1111111111")
                .setPatients(Arrays.asList(patient2, patient1, patient3)).setCabinetNumber(1)
                .setSalary(12345).createDoctor();

        doctor.sortPatientsByNameAndSurname();

        assertEquals(patient1, doctor.getPatients().get(0));
        assertEquals(patient2, doctor.getPatients().get(1));
        assertEquals(patient3, doctor.getPatients().get(2));
    }

    @Test
    public void testSortPatientsByMedicalCardId() {
        Doctor doctor = new Doctor.Builder().setName("Name").setSurname("Surname")
                .setBirthday(calendar1).setId(1).setPhoneNumber("+1111111111")
                .setPatients(Arrays.asList(patient1, patient2, patient3)).setCabinetNumber(1)
                .setSalary(12345).createDoctor();

        doctor.sortPatientsByMedicalCardId();

        assertEquals(patient3, doctor.getPatients().get(0));
        assertEquals(patient2, doctor.getPatients().get(1));
        assertEquals(patient1, doctor.getPatients().get(2));
    }

    @Test
    public void testSortPatientsByAge() {
        Doctor doctor = new Doctor.Builder().setName("Name").setSurname("Surname")
                .setBirthday(calendar1).setId(1).setPhoneNumber("+1111111111")
                .setPatients(Arrays.asList(patient2, patient1, patient3)).setCabinetNumber(1)
                .setSalary(12345).createDoctor();

        doctor.sortPatientsByAge();

        assertEquals(patient1, doctor.getPatients().get(0));
        assertEquals(patient2, doctor.getPatients().get(1));
        assertEquals(patient3, doctor.getPatients().get(2));
    }

    @Test
    public void testGetPatientsMedicalCardId() {
        List<Integer> expected = new ArrayList<>(List.of(3, 2, 1));
        List<Integer> actual = doctor1.getPatientsMedicalCardId();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPatientsMedicalCardIdStream() {
        List<Integer> expected = new ArrayList<>(List.of(3, 2, 1));
        List<Integer> actual = doctor1.getPatientsMedicalCardIdStream();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPatientsByMedicalCardId() {
        assertEquals(patient1, doctor1.getPatientsByMedicalCardId(patient1.getMedicalCardId()));
    }

    @Test
    public void testGetPatientsByMedicalCardIdStream() {
        assertEquals(patient2, doctor1.getPatientsByMedicalCardIdStream(patient2.getMedicalCardId()));
    }

    @Test
    public void testGetPatientsById() {
        assertEquals(patient3, doctor1.getPatientsById(patient3.getId()));
    }

    @Test
    public void testGetPatientsByIdStream() {
        assertEquals(patient1, doctor1.getPatientsByIdStream(patient1.getId()));
    }

    @Test
    public void testGetPatientsBySurname() {
        List<Patient> expected = new ArrayList<>();
        expected.add(patient2);
        assertEquals(expected, doctor1.getPatientsBySurname(patient2.getSurname()));
    }

    @Test
    public void testGetPatientsBySurnameStream() {
        List<Patient> expected = new ArrayList<>();
        expected.add(patient3);
        assertEquals(expected, doctor1.getPatientsBySurnameStream(patient3.getSurname()));
    }

    @Test
    public void testGetPatientsByName() {
        List<Patient> expected = new ArrayList<>();
        expected.add(patient1);
        assertEquals(expected, doctor1.getPatientsByName(patient1.getName()));
    }

    @Test
    public void testGetPatientsByNameStream() {
        List<Patient> expected = new ArrayList<>();
        expected.add(patient2);
        assertEquals(expected, doctor1.getPatientsByNameStream(patient2.getName()));
    }

    @Test
    public void testSortDoctorsByNameAndSurname() {
        Hospital hospital = new Hospital.Builder().setAddress("address").setNumber(1)
                .setDoctors(Arrays.asList(doctor2, doctor1)).createHospital();

        hospital.sortDoctorsByNameAndSurname();

        assertEquals(doctor1, hospital.getDoctors().get(0));
        assertEquals(doctor2, hospital.getDoctors().get(1));
    }

    @Test
    public void testSortDoctorsByNumberOfPatients() {
        Hospital hospital = new Hospital.Builder().setAddress("address").setNumber(1)
                .setDoctors(Arrays.asList(doctor2, doctor1)).createHospital();

        hospital.sortDoctorsByNameAndSurname();

        assertEquals(doctor1, hospital.getDoctors().get(0));
        assertEquals(doctor2, hospital.getDoctors().get(1));
    }

    @Test
    public void testGetDoctorsById() {
        assertEquals(doctor1, hospital1.getDoctorsById(doctor1.getId()));
        assertEquals(doctor2, hospital1.getDoctorsById(doctor2.getId()));
    }

    @Test
    public void testGetDoctorsBySurname() {
        List<Doctor> expected = new ArrayList<>();
        expected.add(doctor1);
        assertEquals(expected, hospital1.getDoctorsBySurname(doctor1.getSurname()));
    }

    @Test
    public void testGetDoctorsByName() {
        List<Doctor> expected = new ArrayList<>();
        expected.add(doctor2);
        assertEquals(expected, hospital1.getDoctorsByName(doctor2.getName()));
    }
}
