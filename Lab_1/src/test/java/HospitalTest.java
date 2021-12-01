import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import hospital.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HospitalTest {
    Patient patient0 = new Patient.Builder().setName("Name0").setSurname("Surname0")
            .setBirthday(LocalDate.of(1950, 5, 5)).setId(0)
            .setPhoneNumber("+1111111111").setMedicalCardId(1).createPatient();
    Patient patient1 = new Patient.Builder().setName("Name1").setSurname("Surname1")
            .setBirthday(LocalDate.of(1960, 6, 6)).setId(1)
            .setPhoneNumber("+1111111111").setMedicalCardId(2).createPatient();
    Patient patient2 = new Patient.Builder().setName("Name2").setSurname("Surname2")
            .setBirthday(LocalDate.of(1970, 7, 7)).setId(2)
            .setPhoneNumber("+1111111111").setMedicalCardId(3).createPatient();

    Doctor doctor0 = new Doctor.Builder().setName("Name0").setSurname("Surname0")
            .setBirthday(LocalDate.of(1950, 5, 5)).setId(0)
            .setPhoneNumber("+1111111111").setPatients(Arrays.asList(patient0, patient1, patient2))
            .setCabinetNumber(0).setSalary(12345).createDoctor();
    Doctor doctor1 = new Doctor.Builder().setName("Name1").setSurname("Surname1")
            .setBirthday(LocalDate.of(1955, 5, 5)).setId(1)
            .setPhoneNumber("+1111111111").setPatients(Arrays.asList(patient0, patient2))
            .setCabinetNumber(2).setSalary(1256).createDoctor();
    Doctor doctor2 = new Doctor.Builder().setName("Name2").setSurname("Surname2")
            .setBirthday(LocalDate.of(1954, 5, 5)).setId(2)
            .setPhoneNumber("+1111111111").setPatients(Arrays.asList(patient1, patient2))
            .setCabinetNumber(4).setSalary(345).createDoctor();

    @DataProvider(name = "HumanProvider")
    public Object[][] dpHuman() {
        return new Object[][]{{"Name0", "Surname0", LocalDate.of(1950, 5, 5), 0},
                {"Name1", "Surname1", LocalDate.of(1960, 6, 6), 1},
                {"Name2", "Surname2", LocalDate.of(1970, 7, 7), 2}};
    }

    @Test(dataProvider = "HumanProvider")
    public void testHumanBuilding(String name, String surname, LocalDate date, int id) {
        Human human = new Human.Builder().setName(name).setSurname(surname).setBirthday(date).setId(id).createHuman();
        assertEquals(human.getName(), name);
        assertEquals(human.getSurname(), surname);
        assertEquals(human.getBirthday(), date);
        assertEquals(human.getId(), id);

        human.setName("name");
        assertEquals(human.getName(), "name");

        human.setSurname("surname");
        assertEquals(human.getSurname(), "surname");
    }

    @Test(dataProvider = "HumanProvider")
    public void testHumanEquals(String name, String surname, LocalDate date, int id) {
        Human human0 = new Human.Builder().setName(name).setSurname(surname).setBirthday(date).setId(id).createHuman();
        Human human1 = new Human.Builder().setName(name).setSurname(surname).setBirthday(date).setId(id).createHuman();
        Human human2 = new Human.Builder().setName(name).setSurname(surname).setBirthday(date).setId(7).createHuman();

        assertEquals(human1, human0);
        assertEquals(human0, human1);

        assertNotEquals(human2, human0);
        assertNotEquals(human0, human2);
    }

    @Test(dataProvider = "HumanProvider")
    public void testHumanHash(String name, String surname, LocalDate date, int id) {
        Human human0 = new Human.Builder().setName(name).setSurname(surname).setBirthday(date).setId(id).createHuman();
        Human human1 = new Human.Builder().setName(name).setSurname(surname).setBirthday(date).setId(id).createHuman();
        Human human2 = new Human.Builder().setName(name).setSurname(surname).setBirthday(date).setId(9).createHuman();

        assertEquals(human0.hashCode(), human1.hashCode());
        assertNotEquals(human0.hashCode(), human2.hashCode());
    }

    @Test
    public void testHumanToString() {
        Human human = new Human.Builder().setName("name").setSurname("surname")
                .setBirthday(LocalDate.of(1950, 12, 12)).setId(1).createHuman();
        String str = "{name: name, surname: surname, birthday: 1950-12-12, ID: 1}";

        assertEquals(human.toString(), str);
    }

    @DataProvider(name = "PatientProvider")
    public Object[][] dpPatient() {
        return new Object[][]{{"Name0", "Surname0", LocalDate.of(1950, 5, 5), 0, "+1111111111", 1},
                {"Name1", "Surname1", LocalDate.of(1960, 6, 6), 1, "+1111111111", 1},
                {"Name2", "Surname2", LocalDate.of(1970, 7, 7), 2, "+1111111111", 1}};
    }

    @Test(dataProvider = "PatientProvider")
    public void testPatientBuilding(String name, String surname, LocalDate date, int id, String number, int medicalCardId) {
        Patient patient = new Patient.Builder().setName(name).setSurname(surname).setBirthday(date).setId(id)
                .setPhoneNumber(number).setMedicalCardId(medicalCardId).createPatient();

        assertEquals(patient.getName(), name);
        assertEquals(patient.getSurname(), surname);
        assertEquals(patient.getBirthday(), date);
        assertEquals(patient.getId(), id);
        assertEquals(patient.getPhoneNumber(), number);
        assertEquals(patient.getMedicalCardId(), medicalCardId);

        patient.setName("name");
        assertEquals(patient.getName(), "name");

        patient.setSurname("surname");
        assertEquals(patient.getSurname(), "surname");

        patient.setPhoneNumber("+1111111112");
        assertEquals(patient.getPhoneNumber(), "+1111111112");
    }

    @DataProvider(name = "DoctorProvider")
    public Object[][] dpDoctor() {
        return new Object[][]{{"Name0", "Surname0", LocalDate.of(1950, 5, 5), 0, "+1111111111",
                Arrays.asList(patient1, patient2), 0, 4554},
                {"Name1", "Surname1", LocalDate.of(1960, 6, 6), 1, "+1111111111",
                        Arrays.asList(patient0, patient1), 1, 4654},
                {"Name2", "Surname2", LocalDate.of(1970, 7, 7), 2, "+1111111111",
                        Arrays.asList(patient2, patient0), 3, 4554}};
    }

    @Test(dataProvider = "DoctorProvider")
    public void testDoctorBuilding(String name, String surname, LocalDate date, int id, String number,
                                   List<Patient> patients, int cabinetNumber, int salary) {
        Doctor doctor = new Doctor.Builder().setName(name).setSurname(surname).setBirthday(date).setId(id)
                .setPhoneNumber(number).setPatients(patients).setCabinetNumber(cabinetNumber).setSalary(salary).createDoctor();

        assertEquals(doctor.getName(), name);
        assertEquals(doctor.getSurname(), surname);
        assertEquals(doctor.getBirthday(), date);
        assertEquals(doctor.getId(), id);
        assertEquals(doctor.getPhoneNumber(), number);
        assertEquals(doctor.getAllPatients(), patients);
        assertEquals(doctor.getCabinetNumber(), cabinetNumber);
        assertEquals(doctor.getSalary(), salary);


        doctor.setName("name");
        assertEquals(doctor.getName(), "name");

        doctor.setSurname("surname");
        assertEquals(doctor.getSurname(), "surname");

        doctor.setPhoneNumber("+1111111112");
        assertEquals(doctor.getPhoneNumber(), "+1111111112");

        doctor.setCabinetNumber(7);
        assertEquals(doctor.getCabinetNumber(), 7);

        doctor.setSalary(27536);
        assertEquals(doctor.getSalary(), 27536);
    }

    @DataProvider(name = "BuildingProvider")
    public Object[][] dpBuilding() {
        return new Object[][]{{"address0"}, {"address1"}, {"address2"}};
    }

    @Test(dataProvider = "BuildingProvider")
    public void testBuilding(String address) {
        Building building = new Building(address);

        assertEquals(building.getAddress(), address);

        building.setAddress("address");
        assertEquals(building.getAddress(), "address");
    }

    @DataProvider(name = "HospitalProvider")
    public Object[][] dpHospital() {
        return new Object[][]{{"address0", 0, Arrays.asList(doctor0, doctor2)},
                {"address1", 1, Arrays.asList(doctor1, doctor2)},
                {"address2", 2, Arrays.asList(doctor0, doctor1)}};
    }

    @Test(dataProvider = "HospitalProvider")
    public void testHospitalBuilding(String address, int number, List<Doctor> doctors) {
        Hospital hospital = new Hospital(address, number, doctors);

        assertEquals(hospital.getAddress(), address);
        assertEquals(hospital.getNumber(), number);
        assertEquals(hospital.getAllDoctors(), doctors);

        hospital.setAddress("address");
        assertEquals(hospital.getAddress(), "address");

        hospital.setNumber(5);
        assertEquals(hospital.getNumber(), 5);

        Doctor doctor = new Doctor.Builder().setName("name").setSurname("surname")
                .setBirthday(LocalDate.of(1950, 5, 5)).setId(7)
                .setPhoneNumber("+1111111111").setPatients(List.of(patient0, patient2)).setCabinetNumber(55)
                .setSalary(15441).createDoctor();

        List<Doctor> doctorList = new ArrayList<>(doctors);

        hospital.addDoctor(doctor);
        doctorList.add(doctor);
        assertEquals(hospital.getAllDoctors(), doctorList);

        hospital.eraseDoctor(doctor);
        doctorList.remove(doctor);
        assertEquals(hospital.getAllDoctors(), doctorList);

        List<Doctor> docs = Arrays.asList(doctor, doctor2);
        hospital.addDoctors(docs);
        doctorList.addAll(docs);
        assertEquals(hospital.getAllDoctors(), doctorList);
    }

}