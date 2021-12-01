package hospital;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = Doctor.Builder.class)
public class Doctor extends Human {
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
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
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
     * Set doctor cabinet number
     *
     * @param cabinetNumber cabinet number
     */
    public void setCabinetNumber(int cabinetNumber) {
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
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * @return doctor salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Build instance of Doctor
     */
    @JsonPOJOBuilder(buildMethodName = "createDoctor", withPrefix = "set")
    public static class Builder extends Human.Builder {
        private String phoneNumber;
        private final List<Patient> patients = new ArrayList<>();
        private int cabinetNumber;
        private int salary;

        /**
         * Check number and set it as patient phone number
         *
         * @param phoneNumber phone number
         * @return Builder instance
         */
        public Builder setPhoneNumber(String phoneNumber) {
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
         */
        public Builder setCabinetNumber(int cabinetNumber) {
            this.cabinetNumber = cabinetNumber;
            return this;
        }

        /**
         * Check number and set it as doctor salary
         *
         * @param salary salary
         * @return Builder instance
         */
        public Builder setSalary(int salary) throws IllegalArgumentException {
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
        public Builder setBirthday(Calendar birthday) {
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
