import database.HospitalWrapper;
import hospital.Doctor;
import hospital.Hospital;
import hospital.Patient;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.DECEMBER, 11);

            Patient patient1 = new Patient.Builder().setName("Patientname1").setSurname("Patientsurname1")
                    .setBirthday(calendar).setId(211).setPhoneNumber("+380506241727").setDoctorId(311)
                    .setMedicalCardId(24).createPatient();
            Patient patient2 = new Patient.Builder().setName("Patientname2").setSurname("Patientsurname2")
                    .setBirthday(calendar).setId(221).setPhoneNumber("+380506241727").setDoctorId(311)
                    .setMedicalCardId(22).createPatient();
            Patient patient3 = new Patient.Builder().setName("Patientname3").setSurname("Patientsurname2")
                    .setBirthday(calendar).setId(231).setPhoneNumber("+380506241727").setDoctorId(321)
                    .setMedicalCardId(23).createPatient();

            Doctor doctor1 = new Doctor.Builder().setName("Doctorname1").setSurname("Doctorsurname1")
                    .setBirthday(calendar).setId(311).setPhoneNumber("+380506241727")
                    .setPatients(Arrays.asList(patient1, patient2)).setCabinetNumber(1).setHospitalNumber(1)
                    .setSalary(12345).createDoctor();

            Doctor doctor2 = new Doctor.Builder().setName("Doctorname2").setSurname("Doctorsurname2")
                    .setBirthday(calendar).setId(321).setPhoneNumber("+380506241727")
                    .setPatients(List.of(patient3)).setCabinetNumber(2).setHospitalNumber(1)
                    .setSalary(4321).createDoctor();

            Hospital hospital = new Hospital.Builder().setAddress("Address").setNumber(1)
                    .setDoctors(Arrays.asList(doctor1, doctor2)).createHospital();


        HospitalWrapper hospitalWrapper = new HospitalWrapper("jdbc:h2:./Hospital", "username", "password");

        hospitalWrapper.addHospital(hospital);

        for(Patient patient : hospitalWrapper.getPatients())
            System.out.println(patient);

        hospitalWrapper.erasePatient(patient1);

        for(Doctor doctor : hospitalWrapper.getDoctors())
            System.out.println(doctor);

        hospitalWrapper.eraseDoctor(doctor1);

        for(Hospital hospital1 : hospitalWrapper.getHospitals())
            System.out.println(hospital1);

        hospitalWrapper.setData("DROP ALL OBJECTS DELETE FILES");

    }

}
