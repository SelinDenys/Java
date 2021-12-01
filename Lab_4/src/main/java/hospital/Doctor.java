package hospital;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.*;

public class Doctor extends Human implements Comparable<Doctor> {
    private String phoneNumber;
    private final List<Patient> patients = new ArrayList<>();
    private int cabinetNumber;
    private int salary;

    public Doctor(Builder builder) {
        super(builder);
        phoneNumber = builder.phoneNumber;
        patients.addAll(builder.patients);
        cabinetNumber = builder.cabinetNumber;
        salary = builder.salary;
    }

    /**
     * Set doctor phone number
     *
     * @param phoneNumber phone number
     * @throws IllegalArgumentException if number is not a phone number
     */
    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (!phoneNumber.matches("^[+][1-9][0-9]{9,11}"))
            throw new IllegalArgumentException("Wrong number");
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Erase patient from doctor's patient list
     *
     * @param patient patient whom need to erase
     */
    public void erasePatient(Patient patient) {
        patients.remove(patient);
    }

    /**
     * Add patient to doctor's patient list
     *
     * @param patient patient whom need to add
     */
    public void setPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Add patients to doctor's patient list
     *
     * @param patients patients whoms need to add
     */
    public void setPatients(List<Patient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Return all doctor's patient
     *
     * @return list of doctor's patient
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Return doctor's patient with specified name
     *
     * @param name patients name
     * @return list of doctor's patient with specified name
     */
    public List<Patient> getPatientsByNameStream(String name) {
        return patients.stream().filter(patient -> Objects.equals(patient.getName(), name)).toList();
    }

    public List<Patient> getPatientsByName(String name) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                result.add(patient);
            }
        }
        return result;
    }

    /**
     * Return doctor's patient with specified surname
     *
     * @param surname patients surname
     * @return list of doctor's patient with specified surname
     */
    public List<Patient> getPatientsBySurnameStream(String surname) {
        return patients.stream().filter(patient -> Objects.equals(patient.getSurname(), surname)).toList();
    }

    public List<Patient> getPatientsBySurname(String surname) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getSurname().equals(surname)) {
                result.add(patient);
            }
        }
        return result;
    }

    /**
     * Return doctor's patient with specified id
     *
     * @param id patient id
     * @return patient with specified id
     */
    public Patient getPatientsByIdStream(int id) {
        return patients.stream().filter(patient -> Objects.equals(patient.getId(), id)).findFirst().get();
    }

    public Patient getPatientsById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Return doctor's patient with specified medical card id
     *
     * @param medicalCardId patient medical card id
     * @return patient with specified medical card id
     */
    public Patient getPatientsByMedicalCardIdStream(int medicalCardId) {
        return patients.stream().filter(patient -> Objects.equals(patient.getMedicalCardId(), medicalCardId))
                .findFirst().get();
    }

    public Patient getPatientsByMedicalCardId(int medicalCardId) {
        for (Patient patient : patients) {
            if (patient.getMedicalCardId() == medicalCardId) {
                return patient;
            }
        }
        return null;
    }

    public List<Integer> getPatientsMedicalCardId() {
        List<Integer> result = new ArrayList<>();
        for (Patient patient : patients) {
            result.add(patient.getMedicalCardId());
        }
        return result;
    }

    public List<Integer> getPatientsMedicalCardIdStream() {
        return patients.stream().map(Patient::getMedicalCardId).toList();
    }

    public void sortPatientsByNameAndSurname() {
        Collections.sort(patients);
    }

    public void sortPatientsByMedicalCardId() {
        patients.sort(new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                if (o1.getMedicalCardId() < o2.getMedicalCardId())
                    return -1;
                else if (o1.getMedicalCardId() == o2.getMedicalCardId())
                    return 0;
                return 1;
            }
        });
    }

    public void sortPatientsByAge() {
        patients.sort(Comparator.comparing(Patient::getBirthday));
    }

    /**
     * Set doctor cabinet number
     *
     * @param cabinetNumber cabinet number
     * @throws IllegalArgumentException if cabinet number less than zero
     */
    public void setCabinetNumber(int cabinetNumber) throws IllegalArgumentException {
        if (cabinetNumber < 0)
            throw new IllegalArgumentException("Wrong cabinet number");
        this.cabinetNumber = cabinetNumber;
    }

    /**
     * @return cabinet number
     */
    public int getCabinetNumber() {
        return cabinetNumber;
    }

    /**
     * Set doctor salary
     *
     * @param salary salary
     * @throws IllegalArgumentException if salary less than zero
     */
    public void setSalary(int salary) throws IllegalArgumentException {
        if (salary < 0)
            throw new IllegalArgumentException("Wrong salary");
        this.salary = salary;
    }

    /**
     * @return doctor salary
     */
    public int getSalary() {
        return salary;
    }

    @Override
    public int compareTo(Doctor o) {
        return (this.name + this.surname).compareTo(o.name + o.surname);
    }

    /**
     * Build instance of Doctor
     */
    public static class Builder extends Human.Builder {
        @Size(min = 11, max = 13, message = "Wrong phone number")
        private String phoneNumber;
        private final List<Patient> patients = new ArrayList<>();
        @Positive(message = "Cabinet number must be greater than zero")
        private int cabinetNumber;
        @Positive(message = "Salary must be greater than zero")
        private int salary;

        /**
         * Check number and set it as patient phone number
         *
         * @param phoneNumber phone number
         * @return Builder instance
         * @throws IllegalArgumentException if number is not a phone number
         */
        public Builder setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
            if (!phoneNumber.matches("^[+][1-9][0-9]{9,11}"))
                throw new IllegalArgumentException("Wrong number");
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Set list of doctor's patient
         *
         * @param patients list of patients
         * @return Builder instance
         */
        public Builder setPatients(List<Patient> patients) {
            this.patients.addAll(patients);
            return this;
        }

        /**
         * Check number and set it as doctor cabinet number
         *
         * @param cabinetNumber cabinet number
         * @return Builder instance
         * @throws IllegalArgumentException if cabinet number less than zero
         */
        public Builder setCabinetNumber(int cabinetNumber) throws IllegalArgumentException {
            if (cabinetNumber < 0)
                throw new IllegalArgumentException("Wrong cabinet number");
            this.cabinetNumber = cabinetNumber;
            return this;
        }

        /**
         * Check number and set it as doctor salary
         *
         * @param salary salary
         * @return Builder instance
         * @throws IllegalArgumentException if salary less than zero
         */
        public Builder setSalary(int salary) throws IllegalArgumentException {
            if (salary < 0)
                throw new IllegalArgumentException("Wrong salary");
            this.salary = salary;
            return this;
        }

        @Override
        public Builder setName(String name) {
            return (Builder) super.setName(name);
        }

        @Override
        public Builder setSurname(String surname) {
            return (Builder) super.setSurname(surname);
        }

        @Override
        public Builder setBirthday(Calendar birthday) throws IllegalArgumentException {
            return (Builder) super.setBirthday(birthday);
        }

        @Override
        public Builder setId(int id) throws IllegalArgumentException {
            return (Builder) super.setId(id);
        }

        /**
         * Create new Doctor object
         *
         * @return Doctor instance
         */
        public Doctor createDoctor() {
            return new Doctor(this);
        }
    }

    /**
     * Generate hash code for Doctor
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(cabinetNumber + ((Human) this).hashCode());
    }

    /**
     * Generate string from Doctor object
     *
     * @return string representation of Doctor
     */
    @Override
    public String toString() {
        String human = super.toString();

        return human.substring(0, human.lastIndexOf('}')) + ",\"phoneNumber\":\"" + phoneNumber + "\",\"patients\":" + patients + ",\"cabinetNumber\":\""
                + cabinetNumber + "\",\"salary\":\"" + salary + "\"}";
    }

    /**
     * Compare doctors objects
     *
     * @param obj object to compare
     * @return are two objects equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        if (this == obj)
            return true;
        return super.equals(obj) && Objects.equals(phoneNumber, ((Doctor) obj).phoneNumber)
                && Objects.equals(cabinetNumber, ((Doctor) obj).cabinetNumber)
                && Objects.equals(salary, ((Doctor) obj).salary)
                && Objects.equals(patients, ((Doctor) obj).patients);
    }
}
