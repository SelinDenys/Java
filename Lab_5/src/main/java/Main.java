import database.DBManager;
import hospital.Doctor;
import hospital.Hospital;
import hospital.Patient;

import java.sql.ResultSet;
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

        DBManager dbManager = new DBManager("jdbc:h2:./Hospital", "username", "password");

        dbManager.setData("CREATE TABLE Hospitals " +
                "(number INTEGER PRIMARY KEY, " +
                "address TEXT NOT NULL)");

        dbManager.setData("CREATE TABLE Doctors " +
                "(id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "surname TEXT NOT NULL, " +
                "age DATE NOT NULL, " +
                "phoneNumber TEXT NOT NULL, " +
                "cabinetNumber INTEGER NOT NULL, " +
                "salary INTEGER NOT NULL, " +
                "hospitalNumber INTEGER, " +
                "FOREIGN KEY (hospitalNumber) REFERENCES Hospitals(number))");

        dbManager.setData("CREATE TABLE Patients " +
                "(id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "surname TEXT NOT NULL, " +
                "age DATE NOT NULL, " +
                "phoneNumber TEXT NOT NULL, " +
                "medicalCardId INTEGER NOT NULL, " +
                "doctorId INTEGER, " +
                "FOREIGN KEY (doctorId) REFERENCES Doctors (id))");

        dbManager.setData("INSERT INTO Hospitals (number, address) " +
                "VALUES (" + getHospitalInfo(hospital) + ");");

        for (Doctor doctor : hospital.getDoctors()) {
            dbManager.setData("INSERT INTO Doctors (id, name, surname, age, phoneNumber, " +
                    "cabinetNumber, salary, hospitalNumber) " +
                    "VALUES (" + getDoctorInfo(doctor) + ");");
            for (Patient patient : doctor.getPatients()) {
                dbManager.setData("INSERT INTO Patients (id, name, surname, age, phoneNumber, " +
                        "medicalCardId, doctorId) " +
                        "VALUES (" + getPatientInfo(patient) + ");");
            }
        }

        ResultSet set = dbManager.getData("SELECT age FROM Doctors WHERE name = 'Doctorname1'");
        try {
            if (set.next()) {
                System.out.println(set.getDate("age"));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        //dbManager.setData("DROP ALL OBJECTS DELETE FILES");

        dbManager.close();

    }

    public static String getPatientInfo(Patient patient) {
        return String.format("'%d', '%s', '%s', '%d-%d-%d', '%s', '%d', '%d'",
                patient.getId(), patient.getName(), patient.getSurname(), patient.getBirthday().get(Calendar.YEAR),
                patient.getBirthday().get(Calendar.MONTH), patient.getBirthday().get(Calendar.DAY_OF_MONTH),
                patient.getPhoneNumber(), patient.getMedicalCardId(), patient.getDoctorId());
    }

    public static String getDoctorInfo(Doctor doctor) {
        return String.format("'%d', '%s', '%s', '%d-%d-%d', '%s', '%d', '%d', '%d'",
                doctor.getId(), doctor.getName(), doctor.getSurname(), doctor.getBirthday().get(Calendar.YEAR),
                doctor.getBirthday().get(Calendar.MONTH), doctor.getBirthday().get(Calendar.DAY_OF_MONTH),
                doctor.getPhoneNumber(), doctor.getCabinetNumber(), doctor.getSalary(), doctor.getHospitalNumber());
    }

    public static String getHospitalInfo(Hospital hospital) {
        return String.format("'%d', '%s'", hospital.getNumber(), hospital.getAddress());
    }
}
