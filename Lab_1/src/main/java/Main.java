import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import hospital.*;

public class Main {
    public static void main(String[] args) {
        Human human1 = new Human.Builder().setName("HumanName1").setSurname("HumanSurname1").setBirthday(LocalDate.now())
                .setId(654789).createHuman();
        Human human2 = new Human.Builder().setName("HumanName2").setSurname("HumanSurname2").setBirthday(LocalDate.now())
                .setId(65444789).createHuman();

        System.out.println(human1.equals(human2));

        System.out.println(human1.getName());
        System.out.println(human1.getSurname());
        System.out.println(human1);

        Patient patient1 = new Patient.Builder().setName("PatientName1").setSurname("PatientSurname1")
                .setBirthday(LocalDate.of(1999, 12, 5)).setId(66)
                .setPhoneNumber("+1111111111").setMedicalCardId(951).createPatient();
        Patient patient2 = new Patient.Builder().setName("PatientName2").setSurname("PatientSurname2")
                .setBirthday(LocalDate.of(1979, 12, 5)).setId(96)
                .setPhoneNumber("+1111111111").setMedicalCardId(951).createPatient();
        Patient patient3 = new Patient.Builder().setName("PatientName3").setSurname("PatientSurname3")
                .setBirthday(LocalDate.of(1879, 12, 5)).setId(90)
                .setPhoneNumber("+1111111111").setMedicalCardId(951).createPatient();

        System.out.println(patient1.getName());
        System.out.println(patient1);

        List<Patient> patientList = Arrays.asList(patient1, patient2);

        List<Patient> patientList1 = List.of(patient3);

        Doctor doctor1 = new Doctor.Builder().setName("DoctorName1").setSurname("DoctorSurname1")
                .setBirthday(LocalDate.of(1955, 6, 9))
                .setId(45445).setCabinetNumber(66).setSalary(5453).setPhoneNumber("+1111111111")
                .setPatients(patientList).createDoctor();

        Doctor doctor2 = new Doctor.Builder().setName("DoctorName2").setSurname("DoctorSurname2")
                .setBirthday(LocalDate.of(1985, 6, 9))
                .setId(4445).setCabinetNumber(46).setSalary(553).setPhoneNumber("+1111111111")
                .setPatients(patientList1).createDoctor();

        System.out.println(doctor1);

        List<Doctor> doctorList = Arrays.asList(doctor1, doctor2);

        Hospital hospital = new Hospital("Address", 1, doctorList);

        System.out.println(hospital);
    }
}