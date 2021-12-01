import hospital.Doctor;
import hospital.Hospital;
import hospital.Patient;

import java.util.Arrays;
import java.util.Calendar;

public class Main {
    public static void main(String[] args)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.DECEMBER, 11);

        Patient patient1 = new Patient.Builder().setName("Patient1Name").setSurname("Patient1Surname")
                .setBirthday(calendar).setId(211).setPhoneNumber("+1111111111")
                .setMedicalCardId(24).createPatient();
        Patient patient2 = new Patient.Builder().setName("Patient2Name").setSurname("Patient2Surname")
                .setBirthday(calendar).setId(221).setPhoneNumber("+1111111111")
                .setMedicalCardId(22).createPatient();
        Patient patient3 = new Patient.Builder().setName("Patient3Name").setSurname("Patient3Surname")
                .setBirthday(calendar).setId(231).setPhoneNumber("+1111111111")
                .setMedicalCardId(23).createPatient();

        Doctor doctor1 = new Doctor.Builder().setName("Doctor1Name").setSurname("Doctor1Surname")
                .setBirthday(calendar).setId(311).setPhoneNumber("+1111111111")
                .setPatients(Arrays.asList(patient2, patient1)).setCabinetNumber(1)
                .setSalary(12345).createDoctor();

        Doctor doctor2 = new Doctor.Builder().setName("Doctor2Name").setSurname("Doctor2Surname")
                .setBirthday(calendar).setId(321).setPhoneNumber("+1111111111")
                .setPatients(Arrays.asList(patient1, patient2, patient3)).setCabinetNumber(2)
                .setSalary(4321).createDoctor();

        Hospital hospital = new Hospital.Builder().setAddress("address").setNumber(1)
                .setDoctors(Arrays.asList(doctor1, doctor2)).createHospital();

        System.out.println(doctor1);

        doctor1.sortPatientsByNameAndSurname();
        System.out.println(doctor1);

        doctor1.sortPatientsByMedicalCardId();
        System.out.println(doctor1);

        System.out.println(hospital);

        hospital.sortDoctorsByNumberOfPatients();
        System.out.println(hospital);
    }
}
