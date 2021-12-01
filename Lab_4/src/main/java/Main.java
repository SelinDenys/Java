import hospital.Patient;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 2, 5);
        try {
            Patient patient = new Patient.Builder().setName("Name").setSurname("Surname").setId(5).setBirthday(calendar)
                    .setPhoneNumber("+380501111111").setMedicalCardId(2).createPatient();
            System.out.println(patient);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }


    }
}
