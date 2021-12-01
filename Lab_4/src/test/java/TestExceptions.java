import hospital.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;

public class TestExceptions {
    Calendar calendar = Calendar.getInstance();

    {
        calendar.set(1987, Calendar.DECEMBER, 24);
    }

    Calendar calendar1 = Calendar.getInstance();

    {
        calendar1.set(1234, Calendar.DECEMBER, 24);
    }

    Calendar calendar2 = Calendar.getInstance();

    {
        calendar2.set(2022, Calendar.DECEMBER, 24);
    }

    Calendar calendar3 = Calendar.getInstance();

    {
        calendar3.set(9876, Calendar.DECEMBER, 24);
    }

    Human human1 = new Human.Builder().setName("Name1h").setSurname("Surname1h")
            .setBirthday(calendar).setId(1).createHuman();

    Patient patient1 = new Patient.Builder().setName("Name1p").setSurname("Surname1p").setBirthday(calendar)
            .setId(2).setPhoneNumber("+1111111111").setMedicalCardId(1).createPatient();

    Doctor doctor1 = new Doctor.Builder().setName("Name1d").setSurname("Surname1d").setBirthday(calendar)
            .setId(3).setPhoneNumber("+1111111112").setPatients(List.of(patient1)).setSalary(123456)
            .setCabinetNumber(2).createDoctor();

    Building building1 = new Building("Address");

    Hospital hospital1 = new Hospital.Builder().setAddress("Address1").setNumber(1)
            .setDoctors(List.of(doctor1)).createHospital();

    @DataProvider(name = "NameAndSurnameProvider")
    public Object[][] dpNameAndSurname() {
        return new Object[][]{{""}, {"name"}, {"1Surname"}, {"Na6*&@#$%"}};
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NameAndSurnameProvider")
    public void testHumanBuilderNameExceptions(String name) {
        Human human = new Human.Builder().setName(name).createHuman();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NameAndSurnameProvider")
    public void testHumanBuilderSurnameExceptions(String surname) {
        Human human = new Human.Builder().setSurname(surname).createHuman();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NameAndSurnameProvider")
    public void testHumanSetNameExceptions(String name) {
        human1.setName(name);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NameAndSurnameProvider")
    public void testHumanSetSurnameExceptions(String surname) {
        human1.setName(surname);
    }

    @DataProvider(name = "NumberProvider")
    public Object[][] dpId() {
        return new Object[][]{{-5}, {-100}, {-415154}};
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testHumanBuilderIdExceptions(int id) {
        Human human = new Human.Builder().setId(id).createHuman();
    }


    @DataProvider(name = "CalendarProvider")
    public Object[][] dpCalendar() {
        return new Object[][]{{calendar1}, {calendar2}, {calendar3}};
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "CalendarProvider")
    public void testHumanBuilderBirthdayException(Calendar cal) {
        Human human = new Human.Builder().setBirthday(cal).createHuman();
    }

    @DataProvider(name = "PhoneNumberProvider")
    public Object[][] dpPhoneNumber() {
        return new Object[][]{{"abcdefg"}, {"000000000"}, {"+150ad117D7"}, {"+123456"}, {"+1234567890101112"}};
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "PhoneNumberProvider")
    public void testPatientBuilderPhoneNumberException(String phoneNumber) {
        Patient patient = new Patient.Builder().setPhoneNumber(phoneNumber).createPatient();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "PhoneNumberProvider")
    public void testPatientSetPhoneNumberException(String phoneNumber) {
        patient1.setPhoneNumber(phoneNumber);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testPatientBuilderMedicalCardIdException(int medicalCardId) {
        Patient patient = new Patient.Builder().setMedicalCardId(medicalCardId).createPatient();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "PhoneNumberProvider")
    public void testDoctorBuilderPhoneNumberException(String phoneNumber) {
        Doctor doctor = new Doctor.Builder().setPhoneNumber(phoneNumber).createDoctor();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testDoctorBuilderCabinetNumberException(int cabinetNumber) {
        Doctor doctor = new Doctor.Builder().setCabinetNumber(cabinetNumber).createDoctor();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testDoctorBuilderSalaryException(int salary) {
        Doctor doctor = new Doctor.Builder().setSalary(salary).createDoctor();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "PhoneNumberProvider")
    public void testDoctorSetPhoneNumberException(String phoneNumber) {
        doctor1.setPhoneNumber(phoneNumber);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testDoctorSetCabinetNumberException(int cabinetNumber) {
        doctor1.setCabinetNumber(cabinetNumber);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testDoctorSetSalaryException(int salary) {
        doctor1.setSalary(salary);
    }

    @DataProvider(name = "AddressProvider")
    public Object[][] dpAddressProvider() {
        return new Object[][]{{"address"}, {"T%^&"}, {"1address"}, {""}, {"123456"}};
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "AddressProvider")
    public void testBuildingConstructorException(String address) {
        Building building = new Building(address);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "AddressProvider")
    public void testBuildingSetAddressException(String address) {
        building1.setAddress(address);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testHospitalBuilderNumberException(int number) {
        Hospital hospital = new Hospital.Builder().setNumber(number).createHospital();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "NumberProvider")
    public void testHospitalSetNumberException(int number) {
        hospital1.setNumber(number);
    }
}
