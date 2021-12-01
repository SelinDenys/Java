package database;

import hospital.Doctor;
import hospital.Hospital;
import hospital.Patient;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HospitalWrapper extends DBManager {
    public HospitalWrapper(String url, String username, String password) {
        super(url, username, password);

        setData("CREATE TABLE Hospitals " +
                "(number INTEGER PRIMARY KEY, " +
                "address TEXT NOT NULL)");

        setData("CREATE TABLE Doctors " +
                "(id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "surname TEXT NOT NULL, " +
                "age DATE NOT NULL, " +
                "phoneNumber TEXT NOT NULL, " +
                "cabinetNumber INTEGER NOT NULL, " +
                "salary INTEGER NOT NULL, " +
                "hospitalNumber INTEGER, " +
                "FOREIGN KEY (hospitalNumber) REFERENCES Hospitals(number))");

        setData("CREATE TABLE Patients " +
                "(id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "surname TEXT NOT NULL, " +
                "age DATE NOT NULL, " +
                "phoneNumber TEXT NOT NULL, " +
                "medicalCardId INTEGER NOT NULL, " +
                "doctorId INTEGER, " +
                "FOREIGN KEY (doctorId) REFERENCES Doctors (id))");
    }

    public boolean addHospital(Hospital hospital) {
        String str = String.format("'%d', '%s'", hospital.getNumber(), hospital.getAddress());

        boolean result = setData("INSERT INTO Hospitals (number, address) " +
                "VALUES (" + str + ");");

        for (Doctor doctor : hospital.getDoctors())
            result = addDoctor(doctor);

        return result;
    }

    public boolean addDoctor(Doctor doctor) {
        String str = String.format("'%d', '%s', '%s', '%d-%d-%d', '%s', '%d', '%d', '%d'",
                doctor.getId(), doctor.getName(), doctor.getSurname(), doctor.getBirthday().get(Calendar.YEAR),
                doctor.getBirthday().get(Calendar.MONTH), doctor.getBirthday().get(Calendar.DAY_OF_MONTH),
                doctor.getPhoneNumber(), doctor.getCabinetNumber(), doctor.getSalary(), doctor.getHospitalNumber());

        boolean result = setData("INSERT INTO Doctors (id, name, surname, age, phoneNumber, " +
                "cabinetNumber, salary, hospitalNumber) " +
                "VALUES (" + str + ");");

        for (Patient patient : doctor.getPatients())
            result = addPatient(patient);

        return result;
    }

    public boolean addPatient(Patient patient) {
        String str = String.format("'%d', '%s', '%s', '%d-%d-%d', '%s', '%d', '%d'",
                patient.getId(), patient.getName(), patient.getSurname(), patient.getBirthday().get(Calendar.YEAR),
                patient.getBirthday().get(Calendar.MONTH), patient.getBirthday().get(Calendar.DAY_OF_MONTH),
                patient.getPhoneNumber(), patient.getMedicalCardId(), patient.getDoctorId());

        return setData("INSERT INTO Patients (id, name, surname, age, phoneNumber, " +
                "medicalCardId, doctorId) " +
                "VALUES (" + str + ");");
    }

    public List<Patient> getPatientsByQuery(String query) {
        List<Patient> result = new ArrayList<>();
        ResultSet set = getData(query);
        try {
            while (set.next()) {
                result.add(getPatient(set));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }

    public List<Patient> getPatients() {
        return getPatientsByQuery("SELECT * FROM patients");
    }

    public List<Patient> getPatientsByDoctor(int doctorId) {
        return getPatientsByQuery("SELECT * FROM patients WHERE doctorId = '" + doctorId + "'");
    }

    public List<Patient> getPatientsByName(String name) {
        return getPatientsByQuery("SELECT * FROM patients WHERE name = '" + name + "'");
    }

    public List<Patient> getPatientsBySurname(String surname) {
        return getPatientsByQuery("SELECT * FROM patients WHERE surname = '" + surname + "'");
    }

    public List<Integer> getPatientsMedicalCardId() {
        List<Patient> patients = getPatients();
        List<Integer> result = new ArrayList<>();
        for (Patient patient : patients)
            result.add(patient.getMedicalCardId());
        return result;
    }

    public Patient getPatientsById(int id) {
        List<Patient> result = getPatientsByQuery("SELECT * FROM patients WHERE id = '" + id + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public Patient getPatientsByMedicalCardId(int medicalCardId) {
        List<Patient> result = getPatientsByQuery("SELECT * FROM patients WHERE medicalCardId = '" + medicalCardId + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public List<Doctor> getDoctorsByQuery(String query) {
        List<Doctor> result = new ArrayList<>();
        ResultSet set = getData(query);
        try {
            while (set.next()) {
                result.add(getDoctor(set));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }

    public List<Doctor> getDoctors() {
        return getDoctorsByQuery("SELECT * FROM doctors");
    }

    public List<Doctor> getDoctorsByHospital(int hospitalNumber) {
        return getDoctorsByQuery("SELECT * FROM doctors WHERE hospitalNumber = '" + hospitalNumber + "'");
    }

    public List<Doctor> getDoctorsByName(String name) {
        return getDoctorsByQuery("SELECT * FROM doctors WHERE name = '" + name + "'");
    }

    public List<Doctor> getDoctorsBySurname(String surname) {
        return getDoctorsByQuery("SELECT * FROM doctors WHERE surname = '" + surname + "'");
    }

    public Doctor getDoctorsById(int id) {
        List<Doctor> result = getDoctorsByQuery("SELECT * FROM doctors WHERE id = '" + id + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public List<Hospital> getHospitalsByQuery(String query) {
        List<Hospital> result = new ArrayList<>();
        ResultSet set = getData(query);
        try {
            while (set.next()) {
                result.add(getHospital(set));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }

    public List<Hospital> getHospitals() {
        return getHospitalsByQuery("SELECT * FROM hospitals");
    }

    public Hospital getHospitalByNumber(int number) {
        List<Hospital> result = getHospitalsByQuery("SELECT * FROM hospitals WHERE number = '" + number + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public Hospital getHospitalByAddress(String address) {
        List<Hospital> result = getHospitalsByQuery("SELECT * FROM hospitals WHERE address = '" + address + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public void updatePatient(Patient patient) {
        setData("UPDATE patients SET name = '" + patient.getName() + "', " +
                "surname = '" + patient.getSurname() + "', " +
                "phoneNumber = '" + patient.getPhoneNumber() + "', " +
                "doctorId = '" + patient.getDoctorId() + "' " +
                "WHERE id = '" + patient.getId() + "'");
    }

    public void updateDoctor(Doctor doctor) {
        setData("UPDATE doctors SET name = '" + doctor.getName() + "', " +
                "surname = '" + doctor.getSurname() + "', " +
                "phoneNumber = '" + doctor.getPhoneNumber() + "', " +
                "hospitalNumber = '" + doctor.getHospitalNumber() + "', " +
                "cabinetNumber = '" + doctor.getCabinetNumber() + "', " +
                "salary = '" + doctor.getSalary() + "' " +
                "WHERE id = '" + doctor.getId() + "'");
    }

    public void updateHospital(Hospital hospital) {
        setData("UPDATE hospitals SET address = '" + hospital.getAddress() + "'" +
                "WHERE number = '" + hospital.getNumber() + "'");
    }

    public void erasePatient(Patient patient) {
        setData("DELETE FROM patients  WHERE id = '" + patient.getId() + "'");
    }

    public void eraseDoctor(Doctor doctor) {
        for (Patient patient : doctor.getPatients())
            setData("UPDATE patients SET doctorId = NULL " +
                    "WHERE id = '" + patient.getId() + "'");


        setData("DELETE FROM doctors  WHERE id = '" + doctor.getId() + "'");
    }

    public void eraseHospital(Hospital hospital) {
        for (Doctor doctor : hospital.getDoctors())
            setData("UPDATE doctors SET hospitalNumber = NULL " +
                    "WHERE id = '" + doctor.getId() + "'");

        setData("DELETE FROM hospitals WHERE number = '" + hospital.getNumber() + "'");
    }

    private Patient getPatient(ResultSet set) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(set.getDate("age"));

            return new Patient.Builder().setName(set.getString("name"))
                    .setSurname(set.getString("surname"))
                    .setId(set.getInt("id"))
                    .setPhoneNumber(set.getString("phoneNumber"))
                    .setMedicalCardId(set.getInt("medicalCardId"))
                    .setDoctorId(set.getInt("doctorId"))
                    .setBirthday(calendar)
                    .createPatient();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    private Doctor getDoctor(ResultSet set) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(set.getDate("age"));

            return new Doctor.Builder().setName(set.getString("name"))
                    .setSurname(set.getString("surname"))
                    .setId(set.getInt("id"))
                    .setPhoneNumber(set.getString("phoneNumber"))
                    .setCabinetNumber(set.getInt("cabinetNumber"))
                    .setHospitalNumber(set.getInt("hospitalNumber"))
                    .setSalary(set.getInt("salary"))
                    .setBirthday(calendar)
                    .setPatients(getPatientsByDoctor(set.getInt("id")))
                    .createDoctor();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private Hospital getHospital(ResultSet set) {
        try {
            return new Hospital.Builder().setAddress(set.getString("address"))
                    .setNumber(set.getInt("number"))
                    .setDoctors(getDoctorsByHospital(set.getInt("number")))
                    .createHospital();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

}
