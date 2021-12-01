import Serialize.JsonSerializer;
import Serialize.TxtSerializer;
import Serialize.XmlSerializer;
import hospital.Doctor;
import hospital.Hospital;
import hospital.Human;
import hospital.Patient;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.Calendar;

public class SerializeTest {
    Calendar calendar = Calendar.getInstance();

    {
        calendar.set(1999, Calendar.DECEMBER, 11);
    }

    Human human = new Human.Builder().setName("HumanName").setSurname("HumanSurname")
            .setBirthday(calendar).setId(111).createHuman();

    Patient patient1 = new Patient.Builder().setName("Patient1Name").setSurname("Patient1Surname")
            .setBirthday(calendar).setId(211).setPhoneNumber("+1111111111")
            .setMedicalCardId(21).createPatient();
    Patient patient2 = new Patient.Builder().setName("Patient2Name").setSurname("Patient2Surname")
            .setBirthday(calendar).setId(221).setPhoneNumber("+1111111111")
            .setMedicalCardId(22).createPatient();
    Patient patient3 = new Patient.Builder().setName("Patient3Name").setSurname("Patient3Surname")
            .setBirthday(calendar).setId(231).setPhoneNumber("+1111111111")
            .setMedicalCardId(23).createPatient();

    Doctor doctor1 = new Doctor.Builder().setName("Doctor1Name").setSurname("Doctor1Surname")
            .setBirthday(calendar).setId(311).setPhoneNumber("+1111111111")
            .setPatients(Arrays.asList(patient1, patient2)).setCabinetNumber(1)
            .setSalary(12345).createDoctor();

    Doctor doctor2 = new Doctor.Builder().setName("Doctor2Name").setSurname("Doctor2Surname")
            .setBirthday(calendar).setId(321).setPhoneNumber("+1111111111")
            .setPatients(Arrays.asList(patient1, patient3)).setCabinetNumber(2)
            .setSalary(4321).createDoctor();

    Hospital hospital = new Hospital.Builder().setAddress("address").setNumber(1)
            .setDoctors(Arrays.asList(doctor1, doctor2)).createHospital();

    @Test
    public void testJsonSerialization() {
        JsonSerializer json = new JsonSerializer();

        json.serialize(human, "Human.json");
        Human deserializedHuman = json.deserialize(Human.class, "Human.json");
        assertEquals(human, deserializedHuman);

        json.serialize(patient1, "Patient.json");
        Patient deserializedPatient = json.deserialize(Patient.class, "Patient.json");
        assertEquals(patient1, deserializedPatient);

        json.serialize(doctor1, "Doctor.json");
        Doctor deserializedDoctor = json.deserialize(Doctor.class, "Doctor.json");
        assertEquals(doctor1, deserializedDoctor);

        json.serialize(hospital, "Hospital.json");
        Hospital deserializedHospital = json.deserialize(Hospital.class, "Hospital.json");
        assertEquals(hospital, deserializedHospital);
    }

    @Test
    public void testXmlSerialization() {
        XmlSerializer xml = new XmlSerializer();

        xml.serialize(human, "Human.xml");
        Human deserializedHuman = xml.deserialize(Human.class, "Human.xml");
        assertEquals(human, deserializedHuman);

        xml.serialize(patient1, "Patient.xml");
        Patient deserializedPatient = xml.deserialize(Patient.class, "Patient.xml");
        assertEquals(patient1, deserializedPatient);

        xml.serialize(doctor1, "Doctor.xml");
        Doctor deserializedDoctor = xml.deserialize(Doctor.class, "Doctor.xml");
        assertEquals(doctor1, deserializedDoctor);

        xml.serialize(hospital, "Hospital.xml");
        Hospital deserializedHospital = xml.deserialize(Hospital.class, "Hospital.xml");
        assertEquals(hospital, deserializedHospital);
    }

    @Test
    public void testTxtSerialization() {
        TxtSerializer txt = new TxtSerializer();

        txt.serialize(human, "Human.txt");
        Human deserializedHuman = txt.deserialize(Human.class, "Human.txt");
        assertEquals(human, deserializedHuman);

        txt.serialize(patient1, "Patient.txt");
        Patient deserializedPatient = txt.deserialize(Patient.class, "Patient.txt");
        assertEquals(patient1, deserializedPatient);

        txt.serialize(doctor1, "Doctor.txt");
        Doctor deserializedDoctor = txt.deserialize(Doctor.class, "Doctor.txt");
        assertEquals(doctor1, deserializedDoctor);

        txt.serialize(hospital, "Hospital.txt");
        Hospital deserializedHospital = txt.deserialize(Hospital.class, "Hospital.txt");
        assertEquals(hospital, deserializedHospital);
    }
}
